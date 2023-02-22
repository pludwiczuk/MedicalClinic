package medicalclinic.models;

public class Zabieg {
    private int id;
    private Pacjent pacjent;
    private Pielegniarka pielegniarka;
    private Skierowanie skierowanie;
    private String data;
    private String rodzajZabiegu;
    private String opisZabiegu;

    public Zabieg() {
    }

    public Zabieg(int id, Pacjent pacjent, Pielegniarka pielegniarka, Skierowanie skierowanie, String data, String rodzajZabiegu, String opisZabiegu) {
        this.id = id;
        this.pacjent = pacjent;
        this.pielegniarka = pielegniarka;
        this.skierowanie = skierowanie;
        this.data = data;
        this.rodzajZabiegu = rodzajZabiegu;
        this.opisZabiegu = opisZabiegu;
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

    public Pielegniarka getPielegniarka() {
        return pielegniarka;
    }

    public void setPielegniarka(Pielegniarka pielegniarka) {
        this.pielegniarka = pielegniarka;
    }

    public Skierowanie getSkierowanie() {
        return skierowanie;
    }

    public void setSkierowanie(Skierowanie skierowanie) {
        this.skierowanie = skierowanie;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRodzajZabiegu() {
        return rodzajZabiegu;
    }

    public void setRodzajZabiegu(String rodzajZabiegu) {
        this.rodzajZabiegu = rodzajZabiegu;
    }

    public String getOpisZabiegu() {
        return opisZabiegu;
    }

    public void setOpisZabiegu(String opisZabiegu) {
        this.opisZabiegu = opisZabiegu;
    }
    
}
