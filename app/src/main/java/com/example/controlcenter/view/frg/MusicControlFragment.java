package com.example.controlcenter.view.frg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.controlcenter.databinding.FrgMusicControlBinding;
import com.example.controlcenter.viewMd.M001MD;

public class MusicControlFragment extends BaseFragment<FrgMusicControlBinding, M001MD>{
    public static final String TAG =MusicControlFragment.class.getName() ;
    @Override
    protected Class<M001MD> getClassVM() {
        return M001MD.class;
    }

    @Override
    protected FrgMusicControlBinding initViewBingding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FrgMusicControlBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initViews() {
        binding.ivBack.setOnClickListener(v ->  back());
        binding.trMusic1.setOnClickListener(v -> chooseMusicPlayer());
    }

    private void chooseMusicPlayer() {
        callBack.showFragment(MusicPlayerFragment.TAG,null,true);
    }

    private void back() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
