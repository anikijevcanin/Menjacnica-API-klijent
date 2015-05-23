package rs.fon.pp.dodatna.menjacnica;

public class Valuta {
	
	private String naziv;
	private double kurs;
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		if(naziv.isEmpty() || naziv == null)
			throw new RuntimeException("Naziv valute ne sme biti prazan String ili null objekat.");
		this.naziv = naziv;
	}
	public double getKurs() {
		return kurs;
	}
	public void setKurs(double kurs) {
		if(kurs <= 0)
			throw new RuntimeException("Kurs mora biti pozitivan broj.");
		this.kurs = kurs;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(kurs);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
		return result;
	}
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj instanceof Valuta) {
			Valuta v = (Valuta) obj;
			double pom = v.getKurs() - kurs;
			if(v.getNaziv().indexOf(naziv) != -1 && Math.abs(pom) <= 0.00001)
				return true;
		}
		return false;
	}
	
	public String toString() {
		return "Valuta [naziv=" + naziv + ", kurs=" + kurs + "]"; 
	}	
}
