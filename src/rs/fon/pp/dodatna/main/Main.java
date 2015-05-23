package rs.fon.pp.dodatna.main;

import rs.fon.pp.dodatna.api.JsonAPIRatesKomunikacija;
import rs.fon.pp.dodatna.azuriranje.AzuriranjeKursneListe;
import rs.fon.pp.dodatna.menjacnica.Menjacnica;

public class Main {

	public static void main(String[] args) {
		Menjacnica m = new Menjacnica();
		JsonAPIRatesKomunikacija kom = new JsonAPIRatesKomunikacija();
		
		AzuriranjeKursneListe.azurirajValute(m, kom);
	}

}
