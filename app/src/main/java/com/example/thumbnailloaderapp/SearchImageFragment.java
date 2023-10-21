package com.example.thumbnailloaderapp;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchImageFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SearchImageFragment() {
        // Required empty public constructor
    }

    public static SearchImageFragment newInstance(String param1, String param2) {
        SearchImageFragment fragment = new SearchImageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    SearchResponseVM sViewModel;
    ImageVM imageViewModel;
    ErrorVM errorViewModel;
    FragmentViewModel fragmentViewModel;
    Button loadImage;
    Button rv1View;
    Button rv2View;
    ImageView picture;
    ProgressBar progressBar;
    EditText searchKey;
    ArrayList<Bitmap> imageList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentViewModel = new ViewModelProvider(getActivity()).get(FragmentViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_search_image, container, false);

        sViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(SearchResponseVM.class);
        imageViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(ImageVM.class);
        errorViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(ErrorVM.class);

        loadImage = rootView.findViewById(R.id.loadImage);
        picture = rootView.findViewById(R.id.pictureId);
        progressBar = rootView.findViewById(R.id.progressBarId);
        searchKey = rootView.findViewById(R.id.inputSearch);
        rv1View = rootView.findViewById(R.id.gotoRV1);
        rv2View = rootView.findViewById(R.id.gotoRV2);

        picture.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        loadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                searchImage();

                Log.d("ArrayList","Number of elements in the arraylist: " + fragmentViewModel.imageArray.size());
            }
        });

        rv1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentViewModel.setClickedLayout(1);
                fragmentViewModel.setClickedFragment(2);
            }
        });

        rv2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentViewModel.setClickedLayout(2);
                fragmentViewModel.setClickedFragment(2);
            }
        });

        return rootView;
    }



    public void searchImage() {
        picture.setVisibility(View.INVISIBLE);
        String searchValues = searchKey.getText().toString();
        APISearchThread searchThread = new APISearchThread(searchValues, getActivity(), sViewModel);
        progressBar.setVisibility(View.VISIBLE);
        searchThread.start();

        sViewModel.response.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Search Complete, Now Retrieving Image...", Toast.LENGTH_LONG).show();
                ImageRetrievalThread imageRetrievalThread = new ImageRetrievalThread(getActivity(), sViewModel, imageViewModel, errorViewModel,fragmentViewModel);
                progressBar.setVisibility(View.VISIBLE);
                imageRetrievalThread.start();

            }
        });
        imageViewModel.image.observe(getActivity(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                if (imageViewModel.finishSearch) {
                    Toast.makeText(getActivity(), "Retrieving Image Complete", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    picture.setVisibility(View.VISIBLE);
                    picture.setImageBitmap(imageViewModel.getImage());
                }

                if (!hasBitmap(imageViewModel.getImage())) {
                    fragmentViewModel.addToArray(imageViewModel.getImage());
                    Log.d("ArrayList","Item added to array" + imageViewModel.getImage().toString());

                }
            }
        });

        errorViewModel.errorCode.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }

    public boolean hasBitmap(Bitmap inBitmap) {
        for(int i = 0;i < fragmentViewModel.getImageArray().size();i++) {
            if (inBitmap == fragmentViewModel.getImageArray().get(i)) {
                return true;
            }
        }
        return false;
    }
}