package rs.fon.pp.dodatna.main;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import rs.fon.pp.dodatna.azuriranje.AzuriranjeKursneListe;
import rs.fon.pp.dodatna.menjacnica.SviKurseviZaDatum;
import rs.fon.pp.dodatna.menjacnica.Valuta;

public class Main {

	public static void main(String[] args) {
		AzuriranjeKursneListe azuriranje = new AzuriranjeKursneListe();
		LinkedList<Valuta> valute = new LinkedList<Valuta>();
		
		Valuta v1 = new Valuta();
		v1.setKurs(121.3434);
		v1.setNaziv("EUR");
		Valuta v2 = new Valuta();
		v2.setKurs(121.3434);
		v2.setNaziv("USD");
		Valuta v3 = new Valuta();
		v3.setKurs(121.3434);
		v3.setNaziv("CAD");
		Valuta v4 = new Valuta();
		v4.setKurs(121.3434);
		v4.setNaziv("GBP");
		
		valute.add(v1);
		valute.add(v2);
		valute.add(v3);
		valute.add(v4);
		
		azuriranje.azurirajValute();
		

		
		
		
		
	}

}
