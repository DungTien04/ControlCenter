package com.example.controlcenter.view.frg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.controlcenter.databinding.FrgRecordSetupBinding;
import com.example.controlcenter.viewMd.M001MD;

public class RecordSetupFragment extends BaseFragment<FrgRecordSetupBinding, M001MD>{
    public static final String TAG = RecordSetupFragment.class.getName();
    @Override
    protected Class<M001MD> getClassVM() {
        return M001MD.class;
    }

    @Override
    protected FrgRecordSetupBinding initViewBingding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FrgRecordSetupBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initViews() {
        binding.ivBack.setOnClickListener(v ->  back());
        binding.tvAdvance.setOnClickListener(v -> {
            binding.tvAdvance.setVisibility(View.GONE);
            binding.lnAdvance.setVisibility(View.GONE);
            binding.tvBasic.setVisibility(View.VISIBLE);
        });
        binding.tvBasic.setOnClickListener(v -> {
            binding.tvAdvance.setVisibility(View.VISIBLE);
            binding.lnAdvance.setVisibility(View.VISIBLE);
            binding.tvBasic.setVisibility(View.GONE);
        });
    }

    private void back() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
