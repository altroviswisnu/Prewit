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
import com.altrovis.prewit.Bussines.Unfinished.UnfinishedAsyncTask;
import com.altrovis.prewit.Bussines.Unfinished.UnfinishedEndlessScroll;
import com.altrovis.prewit.Entities.GlobalVariable;


public class FragmentUnfinished extends Fragment {

    Spinner spinnerUnfinished;
    ListView listViewUnfinished;
    UnfinishedAdapter adapter;

    public FragmentUnfinished() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unfinished, container, false);

        spinnerUnfinished = (Spinner) view.findViewById(R.id.SpinnerUnFinished);
        listViewUnfinished = (ListView) view.findViewById(R.id.ListViewUnFinished);
        adapter = new UnfinishedAdapter(getActivity(), R.layout.item_listview,
                GlobalVariable.listOfUnfinished);
        listViewUnfinished.setAdapter(adapter);
        listViewUnfinished.setOnScrollListener(new UnfinishedEndlessScroll((ActivityHome)
                getActivity(), adapter));
        spinnerUnfinished.setSelection(0);

        spinnerUnfinished.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {

                    LinearLayout mainContainer = (LinearLayout) getActivity()
                            .findViewById(R.id.LinearLayoutAllUnFinished);
                    mainContainer.setVisibility(LinearLayout.GONE);

                    FragmentUnfinishedByMe newFragment = new FragmentUnfinishedByMe();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.LinearLayoutFragmentUnFinished, newFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                    spinnerUnfinished.setSelection(0);
                } else if (position == 2) {

                    LinearLayout mainContainer = (LinearLayout) getActivity()
                            .findViewById(R.id.LinearLayoutAllUnFinished);
                    mainContainer.setVisibility(LinearLayout.GONE);

                    FragmentUnfinishedToMe newFragment = new FragmentUnfinishedToMe();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.LinearLayoutFragmentUnFinished, newFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                    spinnerUnfinished.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        new UnfinishedAsyncTask(getActivity(), adapter).execute();

        return view;
    }
}
