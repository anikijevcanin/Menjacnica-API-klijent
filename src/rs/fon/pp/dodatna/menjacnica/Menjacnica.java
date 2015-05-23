package rs.fon.pp.dodatna.menjacnica;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import com.google.gson.annotations.SerializedName;

public class Menjacnica {
	@SerializedName ("datum")
	private GregorianCalendar datum;
	
	@SerializedName("valute")
	private LinkedList<Valuta> valute;
	
	public GregorianCalendar getDatum() {
		return datum;
	}
	public void setDatum(GregorianCalendar datum) {
		this.datum = datum;
	}
	public LinkedList<Valuta> getValute() {
		return valute;
	}
	public void setValute(LinkedList<Valuta> valute) {
		this.valute = valute;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datum == null) ? 0 : datum.hashCode());
		result = prime * result + ((valute == null) ? 0 : valute.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menjacnica other = (Menjacnica) obj;
		if (datum == null) {
			if (other.datum != null)
				return false;
		} else if (!datum.equals(other.datum))
			return false;
		if (valute == null) {
			if (other.valute != null)
				return false;
		} else if (!valute.equals(other.valute))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Menjacnica [datum=" + datum + ", valute=" + valute + "]";
	}
	
}
