package com.altrovis.prewit.Bussines.Unfinished;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by ricki on 3/8/2016.
 */
public class UnfinishedAdapter extends ArrayAdapter<String> {

    Context context;
    int resource;
    ArrayList<String> listOfUnfinished;

    public UnfinishedAdapter(Context context, int resource, ArrayList<String> listOfUnfinished) {
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
