package com.altrovis.prewit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.altrovis.prewit.Bussines.Finished.FinishedAdapter;
import com.altrovis.prewit.Bussines.Finished.FinishedToMeAsyncTask;
import com.altrovis.prewit.Entities.GlobalVariable;

public class FragmentFinishedToMe extends Fragment {

    Spinner spinnerFinishedToMe;
    ListView listViewFinishedToMe;
    FinishedAdapter adapter;

    public FragmentFinishedToMe() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_finished_to_me, container, false);

        spinnerFinishedToMe = (Spinner) view.findViewById(R.id.SpinnerFinishedToMe);
        listViewFinishedToMe = (ListView) view.findViewById(R.id.ListViewFinishedToMe);
        adapter = new FinishedAdapter(getActivity(), R.layout.item_listview,
                GlobalVariable.listOfFinishedToMe);
        listViewFinishedToMe.setAdapter(adapter);
        spinnerFinishedToMe.setSelection(2);

        spinnerFinishedToMe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                    LinearLayout mainContainer = (LinearLayout) getActivity()
                            .findViewById(R.id.LinearLayoutAllFinished);
                    mainContainer.setVisibility(LinearLayout.VISIBLE);

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.popBackStack();

                    spinnerFinishedToMe.setSelection(2);
                } else if (position == 1) {

                    FragmentFinishedByMe newFragment = new FragmentFinishedByMe();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.LinearLayoutFragmentFinished, newFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                    spinnerFinishedToMe.setSelection(2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        new FinishedToMeAsyncTask(getActivity(), adapter).execute();

        return view;
    }

}
