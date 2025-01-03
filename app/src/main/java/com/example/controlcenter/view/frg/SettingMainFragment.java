package com.example.controlcenter.view.frg;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import com.example.controlcenter.databinding.FrgSettingMainBinding;
import com.example.controlcenter.view.dialog.backgroundDL;
import com.example.controlcenter.viewMd.CommonMD;

public class SettingMainFragment extends BaseFragment<FrgSettingMainBinding, CommonMD>{
    public  static final String TAG = SettingMainFragment.class.getName();
    @Override
    protected Class<CommonMD> getClassVM() {
        return CommonMD.class;
    }

    @Override
    protected FrgSettingMainBinding initViewBingding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FrgSettingMainBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initViews() {
        binding.ivList.setOnClickListener(v -> openMenu());
        binding.trSize.setOnClickListener(v -> openHandleSetting());
        binding.trColor.setOnClickListener(v -> openHandleSetting());
        binding.trPosition.setOnClickListener(v -> openHandleSetting());
        binding.trRecord.setOnClickListener(v -> openRecordSetup());
        binding.trMusic.setOnClickListener(v -> openMusicControl());
        binding.trCustomize.setOnClickListener(v->openCustomize());
        binding.trBackground.setOnClickListener(v->openDLBackground());
    }

    private void openDLBackground() {
        backgroundDL dialog = new backgroundDL();
        dialog.show(getChildFragmentManager(), backgroundDL.TAG);
        dialog.setCancelable(false);
    }

    private void openCustomize() {
        callBack.showFragment(CustomizeControlFragment.TAG,null,true);
    }

    private void openMusicControl() {
        callBack.showFragment(MusicControlFragment.TAG,null,true);
    }

    private void openRecordSetup() {
        callBack.showFragment(RecordSetupFragment.TAG,null,true);
    }

    private void openHandleSetting() {
        callBack.showFragment(HandleSettingFragment.TAG,null,true);
    }

    private void openMenu() {
            binding.drawer.openDrawer(GravityCompat.START);
    }

}
