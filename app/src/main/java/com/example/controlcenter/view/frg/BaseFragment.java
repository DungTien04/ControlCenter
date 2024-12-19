package com.example.controlcenter.view.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.controlcenter.OnMainCallBack;


public abstract class BaseFragment<B extends ViewBinding, V extends ViewModel> extends Fragment implements OnClickListener {
    protected B binding;
    protected V viewModel;
    protected Object data;
    protected Context context;
    protected OnMainCallBack callBack;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @NonNull
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = initViewBingding(inflater,container);
        viewModel= new ViewModelProvider(this).get(getClassVM());
        return binding.getRoot();
    }

    public final void setCallBack(OnMainCallBack callBack) {
        this.callBack = callBack;
    }

    public void setData(Object data) {
        this.data = data;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }
    @Override
    public final void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_out));
        clickView(v);
    }

    protected abstract Class<V> getClassVM();

    protected abstract B initViewBingding(LayoutInflater inflater,@Nullable ViewGroup container);

    protected abstract void initViews();

    protected void clickView(View v) {
        // do something
    }
    protected void showNotify(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    protected void showNotify(int msgID) {
        Toast.makeText(context, msgID, Toast.LENGTH_LONG).show();
    }

}
