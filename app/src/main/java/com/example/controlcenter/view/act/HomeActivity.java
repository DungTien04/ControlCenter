package com.example.controlcenter.view.act;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.example.controlcenter.App;
import com.example.controlcenter.databinding.ActivityMainBinding;
import com.example.controlcenter.model.Apps;
import com.example.controlcenter.view.frg.M002Fragment;
import com.example.controlcenter.viewMd.M001MD;
import java.util.ArrayList;
import java.util.List;
public class HomeActivity extends BaseAct<ActivityMainBinding, M001MD> {
    private final List<Apps> appList = new ArrayList<>();

    @Override
    protected void initViews() {
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo appInfo : apps) {
            String appName = appInfo.loadLabel(packageManager).toString();
            Drawable appIcon = appInfo.loadIcon(packageManager);
            String packageName = appInfo.packageName;

            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                Apps app = new Apps(appName, appIcon, packageName);
                appList.add(app);
            }
            else if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                if (!isUnwantedSystemApp(appName)) {
                    Apps app = new Apps(appName, appIcon, packageName);
                    appList.add(app);
                }
            }
        }
        App.getInstance().getStorage().listApps = appList;
        new Handler().postDelayed(this::GotoMainScreen, 2000);
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