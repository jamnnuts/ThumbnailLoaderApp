package com.example.thumbnailloaderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SearchImageFragment searchImageFragment = new SearchImageFragment();
    RecViewFragment recViewFragment = new RecViewFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentViewModel fragmentViewmodel = new ViewModelProvider(this).get(FragmentViewModel.class);

        loadSearchImage();

        fragmentViewmodel.clickedFragment.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (fragmentViewmodel.getClickedFragment() == 1) {
                    loadSearchImage();
            }
                else if (fragmentViewmodel.getClickedFragment() == 2) {
                    loadRV();
                }
        }
    });
}

    public void loadSearchImage() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragmentContainerMain);

        if (frag == null) {
            fm.beginTransaction().add(R.id.fragmentContainerMain, searchImageFragment).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.fragmentContainerMain, searchImageFragment).commit();
        }

    }

    public void loadRV() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragmentContainerMain);

        if (frag == null) {
            fm.beginTransaction().add(R.id.fragmentContainerMain, recViewFragment).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.fragmentContainerMain, recViewFragment).commit();
        }
    }
}