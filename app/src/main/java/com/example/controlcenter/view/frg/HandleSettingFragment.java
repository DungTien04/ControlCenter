package com.example.controlcenter.view.frg;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.example.controlcenter.R;
import com.example.controlcenter.databinding.FrgHandleSettingBinding;
import com.example.controlcenter.viewMd.M001MD;

public class HandleSettingFragment extends BaseFragment<FrgHandleSettingBinding, M001MD>{
    public static final String TAG = HandleSettingFragment.class.getName();
    private View draggableView;
    private RadioGroup radioGroup;
    private int width;
    private int height;
    @Override
    protected Class<M001MD> getClassVM() {
        return M001MD.class;
    }

    @Override
    protected FrgHandleSettingBinding initViewBingding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FrgHandleSettingBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initViews() {
        binding.ivBack.setOnClickListener(v ->  back());
        draggableView = binding.draggableView;
        radioGroup = binding.radioGroup;

         width = getResources().getDisplayMetrics().widthPixels;
         height = getResources().getDisplayMetrics().heightPixels;
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            setPosition(checkedId);
        });



    }
    private void setPosition(int checkedId) {
        if (checkedId == R.id.radio_top) {
            draggableView.setX((float) (width - draggableView.getWidth()) / 2);
            draggableView.setY(0);
            draggableView.setRotation(90);
        } else if (checkedId == R.id.radio_left) {
            draggableView.setX(0);
            draggableView.setY((float) (height - draggableView.getHeight()) / 2);
            draggableView.setRotation(0);
        } else if (checkedId == R.id.radio_right) {
            draggableView.setX(width - draggableView.getWidth());
            draggableView.setY((float) (height - draggableView.getHeight()) / 2);
            draggableView.setRotation(0);
        } else if (checkedId == R.id.radio_bot) {
            draggableView.setX((float) (width - draggableView.getWidth()) / 2);
            draggableView.setY(height - draggableView.getHeight());
            draggableView.setRotation(90);
        }
    }
    private void back() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
