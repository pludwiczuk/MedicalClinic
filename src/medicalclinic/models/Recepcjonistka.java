package medicalclinic.models;

import java.util.Vector;
import medicalclinic.DBConnection.DBConnection;

public class Recepcjonistka {

    private int id;
    private String imie;
    private String nazwisko;
    private String dataUrodzenia;
    private String telefon;
    private String email;
    private String login;
    private String haslo;

    public Recepcjonistka() {
    }

    public Recepcjonistka(int id, String imie, String nazwisko, String dataUrodzenia, String telefon, String email) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.telefon = telefon;
        this.email = email;
    }

    public Recepcjonistka(int id, String imie, String nazwisko, String dataUrodzenia, String telefon, String email, String login, String haslo) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.telefon = telefon;
        this.email = email;
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
            if (db.zmienHaslo("recepcjonistka", this.id, noweHaslo).equals("true")) {
                this.haslo = noweHaslo;
                return true;
            }
        }
        return false;
    }

    public boolean dodajPacjenta(Pacjent pacjent) {
        DBConnection db = DBConnection.getInstance();
        return db.dodajPacjent(pacjent);
    }

    public boolean usunPacjenta(int id) {
        DBConnection db = DBConnection.getInstance();
        return db.usunUzytkownika("pacjent", id);
    }

    public boolean modyfikujDanePacjenta(Pacjent pacjent) {
        return true;
    }

    public boolean umowPacjentaNaWizyte(Wizyta w) {
        DBConnection db = DBConnection.getInstance();
        return db.dodajWizyta(w);
    }

    public boolean odwolajWizytePacjenta(int idWizyty) {
        DBConnection db = DBConnection.getInstance();
        return db.usunUzytkownika("wizyta", idWizyty);
    }
    
}
