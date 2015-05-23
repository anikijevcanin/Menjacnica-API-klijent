package rs.fon.pp.dodatna.azuriranje;


import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rs.fon.pp.dodatna.api.JsonAPIRatesKomunikacija;
import rs.fon.pp.dodatna.menjacnica.Menjacnica;
import rs.fon.pp.dodatna.menjacnica.Valuta;
import rs.fon.pp.dodatna.util.ValutaJsonUtil;

public class AzuriranjeKursneListe {
	
	private static final String putanja = "data/valute.json";
	
	public static LinkedList<Valuta> ucitajValute(Menjacnica m) {
		
		LinkedList<Valuta> valute = new LinkedList<Valuta>();
			
		try {
			FileReader reader = new FileReader(putanja);
			Gson gson = new GsonBuilder().create();
			JsonObject menjacnica = gson.fromJson(reader, JsonObject.class);
			valute = ValutaJsonUtil.parseValute((JsonArray) menjacnica.get("valute"));	

		} catch (Exception e) {
			System.out.println("Greska - " + e.getMessage());
		}
		return valute;		
	}
	
	public static void upisiValute(LinkedList<Valuta> valute, GregorianCalendar datum) {
		JsonArray valuteJson = ValutaJsonUtil.serijalizujValute(valute, datum);
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("data/valute.json")));
		
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String valuteString = gson.toJson(valuteJson);
			
			out.println(valuteString);
			out.close();
		} catch (Exception e) {
			System.out.println("Greska - " + e.getMessage());
		}
	}
	
	public static void azurirajValute(Menjacnica m, JsonAPIRatesKomunikacija komunikacija) {
		try {
			LinkedList<Valuta> valute = ucitajValute(m);
			String[] nazivi = new String[valute.size()];
			for (int i = 0; i < valute.size(); i++) {
				nazivi[i] = valute.get(i).getNaziv();
			}
			valute.clear();
			valute = komunikacija.vratiIznosKurseva(nazivi);
			upisiValute(valute, new GregorianCalendar());
		} catch (Exception e) {
			System.out.println("Greska - " + e.getMessage());
		}
	}
}

