package com.altrovis.prewit.Bussines.Logout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.altrovis.prewit.ActivityHome;
import com.altrovis.prewit.ActivityLogin;
import com.altrovis.prewit.Bussines.GlobalFunction;
import com.altrovis.prewit.Entities.GlobalVariable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Wisnu on 10/03/2016.
 */
public class LogoutAsyncTask extends AsyncTask<Void, Void, JSONObject> {

    ProgressDialog progressDialog;
    ActivityHome context;

    String url = GlobalVariable.UrlLogout;
    String param1 = "?username=";
    String param2 = "&accessToken=";

    String username = "";
    String accessToken = "";
    String completeUrl = "";

    private LogoutAsyncTask(ActivityHome context){

        this.context = context;

        SharedPreferences login = context.getSharedPreferences("login", context.MODE_PRIVATE);
        username = login.getString("username", "");
        accessToken = login.getString("accessToken", "");

        progressDialog = new ProgressDialog(this.context);
        progressDialog.setMessage("Silahkan Tunggu");
        progressDialog.show();

        completeUrl = url.concat(param1).concat(username).concat(param2).concat(accessToken);
    }

    protected void onPreExecute() {
        super.onPreExecute();

        if(!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        try {
            return GlobalFunction.GetJSONObject(completeUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        if(result != null){
            try {
                boolean isSuccessful = result.getBoolean("IsSuccessful");
                if(isSuccessful){

                    SharedPreferences logoutClear = context.getSharedPreferences("login", context.MODE_PRIVATE);
                    logoutClear.edit().remove("username").commit();
                    logoutClear.edit().remove("accesstoken").commit();

                    Intent intent = new Intent(context, ActivityLogin.class);
                    context.startActivity(intent);
                    context.finish();
                } else {
                    String errorMessage = result.getString("ErrorMessage");
                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG);
                }
            } catch (JSONException e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG);
            }
        }

    }
}

