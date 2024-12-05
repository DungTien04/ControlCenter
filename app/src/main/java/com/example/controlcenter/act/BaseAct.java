package com.example.controlcenter.act;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.controlcenter.OnMainCallBack;
import com.example.controlcenter.R;
import com.example.controlcenter.frg.BaseFragment;


import java.lang.reflect.Constructor;

public abstract class BaseAct<V extends ViewBinding, M extends ViewModel> extends AppCompatActivity implements View.OnClickListener, OnMainCallBack {
    protected V binding;
    protected M viewModel;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinnding();
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(getClassVM());
        initViews();
    }

    protected abstract void initViews();

    protected abstract Class<M> getClassVM();

    protected abstract V initViewBinnding();

    @Override
    public void onClick(View v) {
        v.setAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    protected void clickView(View v) {

    }

    protected void showNotify(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    protected void showNotify(int msgID) {
        Toast.makeText(this, msgID, Toast.LENGTH_LONG).show();
    }
    @Override
    public void showFragment(String tag, Object data, boolean isBacked) {
        try {
            Class<?> clazz = Class.forName(tag);
            Constructor<?> constructor = clazz.getConstructor();
            BaseFragment<?,?> baseFragment = (BaseFragment<?,?>) constructor.newInstance();

            baseFragment.setCallBack(this);
            baseFragment.setData(data);
            // nhung fragment
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            if (isBacked) {
                transaction.addToBackStack(null);
            }
            transaction.replace(R.id.main, baseFragment, tag).commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
