package com.altrovis.prewit.Bussines.Unfinished;

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
 * Created by ricki on 3/8/2016.
 */
public class UnfinishedAdapter extends ArrayAdapter<WorkItem> {

    Context context;
    int resource;
    ArrayList<WorkItem> listOfUnfinished;
    DateFormat dateFormat;

    public UnfinishedAdapter(Context context, int resource, ArrayList<WorkItem> listOfUnfinished) {
        super(context, resource, listOfUnfinished);

        this.context = context;
        this.resource = resource;
        this.listOfUnfinished = listOfUnfinished;

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

        WorkItem workItem = listOfUnfinished.get(position);

        SharedPreferences login = context.getSharedPreferences("login", context.MODE_PRIVATE);
        textViewUsername.setText(login.getString("username", ""));
        textViewCreated.setText(dateFormat.format(workItem.getCreated()));

        textViewDescription.setText(workItem.getDescription());
        textViewProject.setText(workItem.getProjectName());
        textViewAssignedBy.setText(workItem.getAssignedBy());

        return view;
    }
}
