package com.example.porfirio.codicefiscale.GUI;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.porfirio.codicefiscale.R;
import com.example.porfirio.codicefiscale.controller.CitiesCodes;
import com.example.porfirio.codicefiscale.controller.Engine;
import com.example.porfirio.codicefiscale.model.Person;
import com.example.porfirio.codicefiscale.controller.ReverseGeocoding;

// COMMENTO DI PROVA
public class MainActivity extends Activity {
    private CitiesCodes cc;
    private Person person = new Person();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Lettura dei codici delle città da file
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        //Lettura della posizione attuale
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.NETWORK_PROVIDER;
        // Or use LocationManager.GPS_PROVIDER
        Location lastKnownLocation;
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            this.requestPermissions( new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET}, 1 );
        }
        lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
        String lat="";
        String lon="";
        String citta="";
        cc = new CitiesCodes(getAssets());
        if (lastKnownLocation!=null) {
            lat = Double.toString(lastKnownLocation.getLatitude());
            lon = Double.toString(lastKnownLocation.getLongitude());
            citta = ReverseGeocoding.getCity(lat, lon, cc);
        }

        final EditText editNome = findViewById(R.id.txtNome);
        final EditText editCognome = findViewById(R.id.txtCognome);
        final TextView txtCodice = findViewById(R.id.txtCodice);
        final Spinner editSesso = findViewById(R.id.sesso);
        final EditText btnDataDiNascita = findViewById(R.id.dataDiNascita);
        final Spinner editLuogo = findViewById(R.id.luogoDiNascita);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item);

        for (int i = 0; i < cc.getCities().size(); i++)
            adapter.add(cc.getCities().get(i).getNome());

        editLuogo.setAdapter(adapter);

        for (int i = 0; i < editLuogo.getCount(); i++) {
            if (adapter.getItem(i).equals(citta)) {
                editLuogo.setSelection(i);
            }
        }

        editLuogo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                person.setBornCity(cc.getCities().get(pos).getNome());
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        final DatePickerDialog dpd=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                person.setData(dayOfMonth,month+1,year);
                btnDataDiNascita.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        },1980,1,1);

        btnDataDiNascita.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO: Apri dialog di scelta del tempo DatePickerDIalog
                dpd.show();
            }
        });


        final ArrayAdapter<String> adapterSesso = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item);
        adapterSesso.add("M");
        adapterSesso.add("F");
        editSesso.setAdapter(adapterSesso);
        editSesso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                person.setSex(adapterSesso.getItem(pos));
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button btnCalcola = findViewById(R.id.buttonCalcola);
        btnCalcola.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.dismiss();
                    }
                });

                person.setSurname(editCognome.getText().toString());
                person.setName(editNome.getText().toString());
                //genere, luogo e data di nascita sono già stati settati
                Engine engine=new Engine(person,cc);
                String messaggio="";
                if (engine.valida(person.getSurname(),person.getName(),person.getSex(),person.getData().getDay(),person.getData().getMonth(),person.getData().getYear(),person.getBornCity(),messaggio)) {
                    txtCodice.setText(engine.getCode());
                    dialog.setMessage(engine.getCode());
                } else {
                    dialog.setMessage(messaggio);
                }
                dialog.show();
            }
        });



    }

}
