package com.example.controlcenter.view.act;

import android.content.pm.PackageManager;
import android.os.Handler;

import com.example.controlcenter.App;
import com.example.controlcenter.databinding.ActivityMainBinding;
import com.example.controlcenter.view.frg.SettingMainFragment;
import com.example.controlcenter.viewMd.M001MD;
public class HomeActivity extends BaseAct<ActivityMainBinding, M001MD> {
    @Override
    protected void initViews() {
        PackageManager packageManager = getPackageManager();
        viewModel.loadApps(packageManager);
        viewModel.getAppListLiveData().observe(this, apps -> {
            App.getInstance().getStorage().listApps = viewModel.getAppListLiveData().getValue();
            new Handler().postDelayed(this::GotoMainScreen, 1000);
        });

    }
    public void GotoMainScreen() {
        showFragment(SettingMainFragment.TAG, null, false);
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