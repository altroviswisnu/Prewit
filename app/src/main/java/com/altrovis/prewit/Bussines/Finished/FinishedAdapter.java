package com.altrovis.prewit.Bussines.Finished;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.altrovis.prewit.Entities.WorkItem;
import com.altrovis.prewit.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Wisnu on 10/03/2016.
 */
public class FinishedAdapter extends ArrayAdapter<WorkItem> {

    Context context;
    int resource;
    ArrayList<WorkItem> listOfFinished;
    DateFormat dateFormat;

    public FinishedAdapter(Context context, int resource, ArrayList<WorkItem> listOfFinished) {
        super(context, resource, listOfFinished);

        this.context = context;
        this.resource = resource;
        this.listOfFinished = listOfFinished;

        dateFormat = new SimpleDateFormat("EEEE, d MMMM y HH:mm", new Locale("id", "ID"));
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(resource, viewGroup, false);
        }

        TextView textViewUsername = (TextView) view.findViewById(R.id.TextViewUserName);
        TextView textViewCreated = (TextView) view.findViewById(R.id.TextViewCreated);
        TextView textViewDescription = (TextView) view.findViewById(R.id.TextViewDescription);
        TextView textViewProject = (TextView) view.findViewById(R.id.TextViewProjectName);
        TextView textViewAssignedBy = (TextView) view.findViewById(R.id.TextViewAssignedBy);

        WorkItem workItem = listOfFinished.get(position);

        SharedPreferences login = context.getSharedPreferences("login", context.MODE_PRIVATE);
        textViewUsername.setText(login.getString("username", ""));
        textViewCreated.setText(dateFormat.format(workItem.getCreated()));

        textViewDescription.setText(workItem.getDescription());
        textViewProject.setText(workItem.getProjectName());
        textViewAssignedBy.setText(workItem.getAssignedBy());

        return view;
    }
}
