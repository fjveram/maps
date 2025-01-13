package com.example.maps;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    GoogleMap Mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Conectamos el fragment a google maps
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Mapa = googleMap;
        Mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        Mapa.getUiSettings().setZoomControlsEnabled(true);
        LatLng Quevedo = new LatLng(-1.02723, -79.46767);
        CameraUpdate camUpd1 =
                CameraUpdateFactory
                        .newLatLngZoom(Quevedo,
                                15);
        Mapa.animateCamera(camUpd1);
        /*LatLng Quevedo = new LatLng(-1.02723, -79.46767);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(Quevedo)
                .zoom(25)
                .bearing(45) //noreste arriba
                .tilt(90) //punto de vista de la cámara 70 grados
                .build();
        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);
        Mapa.animateCamera(camUpd3);*/
        PolylineOptions lineas = new
                PolylineOptions()
                .add(new LatLng(-1.026723904619399, -79.46750899825246))
                .add(new LatLng(-1.0294666067247356, -79.46842763240642))
                .add(new LatLng(-1.0297536335529969, -79.4634198003866))
                .add(new LatLng(-1.02710660738014, -79.46342617979045))


                .add(new LatLng(-1.026723904619399, -79.46750899825246
                ));
        lineas.width(8);
        lineas.color(Color.RED);
        Mapa.addPolyline(lineas);

        //Agregar marcadores
        Mapa.addMarker(new MarkerOptions().position(Quevedo).title("Centro de QUevedo"));
        Mapa.addMarker(new MarkerOptions()
                .position(new LatLng(-1.026723904619399, -79.46750899825246)));
        Mapa.addMarker(new MarkerOptions()
                .position(new LatLng(-1.0294666067247356, -79.46842763240642)));
        Mapa.addMarker(new MarkerOptions()
                .position(new LatLng(-1.0297536335529969, -79.4634198003866)));
        Mapa.addMarker(new MarkerOptions()
                .position(new LatLng(-1.02710660738014, -79.46342617979045)));

        //Capturar click
        Mapa.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                Mapa.addMarker(new MarkerOptions()
                        .position(new LatLng(point.latitude, point.longitude)));
            }
        });
        //pinta circulo
        CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(-1.02723, -79.46767))
                .radius(5000) //En Metros
                .strokeColor(Color.RED)
                .fillColor(Color.argb(50, 150, 50, 50));
        Circle circulo = Mapa.addCircle(circleOptions);
    }


}