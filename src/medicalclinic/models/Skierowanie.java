package medicalclinic.models;

import java.util.Vector;

public class Skierowanie {
    private int id;
    private Pacjent pacjent;
    private Lekarz lekarz;
    private String dataSkierowania;
    private String rodzaj;
    private String specjalizacja;
    private String rozpoznanie;
    private Vector<String> listaBadan;
    private boolean czyWewnetrzne;

    public Skierowanie() {
    }

    public Skierowanie(int id, Pacjent pacjent, Lekarz lekarz, String dataSkierowania, String rodzaj, String specjalizacja, boolean czyWewnetrzne) {
        this.id = id;
        this.pacjent = pacjent;
        this.lekarz = lekarz;
        this.dataSkierowania = dataSkierowania;
        this.rodzaj = rodzaj;
        this.specjalizacja = specjalizacja;
        this.czyWewnetrzne = czyWewnetrzne;
    }

    public Skierowanie(int id, Pacjent pacjent, Lekarz lekarz, String dataSkierowania, String rodzaj, Vector<String> listaBadan, boolean czyWewnetrzne) {
        this.id = id;
        this.pacjent = pacjent;
        this.lekarz = lekarz;
        this.dataSkierowania = dataSkierowania;
        this.rodzaj = rodzaj;
        this.listaBadan = listaBadan;
        this.czyWewnetrzne = czyWewnetrzne;
    }

    public Skierowanie(int id, Pacjent pacjent, Lekarz lekarz, String dataSkierowania, String rodzaj, String rozpoznanie, Vector<String> listaBadan, boolean czyWewnetrzne) {
        this.id = id;
        this.pacjent = pacjent;
        this.lekarz = lekarz;
        this.dataSkierowania = dataSkierowania;
        this.rodzaj = rodzaj;
        this.rozpoznanie = rozpoznanie;
        this.listaBadan = listaBadan;
        this.czyWewnetrzne = czyWewnetrzne;
    }

    public Skierowanie(int id, Pacjent pacjent, Lekarz lekarz, String dataSkierowania, String rodzaj, String specjalizacja, String rozpoznanie, Vector<String> listaBadan, boolean czyWewnetrzne) {
        this.id = id;
        this.pacjent = pacjent;
        this.lekarz = lekarz;
        this.dataSkierowania = dataSkierowania;
        this.rodzaj = rodzaj;
        this.specjalizacja = specjalizacja;
        this.rozpoznanie = rozpoznanie;
        this.listaBadan = listaBadan;
        this.czyWewnetrzne = czyWewnetrzne;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pacjent getPacjent() {
        return pacjent;
    }

    public void setPacjent(Pacjent pacjent) {
        this.pacjent = pacjent;
    }

    public Lekarz getLekarz() {
        return lekarz;
    }

    public void setLekarz(Lekarz lekarz) {
        this.lekarz = lekarz;
    }

    public String getDataSkierowania() {
        return dataSkierowania;
    }

    public void setDataSkierowania(String dataSkierowania) {
        this.dataSkierowania = dataSkierowania;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public String getSpecjalizacja() {
        return specjalizacja;
    }

    public void setSpecjalizacja(String specjalizacja) {
        this.specjalizacja = specjalizacja;
    }

    public String getRozpoznanie() {
        return rozpoznanie;
    }

    public void setRozpoznanie(String rozpoznanie) {
        this.rozpoznanie = rozpoznanie;
    }

    public Vector<String> getListaBadan() {
        return listaBadan;
    }

    public void setListaBadan(Vector<String> listaBadan) {
        this.listaBadan = listaBadan;
    }

    public boolean isCzyWewnetrzne() {
        return czyWewnetrzne;
    }

    public void setCzyWewnetrzne(boolean czyWewnetrzne) {
        this.czyWewnetrzne = czyWewnetrzne;
    }
    
}
