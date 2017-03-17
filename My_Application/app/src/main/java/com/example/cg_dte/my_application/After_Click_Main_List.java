package com.example.cg_dte.my_application;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class After_Click_Main_List extends AppCompatActivity {

    Button btnShoall;
    ImageView imgTol;
    TextView edtSho;
    ListView listShow;
    TextView txtVlist;
    Button Show_Location;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after__click__main__list);

        btnShoall = (Button) findViewById(R.id.btnSho);
        edtSho = (TextView) findViewById(R.id.edtSho);
        imgTol = (ImageView) findViewById(R.id.imgTol);
        listShow = (ListView) findViewById(R.id.listShow);
        txtVlist = (TextView) findViewById(R.id.txtVlist);

        final Bundle bundle = getIntent().getExtras();
        final ArrayList<FacilityList> aaObj = (ArrayList<FacilityList>) bundle.getSerializable("array_list");
        bundle.getString("");
        String val = bundle.getString("what");
        if(val.equals("1"))
        {
            txtVlist.setText("List of Toilet");
        } else if(val.equals("6"))
            {
               txtVlist.setText("List of Stadium");
            }
        else {
                txtVlist.setText("List of Park");
            }



        BaseAdapter_for_ListShow baseAdapter_for_listShow = new BaseAdapter_for_ListShow(this, aaObj);
        listShow.setAdapter(baseAdapter_for_listShow);


        btnShoall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent item = new Intent(After_Click_Main_List.this, MapsActivity.class);

                item.putExtra("Array_list", aaObj);
                startActivity(item);
                After_Click_Main_List.this.finish();

            }
        });




        Show_Location = (Button) findViewById(R.id.Show_Location);
        Show_Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GPSTracker(After_Click_Main_List.this);
                if(gps.canGetLocation()){
                    double latitude= gps.getLatitude();
                    double longitude = gps.getLongitude();
                    Toast.makeText(getApplicationContext(),"your location is" +"\nlong: "+longitude,Toast.LENGTH_LONG).show();

                }else{
                    gps.showSettingsAlert();
                }

            }
        });

        listShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {


          /* // String use[]=bundle.getStringArray("some string");

            Intent intent = getIntent();
            String [] stringArray = intent.getStringArrayExtra("string-array");
            List<String> list = Arrays.asList(stringArray);

        if(list.contains("Toilet")){
                edtxtVlist.setText(getString("string-array",""));
            }

*/



            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(After_Click_Main_List.this, MapsActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("lon", (String.valueOf(((FacilityList) aaObj.get(position)).getLongitude())));
                bundle1.putString("lat", (String.valueOf(((FacilityList) aaObj.get(position)).getLatitude())));
                bundle1.putString("img", (String.valueOf(((FacilityList)aaObj.get(position)).getImage())));
                bundle1.putString("name", (String.valueOf(((FacilityList)aaObj.get(position)).getName())));

               /* if("name".equals("park")){
                    edtName.setText(prefs.getString("name",""));
                }
*/
                /*intent.putExtra("lat", ((FacilityList) aaObj.get(position)).getLatitude());
                intent.putExtra("img", ((FacilityList) aaObj.get(position)).getImage());*/
                intent.putExtra("bundle",bundle1);
                startActivity(intent);
                After_Click_Main_List.this.finish();

                /*String map = "http://maps.google.co.in/maps?q=" + "NIT Raipur";
                String uri = String.format(Locale.ENGLISH, map);
                Intent intent = new Intent(After_Click_Main_List.this, MapsActivity.class);
                startActivity(intent);*/
            }
        });

    }
}
