package medicalclinic.models;

import medicalclinic.DBConnection.DBConnection;

public class Admin {

    private int id;
    private String imie;
    private String nazwisko;
    private String dataUrodzenia;
    private String telefon;
    private String email;
    private String login;
    private String haslo;

    public Admin() {
    }

    public Admin(int id, String imie, String nazwisko, String dataUrodzenia, String telefon, String email) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.telefon = telefon;
        this.email = email;
    }

    public Admin(int id, String imie, String nazwisko, String dataUrodzenia, String telefon, String email, String login, String haslo) {
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
            if (db.zmienHaslo("admin", this.id, noweHaslo).equals("true")) {
                this.haslo = noweHaslo;
                return true;
            }
        }
        return false;
    }

    public void generujRaport() {

    }

    public boolean dodajAdmina(Admin admin) {
        DBConnection db = DBConnection.getInstance();
        return db.dodajAdmin(admin);
    }

    public boolean dodajLekarza(Lekarz lekarz) {
        DBConnection db = DBConnection.getInstance();
        return db.dodajLekarz(lekarz);
    }

    public boolean dodajRecepcjonistke(Recepcjonistka recepc) {
        DBConnection db = DBConnection.getInstance();
        return db.dodajRecepcjonistka(recepc);
    }

    public boolean dodajPielegniarke(Pielegniarka pielegniarka) {
        DBConnection db = DBConnection.getInstance();
        return db.dodajPielegniarka(pielegniarka);
    }
    
    public boolean usunAdmina(int id) {
        DBConnection db = DBConnection.getInstance();
        return db.usunUzytkownika("admin", id);
    }
    public boolean usunLekarza(int id) {
        DBConnection db = DBConnection.getInstance();
        return db.usunUzytkownika("lekarz", id);
    }
    public boolean usunRecepcjonistke(int id) {
        DBConnection db = DBConnection.getInstance();
        return db.usunUzytkownika("recepcjonistka", id);
    }
    public boolean usunPielegniarke(int id) {
        DBConnection db = DBConnection.getInstance();
        return db.usunUzytkownika("pielegniarka", id);
    }
}
