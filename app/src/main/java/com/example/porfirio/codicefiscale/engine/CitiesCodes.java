package com.example.porfirio.codicefiscale.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
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

	
	public CitiesCodes(){
	//leggi via internet da https://www.istat.it/it/archivio/6789
	
	//elabora via StringTokenizer il file csv 
	//https://www.istat.it/storage/codici-unita-amministrative/Elenco-comuni-italiani.csv

		//Apre una connessione con gli orari
		URL u = null;
		try {
			u = new URL("https://www.istat.it/storage/codici-unita-amministrative/Elenco-comuni-italiani.csv");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpURLConnection conn = null;
		InputStream in;
		try {
			//conn = Connection.connect(new URL(url));
			assert u != null;
			conn = (HttpURLConnection) u.openConnection();
			conn.setConnectTimeout(50000);
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		try {
			in = conn.getInputStream();
			BufferedReader r = new BufferedReader(new InputStreamReader(in));
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
