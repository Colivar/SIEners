package model;

import java.util.ArrayList;

public class PrIS {
    private ArrayList<Docent> deDocenten;
    private ArrayList<Student> deStudenten;
    public ArrayList<Klas> deKlassen;

    public ArrayList<Klas> getDeKlassen() {
            return deKlassen;
    }

    /**
     * De constructor maakt een set met standaard-data aan. Deze data
     * moet nog vervangen worden door gegevens die uit een bestand worden
     * ingelezen, maar dat is geen onderdeel van deze demo-applicatie!
     * <p>
     * De klasse PrIS (PresentieInformatieSysteem) heeft nu een meervoudige
     * associatie met de klassen Docent en Student. Uiteraard kan dit nog veel
     * verder uitgebreid en aangepast worden!
     * <p>
     * De klasse fungeert min of meer als ingangspunt voor het domeinmodel. Op
     * dit moment zijn de volgende methoden aanroepbaar:
     * <p>
     * String login(String gebruikersnaam, String wachtwoord)
     * Docent getDocent(String gebruikersnaam)
     * Student getStudent(String gebruikersnaam)
     * ArrayList<Student> getStudentenVanKlas(String klasCode)
     * <p>
     * Methode login geeft de rol van de gebruiker die probeert in te loggen,
     * dat kan 'studentRead', 'docent' of 'undefined' zijn! Die informatie kan gebruikt
     * worden om in de Polymer-GUI te bepalen wat het volgende scherm is dat getoond
     * moet worden.
     */
    public PrIS() {
        deDocenten = new ArrayList<Docent>();
        deStudenten = new ArrayList<Student>();
        deKlassen = new ArrayList<Klas>();

        ReadCSV readCSV = new ReadCSV();
        deKlassen = readCSV.klasRead();
        deStudenten =readCSV.studentRead();

        Docent d1 = new Docent("Wim", "geheim");
        Docent d2 = new Docent("Hans", "geheim");
        Docent d3 = new Docent("Jan", "geheim");

        d1.voegVakToe(new Vak("TCIF-V1AUI-15", "Analyse en User Interfaces"));
        d1.voegVakToe(new Vak("TICT-V1GP-15", "Group Project"));
        d1.voegVakToe(new Vak("TICT-V1OODC-15", "Object Oriented Design & Construction"));

        deDocenten.add(d1);
        deDocenten.add(d2);
        deDocenten.add(d3);
    }


    public String login(String gebruikersnaam, String wachtwoord) {
        for (Docent d : deDocenten) {
            if (d.getGebruikersNaam().equals(gebruikersnaam)) {
                if (d.controleerWachtwoord(wachtwoord)) {
                    return "docent";
                }
            }
        }

        for (Student s : deStudenten) {
            if (s.getGebruikersNaam().equals(gebruikersnaam)) {
                if (s.controleerWachtwoord(wachtwoord)) {
                    return "student";
                }
            }
        }

        return "undefined";
    }

    public Docent getDocent(String gebruikersnaam) {
        Docent resultaat = null;

        for (Docent d : deDocenten) {
            if (d.getGebruikersNaam().equals(gebruikersnaam)) {
                resultaat = d;
                break;
            }
        }

        return resultaat;
    }

    public Student getStudent(String gebruikersnaam) {
        Student resultaat = null;

        for (Student s : deStudenten) {
            if (s.getGebruikersNaam().equals(gebruikersnaam)) {
                resultaat = s;
                break;
            }
        }

        return resultaat;
    }

    public ArrayList<Student> getStudentenVanKlas(String klasCode) {
        ArrayList<Student> resultaat = new ArrayList<Student>();

        for (Student s : deStudenten) {
            if (s.getMijnKlas().getKlasCode().equals(klasCode)) {
                resultaat.add(s);
            }
        }

        return resultaat;
    }
}
