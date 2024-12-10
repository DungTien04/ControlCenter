package com.example.controlcenter.frg;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.controlcenter.R;
import com.example.controlcenter.databinding.Frg002MainBinding;
import com.example.controlcenter.viewMd.CommonMD;

public class M002Fragment extends BaseFragment<Frg002MainBinding, CommonMD>{
    public  static final String TAG =M002Fragment.class.getName();
    @Override
    protected Class<CommonMD> getClassVM() {
        return CommonMD.class;
    }

    @Override
    protected Frg002MainBinding initViewBingding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return Frg002MainBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initViews() {
        binding.ivList.setOnClickListener(v -> openMenu());

    }

    private void openMenu() {
            binding.drawer.openDrawer(GravityCompat.START);
    }

}
