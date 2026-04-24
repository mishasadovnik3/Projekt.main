package paket_1;

import java.sql.ResultSet;

public class Themen {
	private DBZugriff aktuelleZugirff = new DBZugriff();
	private String tier;
	private String Informatik;
	private String Allgemeinwissen;
	public String getTier() {
		return tier;
	}
	public void setTier(String tier) {
		this.tier = tier;
	}
	public String getInformatik() {
		return Informatik;
	}
	public void setInformatik(String informatik) {
		Informatik = informatik;
	}
	public String getAllgemeinwissen() {
		return Allgemeinwissen;
	}
	public void setAllgemeinwissen(String allgemeinwissen) {
		Allgemeinwissen = allgemeinwissen;
	}
	public DBZugriff getAktuelleZugirff() {
		return aktuelleZugirff;
	}
	
	public ResultSet Wortablesen()
	{ResultSet rsWort;
	String mSQL;
	
	mSQL = "";
	System.out.println(mSQL);
	rsWort = getAktuelleZugirff().lesen(mSQL);
	return rsWort;	
	};
	
}
