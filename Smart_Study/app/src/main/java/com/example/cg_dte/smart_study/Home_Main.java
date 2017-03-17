package com.example.cg_dte.smart_study;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Jay_Dubey on 2/20/2017.
 */
public class Home_Main extends AppCompatActivity {
    EditText edtPass, edtName;
    Button btnWell, BtnLogout, BtnPic;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
  //  private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtName = (EditText) findViewById(R.id.edtName);
        edtPass = (EditText) findViewById(R.id.edtPass);
        BtnPic = (Button) findViewById(R.id.BtnPic);


        prefs = getSharedPreferences("LoginCredentials", MODE_PRIVATE);
        editor = prefs.edit();
        editor.apply();

       /* boolean hasLoggedIn = prefs.getBoolean("hasLoggedIn", false);

        if(hasLoggedIn)
        {
            Intent i = new Intent(Home_Main.this, LoginClass.class);
            startActivity(i);
            finish();
        }*/

        BtnLogout = (Button) findViewById(R.id.BtnLogout);

        BtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putBoolean("hasLoggedIn", false);
                editor.commit();
                Intent ob3 = new Intent(Home_Main.this, LoginClass.class);
                startActivity(ob3);
                Home_Main.this.finish();


            }
        });
        if (!prefs.getString("name", "").equals("")) {
            edtPass.setText(prefs.getString("pass", ""));
            edtName.setText(prefs.getString("name", ""));

        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
   /* public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Home_Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }*/

    /*@Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }*/
}
