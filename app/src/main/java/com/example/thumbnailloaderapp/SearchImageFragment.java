package com.example.thumbnailloaderapp;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

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
    ImageView picture;
    ProgressBar progressBar;
    EditText searchKey;

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
        picture = rootView.findViewById(R.id.picureId);
        progressBar = rootView.findViewById(R.id.progressBarId);
        searchKey = rootView.findViewById(R.id.inputSearch);

        picture.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        loadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picture.setVisibility(View.INVISIBLE);
                String searchValues = searchKey.getText().toString();
                APISearchThread searchThread = new APISearchThread(searchValues, getActivity(), sViewModel);
                progressBar.setVisibility(View.VISIBLE);
                searchThread.start();
            }
        });

        sViewModel.response.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Search Complete", Toast.LENGTH_LONG).show();
                ImageRetrievalThread imageRetrievalThread = new ImageRetrievalThread(getActivity(), sViewModel, imageViewModel, errorViewModel);
                progressBar.setVisibility(View.VISIBLE);
                imageRetrievalThread.start();

            }
        });

        imageViewModel.image.observe(getActivity(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                progressBar.setVisibility(View.INVISIBLE);
                picture.setVisibility(View.VISIBLE);
                picture.setImageBitmap(imageViewModel.getImage());
            }
        });
        errorViewModel.errorCode.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

        return rootView;
    }
}