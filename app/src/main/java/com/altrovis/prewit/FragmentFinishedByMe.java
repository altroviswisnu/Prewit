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
import com.altrovis.prewit.Bussines.Finished.FinishedByMeAsyncTask;
import com.altrovis.prewit.Bussines.Finished.FinishedByMeEndlessScroll;
import com.altrovis.prewit.Entities.GlobalVariable;

public class FragmentFinishedByMe extends Fragment {

    Spinner spinnerFinishedByMe;
    ListView listViewFinishedByMe;
    FinishedAdapter adapter;

    public FragmentFinishedByMe() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finished_by_me, container, false);

        spinnerFinishedByMe = (Spinner) view.findViewById(R.id.SpinnerFinishedByMe);
        listViewFinishedByMe = (ListView) view.findViewById(R.id.ListViewFinishedByMe);
        adapter = new FinishedAdapter(getActivity(), R.layout.item_listview,
                GlobalVariable.listOfFinishedByMe);
        listViewFinishedByMe.setAdapter(adapter);
        listViewFinishedByMe.setOnScrollListener(new FinishedByMeEndlessScroll((ActivityHome)
                getActivity(), adapter));
        spinnerFinishedByMe.setSelection(1);

        spinnerFinishedByMe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                    LinearLayout mainContainer = (LinearLayout) getActivity()
                            .findViewById(R.id.LinearLayoutAllFinished);
                    mainContainer.setVisibility(LinearLayout.VISIBLE);

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.popBackStack();

                    spinnerFinishedByMe.setSelection(1);
                } else if (position == 2) {

                    FragmentFinishedToMe newFragment = new FragmentFinishedToMe();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.LinearLayoutFragmentFinished, newFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                    spinnerFinishedByMe.setSelection(1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        new FinishedByMeAsyncTask(getActivity(), adapter).execute();

        return view;
    }
}
