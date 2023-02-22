package medicalclinic.models;

import medicalclinic.DBConnection.DBConnection;

public class Sesja {

    private boolean czyZalogowany = false;
    private String typUzytkownika = null;
    private int idUzytkownika = 0;
    private Admin admin = null;
    private Lekarz lekarz = null;
    private Pielegniarka pielegniarka = null;
    private Recepcjonistka recepcjonistka = null;
    private Pacjent pacjent = null;

    public boolean isCzyZalogowany() {
        return czyZalogowany;
    }

    public void setCzyZalogowany(boolean czyZalogowany) {
        this.czyZalogowany = czyZalogowany;
    }

    public String getTypUzytkownika() {
        return typUzytkownika;
    }

    public void setTypUzytkownika(String typUzytkownika) {
        this.typUzytkownika = typUzytkownika;
    }

    public int getIdUzytkownika() {
        return idUzytkownika;
    }

    public void setIdUzytkownika(int idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Lekarz getLekarz() {
        return lekarz;
    }

    public void setLekarz(Lekarz lekarz) {
        this.lekarz = lekarz;
    }

    public Pielegniarka getPielegniarka() {
        return pielegniarka;
    }

    public void setPielegniarka(Pielegniarka pielegniarka) {
        this.pielegniarka = pielegniarka;
    }

    public Recepcjonistka getRecepcjonistka() {
        return recepcjonistka;
    }

    public void setRecepcjonistka(Recepcjonistka recepcjonistka) {
        this.recepcjonistka = recepcjonistka;
    }

    public Pacjent getPacjent() {
        return pacjent;
    }

    public void setPacjent(Pacjent pacjent) {
        this.pacjent = pacjent;
    }

    public String zaloguj(String login, String haslo) {
        DBConnection db=DBConnection.getInstance();
        String result = db.zaloguj(login, haslo);
        if(result.split(" ")[0].equals("pacjent")) {
            czyZalogowany = true;
            typUzytkownika = "pacjent";
            int id = Integer.parseInt(result.split(" ")[1]);
            idUzytkownika = id;
            pacjent=db.getPacjent(id);
            return "true pacjent";
        }
        else if(result.split(" ")[0].equals("admin")) {
            czyZalogowany = true;
            typUzytkownika = "admin";
            int id = Integer.parseInt(result.split(" ")[1]);
            idUzytkownika = id;
            admin=db.getAdmin(id);
            return "true admin";
        }
        else if(result.split(" ")[0].equals("recepcjonistka")) {
            czyZalogowany = true;
            typUzytkownika = "recepcjonistka";
            int id = Integer.parseInt(result.split(" ")[1]);
            idUzytkownika = id;
            recepcjonistka = db.getRecepcjonistka(id);
            return "true recepcjonistka";
        }
        else if(result.split(" ")[0].equals("lekarz")) {
            czyZalogowany = true;
            typUzytkownika = "lekarz";
            int id = Integer.parseInt(result.split(" ")[1]);
            idUzytkownika = id;
            lekarz = db.getLekarz(id);
            return "true lekarz";
        }
        else if(result.split(" ")[0].equals("pielegniarka")) {
            czyZalogowany = true;
            typUzytkownika = "pielegniarka";
            int id = Integer.parseInt(result.split(" ")[1]);
            idUzytkownika = id;
            pielegniarka = db.getPielegniarka(id);
            return "true pielegniarka";
        }
        else if(result.split(" ")[0].equals("error")) return "error";
        return "false";
    }

    public boolean wyloguj() {
        if(czyZalogowany) {
            czyZalogowany = false;
            if(typUzytkownika.equals("admin")){
                idUzytkownika = 0;
                typUzytkownika = null;
                admin = null;
            }
            else if(typUzytkownika.equals("lekarz")){
                idUzytkownika = 0;
                typUzytkownika = null;
                lekarz = null;
            }
            else if(typUzytkownika.equals("pacjent")){
                idUzytkownika = 0;
                typUzytkownika = null;
                pacjent = null;
            }
            else if(typUzytkownika.equals("pielegniarka")){
                idUzytkownika = 0;
                typUzytkownika = null;
                pielegniarka = null;
            }
            else if(typUzytkownika.equals("recepcjonistka")){
                idUzytkownika = 0;
                typUzytkownika = null;
                recepcjonistka = null;
            }
        }
        return true;
    }
}
