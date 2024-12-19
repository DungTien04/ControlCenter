package com.example.controlcenter.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlcenter.R;
import com.example.controlcenter.model.Apps;

import java.util.ArrayList;
import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {
    private  List<Apps> mlist = new ArrayList<>();
    private  Context contex;
    private final MutableLiveData<Apps> appId = new MutableLiveData<>();
    private OnItemActionListener listener;
    public MutableLiveData<Apps> getAppId() {
        return appId;
    }

    public AppAdapter(Context contex, List<Apps> mlist,OnItemActionListener listener) {
        this.mlist = mlist;
        this.contex = contex;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contex).inflate(R.layout.item_cus_controls, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        Apps apps = mlist.get(position);
        appId.postValue(apps);
        if (apps == null) {
            return;
        }
        holder.tvName.setText(apps.getAppName());
        holder.ivIcon.setImageDrawable(apps.getAppIcon());
        holder.bind(apps,listener, position);
    }

    @Override
    public int getItemCount() {
        if (mlist != null) {
            return mlist.size();
        }
        return 0;
    }
    public void addItem(Apps apps) {
        mlist.add(0,apps);
        notifyItemInserted(0);
        notifyItemRangeChanged(0, mlist.size());
    }

    public void removeItem(int position) {
        mlist.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mlist.size());
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {
         TextView tvName;
        ImageView ivIcon;
        ImageView delete;
        ImageView add;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            Apps apps = new Apps(tvName.toString(), ivIcon.getDrawable());
            delete = itemView.findViewById(R.id.iv_delete);
            add = itemView.findViewById(R.id.iv_add);
            delete.setVisibility(View.GONE);
            add.setVisibility(View.VISIBLE);
        }
        public void bind(Apps apps,OnItemActionListener listener, int position) {
            add.setOnClickListener(v -> {
                listener.onAddClick(position);
            });
        }
    }
    public interface OnItemActionListener {
        void onAddClick(int position);

    }

}
