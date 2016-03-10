package com.altrovis.prewit.Bussines.Finished;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.altrovis.prewit.Entities.WorkItem;

import java.util.ArrayList;

/**
 * Created by Wisnu on 10/03/2016.
 */
public class FinishedAdapter extends ArrayAdapter<WorkItem> {

    Context context;
    int resource;
    ArrayList<WorkItem> listOfUnfinished;

    public FinishedAdapter(Context context, int resource, ArrayList<WorkItem> listOfUnfinished) {
        super(context, resource, listOfUnfinished);

        this.context = context;
        this.resource = resource;
        this.listOfUnfinished = listOfUnfinished;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(resource, viewGroup, false);
        }

        return view;
    }
}
