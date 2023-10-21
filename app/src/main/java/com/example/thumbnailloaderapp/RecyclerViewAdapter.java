package com.example.thumbnailloaderapp;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.imageVH> {
    ArrayList<Bitmap> imageList;
    private imageVH holder;
    private int position;

    public RecyclerViewAdapter(ArrayList<Bitmap> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.imageVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.search_image_layout, parent, false);

        return new imageVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.imageVH holder, int position) {
        Bitmap photo = imageList.get(position);
        holder.searchImage.setImageBitmap(photo);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class imageVH extends RecyclerView.ViewHolder {
        ImageView searchImage;
        public imageVH(@NonNull View view) {
            super(view);
            searchImage = view.findViewById(R.id.searchImage);
        }
    }
}
