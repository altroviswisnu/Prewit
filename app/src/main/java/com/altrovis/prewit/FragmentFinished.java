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
import com.altrovis.prewit.Bussines.Finished.FinishedAsyncTask;
import com.altrovis.prewit.Entities.GlobalVariable;


public class FragmentFinished extends Fragment {

    Spinner spinnerFinished;
    ListView listViewFinished;
    FinishedAdapter adapter;

    public FragmentFinished() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_finished, container, false);

        spinnerFinished = (Spinner) view.findViewById(R.id.SpinnerFinished);
        listViewFinished = (ListView) view.findViewById(R.id.ListViewFinished);
        adapter = new FinishedAdapter(getActivity(), R.layout.item_listview,
                GlobalVariable.listOfFinished);
        listViewFinished.setAdapter(adapter);

        spinnerFinished.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {

                    LinearLayout mainContainer = (LinearLayout) getActivity()
                            .findViewById(R.id.LinearLayoutFragmentFinished);
                    mainContainer.setVisibility(LinearLayout.GONE);

                    FragmentFinishedByMe newFragment = new FragmentFinishedByMe();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.LinearLayoutFragmentFinished, newFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                    spinnerFinished.setSelection(0);
                } else if (position == 2) {

                    LinearLayout mainContainer = (LinearLayout) getActivity()
                            .findViewById(R.id.LinearLayoutFragmentFinished);
                    mainContainer.setVisibility(LinearLayout.GONE);

                    FragmentFinishedToMe newFragment = new FragmentFinishedToMe();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.LinearLayoutFragmentFinished, newFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                    spinnerFinished.setSelection(0);
                }
            }
        });

        new FinishedAsyncTask(getActivity(),adapter).execute();

        return view;
    }
}
