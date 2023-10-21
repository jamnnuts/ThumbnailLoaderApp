package com.example.thumbnailloaderapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecViewFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    RecyclerView rv;
    RecyclerViewAdapter adapter;
    FragmentViewModel fragmentViewModel;
    Button returnButton;

    public RecViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageViewRV.
     */
    // TODO: Rename and change types and number of parameters
    public static RecViewFragment newInstance(String param1, String param2) {
        RecViewFragment fragment = new RecViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        View rootView = inflater.inflate(R.layout.fragment_image_view_r_v,container,false);
        fragmentViewModel = new ViewModelProvider(getActivity()).get(FragmentViewModel.class);

        returnButton = rootView.findViewById(R.id.returnButton);
        rv = rootView.findViewById(R.id.searchImageRV);

        if(fragmentViewModel.getClickedLayout() == 1) {

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
            rv.setLayoutManager(linearLayoutManager);

        }
        else if (fragmentViewModel.getClickedLayout() == 2) {
            int spanCount = 2;
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), spanCount, GridLayoutManager.HORIZONTAL, false);
            rv.setLayoutManager(gridLayoutManager);
        }

        adapter = new RecyclerViewAdapter(fragmentViewModel.getImageArray(), getContext());
        rv.setAdapter(adapter);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentViewModel.setClickedFragment(1);
            }
        });

        return rootView;
    }


}