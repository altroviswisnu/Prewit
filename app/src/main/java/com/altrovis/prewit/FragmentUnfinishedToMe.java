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
import com.altrovis.prewit.Bussines.Unfinished.UnfinishedToMeAsyncTask;
import com.altrovis.prewit.Bussines.Unfinished.UnfinishedToMeEndlessScroll;
import com.altrovis.prewit.Entities.GlobalVariable;

public class FragmentUnfinishedToMe extends Fragment {

    Spinner spinnerUnfinishedToMe;
    ListView listViewUnfinishedToMe;
    UnfinishedAdapter adapter;


    public FragmentUnfinishedToMe() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unfinished_to_me, container, false);

        spinnerUnfinishedToMe = (Spinner) view.findViewById(R.id.SpinnerUnFinishedToMe);
        listViewUnfinishedToMe = (ListView) view.findViewById(R.id.ListViewUnFinishedToMe);
        adapter = new UnfinishedAdapter(getActivity(), R.layout.item_listview,
                GlobalVariable.listOfUnfinishedToMe);
        listViewUnfinishedToMe.setAdapter(adapter);
        listViewUnfinishedToMe.setOnScrollListener(new UnfinishedToMeEndlessScroll((ActivityHome)
                getActivity(), adapter));
        spinnerUnfinishedToMe.setSelection(2);

        spinnerUnfinishedToMe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                    LinearLayout mainContainer = (LinearLayout) getActivity()
                            .findViewById(R.id.LinearLayoutAllUnFinished);
                    mainContainer.setVisibility(LinearLayout.VISIBLE);

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.popBackStack();

                    spinnerUnfinishedToMe.setSelection(2);
                } else if (position == 1) {

                    FragmentUnfinishedByMe newFragment = new FragmentUnfinishedByMe();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.LinearLayoutFragmentUnFinished, newFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                    spinnerUnfinishedToMe.setSelection(2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        new UnfinishedToMeAsyncTask(getActivity(), adapter).execute();


        return view;
    }

}
