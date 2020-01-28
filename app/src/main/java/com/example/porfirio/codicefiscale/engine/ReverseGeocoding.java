package com.example.porfirio.codicefiscale.engine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class ReverseGeocoding {


    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String getCity(String lat, String lon, CitiesCodes cc) {
        //https://maps.googleapis.com/maps/api/geocode/json?&latlng=40.75,14.05
        //MY API key AIzaSyAXtPSk3UEy7HoU5jTPhQLxSk30RwEYrj8

        //lat="40.843107";
        //lon="14.226497";

        JSONObject jsonObject = null;
        InputStream is = null;
        try {
            is = new URL("https://api.bigdatacloud.net/data/reverse-geocode-client?latitude=" + lat + "&longitude=" + lon + "&localityLanguage=it").openStream();
            //is = new URL("https://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lon+"&key=AIzaSyAXtPSk3UEy7HoU5jTPhQLxSk30RwEYrj8").openStream();
            //is = new URL("https://maps.googleapis.com/maps/api/geocode/json?latlng=40.75,14.05&key=AIzaSyAXtPSk3UEy7HoU5jTPhQLxSk30RwEYrj8").openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonText = null;
        try {
            String trovata = "";
            jsonText = convertStreamToString(is);
            jsonObject = new JSONObject(jsonText);
            JSONObject results = (JSONObject) jsonObject.get("localityInfo");
            JSONArray adm = results.getJSONArray("administrative");
            Boolean ok = false;
            int i = adm.length() - 1;
            while (i >= 0 && !ok) {
                JSONObject city = adm.getJSONObject(i);
                String c = (String) city.get("name");
                for (int j = 0; j < CitiesCodes.cities.size() && !ok; j++)
                    if (CitiesCodes.cities.get(j).nome.contentEquals(c)) {
                        ok = true;
                        trovata = c;
                    }
                i--;
            }
            return trovata;

            //            JSONArray results=jsonObject.getJSONArray("results");
//            JSONObject adcomp = results.getJSONObject(0);
//            JSONArray adcomp2=adcomp.getJSONArray("address_components");
//            JSONObject city=adcomp2.getJSONObject(2);
//            return (String) city.get("long_name");

        } catch (JSONException e) {
            return "Roma";
        }

        //CERCA MEGLIO LA CITTA NEL JSON
    //return "Roma";
    }


}