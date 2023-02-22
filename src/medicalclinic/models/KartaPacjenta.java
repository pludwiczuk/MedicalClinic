package medicalclinic.models;

import java.util.Vector;

public class KartaPacjenta {

    private int id;
    private int wzrost;
    private int waga;
    private Vector<Choroba> choroby;
    private Vector<Recepta> wystawioneRecepty;
    private Vector<Skierowanie> skierowania;
    private Vector<Zabieg> zabiegi;

    public KartaPacjenta() {
    }

    public KartaPacjenta(int id, int wzrost, int waga) {
        this.id = id;
        this.wzrost = wzrost;
        this.waga = waga;
    }

    public KartaPacjenta(int id, int wzrost, int waga, Vector<Choroba> choroby) {
        this.id = id;
        this.wzrost = wzrost;
        this.waga = waga;
        this.choroby = choroby;
    }

    public KartaPacjenta(int id, int wzrost, int waga, Vector<Choroba> choroby, Vector<Recepta> wystawioneRecepty) {
        this.id = id;
        this.wzrost = wzrost;
        this.waga = waga;
        this.choroby = choroby;
        this.wystawioneRecepty = wystawioneRecepty;
    }

    public KartaPacjenta(int id, int wzrost, int waga, Vector<Choroba> choroby, Vector<Recepta> wystawioneRecepty, Vector<Skierowanie> skierowania) {
        this.id = id;
        this.wzrost = wzrost;
        this.waga = waga;
        this.choroby = choroby;
        this.wystawioneRecepty = wystawioneRecepty;
        this.skierowania = skierowania;
    }

    public KartaPacjenta(int id, int wzrost, int waga, Vector<Choroba> choroby, Vector<Recepta> wystawioneRecepty, Vector<Skierowanie> skierowania, Vector<Zabieg> zabiegi) {
        this.id = id;
        this.wzrost = wzrost;
        this.waga = waga;
        this.choroby = choroby;
        this.wystawioneRecepty = wystawioneRecepty;
        this.skierowania = skierowania;
        this.zabiegi = zabiegi;
    }

    public Vector<Zabieg> getZabiegi() {
        return zabiegi;
    }

    public void setZabiegi(Vector<Zabieg> zabiegi) {
        this.zabiegi = zabiegi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWzrost() {
        return wzrost;
    }

    public void setWzrost(int wzrost) {
        this.wzrost = wzrost;
    }

    public int getWaga() {
        return waga;
    }

    public void setWaga(int waga) {
        this.waga = waga;
    }

    public Vector<Choroba> getChoroby() {
        return choroby;
    }

    public void setChoroby(Vector<Choroba> choroby) {
        this.choroby = choroby;
    }

    public Vector<Recepta> getWystawioneRecepty() {
        return wystawioneRecepty;
    }

    public void setWystawioneRecepty(Vector<Recepta> wystawioneRecepty) {
        this.wystawioneRecepty = wystawioneRecepty;
    }

    public Vector<Skierowanie> getSkierowania() {
        return skierowania;
    }

    public void setSkierowania(Vector<Skierowanie> skierowania) {
        this.skierowania = skierowania;
    }

}
