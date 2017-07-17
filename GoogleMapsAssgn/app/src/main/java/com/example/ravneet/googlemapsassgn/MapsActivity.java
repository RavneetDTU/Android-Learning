package com.example.ravneet.googlemapsassgn;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    EditText et_location, et_destination;
    Button btn_search, btn_mark;
    ZoomControls zoom;
    Double myLatitude = null;
    Double myLongitude = null;

    private GoogleApiClient googleApiClient;
    // For LocationRequest check depandency of location
    private LocationRequest locationRequest;

    public static final int MY_PERMISSION_FINE_LOCATION = 101;

    public static final String TAG = "Maps";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        googleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        locationRequest = new LocationRequest();
        locationRequest.setInterval(15 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        // for Virtual Devices Use HIGH_ACCURACY

        et_location = (EditText) findViewById(R.id.et_location);
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_mark = (Button) findViewById(R.id.btn_mark);
        zoom = (ZoomControls) findViewById(R.id.zoom);

        zoom.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });

        zoom.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });
        btn_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 LatLng mylocation = new LatLng(myLatitude, myLongitude);
                mMap.addMarker(new MarkerOptions().position(mylocation).title("My Location"));
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                List<Address> addressList = null;
//
//                String location = et_location.getText().toString().trim();
//                if(location != null || !location.equals("")){
//
//                    Geocoder geocoder = new Geocoder(getApplicationContext());
//                    try {
//                        addressList = geocoder.getFromLocationName(location,1);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    Address address = addressList.get(0);
//
//                    LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
//
//                    mMap.addMarker(new MarkerOptions().position(latLng).title("Marker On Your Location"));
//                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//                }

            }
        });
    }

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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        //.icon and further is for adding our own Marker At perticular Location.
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        // to Draw Polylines to map through given Coordinates.,use......
        mMap.addPolyline(new PolylineOptions().add()

        );
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        } else {
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        mMap.setMyLocationEnabled(true);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "This App Require Location Permission", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        requestLocationUpdate();
    }

    private void requestLocationUpdate() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, (com.google.android.gms.location.LocationListener) this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection Suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "onConnectionFailed: Error Code is: "+connectionResult.getErrorCode());
    }

    @Override
    public void onLocationChanged(Location location) {
        myLatitude = location.getLatitude();
        myLongitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(googleApiClient.isConnected()){
            requestLocationUpdate();
        }
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, (com.google.android.gms.location.LocationListener) this);
//        }
//    }

    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }
}
