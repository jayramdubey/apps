package com.example.cg_dte.smart_study;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity  extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;


    EditText edtName, edtMob, edtEmail, edtAdd, edtUsrId, edtPass, edtCPass;
    RadioButton rdoBtnM, rdoBtnF;
    Button photoButton;
    ImageView imgV;
    SharedPreferences prefs;

    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        prefs = getSharedPreferences("LoginCredentials", MODE_PRIVATE);
        editor = prefs.edit();
        editor.apply();
        imgV = (ImageView) findViewById(R.id.imgV);
        Button photoButton = (Button) this.findViewById(R.id.photoButton);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cmraIntnt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cmraIntnt, CAMERA_REQUEST);

            }

        });


        /*if (!prefs.getString("Mob_No", "").equals("") && !prefs.getString("User_Id", "").equals("")) {
            Intent i = new Intent(MainActivity.this, Home_Main.class);
            startActivity(i);
            finish();
        }*/

        Button b1 = (Button) findViewById(R.id.BtnOK);
        edtName = (EditText) findViewById(R.id.edtName);
        edtMob = (EditText) findViewById(R.id.edtMob);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtAdd = (EditText) findViewById(R.id.edtAdd);
        edtUsrId = (EditText) findViewById(R.id.edtUsrId);
        edtPass = (EditText) findViewById(R.id.edtPass);
        edtCPass = (EditText) findViewById(R.id.edtCPass);
        rdoBtnM = (RadioButton) findViewById(R.id.rdoBtnM);
        rdoBtnF = (RadioButton) findViewById(R.id.rdoBtnF);

        /*final String s1 = edtPass.getText().toString();
        final String s2 = edtCPass.getText().toString();*/


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtName.getText().toString().equals("")) {
                    if (!edtMob.getText().toString().equals("")) {
                        if (edtMob.getText().toString().length() == 10) {
                            if (!edtEmail.getText().toString().equals("")) {
                                boolean a = isValidEmaillId(edtEmail.getText().toString());
                                if (a) {
                                    if (!edtAdd.getText().toString().equals("")) {
                                        if (!edtUsrId.getText().toString().equals("")) {
                                            if (!edtPass.getText().toString().equals("")) {
                                                if (!edtCPass.getText().toString().equals("")) {

                                                    if (edtPass.getText().toString().equals(edtCPass.getText().toString())) {
                                                        //Toast.makeText(MainActivity.this,"you are registered",Toast.LENGTH_LONG).show();
                                                        editor.putString("name", edtName.getText().toString());
                                                        editor.putString("mob", edtMob.getText().toString());
                                                        editor.putString("email", edtEmail.getText().toString());
                                                        editor.putString("add", edtAdd.getText().toString());
                                                        editor.putString("userId", edtUsrId.getText().toString());
                                                        editor.putString("pass", edtPass.getText().toString());
                                                        editor.putString("cpass", edtCPass.getText().toString());

                                                        if (rdoBtnM.isChecked())
                                                            editor.putString("gender", "M");
                                                        else
                                                            editor.putString("gender", "F");
                                                        editor.commit();
                                                        startActivity(new Intent(MainActivity.this, LoginClass.class));
                                                        MainActivity.this.finish();
                                                    } else {
                                                        Toast.makeText(MainActivity.this, "Email Id Is Not Correct", Toast.LENGTH_LONG).show();
                                                    }
                                                } else {
                                                    Toast.makeText(MainActivity.this, "Password and Confirm Password Is Not Equal.", Toast.LENGTH_LONG).show();
                                                }
                                            } else {
                                                Toast.makeText(MainActivity.this, "Mobile Number Should Have 10 Digits", Toast.LENGTH_LONG).show();
                                            }


                                            /*String regexStr = "^[0-9]$";
                                            String number = edtMob.getText().toString();*/

                                           /* if (edtMob.getText().toString().length() < 10 || number.length() > 13 || number.matches(regexStr) == false) {
                                                Toast.makeText(MainActivity.this, "Please enter " + "\n" + " valid phone number", Toast.LENGTH_SHORT).show();

                                                if (s1.equals(s2)) {


                                                    if(isValidEmaillId(edtEmail.getText().toString().trim())){
                                                        Toast.makeText(getApplicationContext(), "Valid Email Address.", Toast.LENGTH_SHORT).show();


                                                        Intent ob3 = new Intent(MainActivity.this, Home_Main.class);
                                                        startActivity(ob3);
                                                        MainActivity.this.finish();

                                                    }else{
                                                        Toast.makeText(getApplicationContext(), "InValid Email Address.", Toast.LENGTH_SHORT).show();
                                                    }

                                                } else {
                                                    Toast.makeText(MainActivity.this, "Passwor and cunfurm password is not same please enter the same .", Toast.LENGTH_LONG).show();


                                                }


                                            } else {
                                                Toast.makeText(MainActivity.this, "please fill the 10 digit Mob No.", Toast.LENGTH_LONG).show();

                                            }*/


                                        } else {
                                            Toast.makeText(MainActivity.this, "please fill the cunfirm Password", Toast.LENGTH_LONG).show();
                                        }

                                    } else {
                                        Toast.makeText(MainActivity.this, "please fill the Address", Toast.LENGTH_LONG).show();
                                    }

                                } else {
                                    Toast.makeText(MainActivity.this, "please Email is invalide", Toast.LENGTH_LONG).show();
                                }

                            } else {
                                Toast.makeText(MainActivity.this, "please fill the proper email Id", Toast.LENGTH_LONG).show();
                            }


                        } else {
                            Toast.makeText(MainActivity.this, "please fill the 10 digit Mobile no.", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(MainActivity.this, "please fill Mob. No.", Toast.LENGTH_LONG).show();

                    }

                } else {
                    Toast.makeText(MainActivity.this, "please fill Name", Toast.LENGTH_LONG).show();
                }

            }
        });


        if (!prefs.getString("name", "").equals("")) {
            if (prefs.getString("gender", "").equals("M")) {
                rdoBtnM.setChecked(true);

            } else {
                rdoBtnF.setChecked(true);

            }
            edtName.setText(prefs.getString("name", ""));
            edtMob.setText(prefs.getString("mob", ""));
            edtEmail.setText(prefs.getString("email", ""));
            edtAdd.setText(prefs.getString("add", ""));
            edtUsrId.setText(prefs.getString("userId", ""));
            edtPass.setText(prefs.getString("pass", ""));
            edtCPass.setText(prefs.getString("cpass", ""));

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            if (data != null) {
                ImageView image = (ImageView) this.findViewById(R.id.imgV);

                image.setImageBitmap(
                        BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
                );

            }
        }
    }


    private boolean isValidEmaillId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            if (data != null) {
                ImageView image = (ImageView) this.findViewById(R.id.imgV);
                image.setImageBitmap(
                        BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
                );

            }
        }
    }
}
