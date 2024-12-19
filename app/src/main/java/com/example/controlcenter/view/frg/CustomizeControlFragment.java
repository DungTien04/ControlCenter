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
import com.example.controlcenter.viewMd.M001MD;

import java.util.ArrayList;
import java.util.List;

public class CustomizeControlFragment extends BaseFragment<FrgCusControlsBinding, M001MD>{
    public static final String TAG =CustomizeControlFragment.class.getName() ;
    List<Apps> listApp = App.getInstance().getStorage().listApps;
    List<Apps> listUse = new ArrayList<>();
    private AppAdapter adapter1;
    private App2Adapter adapter2;
     @Override
    protected Class<M001MD> getClassVM() {
        return M001MD.class;
    }

    @Override
    protected FrgCusControlsBinding initViewBingding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FrgCusControlsBinding .inflate(inflater,container,false);
    }

    @Override
    protected void initViews() {
        binding.ivBack.setOnClickListener(v ->  back());

        showListApps();

    }

    private void showListApps() {
        listUse.add(new Apps("Klairs",binding.getRoot().getDividerDrawable()));

          adapter1 = new AppAdapter(context, listApp, new AppAdapter.OnItemActionListener() {
            @Override
            public void onAddClick(int position) {
                Apps apps = listApp.get(position);
                adapter2.addItem(apps);
                adapter1.removeItem(position);
            }
        });
         adapter2 = new App2Adapter(context, listUse, new App2Adapter.OnItemActionListener() {
             @Override
             public void onRemoveClick(int position) {
                 Apps apps = listUse.get(position);
                 adapter1.addItem(apps);
                 adapter2.removeItem(position);

             }
         }) ;

        App.getInstance().getStorage().listUse = listUse;

        binding.rcvControlPre.setLayoutManager(new LinearLayoutManager(context));
        binding.rcvControlPre.setAdapter(adapter2);

        binding.rcvControlNonPre.setLayoutManager(new LinearLayoutManager(context));
        binding.rcvControlNonPre.setAdapter(adapter1);


    }

    private void back() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
