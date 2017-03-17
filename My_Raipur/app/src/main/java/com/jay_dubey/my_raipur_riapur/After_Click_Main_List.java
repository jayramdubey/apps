package com.jay_dubey.my_raipur_riapur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class After_Click_Main_List extends Activity {

    ImageView imgTol;
    TextView edtSho;
    ListView listShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after__click__main__list);

        edtSho = (TextView) findViewById(R.id.edtSho);
        imgTol = (ImageView) findViewById(R.id.imgTol);
        listShow = (ListView) findViewById(R.id.listShow);

        Bundle bundle = getIntent().getExtras();
        final ArrayList<FacilityList> aaObj = (ArrayList<FacilityList>) bundle.getSerializable("array_list");
        bundle.getString("");


        BaseAdapter_for_ListShow baseAdapter_for_listShow = new BaseAdapter_for_ListShow(this, aaObj);
        listShow.setAdapter(baseAdapter_for_listShow);


        listShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(After_Click_Main_List.this, Ggl_Map_Use.class);
                intent.putExtra("lon", ((FacilityList) aaObj.get(position)).getLongitude());
                intent.putExtra("lat", ((FacilityList) aaObj.get(position)).getLatitude());
                intent.putExtra("img", ((FacilityList) aaObj.get(position)).getImage());
                startActivity(intent);
                After_Click_Main_List.this.finish();

                Toast.makeText(After_Click_Main_List.this, "Hello", Toast.LENGTH_LONG).show();
            }
        });

    }
}
