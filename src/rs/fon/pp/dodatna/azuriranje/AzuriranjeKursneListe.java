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
import rs.fon.pp.dodatna.menjacnica.Valuta;
import rs.fon.pp.dodatna.util.ValutaJsonUtil;

public class AzuriranjeKursneListe {
	
	private static final String putanja = "data/valute.json";
	
	public LinkedList<Valuta> ucitajValute() {
		
		LinkedList<Valuta> valute = new LinkedList<Valuta>();
			
		try {
			FileReader reader = new FileReader(putanja);
			Gson gson = new GsonBuilder().create();
			JsonObject menjacnica = gson.fromJson(reader, JsonObject.class);
			JsonArray valuteNiz = menjacnica.get("valute").getAsJsonArray();
			for (int i = 0; i <valuteNiz.size() ; i++) {
				Valuta v = new Valuta();
				v.setKurs(valuteNiz.get(i).getAsJsonObject().get("kurs").getAsDouble());
				v.setNaziv(valuteNiz.get(i).getAsJsonObject().get("naziv").getAsString());
				valute.add(v);	
			}
			reader.close();

		} catch (Exception e) {
			System.out.println("Greska - " + e.getMessage());
		}
		return valute;		
	}
	
	public void upisiValute(LinkedList<Valuta> valute, GregorianCalendar datum) {
		try {
		
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("data/valute.json")));
		
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String valuteString = gson.toJson((JsonObject) ValutaJsonUtil.serijalizujValute(valute, datum));
			
			out.println(valuteString);
			out.close();
		} catch (Exception e) {
			System.out.println("Greska - " + e.getMessage());
		}
	}
	
	public void azurirajValute() {
		try {
			LinkedList<Valuta> valute = ucitajValute();
			String[] nazivi = new String[valute.size()];
			for (int i = 0; i < valute.size(); i++) {
				nazivi[i] = valute.get(i).getNaziv();
			}
			LinkedList<Valuta> azuriraneValute = JsonAPIRatesKomunikacija.vratiIznosKurseva(nazivi);
			valute.clear();
			valute = azuriraneValute;
			GregorianCalendar datum = new GregorianCalendar();
			upisiValute(valute, datum);
		} catch (Exception e) {
			System.out.println("Greska - " + e.getMessage());
		}
	}
}


