package com.example.controlcenter.frg;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.controlcenter.databinding.FrgCusControlsBinding;
import com.example.controlcenter.viewMd.M002MD;

public class FragmentCusControl  extends BaseFragment<FrgCusControlsBinding,M002MD>  {
    private static final String TAG = "FragmentCusControl";
    @Override
    protected Class<M002MD> getClassVM() {
        return M002MD.class;
    }

    @Override
    protected FrgCusControlsBinding initViewBingding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FrgCusControlsBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initViews() {

    }
}
