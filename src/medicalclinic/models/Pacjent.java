package medicalclinic.models;

import java.util.Vector;
import medicalclinic.DBConnection.DBConnection;

public class Pacjent {

    private int id;
    private String imie;
    private String nazwisko;
    private String nazwiskoRodowe;
    private String dataUrodzenia;
    private String telefon;
    private String email;
    private String pesel;
    private String miejsceUrodzenia;
    private String adres;
    private KartaPacjenta karta;
    private Vector<Wizyta> umowioneWizyty;
    private Vector<Wizyta> odbyteWizyty;
    private String plec;
    private String login;
    private String haslo;

    public Pacjent() {
    }

    public Pacjent(int id, String imie, String nazwisko, String nazwiskoRodowe, String dataUrodzenia, String telefon, String email, String pesel, String miejsceUrodzenia, String adres, KartaPacjenta karta, String plec) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nazwiskoRodowe = nazwiskoRodowe;
        this.dataUrodzenia = dataUrodzenia;
        this.telefon = telefon;
        this.email = email;
        this.pesel = pesel;
        this.miejsceUrodzenia = miejsceUrodzenia;
        this.adres = adres;
        this.karta = karta;
        this.plec = plec;
    }

    public Pacjent(int id, String imie, String nazwisko, String nazwiskoRodowe, String dataUrodzenia, String telefon, String email, String pesel, String miejsceUrodzenia, String adres, KartaPacjenta karta, Vector<Wizyta> umowioneWizyty, Vector<Wizyta> odbyteWizyty, String plec) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nazwiskoRodowe = nazwiskoRodowe;
        this.dataUrodzenia = dataUrodzenia;
        this.telefon = telefon;
        this.email = email;
        this.pesel = pesel;
        this.miejsceUrodzenia = miejsceUrodzenia;
        this.adres = adres;
        this.karta = karta;
        this.umowioneWizyty = umowioneWizyty;
        this.odbyteWizyty = odbyteWizyty;
        this.plec = plec;
    }

    public Pacjent(int id, String imie, String nazwisko, String nazwiskoRodowe, String dataUrodzenia, String telefon, String email, String pesel, String miejsceUrodzenia, String adres, KartaPacjenta karta, String plec, String login, String haslo) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nazwiskoRodowe = nazwiskoRodowe;
        this.dataUrodzenia = dataUrodzenia;
        this.telefon = telefon;
        this.email = email;
        this.pesel = pesel;
        this.miejsceUrodzenia = miejsceUrodzenia;
        this.adres = adres;
        this.karta = karta;
        this.plec = plec;
        this.login = login;
        this.haslo = haslo;
    }

    public Pacjent(int id, String imie, String nazwisko, String nazwiskoRodowe, String dataUrodzenia, String telefon, String email, String pesel, String miejsceUrodzenia, String adres, KartaPacjenta karta, Vector<Wizyta> umowioneWizyty, Vector<Wizyta> odbyteWizyty, String plec, String login, String haslo) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nazwiskoRodowe = nazwiskoRodowe;
        this.dataUrodzenia = dataUrodzenia;
        this.telefon = telefon;
        this.email = email;
        this.pesel = pesel;
        this.miejsceUrodzenia = miejsceUrodzenia;
        this.adres = adres;
        this.karta = karta;
        this.umowioneWizyty = umowioneWizyty;
        this.odbyteWizyty = odbyteWizyty;
        this.plec = plec;
        this.login = login;
        this.haslo = haslo;
    }

    public Pacjent(int id, String imie, String nazwisko, String nazwiskoRodowe, String dataUrodzenia, String telefon, String email, String pesel, String miejsceUrodzenia, String adres, String plec, String login, String haslo) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nazwiskoRodowe = nazwiskoRodowe;
        this.dataUrodzenia = dataUrodzenia;
        this.telefon = telefon;
        this.email = email;
        this.pesel = pesel;
        this.miejsceUrodzenia = miejsceUrodzenia;
        this.adres = adres;
        this.plec = plec;
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

    public String getNazwiskoRodowe() {
        return nazwiskoRodowe;
    }

    public void setNazwiskoRodowe(String nazwiskoRodowe) {
        this.nazwiskoRodowe = nazwiskoRodowe;
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

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getMiejsceUrodzenia() {
        return miejsceUrodzenia;
    }

    public void setMiejsceUrodzenia(String miejsceUrodzenia) {
        this.miejsceUrodzenia = miejsceUrodzenia;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public KartaPacjenta getKarta() {
        return karta;
    }

    public void setKarta(KartaPacjenta karta) {
        this.karta = karta;
    }

    public Vector<Wizyta> getUmowioneWizyty() {
        return umowioneWizyty;
    }

    public void setUmowioneWizyty(Vector<Wizyta> umowioneWizyty) {
        this.umowioneWizyty = umowioneWizyty;
    }

    public Vector<Wizyta> getOdbyteWizyty() {
        return odbyteWizyty;
    }

    public void setOdbyteWizyty(Vector<Wizyta> odbyteWizyty) {
        this.odbyteWizyty = odbyteWizyty;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
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
            if (db.zmienHaslo("pacjent", this.id, noweHaslo).equals("true")) {
                this.haslo = noweHaslo;
                return true;
            }
        }
        return false;
    }

    public boolean odwolajWizyte(Wizyta wizyta) {
        return true;
    }

    public boolean potwierdzWizyte(Wizyta wizyta) {
        return true;
    }
}
