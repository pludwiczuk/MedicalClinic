package medicalclinic.models;

public class GodzPracy {

    private String godzOd;
    private String godzDo;
    private int liczWizyt;

    public GodzPracy(String godzOd, String godzDo, int liczWizyt) {
        this.godzOd = godzOd;
        this.godzDo = godzDo;
        this.liczWizyt = liczWizyt;
    }

    public String getGodzOd() {
        return godzOd;
    }

    public void setGodzOd(String godzOd) {
        this.godzOd = godzOd;
    }

    public String getGodzDo() {
        return godzDo;
    }

    public void setGodzDo(String godzDo) {
        this.godzDo = godzDo;
    }

    public int getLiczWizyt() {
        return liczWizyt;
    }

    public void setLiczWizyt(int liczWizyt) {
        this.liczWizyt = liczWizyt;
    }
    
}
