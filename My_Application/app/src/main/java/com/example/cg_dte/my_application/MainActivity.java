package com.example.cg_dte.my_application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GPSTracker gpsTracker;
    TypedArray array;
    String[] use ={"Toilet", "Stadium", "Park"};
    ArrayList<NameValue> arrayList= new ArrayList<>();
    ListView listFram;
    String id;


    SharedPreferences pref;
    SharedPreferences.Editor editor;
/*
    String url = "http://nagarnigamprojects.in/morraipur/websiervice/webservice/webser_get_facility_details.php";
    Gson gson;
    AsyncHttpClient client;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("LoginCredential", MODE_PRIVATE);
        editor = pref.edit();
        editor.apply();

        /*client = new AsyncHttpClient();
        client.get(mainActivity.this, url,new AsyncHttpResponseHandler()){
*/


        array = getResources().obtainTypedArray(R.array.arrPIC);
        listFram = (ListView) findViewById(R.id.listFram);

        for(int i=0; i<3; i++) {

            NameValue nameValue = new NameValue(0, array.getResourceId(i, -1), use[i]);
            arrayList.add(i, nameValue);


        }
            /*Intent intent = new Intent(MainActivity.this, After_Click_Main_List.class);
            Bundle bundle1 = new Bundle();
            bundle1.putStringArray("some string",use);
            intent.putExtra("bundle",bundle1);
            startActivity(intent);
            MainActivity.this.finish();*/


       /*  *//*Intent intent = new Intent(MainActivity.this, After_Click_Main_List.class);
            intent.putExtra("array_list", arrayList);
            startActivity(intent);*//*


        Intent intent = new Intent(MainActivity.this, After_Click_Main_List.class);
        intent.putExtra("string-array", arrayList);
        startActivity(intent);


       *//* if(arrayList.equals("Toilet")){
            txtVlist.setText(prefs.getString("name",""));
        }*/
        BaseAdapterForList baseAdapterForList = new BaseAdapterForList(this, arrayList);
        listFram.setAdapter(baseAdapterForList);
        gpsTracker = new GPSTracker(this);

        listFram.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(gpsTracker.canGetLocation){
                    switch (position){
                        case 0:
                            goToAsync("1");
                            break;
                        // txtVlist.setText(prefs.getString("name",""));
                        case 1:
                            goToAsync("6");
                            break;
                        case 2:
                            goToAsync("8");
                            break;
                    }
                }else{
                    gpsTracker.showSettingsAlert();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(gpsTracker.canGetLocation){

        }else{
            Toast.makeText(this,"Please On Your Location", Toast.LENGTH_LONG).show();
        }
    }

    public void goToAsync(final String id){
        new AsyncClassForBackGroundTask(this){
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(super.message==0){
                    if(super.progressDialog.isShowing()){
                        super.progressDialog.dismiss();

                    }
                }else if(super.message==1){
                    int image;
                    if(id.equals("1")){
                        image = R.drawable.toilet;
                    }else if(id.equals("6")){
                        image = R.drawable.stadium;
                    }else{
                        image = R.drawable.park;
                    }
                    if(super.progressDialog.isShowing()){
                        super.progressDialog.dismiss();
                    }
                    ArrayList<FacilityList> arrayListFacility= new ArrayList<>();
                    try {
                        JSONObject jsonObject = new JSONObject(super.response);
                        if(jsonObject.getString("status").equals("true")){
                            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
                            for(int i=0; i<jsonArray.length();i++){

                                //arrayList.add(0, arrayListFacility );
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                FacilityList facilityList = new FacilityList(i, image, Double.valueOf(jsonObject1.getString("longitude")),
                                        Double.valueOf(jsonObject1.getString("latitude")), jsonObject1.getString("details_name"), jsonObject1.getString("address"));


                                arrayListFacility.add(i, facilityList);

                            }

                            Intent intent = new Intent(MainActivity.this, After_Click_Main_List.class);
                            intent.putExtra("what",id);
                            intent.putExtra("array_list", arrayListFacility);
                            startActivity(intent);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }.execute(id);
    }
}
