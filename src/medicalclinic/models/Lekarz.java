package medicalclinic.models;

import java.util.Vector;
import medicalclinic.DBConnection.DBConnection;

public class Lekarz {

    private int id;
    private String imie;
    private String nazwisko;
    private String dataUrodzenia;
    private String telefon;
    private String email;
    private String specjalizacja;
    private int nrPokoju;
    private Harmonogram harmonogram;
    private Harmonogram zajeteTerminy;
    private Vector<Wizyta> zaplanowaneWizyty;
    private String login;
    private String haslo;

    public Lekarz() {
    }

    public Lekarz(int id, String imie, String nazwisko, String dataUrodzenia, String telefon, String email, String specjalizacja, int nrPokoju, String login, String haslo) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.telefon = telefon;
        this.email = email;
        this.specjalizacja = specjalizacja;
        this.nrPokoju = nrPokoju;
        this.login = login;
        this.haslo = haslo;
    }

    public Lekarz(int id, String imie, String nazwisko, String dataUrodzenia, String telefon, String email, String specjalizacja, int nrPokoju, Harmonogram harmonogram, Harmonogram zajeteTerminy) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.telefon = telefon;
        this.email = email;
        this.specjalizacja = specjalizacja;
        this.nrPokoju = nrPokoju;
        this.harmonogram = harmonogram;
        this.zajeteTerminy = zajeteTerminy;
    }

    public Lekarz(int id, String imie, String nazwisko, String dataUrodzenia, String telefon, String email, String specjalizacja, int nrPokoju, Harmonogram harmonogram, Harmonogram zajeteTerminy, String login, String haslo) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.telefon = telefon;
        this.email = email;
        this.specjalizacja = specjalizacja;
        this.nrPokoju = nrPokoju;
        this.harmonogram = harmonogram;
        this.zajeteTerminy = zajeteTerminy;
        this.login = login;
        this.haslo = haslo;
    }

    public Lekarz(int id, String imie, String nazwisko, String dataUrodzenia, String telefon, String email, String specjalizacja, int nrPokoju, Harmonogram harmonogram, Harmonogram zajeteTerminy, Vector<Wizyta> zaplanowaneWizyty) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.telefon = telefon;
        this.email = email;
        this.specjalizacja = specjalizacja;
        this.nrPokoju = nrPokoju;
        this.harmonogram = harmonogram;
        this.zajeteTerminy = zajeteTerminy;
        this.zaplanowaneWizyty = zaplanowaneWizyty;
    }

    public Lekarz(int id, String imie, String nazwisko, String dataUrodzenia, String telefon, String email, String specjalizacja, int nrPokoju, Harmonogram harmonogram, Harmonogram zajeteTerminy, Vector<Wizyta> zaplanowaneWizyty, String login, String haslo) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.telefon = telefon;
        this.email = email;
        this.specjalizacja = specjalizacja;
        this.nrPokoju = nrPokoju;
        this.harmonogram = harmonogram;
        this.zajeteTerminy = zajeteTerminy;
        this.zaplanowaneWizyty = zaplanowaneWizyty;
        this.login = login;
        this.haslo = haslo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(String dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecjalizacja() {
        return specjalizacja;
    }

    public void setSpecjalizacja(String specjalizacja) {
        this.specjalizacja = specjalizacja;
    }

    public int getNrPokoju() {
        return nrPokoju;
    }

    public void setNrPokoju(int nrPokoju) {
        this.nrPokoju = nrPokoju;
    }

    public Harmonogram getHarmonogram() {
        return harmonogram;
    }

    public void setHarmonogram(Harmonogram harmonogram) {
        this.harmonogram = harmonogram;
    }

    public Harmonogram getZajeteTerminy() {
        return zajeteTerminy;
    }

    public void setZajeteTerminy(Harmonogram zajeteTerminy) {
        this.zajeteTerminy = zajeteTerminy;
    }

    public Vector<Wizyta> getZaplanowaneWizyty() {
        return zaplanowaneWizyty;
    }

    public void setZaplanowaneWizyty(Vector<Wizyta> zaplanowaneWizyty) {
        this.zaplanowaneWizyty = zaplanowaneWizyty;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public boolean zmienHaslo(String stareHaslo, String noweHaslo) {
        if (stareHaslo.equals(this.haslo)) {
            DBConnection db = DBConnection.getInstance();
            if (db.zmienHaslo("lekarz", this.id, noweHaslo).equals("true")) {
                this.haslo = noweHaslo;
                return true;
            }
        }
        return false;
    }

    public boolean odwolajWizyte(Wizyta wizyta) {
        return true;
    }

    public boolean wystawRecepte(Recepta r) {
        DBConnection db = DBConnection.getInstance();
        if(db.dodajRecepta(r)) {
            return true;
        }
        return false;
    }
    
    public boolean usunRecepte(int idRecepty) {
        DBConnection db = DBConnection.getInstance();
        if(db.usunRecepta(idRecepty)) {
            return true;
        }
        return false;
    }
    
    public boolean wystawSkierowanie(Skierowanie s) {
        DBConnection db = DBConnection.getInstance();
        if(db.dodajSkierowanie(s)) {
            return true;
        }
        return false;
    }
    
    public boolean usunSkierowanie(int idSkierowania) {
        DBConnection db = DBConnection.getInstance();
        if(db.usunSkierowanie(idSkierowania)) {
            return true;
        }
        return false;
    }
    
    public boolean dodajWpisDoKartyPacjenta(Choroba ch, int kartaId) {
        DBConnection db = DBConnection.getInstance();
        if(db.dodajChoroba(ch, kartaId)) {
            return true;
        }
        return false;
    }
    
    public boolean usunWpisZKartyPacjenta(int id) {
        DBConnection db = DBConnection.getInstance();
        if(db.usunChoroba(id)) {
            return true;
        }
        return false;
    }

    public void generujRaport() {

    }
}
