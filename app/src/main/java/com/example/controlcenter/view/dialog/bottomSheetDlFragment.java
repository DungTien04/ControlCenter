package com.example.controlcenter.view.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.controlcenter.App;
import com.example.controlcenter.R;
import com.example.controlcenter.model.Apps;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class bottomSheetDlFragment extends BottomSheetDialogFragment {

    private static final String KEY_APPLICATION = "KEY_APPLICATION";
    private int gravity;
    private List<Apps> appsList;
    private ImageView bluetooth,wifi,plane,rotate;
    public bottomSheetDlFragment() {

    }


    public static bottomSheetDlFragment newInstance(){
        bottomSheetDlFragment btsheetDL = new bottomSheetDlFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_APPLICATION,null);
        btsheetDL.setArguments(bundle);
        return btsheetDL;

    }
    public bottomSheetDlFragment(int gravity) {
        this.gravity = gravity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.frg_ios_utilities, container, false);
        bluetooth = view.findViewById(R.id.btn_blueto);
        wifi = view.findViewById(R.id.btn_wifi);
        plane = view.findViewById(R.id.btn_airplane);
        rotate = view.findViewById(R.id.btn_rotate);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundleReceive = getArguments();
        if(bundleReceive!=null){
            // lay du lieu  =bundleReceive.get(KEY)
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog= (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View viewDialog = LayoutInflater.from(getContext()).inflate(R.layout.frg_ios_utilities,null);
        bottomSheetDialog.setContentView(viewDialog);
        setdata();
        initView();

        return bottomSheetDialog;
    }

    private void setdata() {
        appsList = App.getInstance().getStorage().listApps;
        for (Apps apps : appsList){
            if(apps.getAppName().contains("Wifi")){
                wifi.setTag(apps.getPackageName());
            }
            if(apps.getAppName().contains("Bluetooth")){
                bluetooth.setTag(apps.getPackageName());
            }
            if(apps.getAppName().contains("Airplane")){
                plane.setTag(apps.getPackageName());
            }
            if(apps.getAppName().contains("rotate")){
                rotate.setTag(apps.getPackageName());
            }

        }
    }

    private void initView() {
        //setOnClick

    }
    private void launchApp(String packageName) {
        Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntent != null) {
            startActivity(launchIntent);
        } else {

        }
    }


    @Override
    public void onStart() {
        super.onStart();
        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(gravity);
        }
    }
}
