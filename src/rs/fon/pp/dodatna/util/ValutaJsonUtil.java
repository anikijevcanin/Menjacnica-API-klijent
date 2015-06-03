package rs.fon.pp.dodatna.util;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import rs.fon.pp.dodatna.menjacnica.Valuta;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ValutaJsonUtil {
	
	public static JsonObject serijalizujValute(LinkedList<Valuta> valute, GregorianCalendar datum) {
		JsonArray valuteJson = new JsonArray();
		JsonObject objectJson = new JsonObject();
		
		String d = datum.get(GregorianCalendar.DAY_OF_MONTH) + "." +
				(datum.get(GregorianCalendar.MONTH)-1) + "." +
				(datum.get(GregorianCalendar.YEAR));

		for (int i = 0; i < valute.size(); i++) {
			Valuta v = valute.get(i);
			JsonObject valutaJson = new JsonObject();
			valutaJson.addProperty("naziv", v.getNaziv());
			valutaJson.addProperty("kurs", v.getKurs());
			valuteJson.add(valutaJson);
		}
		objectJson.addProperty("datum", d);
		objectJson.add("valute", valuteJson);
		
		
		return objectJson ;
	}
	
	public static LinkedList<Valuta> parseValute(JsonArray valuteJson) {
		
		LinkedList<Valuta> valute = new LinkedList<Valuta>();
		
		for (int i = 0; i <valuteJson.size(); i++) {
			JsonObject movieJson = (JsonObject) valuteJson.get(i);
			Valuta v = new Valuta();
			v.setNaziv(movieJson.get("naziv").getAsString());
			v.setKurs(movieJson.get("kurs").getAsDouble());
			
			valute.add(v);
		}
		
		return valute;
	}
}
