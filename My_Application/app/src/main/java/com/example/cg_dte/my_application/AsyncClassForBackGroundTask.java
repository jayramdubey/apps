package com.example.cg_dte.my_application;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class AsyncClassForBackGroundTask extends AsyncTask<String, Void, String> {
    ProgressDialog progressDialog;
    Context context;
    int action;
    static InputStream is;
    int message = 0;
    String payWay = "";
    public String response, status, application_id,
            reg_id;

    public AsyncClassForBackGroundTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please hold on .....");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        if (new CheckNet(context).isConnectingToInternet()) {
            message = 1;
            getListOfLocation(params[0]);
        } else {
            message = 0;
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
    }

    public void getListOfLocation(String id) {
        try {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", id));


            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://nagarnigamprojects.in/morraipur/websiervice/webservice/webser_get_facility_details.php");////
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();

            is = httpEntity.getContent();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();

            response = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer error", "error converting result" + e.toString());
        }

    }
}


