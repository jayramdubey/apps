package com.example.cg_dte.smart_study;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by Jay_Dubey on 2/20/2017.
 */
public class LoginClass extends AppCompatActivity {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        prefs = getSharedPreferences("LoginCredentials", MODE_PRIVATE);
        editor = prefs.edit();
        editor.apply();

        final EditText edtUserId,edtPass;

        boolean hasLoggedIn = prefs.getBoolean("hasLoggedIn", false);

        if(hasLoggedIn)
        {
            Intent i = new Intent(LoginClass.this, Home_Main.class);
            startActivity(i);
            finish();
        }





      /* if (!prefs.getString("userId", "").equals("") && !prefs.getString("pass", "").equals("")){

            Intent i = new Intent(LoginClass.this, Home_Main.class);
            startActivity(i);
            finish();
        }*/
        edtUserId = (EditText) findViewById(R.id.edtUserId);
        edtPass = (EditText) findViewById(R.id.edtPass);





        Button b1 = (Button) findViewById(R.id.BtnLogin );
        Button b2 = (Button) findViewById(R.id.BtnCancel);
        Button b3 = (Button) findViewById(R.id.BtnNewUser);
        b1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (!edtUserId.getText().toString().equals("")) {
                       if (!edtPass.getText().toString().equals("")) {

                          // Log.e("user id ","uid"+(prefs.getString("userId", "")));
                           if (edtUserId.getText().toString().equals(prefs.getString("userId", "")) && edtPass.getText().toString().equals(prefs.getString("pass", ""))) {
                               editor.putBoolean("hasLoggedIn", true);
                               editor.commit();
                               Intent ob3 = new Intent(LoginClass.this, Home_Main.class);
                               startActivity(ob3);
                               LoginClass.this.finish();
                           } else {
                               Toast.makeText(LoginClass.this, "UserId or Password Is Not Correct", Toast.LENGTH_LONG).show();

                           }


                       } else {
                           Toast.makeText(LoginClass.this, "Fill the proper Password first", Toast.LENGTH_LONG).show();
                       }
                   }
                     else {
                       Toast.makeText(LoginClass.this, "Fill the login Id first", Toast.LENGTH_LONG).show();

                   }

               }

           });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent ob1 = new Intent(LoginClass.this, MainActivity.class );
                    startActivity(ob1);
                    LoginClass.this.finish();
            }
        });

      b2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              LoginClass.this.finish();
          }
      });


    }

}
