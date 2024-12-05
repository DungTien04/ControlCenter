package com.example.controlcenter.act;

import android.os.Handler;

import com.example.controlcenter.databinding.ActivityMainBinding;
import com.example.controlcenter.frg.M002Fragment;
import com.example.controlcenter.viewMd.M001MD;

public class HomeActivity extends BaseAct<ActivityMainBinding, M001MD>  {

    @Override
    protected void initViews() {
        new Handler().postDelayed(this::GotoMainScreen,2000);
    }
    public void GotoMainScreen(){
        showFragment(M002Fragment.TAG,null,false);
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