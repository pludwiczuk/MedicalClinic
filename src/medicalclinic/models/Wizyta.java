package medicalclinic.models;

public class Wizyta {

    private int id;
    private Pacjent pacjent;
    private Lekarz lekarz;
    private String typWizyty;
    private int nrPokoju;
    private String data;
    private String godz;
    private int numerek;
    private boolean czyPotwierdzona;
    private boolean czyOdwolana;
    private boolean czyOdbyta;
    private String opis;

    public Wizyta() {
    }

    public Wizyta(int id, Pacjent pacjent, Lekarz lekarz, String typWizyty, String data, String godz, boolean czyPotwierdzona, boolean czyOdwolana, boolean czyOdbyta) {
        this.id = id;
        this.pacjent = pacjent;
        this.lekarz = lekarz;
        this.typWizyty = typWizyty;
        this.data = data;
        this.godz = godz;
        this.czyPotwierdzona = czyPotwierdzona;
        this.czyOdwolana = czyOdwolana;
        this.czyOdbyta = czyOdbyta;
    }

    public Wizyta(int id, Pacjent pacjent, Lekarz lekarz, String typWizyty, int nrPokoju, String data, String godz, int numerek, boolean czyPotwierdzona, boolean czyOdwolana, boolean czyOdbyta, String opis) {
        this.id = id;
        this.pacjent = pacjent;
        this.lekarz = lekarz;
        this.typWizyty = typWizyty;
        this.nrPokoju = nrPokoju;
        this.data = data;
        this.godz = godz;
        this.numerek = numerek;
        this.czyPotwierdzona = czyPotwierdzona;
        this.czyOdwolana = czyOdwolana;
        this.czyOdbyta = czyOdbyta;
        this.opis = opis;
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

    public String getTypWizyty() {
        return typWizyty;
    }

    public void setTypWizyty(String typWizyty) {
        this.typWizyty = typWizyty;
    }

    public int getNrPokoju() {
        return nrPokoju;
    }

    public void setNrPokoju(int nrPokoju) {
        this.nrPokoju = nrPokoju;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getGodz() {
        return godz;
    }

    public void setGodz(String godz) {
        this.godz = godz;
    }

    public int getNumerek() {
        return numerek;
    }

    public void setNumerek(int numerek) {
        this.numerek = numerek;
    }

    public boolean isCzyPotwierdzona() {
        return czyPotwierdzona;
    }

    public void setCzyPotwierdzona(boolean czyPotwierdzona) {
        this.czyPotwierdzona = czyPotwierdzona;
    }

    public boolean isCzyOdwolana() {
        return czyOdwolana;
    }

    public void setCzyOdwolana(boolean czyOdwolana) {
        this.czyOdwolana = czyOdwolana;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public boolean isCzyOdbyta() {
        return czyOdbyta;
    }

    public void setCzyOdbyta(boolean czyOdbyta) {
        this.czyOdbyta = czyOdbyta;
    }
    
}
