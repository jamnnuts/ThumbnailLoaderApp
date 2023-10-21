package com.example.thumbnailloaderapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.imageVH> {
    ArrayList<Bitmap> imageList;
    private imageVH holder;
    private int position;
    Bitmap photo;
    FirebaseStorage storage;
    StorageReference storageReference;
    private Context applicationContext;

    public RecyclerViewAdapter(ArrayList<Bitmap> imageList, Context context) {

        this.imageList = imageList;
        applicationContext = context;
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
        photo = imageList.get(position);
        holder.searchImage.setImageBitmap(photo);
        holder.uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String storedFilePath;
                storedFilePath = saveToInternalStorage(photo);
                loadImageFromStorageAndUpload(storedFilePath);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    private String saveToInternalStorage(Bitmap bitmapImage){ //https://stackoverflow.com/questions/17674634/saving-and-reading-bitmaps-images-from-internal-memory-in-android
        ContextWrapper cw = new ContextWrapper(applicationContext);
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File myPath = new File(directory,"imageUpload.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myPath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    private void loadImageFromStorageAndUpload(String path)
    {
        File f = new File(path, "imageUpload.jpg");
        Uri fileUri = Uri.fromFile(f);
        //Insert upload to firebase code

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        if (path != null) {

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + UUID.randomUUID().toString());

            ref.putFile(fileUri)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    Toast.makeText(applicationContext, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {


                            Toast.makeText(applicationContext, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    class imageVH extends RecyclerView.ViewHolder {
        ImageView searchImage;
        Button uploadButton;
        public imageVH(@NonNull View view) {
            super(view);
            searchImage = view.findViewById(R.id.searchImage);
            uploadButton = view.findViewById(R.id.uploadImageButton);
        }
    }
}
