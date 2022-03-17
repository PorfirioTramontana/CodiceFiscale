package com.example.porfirio.codicefiscale.engine;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

//https://goo.gl/3amGB4
//Scarico codici delle città
//Modifica Due

//Commento di giuliano

@SuppressWarnings("serial")
public class CitiesCodes {
    public static ArrayList<Citta> cities = new ArrayList<Citta>();

	
	public CitiesCodes(AssetManager assets){
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(assets.open("Elenco-comuni-italiani.csv")));
			String s=r.readLine();s = r.readLine();s = r.readLine();
			do {
				s = r.readLine();
				if (s != null){
					StringTokenizer st0 = new StringTokenizer( s, ";" );
					if (st0.hasMoreTokens()){
						for (int i=0;i<5;i++) st0.nextToken();
                        String strCitta = st0.nextToken();
                        Citta c = new Citta();
                        c.nome = strCitta;
						for (int i=0;i<12;i++) st0.nextToken();
						String codice=st0.nextToken();
                        c.codice = codice;
                        cities.add(c);
					}
					}
			} while (s!=null);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Comparator<Citta> comparator = new Comparator<Citta>() {
            @Override
            public int compare(Citta c1, Citta c2) {
                return c1.nome.compareTo(c2.nome);
            }
        };
        cities.sort(comparator);


	}


}
