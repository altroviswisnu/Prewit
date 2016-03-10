package com.altrovis.prewit.Bussines.AddNewWorkItem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.altrovis.prewit.Entities.GlobalVariable;
import com.altrovis.prewit.Entities.ProjectMember;

/**
 * Created by Wisnu on 10/03/2016.
 */
public class GetAllProjectMembersAsyncTask extends AsyncTask<Void, Void, Void> {

    ProgressDialog progressDialog;
    Context context;
    ArrayAdapter<ProjectMember> projectMemberAdapter;

    String url = GlobalVariable.UrlGetAllProjectMembers;
    String param1 = "?projectID=";
    String param2 = "&username=";
    String param3 = "&accessToken";

    String completeURL = "";
    int projectID;
    String username = "";
    String accessToken = "";

    public GetAllProjectMembersAsyncTask(Context context, int projectID,
                                         ArrayAdapter<ProjectMember> projectMemberAdapter) {
        this.context = context;
        this.projectID = projectID;
        this.projectMemberAdapter = projectMemberAdapter;

        SharedPreferences login = context.getSharedPreferences("login", context.MODE_PRIVATE);
        username = login.getString("username", "");
        accessToken = login.getString("accesstoken","");

        completeURL = url.concat(param1).concat(String.valueOf(this.projectID));

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
            GlobalVariable.listOfProjectMembers = NewWorkItemHelper.getListOfProjectMember(completeURL);
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

        projectMemberAdapter.clear();
        projectMemberAdapter.addAll(GlobalVariable.listOfProjectMembers);
        projectMemberAdapter.notifyDataSetChanged();

    }
}