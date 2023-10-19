package com.example.thumbnailloaderapp;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ImageVM extends ViewModel{
    public MutableLiveData<Bitmap> image;
    public ImageVM(){
        image = new MutableLiveData<Bitmap>();
    }

    public Bitmap getImage(){
        return image.getValue();
    }
    public void setImage(Bitmap bitmap){
        image.postValue(bitmap);
    }
}

