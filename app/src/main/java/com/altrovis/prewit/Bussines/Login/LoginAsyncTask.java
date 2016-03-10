package com.altrovis.prewit.Bussines.Login;

import android.app.ProgressDialog;
import android.content.Context;
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
 * Created by ricki on 3/8/2016.
 */
public class LoginAsyncTask extends AsyncTask<Void, Void, JSONObject> {

    ProgressDialog progressDialog;
    ActivityLogin context;

    String url = GlobalVariable.UrlLogin;
    String param1 = "?username=";
    String param2 = "&password=";

    String username = "";
    String password = "";

    String completeUrl = "";

    public LoginAsyncTask(ActivityLogin context, String username, String password){

        this.context = context;
        this.username = username;
        this.password = password;

        progressDialog = new ProgressDialog(this.context);
        progressDialog.setMessage("Silahkan Tunggu");
        progressDialog.show();

        completeUrl = url.concat(param1).concat(username).concat(param2).concat(password);
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

                    String accessToken = result.getString("AccessToken");
                    SharedPreferences.Editor editor = context.getSharedPreferences(
                            "login", Context.MODE_PRIVATE).edit();
                    editor.putString("username", username);
                    editor.putString("accesstoken", accessToken);
                    editor.commit();


                    Intent intent = new Intent(context, ActivityHome.class);
                    context.startActivity(intent);
                    context.finish();
                } else {
                    String errorMessage = result.getString("ErrorMessage");
                    Toast.makeText(context,errorMessage, Toast.LENGTH_LONG);
                }
            } catch (JSONException e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG);
            }
        }

    }
}
