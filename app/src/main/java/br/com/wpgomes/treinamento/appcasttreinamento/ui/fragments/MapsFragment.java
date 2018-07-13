package br.com.wpgomes.treinamento.appcasttreinamento.ui.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import br.com.wpgomes.treinamento.appcasttreinamento.R;
import br.com.wpgomes.treinamento.appcasttreinamento.ui.activities.PlaceActivity;

/**
 * Created by wgomes on 11/07/16.
 */

public class MapsFragment extends Fragment {
    private GoogleMap googleMap;
    private LatLng latLng;

    public static Fragment newInstance() {
        MapsFragment fragment = new MapsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_places, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.google_maps);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MapsFragment.this.googleMap = googleMap;
                setup();

            }
        });
    }

    private void setup() {

        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        if (ContextCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        }
        googleMap.setOnInfoWindowClickListener(onInfoWindowClickListener);
        UiSettings settings = googleMap.getUiSettings();
        settings.setMyLocationButtonEnabled(true);
        settings.setZoomControlsEnabled(true);
        settings.setCompassEnabled(true);
        latLng = new LatLng(10, 10);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng).title("Cast Group")
                .snippet("Rua Alvarenga Peixoto, 295 - Lourdes - Belo Horizonte/MG");
        googleMap.addMarker(markerOptions);

        PolygonOptions line = new PolygonOptions();
        line.add(latLng);
        line.add(new LatLng(16,11));
        googleMap.addPolygon(line);



    }

    private GoogleMap.OnInfoWindowClickListener
            onInfoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {

            Intent intent = new Intent(getActivity(), PlaceActivity.class);
            intent.putExtra("lat", latLng.latitude);
            intent.putExtra("lng", latLng.longitude);
            startActivity(intent);

        }
    };
}
