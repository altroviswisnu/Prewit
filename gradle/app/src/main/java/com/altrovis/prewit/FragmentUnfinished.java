package com.altrovis.prewit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentUnfinished.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentUnfinished#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUnfinished extends Fragment {

    public FragmentUnfinished() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static FragmentUnfinished newInstance(String param1, String param2) {
        FragmentUnfinished fragment = new FragmentUnfinished();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_unfinished, container, false);
    }
}
