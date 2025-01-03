package com.example.controlcenter.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlcenter.R;
import com.example.controlcenter.model.Apps;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayAdapter extends RecyclerView.Adapter<MusicPlayAdapter.MusicViewHolder>{
    private List<Apps> mlist = new ArrayList<>();
    private final Context context;

    public MusicPlayAdapter(Context context,List<Apps> mlist ) {
        this.mlist = mlist;
        this.context = context;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_music_player, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        Apps apps = mlist.get(position);
        if (apps == null) {
            return;
        }
        holder.textView.setText(apps.getAppName());
        Bitmap bitmap = decodeFromBase64(apps.getAppIconCode());
        Drawable drawable  = bitmapToDrawable(bitmap,context);
        holder.imageView.setImageDrawable(drawable);

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

    public static class MusicViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        RadioButton radioButton;
        LinearLayout linearLayout;
        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_music_player);
            textView = itemView.findViewById(R.id.tv_music_player);
            radioButton = itemView.findViewById(R.id.rb_music_player);
            linearLayout= itemView.findViewById(R.id.ln_music_choose);

        }

    }
}
