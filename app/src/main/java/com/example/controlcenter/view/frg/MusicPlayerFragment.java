package com.example.controlcenter.view.frg;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.controlcenter.App;
import com.example.controlcenter.databinding.FrgChooseMusicPlayerBinding;
import com.example.controlcenter.model.Apps;
import com.example.controlcenter.view.adapter.MusicPlayAdapter;
import com.example.controlcenter.viewMd.CommonMD;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayerFragment extends BaseFragment<FrgChooseMusicPlayerBinding, CommonMD>{
    public static final String TAG = MusicPlayerFragment.class.getName();
    private List<Apps> list = new ArrayList<>();
    @Override
    protected Class<CommonMD> getClassVM() {
        return CommonMD.class;
    }

    @Override
    protected FrgChooseMusicPlayerBinding initViewBingding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FrgChooseMusicPlayerBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initViews() {
        binding.ivBack.setOnClickListener(v ->  back());
        loadData();
        showListApp();
    }

    private void loadData() {
        list=App.getInstance().getStorage().listMusicApp;
    }

    private void showListApp() {

        MusicPlayAdapter adapter = new MusicPlayAdapter(context,list);
        binding.rcvMusicPlayer.setLayoutManager(new LinearLayoutManager(context));
        binding.rcvMusicPlayer.setAdapter(adapter);

    }

    private void back() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
