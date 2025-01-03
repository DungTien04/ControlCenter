package com.example.controlcenter.viewMd;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import androidx.lifecycle.MutableLiveData;

import com.example.controlcenter.App;
import com.example.controlcenter.model.Apps;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class M001MD extends CommonMD{
    private final MutableLiveData<List<Apps>> appListLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Apps>> getAppListLiveData() {
        return appListLiveData;
    }

    public void loadApps(PackageManager packageManager) {
        new Thread(() -> {
            List<ApplicationInfo> apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
            List<Apps> appList = new ArrayList<>();

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
                    Intent launchIntent = packageManager.getLaunchIntentForPackage(packageName);
                    if (launchIntent != null) {
                        Apps app = new Apps(appName, encodeString, packageName);
                        appList.add(app);
                    }
                }
            }

            HashSet<Apps> uniqueApp = new HashSet<>(appList);
            List<Apps> uniqueAppList = new ArrayList<>(uniqueApp);
            insertApp(uniqueAppList);
            appListLiveData.postValue(uniqueAppList); // Cập nhật LiveData
        }).start();
    }
    public void insertApp(List<Apps> uniqueAppList) {
        for (Apps apps1 : uniqueAppList) {
            if(App.getInstance().getControlDB().getDao().check(apps1.getAppName())<=0)
                App.getInstance().getControlDB().getDao().insertApps(apps1);
        }
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

}
