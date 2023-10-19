package com.example.thumbnailloaderapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FragmentViewModel extends ViewModel {

    public MutableLiveData<Integer> clickedFragment;

    public FragmentViewModel() {
        clickedFragment = new MutableLiveData<Integer>();

        clickedFragment.setValue(0);
    }
    public int getClickedFragment() { return clickedFragment.getValue();}
    public void setClickedFragment(int value) { clickedFragment.setValue(value);}

}
