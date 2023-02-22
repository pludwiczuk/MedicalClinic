package medicalclinic.models;

import java.util.Vector;

public class Choroba {

    private int id;
    private String data;
    private String rozpoznanie;
    private Vector<String> leki;
    private String historiaChoroby;

    public Choroba() {
    }

    public Choroba(int id, String data, String rozpoznanie) {
        this.id = id;
        this.data = data;
        this.rozpoznanie = rozpoznanie;
    }

    public Choroba(int id, String data, String rozpoznanie, Vector<String> leki, String historiaChoroby) {
        this.id = id;
        this.data = data;
        this.rozpoznanie = rozpoznanie;
        this.leki = leki;
        this.historiaChoroby = historiaChoroby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRozpoznanie() {
        return rozpoznanie;
    }

    public void setRozpoznanie(String rozpoznanie) {
        this.rozpoznanie = rozpoznanie;
    }

    public Vector<String> getLeki() {
        return leki;
    }

    public void setLeki(Vector<String> leki) {
        this.leki = leki;
    }

    public String getHistoriaChoroby() {
        return historiaChoroby;
    }

    public void setHistoriaChoroby(String historiaChoroby) {
        this.historiaChoroby = historiaChoroby;
    }
    
}
