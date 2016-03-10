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

import com.altrovis.prewit.Bussines.Unfinished.UnfinishedAdapter;
import com.altrovis.prewit.Bussines.Unfinished.UnfinishedByMeAsyncTask;
import com.altrovis.prewit.Entities.GlobalVariable;

public class FragmentUnfinishedByMe extends Fragment {

    Spinner spinnerUnfinishedByMe;
    ListView listViewUnfinishedByMe;
    UnfinishedAdapter adapter;

    public FragmentUnfinishedByMe() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unfinished_by_me, container, false);

        spinnerUnfinishedByMe = (Spinner) view.findViewById(R.id.SpinnerUnFinishedByMe);
        listViewUnfinishedByMe = (ListView) view.findViewById(R.id.ListViewUnFinishedByMe);
        adapter = new UnfinishedAdapter(getActivity(), R.layout.item_listview,
                GlobalVariable.listOfUnfinishedByMe);
        listViewUnfinishedByMe.setAdapter(adapter);
        spinnerUnfinishedByMe.setSelection(1);

        spinnerUnfinishedByMe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                    LinearLayout mainContainer = (LinearLayout) getActivity()
                            .findViewById(R.id.LinearLayoutAllUnFinished);
                    mainContainer.setVisibility(LinearLayout.VISIBLE);

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.popBackStack();

                    spinnerUnfinishedByMe.setSelection(1);
                } else if (position == 2) {

                    FragmentUnfinishedToMe newFragment = new FragmentUnfinishedToMe();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.LinearLayoutFragmentUnFinished, newFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                    spinnerUnfinishedByMe.setSelection(1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        new UnfinishedByMeAsyncTask(getActivity(), adapter).execute();

        return view;
    }

}
