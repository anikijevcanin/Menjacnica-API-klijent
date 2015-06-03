package rs.fon.pp.dodatna.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import rs.fon.pp.dodatna.menjacnica.Valuta;

public class JsonAPIRatesKomunikacija {
	
	private static final String to = "RSD";
	private static final String appKey = "jr-ba8999934fc5a7ab64a4872fb4ed9af7";
	private static final String jsonRatesURL = "http://jsonrates.com/get/";
	
	public static LinkedList<Valuta> vratiIznosKurseva(String[] nazivi) {
		LinkedList<Valuta> valute = new LinkedList<Valuta>();
		for (int i = 0; i < nazivi.length; i++) {
			String url = jsonRatesURL + "?" +
					"from=" + nazivi[i] +
					"&to=" + to +
					"&apiKey=" + appKey;
		
			try {
				String result = sendGet(url);
				Gson gson = new GsonBuilder().create();
				JsonObject jsonResult = (JsonObject) gson.fromJson(result, JsonObject.class);
				Valuta v = new Valuta();
				v.setKurs(jsonResult.get("rate").getAsDouble());
				v.setNaziv(nazivi[i]);
				valute.add(v);
			} catch (Exception e) {
				System.out.println("Greska - " + e.getMessage());
			}
		}
		return valute;
	}

	private static String sendGet(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		
		boolean endReading = false;
		String response = "";
		
		while (!endReading) {
			String s = in.readLine();
			
			if (s != null) {
				response += s;
			} else {
				endReading = true;
			}
		}
		in.close();
	
		return response.toString();
	}
}
