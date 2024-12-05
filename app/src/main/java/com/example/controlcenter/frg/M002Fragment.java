package com.example.controlcenter.frg;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

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

    }
}
