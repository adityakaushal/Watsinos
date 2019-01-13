package com.example.vmac.WatBot;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by adityakaushal on 03/10/17.
 */

public class GetNearbyPlacesData extends AsyncTask<Object, String, String> {
    String googlePlacesData;
    GoogleMap maps;
    String Url;
    @Override
    protected String doInBackground(Object... objects) {

        maps = (GoogleMap) objects[0];
        Url = (String) objects[1];

        DownloadUrl dr = new DownloadUrl();
        try
        {
            googlePlacesData = dr.readUrl(Url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s) {
       List <HashMap<String, String >> nearbyPlaceList =null;
        DataParser parser =  new DataParser();
        nearbyPlaceList = parser.parse(s);
        showNearByPlaces(nearbyPlaceList);
    }

    private void showNearByPlaces(List<HashMap<String,String>> nearbyPlacesList)
    {
        for(int i = 0 ; i< nearbyPlacesList.size(); i++)
        {
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlaces = nearbyPlacesList.get(i);
            String placeName = googlePlaces.get("place_name");
            String vicinity = googlePlaces.get("vicinity");
            double lat = Double.parseDouble(googlePlaces.get("lat"));
            double lng =  Double .parseDouble(googlePlaces.get("lng"));


            LatLng latLng = new LatLng(lat,lng);
            markerOptions.position(latLng);
            markerOptions.title(placeName+":"+vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            maps.addMarker(markerOptions);
            maps.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            maps.animateCamera(CameraUpdateFactory.zoomTo(10));
        }
    }
}
