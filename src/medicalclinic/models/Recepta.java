package medicalclinic.models;

import java.util.Vector;

public class Recepta {

    private int id;
    private Pacjent pacjent;
    private Lekarz lekarz;
    private String dataWystawienia;
    private String nrRecepty;
    private Vector<String> leki;
    private boolean czyZrealizowana;

    public Recepta() {
    }

    public Recepta(int id, Pacjent pacjent, Lekarz lekarz, String dataWystawienia, String nrRecepty, Vector<String> leki, boolean czyZrealizowana) {
        this.id = id;
        this.pacjent = pacjent;
        this.lekarz = lekarz;
        this.dataWystawienia = dataWystawienia;
        this.nrRecepty = nrRecepty;
        this.leki = leki;
        this.czyZrealizowana = czyZrealizowana;
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

    public String getDataWystawienia() {
        return dataWystawienia;
    }

    public void setDataWystawienia(String dataWystawienia) {
        this.dataWystawienia = dataWystawienia;
    }

    public String getNrRecepty() {
        return nrRecepty;
    }

    public void setNrRecepty(String nrRecepty) {
        this.nrRecepty = nrRecepty;
    }

    public Vector<String> getLeki() {
        return leki;
    }

    public void setLeki(Vector<String> leki) {
        this.leki = leki;
    }

    public boolean isCzyZrealizowana() {
        return czyZrealizowana;
    }

    public void setCzyZrealizowana(boolean czyZrealizowana) {
        this.czyZrealizowana = czyZrealizowana;
    }
    
}
