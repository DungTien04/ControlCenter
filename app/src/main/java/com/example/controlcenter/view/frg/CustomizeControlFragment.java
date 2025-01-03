package com.example.controlcenter.view.frg;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.controlcenter.App;
import com.example.controlcenter.databinding.FrgCusControlsBinding;
import com.example.controlcenter.model.Apps;
import com.example.controlcenter.view.adapter.App2Adapter;
import com.example.controlcenter.view.adapter.AppAdapter;
import com.example.controlcenter.viewMd.CustomControlMD;

import java.util.ArrayList;
import java.util.List;

public class CustomizeControlFragment extends BaseFragment<FrgCusControlsBinding, CustomControlMD>{
    public static final String TAG =CustomizeControlFragment.class.getName() ;
    private  List<Apps> listApp=new ArrayList<>();
    private  List<Apps> listUse=new ArrayList<>();
    private AppAdapter adapter1;
    private App2Adapter adapter2;

     @Override
    protected Class<CustomControlMD> getClassVM() {
        return CustomControlMD.class;
    }

    @Override
    protected FrgCusControlsBinding initViewBingding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FrgCusControlsBinding .inflate(inflater,container,false);
    }

    @Override
    protected void initViews() {
        binding.ivBack.setOnClickListener(v ->  back());
        loadData();
        showListApps();
    }

    private void loadData() {
        listApp=App.getInstance().getControlDB().getDao().getUseApp();
        listUse=App.getInstance().getControlDB().getDao().getNonUseApp();
    }
    private void showListApps() {

          adapter1 = new AppAdapter(context, listApp, position -> {
              Apps apps = listApp.get(position);
              apps.setUse(true);
              new Thread(() -> {
                  try {
                      App.getInstance().getControlDB().getDao().updateApps(apps);
                      getActivity().runOnUiThread(() -> {
                          adapter2.addItem(apps);
                          adapter1.removeItem(position);
                      });
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }).start();
          });
         adapter2 = new App2Adapter(context, listUse, position -> {
             Apps apps = listUse.get(position);
             apps.setUse(false);
             new Thread(() -> {
                 try {
                     App.getInstance().getControlDB().getDao().updateApps(apps);
                     getActivity().runOnUiThread(() -> {
                         adapter1.addItem(apps);
                         adapter2.removeItem(position);
                     });
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }).start();
         }) ;

        binding.rcvControlPre.setLayoutManager(new LinearLayoutManager(context));
        binding.rcvControlPre.setAdapter(adapter2);

        binding.rcvControlNonPre.setLayoutManager(new LinearLayoutManager(context));
        binding.rcvControlNonPre.setAdapter(adapter1);

    }

    private void back() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
