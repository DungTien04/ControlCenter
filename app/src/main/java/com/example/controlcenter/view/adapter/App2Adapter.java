package com.example.controlcenter.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlcenter.R;
import com.example.controlcenter.model.Apps;

import java.util.ArrayList;
import java.util.List;

public class App2Adapter extends RecyclerView.Adapter<App2Adapter.AppViewHolder> {
    private List<Apps> mlist = new ArrayList<>();
    private  Context contex;
    private OnItemActionListener listener;

    public App2Adapter(Context contex, List<Apps> mlist, OnItemActionListener listener) {
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
        if (apps == null) {
            return;
        }
        holder.tvName.setText(apps.getAppName());
        Bitmap bitmap = decodeFromBase64(apps.getAppIconCode());
        Drawable drawable  = bitmapToDrawable(bitmap,contex);
        holder.ivIcon.setImageDrawable(drawable);
        holder.bind(apps, listener, position);
    }
    public Bitmap decodeFromBase64(String input) {
        byte[] decodedBytes = Base64.decode(input, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
    public Drawable bitmapToDrawable(Bitmap bitmap, Context context) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    @Override
    public int getItemCount() {
        if (mlist != null) {
            return mlist.size();
        }
        return 0;
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
            delete.setVisibility(View.VISIBLE);
            add.setVisibility(View.GONE);
        }

        public void bind(Apps apps, OnItemActionListener listener, int position) {
            delete.setOnClickListener(v -> {
                listener.onRemoveClick(position);
            });

        }
    }
    public void addItem(Apps apps) {
        mlist.add(apps);
        notifyItemInserted(mlist.size() - 1);
    }

    public void removeItem(int position) {
        mlist.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mlist.size());
    }
    public interface OnItemActionListener {

        void onRemoveClick(int position);
    }
}



