package com.example.porfirio.codicefiscale.controller;

import android.content.res.AssetManager;

import com.example.porfirio.codicefiscale.model.Citta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;



@SuppressWarnings("serial")
public class CitiesCodes {
    public ArrayList<Citta> cities = new ArrayList<Citta>();

	public ArrayList<Citta> getCities() {
		return cities;
	}
	
	public CitiesCodes(AssetManager assets) {
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(assets.open("Elenco-comuni-italiani.csv")));
			String s = r.readLine();
			s = r.readLine();
			s = r.readLine();
			do {
				s = r.readLine();
				if (s != null) {
					StringTokenizer st0 = new StringTokenizer(s, ";");
					if (st0.hasMoreTokens()) {
						for (int i = 0; i < 5; i++) st0.nextToken();
						String strCitta = st0.nextToken();
						for (int i = 0; i < 12; i++) st0.nextToken();
						String strCodice = st0.nextToken();
						cities.add(new Citta(strCitta, strCodice));
					}
				}
			} while (s != null);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		public String getCode(String city){
			for (int i=0;i<cities.size();i++){
				if (cities.get(i).getNome().equals(city)) return cities.get(i).getCodice();
			}
			return "";
		}

	}

//        Comparator<Citta> comparator = new Comparator<Citta>() {
//            @Override
//            public int compare(Citta c1, Citta c2) {
//                return c1.nome.compareTo(c2.nome);
//            }
//        };
//        cities.sort(comparator);




