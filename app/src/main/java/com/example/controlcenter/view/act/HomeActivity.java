package com.example.controlcenter.view.act;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Base64;

import com.example.controlcenter.App;
import com.example.controlcenter.databinding.ActivityMainBinding;
import com.example.controlcenter.model.Apps;
import com.example.controlcenter.view.frg.M002Fragment;
import com.example.controlcenter.viewMd.M001MD;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HomeActivity extends BaseAct<ActivityMainBinding, M001MD> {
    private final List<Apps> appList = new ArrayList<>();
    private List<Apps> list;

    @Override
    protected void initViews() {
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo appInfo : apps) {
            String appName = appInfo.loadLabel(packageManager).toString();
            Drawable appIcon = appInfo.loadIcon(packageManager);
            Bitmap bitmap = drawableToBitmap(appIcon);
            String encodeString = encodeToBase64(bitmap);

            String packageName = appInfo.packageName;
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                Apps app = new Apps(appName, encodeString, packageName);
                appList.add(app);
            } else if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                if (!isUnwantedSystemApp(appName)) {
                    Apps app = new Apps(appName, encodeString, packageName);
                    appList.add(app);
                }
            }
        }
        HashSet<Apps> uniqueApp = new HashSet<>(appList);
        list = new ArrayList<>(uniqueApp);

        App.getInstance().getStorage().listApps = list;
        new Handler().postDelayed(this::GotoMainScreen, 2000);
    }
    public void insertApp() {
        for (Apps apps1 : list) {
            App.getInstance().getControlDB().getDao().insertApps(apps1);
        }
    }

    private boolean isUnwantedSystemApp(String packageName) {
        String[] unwantedApps = {
                "com", "Support", "Filled", "System"};

        for (String unwanted : unwantedApps) {
            if (packageName.contains(unwanted)) {
                return true;
            }
        }
        return false;
    }

    public String encodeToBase64(Bitmap image) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public void GotoMainScreen() {
        showFragment(M002Fragment.TAG, null, false);
    }

    @Override
    protected Class<M001MD> getClassVM() {
        return M001MD.class;
    }

    @Override
    protected ActivityMainBinding initViewBinnding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }
}