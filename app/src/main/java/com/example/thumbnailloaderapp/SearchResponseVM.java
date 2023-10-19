package com.example.thumbnailloaderapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchResponseVM extends ViewModel {

    public MutableLiveData<String> response;
    public SearchResponseVM(){
        response = new MutableLiveData<String>();
    }

    public String getResponse(){
        return response.getValue();
    }
    public void setResponse(String value){
        response.postValue(value);
    }
}
