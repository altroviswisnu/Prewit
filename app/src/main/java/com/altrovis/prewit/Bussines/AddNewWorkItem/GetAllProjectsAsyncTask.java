package com.altrovis.prewit.Bussines.AddNewWorkItem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.altrovis.prewit.Entities.GlobalVariable;
import com.altrovis.prewit.Entities.Project;

/**
 * Created by Wisnu on 10/03/2016.
 */
public class GetAllProjectsAsyncTask extends AsyncTask<Void, Void, Void> {

    ProgressDialog progressDialog;
    Context context;
    ArrayAdapter<Project> projectAdapter;

    String url = GlobalVariable.UrlGetAllProjects;
    String param1 = "?username=";

    String completeURL = "";
    String username = "";

    public GetAllProjectsAsyncTask(Context context, ArrayAdapter<Project> projectAdapter) {

        this.context = context;
        this.projectAdapter = projectAdapter;

        SharedPreferences login = context.getSharedPreferences("login", context.MODE_PRIVATE);
        username = login.getString("username", "");
        completeURL = url.concat(param1).concat(String.valueOf(username));

        progressDialog = new ProgressDialog(this.context);
        progressDialog.setMessage("Silahkan Tunggu");
        progressDialog.show();
    }

    protected void onPreExecute() {
        super.onPreExecute();

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            GlobalVariable.listOfProjects = NewWorkItemHelper.getListOfProject(completeURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        projectAdapter.clear();
        projectAdapter.addAll(GlobalVariable.listOfProjects);
        projectAdapter.notifyDataSetChanged();

    }
}