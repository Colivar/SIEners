
package model;

/**
 * Created by Jasper
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCSV {
    private String csvFileKlas = System.getProperty("user.dir") + "/CSV/klassen.csv";
    private String csvFileRooster = System.getProperty("user.dir") + "/CSV/rooster_C.csv";
    private ArrayList<String> voornaamList = new ArrayList<>();
    private ArrayList<String> achternaamList = new ArrayList<>();
    private ArrayList<String> tussenVoegselList = new ArrayList<>();
    private ArrayList<String> studentNummerList = new ArrayList<>();
    private ArrayList<String> klasCodeList = new ArrayList<>();
    private ArrayList<Klas> klasArrayList = new ArrayList<>();
    private ArrayList<Student> studentArrayList = new ArrayList<>();


    //De ArrayLists voor Rooster_C
    private ArrayList<String> datumArrayList = new ArrayList<>();
    private ArrayList<String> startTijdArrayList = new ArrayList<>();
    private ArrayList<String> eindTijdArrayList = new ArrayList<>();
    private ArrayList<String> vakCodeArrayList = new ArrayList<>();
    private ArrayList<String> docentArrayList = new ArrayList<>();
    private ArrayList<String> lokaalArrayList = new ArrayList<>();
    private ArrayList<Les> lesArrayList = new ArrayList<>();
    //klasArrayList hoort hierbij

//    public ArrayList<Docent> docentread(){
//        BufferedReader br = null;
//        String line = "";
//        String cvsSplitBy = ",";
//        try {
//            br = new BufferedReader(new FileReader(csvFileKlas));
//            while ((line = br.readLine()) != null) {
//                // use comma as separator
//                String[] country = line.split(cvsSplitBy);
//                docentArrayList.add(country[4]);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        for (int i = 0; i < docentArrayList.size(); i++) {
//            String klasCode = klasCodeList.get(i);
//            if (!docentArrayList.contains(new Docent(klasCode))) {
//                docentArrayList.add(new Klas(klasCode));
//            }
//        }
//        return docentArrayList;
//    }



    public ArrayList<Klas> klasRead(){
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFileRooster));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                klasCodeList.add(country[4]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < klasCodeList.size(); i++) {
            String klasCode = klasCodeList.get(i);
            if (!klasArrayList.contains(new Klas(klasCode))) {
                klasArrayList.add(new Klas(klasCode));
            }
        }
        return klasArrayList;
    }

    public ArrayList<Student> studentRead() {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFileKlas));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                studentNummerList.add(country[0]);
                voornaamList.add(country[3]);
                tussenVoegselList.add(country[2]);
                achternaamList.add(country[1]);
                klasCodeList.add(country[4]);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (int i = 0; i < studentNummerList.size(); i++) {
            String studentNummer = studentNummerList.get(i);
            String klasCode = klasCodeList.get(i);
            String voorNaam = voornaamList.get(i);
            String achterNaam = achternaamList.get(i);
            if (tussenVoegselList.get(i) != "") {
                String tussenvoegsel = tussenVoegselList.get(i);
                studentArrayList.add(new Student(studentNummer, voorNaam, achterNaam, tussenvoegsel));
            } else {
                studentArrayList.add(new Student(studentNummer, voorNaam, achterNaam));
            }

            for (Klas k : klasArrayList) {
                if (k.getKlasCode().equals(klasCode)) {
                    studentArrayList.get(i).setMijnKlas(k);
                }
            }
        }
        return studentArrayList;
    }

    public ArrayList<Les> readRoosterCSV(){
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFileRooster));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                datumArrayList.add(country[0]);
                startTijdArrayList.add(country[1]);
                eindTijdArrayList.add(country[2]);
                vakCodeArrayList.add(country[3]);
                docentArrayList.add(country[4]);
                lokaalArrayList.add(country[5]);
                klasCodeList.add(country[6]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // TODO: 23-3-2016 object docent en lokaal genereren uit csv 
        for (int i = 0; i <= studentNummerList.size(); i++) {
            Klas klas = klasArrayList.get(i);
            Docent docent = new Docent(docentArrayList.get(i));
            String startTijd = startTijdArrayList.get(i);
            String eindTijd = eindTijdArrayList.get(i);
            Lokaal lokaal = new Lokaal(lokaalArrayList.get(i));
            String datum = datumArrayList.get(i);
            lesArrayList.add(new Les(klas, docent, datum, startTijd, eindTijd, lokaal));
        }

        return lesArrayList;

    }
}
