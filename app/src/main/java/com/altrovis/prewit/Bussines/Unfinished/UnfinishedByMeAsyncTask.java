package com.altrovis.prewit.Bussines.Unfinished;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.altrovis.prewit.Entities.GlobalVariable;
import com.altrovis.prewit.Entities.WorkItem;

import java.util.ArrayList;

/**
 * Created by Wisnu on 10/03/2016.
 */
public class UnfinishedByMeAsyncTask extends AsyncTask<Void, Void, Void> {

    ProgressDialog progressDialog;
    Context context;
    UnfinishedAdapter adapter;
    ArrayList<WorkItem> listOfUnfinishedWorkItem;

    String url = GlobalVariable.UrlGetAllUnFinishedWorkItemsByMe;
    String param1 = "?username=";
    String param2 = "&accessToken=";
    String param3 = "&lastRetrievedID=";

    String completeURL = "";
    String username = "";
    String accessToken = "";

    public UnfinishedByMeAsyncTask(Context context, UnfinishedAdapter adapter){
        this.context = context;
        this.adapter = adapter;

        SharedPreferences login = context.getSharedPreferences("login", context.MODE_PRIVATE);
        username = login.getString("username", "");
        accessToken = login.getString("accesstoken","");

        completeURL = url.concat(param1).concat(username).concat(param2).concat(accessToken)
                .concat(param3).concat(String.valueOf(GlobalVariable.LastID_UnFinished_ByMe));

        progressDialog = new ProgressDialog(this.context);
        progressDialog.setMessage("Silahkan Tunggu");
        progressDialog.show();
    }

    protected void onPreExecute() {
        super.onPreExecute();

        if(!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            listOfUnfinishedWorkItem = UnfinishedHelper.getListOfWorkItem(completeURL);
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

        adapter.addAll(listOfUnfinishedWorkItem);
        adapter.notifyDataSetChanged();

        if(listOfUnfinishedWorkItem.size() > 0){
            int lastRetrivedID = listOfUnfinishedWorkItem.get(listOfUnfinishedWorkItem.size() - 1).getID();
            GlobalVariable.LastID_UnFinished_ByMe = lastRetrivedID;
        }


    }
}

