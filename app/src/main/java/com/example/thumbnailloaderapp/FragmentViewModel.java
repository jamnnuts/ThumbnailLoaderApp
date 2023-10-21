package com.example.thumbnailloaderapp;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentViewModel extends ViewModel {

    public MutableLiveData<Integer> clickedFragment;
    public ArrayList<Bitmap> imageArray;
    public MutableLiveData<Integer> clickedLayout;

    public FragmentViewModel() {
        clickedFragment = new MutableLiveData<Integer>();
        imageArray = new ArrayList<Bitmap>();
        clickedLayout = new MutableLiveData<Integer>();

        clickedLayout.setValue(0);
        clickedFragment.setValue(0);
    }
    public int getClickedFragment() { return clickedFragment.getValue();}
    public void setClickedFragment(int value) { clickedFragment.setValue(value);}
    public ArrayList<Bitmap> getImageArray() {return imageArray;}
    public void addToArray(Bitmap inImage) {imageArray.add(inImage);}
    public int getClickedLayout() {return clickedLayout.getValue();}
    public void setClickedLayout(int inLayout) { clickedLayout.setValue(inLayout);}
}
