package com.example.cg_dte.my_application;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends Activity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double dlat;
    double dlon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        Bundle bundle = getIntent().getBundleExtra("bundle");
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




    }

   /* public Bitmap resizeMapIcons(String img,int width, int height) {

       
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(img, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, 25, height, false);
        return resizedBitmap;
        mMap.addMarker(new MarkerOptions()
                .title("New Marker")
                .snippet("Check out this place.")
                .position(chelsea).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("image_name", 100, 100))));
    }*/

   /* @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        dlat = Double.parseDouble(getIntent().getStringExtra("lat"));
        dlon = Double.parseDouble(getIntent().getStringExtra("lon"));

        Toast.makeText(getApplicationContext(), "dlat=" + dlat + ", dlong=" + dlon, Toast.LENGTH_SHORT).show();

    }
*/




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        /*dlat = Double.parseDouble(getIntent().getStringExtra("lat"));
        dlon=Double.parseDouble(getIntent().getStringExtra("lon"));
        */

        GPSTracker gpsTracker = new GPSTracker(this);
        LatLng myLocation = new LatLng(gpsTracker.latitude,gpsTracker.longitude);
        //Drawable circleDrawable = getResources().getDrawable(Integer.valueOf(img));
        //BitmapDescriptor markerIcon = getMarkerIconFromDrawable(circleDrawable);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation,15));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        mMap.getUiSettings().setZoomGesturesEnabled(true);

        mMap.addMarker(new MarkerOptions().position(myLocation).title("My Location"));



        if(getIntent().hasExtra("bundle")){
            Bundle bundle = getIntent().getBundleExtra("bundle");
            String lat = bundle.getString("lat");
            String lon = bundle.getString("lon");
            String img = bundle.getString("img");
            String name = bundle.getString("name");

            //dlat = Double.parseDouble(getIntent().getStringExtra("lat"));
            //dlon = Double.parseDouble(getIntent().getStringExtra("lon"));







            // Add a marker in Raipur and move the camera
            LatLng raipur = new LatLng(Double.valueOf(lat), Double.valueOf(lon));
            Drawable circleDrawable = getResources().getDrawable(Integer.valueOf(img));
            BitmapDescriptor markerIcon = getMarkerIconFromDrawable(circleDrawable);

       /* mMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.906991, 12.453360))
                .title("My Marker")
                .icon(markerIcon)
        );*/



            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(raipur,15));
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
            mMap.moveCamera(CameraUpdateFactory.newLatLng(raipur));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            mMap.getUiSettings().setZoomGesturesEnabled(true);

            mMap.addMarker(new MarkerOptions().position(raipur).title(name)
                    .icon(markerIcon));


            // create marker
            MarkerOptions marker = new MarkerOptions().position(new LatLng(Double.valueOf(lat), Double.valueOf(lon))).title("Hello Maps");

            //googleMap.addMarker(marker);

        }else{
            final ArrayList<FacilityList> aaObj = (ArrayList<FacilityList>) getIntent().getSerializableExtra("Array_list");
            for(int i=0; i<aaObj.size(); i++){
                LatLng raipur = new LatLng(Double.valueOf(((FacilityList)aaObj.get(i)).getLatitude()), Double.valueOf(((FacilityList)aaObj.get(i)).getLongitude()));
                Drawable circleDrawable = getResources().getDrawable(Integer.valueOf(((FacilityList)aaObj.get(i)).getImage()));
                BitmapDescriptor markerIcon = getMarkerIconFromDrawable(circleDrawable);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(raipur,15));
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
                mMap.moveCamera(CameraUpdateFactory.newLatLng(raipur));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
                mMap.getUiSettings().setZoomGesturesEnabled(true);

                mMap.addMarker(new MarkerOptions().position(raipur).title(((FacilityList)aaObj.get(i)).getName())).setIcon(markerIcon);
            }
        }

    }

    public void drawMarker() {

        Drawable circleDrawable = getResources().getDrawable(R.drawable.cam);
        BitmapDescriptor markerIcon = getMarkerIconFromDrawable(circleDrawable);

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.906991, 12.453360))
                .title("My Marker")
                .icon(markerIcon)

        );
    }


    private BitmapDescriptor getMarkerIconFromDrawable(Drawable drawable) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}


