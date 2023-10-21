package com.example.thumbnailloaderapp;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ImageVM extends ViewModel{
    public MutableLiveData<Bitmap> image;
    public boolean finishSearch;
    public ImageVM(){
        image = new MutableLiveData<Bitmap>();
        finishSearch = false;
    }

    public Bitmap getImage(){
        return image.getValue();
    }
    public void setImage(Bitmap bitmap){
        image.postValue(bitmap);
    }
    public void setFinishSearch(){ finishSearch = true;}
}

