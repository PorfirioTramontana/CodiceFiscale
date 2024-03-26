package com.example.porfirio.codicefiscale.model;

public class Data {
Integer d;
Integer m;
Integer a;

public Data(Integer d, Integer m, Integer a) {
	this.d = d;
	this.m = m;
	this.a = a;
}

    public boolean valida() {
        if ((d < 1) || (d > 31) || (m == 0) || (a <= 1582)) {
            return false;
        }

        Boolean bisestile = (a % 4 == 0);

        if (bisestile && (a % 100 == 0) && (a % 400 != 0)) {
            bisestile = false;
        }

        if (((m == 2) && (d > 29)) || ((m == 2) && (d == 29) &&!bisestile)) {
            return false;
        }

        if (((m == 4) || (m == 6) || (m == 9) || (m == 11)) && (d > 30)) {
            return false;
        }

        return true;
    }
    
	public int getDay() {
		return d;
	}

	public int getMonth() {
		return m;
	}

	public int getYear() {
		return a;
	}
}
