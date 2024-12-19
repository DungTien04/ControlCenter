package com.example.controlcenter.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.controlcenter.R;

public class backgroundDL extends DialogFragment  {
    public static final String TAG = backgroundDL.class.getName();
    // widgets

    private RadioButton mInput;
    private TextView mActionCancel;
    private RadioButton rbTrans, rbCurr, rbDefault, rbScreenBlur;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dia_frg_background,container,false);
        mActionCancel = view.findViewById(R.id.tv_cancel);
        rbTrans = view.findViewById(R.id.rb_trans);
        rbCurr = view.findViewById(R.id.rb_curr);
        rbDefault = view.findViewById(R.id.rb_defaul);
        rbScreenBlur = view.findViewById(R.id.rb_screen_blur);

        mActionCancel.setOnClickListener(v -> dismiss());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        String selectedOption = "";
        if (rbTrans.isChecked()) {
            selectedOption = rbTrans.getText().toString();
        } else if (rbCurr.isChecked()) {
            selectedOption = rbCurr.getText().toString();
        } else if (rbDefault.isChecked()) {
            selectedOption = rbDefault.getText().toString();
        } else if (rbScreenBlur.isChecked()) {
            selectedOption = rbScreenBlur.getText().toString();
        }
        // Gửi giá trị đã chọn trở lại Activity hoặc Fragment chứa Dialog

    }
}
