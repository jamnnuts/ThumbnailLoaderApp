package com.example.thumbnailloaderapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ErrorVM extends ViewModel {

    public MutableLiveData<Integer> errorCode;
    public ErrorVM(){
        errorCode = new MutableLiveData<Integer>();
        errorCode.setValue(0);
    }

    public Integer getErrorCode(){
        return errorCode.getValue();
    }
    public void setErrorCode(Integer value){
            errorCode.postValue(value);
        }
}

