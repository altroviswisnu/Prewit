package com.altrovis.prewit.Bussines.Unfinished;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.altrovis.prewit.Bussines.Finished.FinishedAdapter;

/**
 * Created by Wisnu on 10/03/2016.
 */
public class UnfinishedByMeAsyncTask extends AsyncTask<Void, Void, Void> {

    ProgressDialog progressDialog;
    Context context;
    FinishedAdapter adapter;

    private UnfinishedByMeAsyncTask(Context context, FinishedAdapter adapter){

        this.context = context;
        this.adapter = adapter;

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

    }
}

