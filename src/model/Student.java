package model;

public class Student {
	private String studentNummer;
	private String voorNaam, tussenVoegsel, achterNaam, wachtwoord;
	private Klas mijnKlas;

	public Student(String studentNummer, String voorNaam, String achterNaam, String tussenVoegsel) {
		this.studentNummer = studentNummer;
		this.voorNaam = voorNaam;
		this.tussenVoegsel = tussenVoegsel;
		this.achterNaam = achterNaam;
		wachtwoord = "test";
	}

	public Student(String studentNummer, String voorNaam, String achterNaam) {
		this.studentNummer = studentNummer;
		this.voorNaam = voorNaam;
		this.achterNaam = achterNaam;
		wachtwoord = "";
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	public String getGebruikersNaam() {
		return studentNummer;
	}

	public boolean controleerWachtwoord(String wachtwoord) {
		return wachtwoord.equals(this.wachtwoord);
	}

	public void setMijnKlas(Klas mijnKlas) {
		this.mijnKlas = mijnKlas;
	}

	public String getAchterNaam() {
		return achterNaam;
	}

	public String getVoorNaam() {
		return voorNaam;
	}

	public Klas getMijnKlas() {
		return mijnKlas;
	}
}
