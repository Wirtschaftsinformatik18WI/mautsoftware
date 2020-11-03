package java;

public class Standortdaten {
	
	private String standort;
	private String kennzeichen;
	private String kfzLand;
	private String datum;
	private String zeit;
	private Peilsendertyp typ;
	
	public enum Peilsendertyp {
		Abfahrt, Durchfahrt
	}
	
	public void senden() {
		
	}
	
	public void convertToJson() {
		
		
	}
	
	
	//---------------------Getter und Setter-------------------------
	
	public String getStandort() {
		return standort;
	}
	public void setStandort(String standort) {
		this.standort = standort;
	}
	public String getKennzeichen() {
		return kennzeichen;
	}
	public void setKennzeichen(String kennzeichen) {
		this.kennzeichen = kennzeichen;
	}
	public String getKfzLand() {
		return kfzLand;
	}
	public void setKfzLand(String kfzLand) {
		this.kfzLand = kfzLand;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getZeit() {
		return zeit;
	}
	public void setZeit(String zeit) {
		this.zeit = zeit;
	}
	
	

}
