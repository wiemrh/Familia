package tn.esprit.miafamilia10.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tn.esprit.miafamilia10.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RootFragmentHome extends Fragment {


    public RootFragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_root_home, container, false);
    }

}
