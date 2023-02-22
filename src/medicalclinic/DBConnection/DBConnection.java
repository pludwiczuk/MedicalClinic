package medicalclinic.DBConnection;

import medicalclinic.models.Skierowanie;
import medicalclinic.models.KartaPacjenta;
import medicalclinic.models.Choroba;
import medicalclinic.models.Lekarz;
import medicalclinic.models.Recepcjonistka;
import medicalclinic.models.Pielegniarka;
import medicalclinic.models.Admin;
import medicalclinic.models.Wizyta;
import medicalclinic.models.Zabieg;
import medicalclinic.models.Pacjent;
import medicalclinic.models.GodzPracy;
import medicalclinic.models.Recepta;
import medicalclinic.models.Harmonogram;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DBConnection {

    private static DBConnection instance = null;
    private Connection connection;
    private Statement statement;
    private ResultSet rs;

    public DBConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            Scanner scan = new Scanner(new File("pliki\\config.txt"));
            String hostname=scan.nextLine().split(" ")[1];
            String port=scan.nextLine().split(" ")[1];
            String sid=scan.nextLine().split(" ")[1];
            scan.close();
            
            connection = DriverManager.getConnection("jdbc:oracle:thin:@"+hostname+":"+port+":"+sid, "Clinic", "MedicalClinicPass");
            //connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.8.101:1521:xe", "Clinic", "MedicalClinicPass");
            statement = connection.createStatement();
            System.out.println("You are connected to database!");
        } catch (Exception ex) {
            System.out.println("#DBConnection(): Error: " + ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public String zaloguj(String login, String haslo) {
        try {
            rs = statement.executeQuery("SELECT * FROM pacjent WHERE login='" + login + "' AND haslo='" + haslo + "'");
            if (rs.next()) {
                return "pacjent " + rs.getString("id");
            }
            rs = statement.executeQuery("SELECT * FROM admin WHERE login='" + login + "' AND haslo='" + haslo + "'");
            if (rs.next()) {
                return "admin " + rs.getString("id");
            }
            rs = statement.executeQuery("SELECT * FROM pielegniarka WHERE login='" + login + "' AND haslo='" + haslo + "'");
            if (rs.next()) {
                return "pielegniarka " + rs.getString("id");
            }
            rs = statement.executeQuery("SELECT * FROM recepcjonistka WHERE login='" + login + "' AND haslo='" + haslo + "'");
            if (rs.next()) {
                return "recepcjonistka " + rs.getString("id");
            }
            rs = statement.executeQuery("SELECT * FROM lekarz WHERE login='" + login + "' AND haslo='" + haslo + "'");
            if (rs.next()) {
                return "lekarz " + rs.getString("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
        return "null";
    }

    public String zmienHaslo(String typUzytkownika, int id, String haslo) {
        if (typUzytkownika.equals("pacjent")) {
            try {
                statement.execute("UPDATE pacjent SET haslo='" + haslo + "' WHERE id=" + id);
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "true";
        } else if (typUzytkownika.equals("admin")) {
            try {
                statement.execute("UPDATE admin SET haslo='" + haslo + "' WHERE id=" + id);
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "true";
        } else if (typUzytkownika.equals("pielegniarka")) {
            try {
                statement.execute("UPDATE pielegniarka SET haslo='" + haslo + "' WHERE id=" + id);
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "true";
        } else if (typUzytkownika.equals("recepcjonistka")) {
            try {
                statement.execute("UPDATE recepcjonistka SET haslo='" + haslo + "' WHERE id=" + id);
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "true";
        } else if (typUzytkownika.equals("lekarz")) {
            try {
                statement.execute("UPDATE lekarz SET haslo='" + haslo + "' WHERE id=" + id);
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "true";
        }
        return "false";
    }
    
    public boolean checkLogin(String login) {
        try {
            rs=statement.executeQuery("SELECT id FROM admin WHERE login='"+login+"'");
            if(rs.next()) return false;
            rs=statement.executeQuery("SELECT id FROM pacjent WHERE login='"+login+"'");
            if(rs.next()) return false;
            rs=statement.executeQuery("SELECT id FROM lekarz WHERE login='"+login+"'");
            if(rs.next()) return false;
            rs=statement.executeQuery("SELECT id FROM recepcjonistka WHERE login='"+login+"'");
            if(rs.next()) return false;
            rs=statement.executeQuery("SELECT id FROM pielegniarka WHERE login='"+login+"'");
            if(rs.next()) return false;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return true;
    }

    public Pacjent getPacjent(int id) {
        Pacjent pacjent = null;
        try {
            rs = statement.executeQuery("SELECT * FROM pacjent WHERE id=" + id);
            if (rs.next()) {
                pacjent = new Pacjent(rs.getInt("id"), rs.getString("imie"), rs.getString("nazwisko"), rs.getString("nazwisko_rodowe"), rs.getDate("data_urodzenia").toString(), rs.getString("telefon"), rs.getString("email"), rs.getString("pesel"), rs.getString("miejsce_urodzenia"), rs.getString("adres"), rs.getString("plec"), rs.getString("login"), rs.getString("haslo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pacjent;
    }
    
    public Pacjent getPacjentByLogin(String login) {
        Pacjent pacjent = null;
        try {
            rs = statement.executeQuery("SELECT * FROM pacjent WHERE login='"+login+"'");
            if (rs.next()) {
                pacjent = new Pacjent(rs.getInt("id"), rs.getString("imie"), rs.getString("nazwisko"), rs.getString("nazwisko_rodowe"), rs.getDate("data_urodzenia").toString(), rs.getString("telefon"), rs.getString("email"), rs.getString("pesel"), rs.getString("miejsce_urodzenia"), rs.getString("adres"), rs.getString("plec").equals("m") ? "Mężczyzna" : "Kobieta", rs.getString("login"), rs.getString("haslo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pacjent;
    }

    public Admin getAdmin(int id) {
        Admin admin = null;
        try {
            rs = statement.executeQuery("SELECT * FROM admin WHERE id=" + id);
            if (rs.next()) {
                admin = new Admin(rs.getInt("id"), rs.getString("imie"), rs.getString("nazwisko"), rs.getDate("data_urodzenia").toString(), rs.getString("telefon"), rs.getString("email"), rs.getString("login"), rs.getString("haslo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }

    public Recepcjonistka getRecepcjonistka(int id) {
        Recepcjonistka recepcjonistka = null;
        try {
            rs = statement.executeQuery("SELECT * FROM recepcjonistka WHERE id=" + id);
            if (rs.next()) {
                recepcjonistka = new Recepcjonistka(rs.getInt("id"), rs.getString("imie"), rs.getString("nazwisko"), rs.getDate("data_urodzenia").toString(), rs.getString("telefon"), rs.getString("email"), rs.getString("login"), rs.getString("haslo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recepcjonistka;
    }

    public Lekarz getLekarz(int id) {
        Lekarz lekarz = null;
        try {
            rs = statement.executeQuery("SELECT * FROM lekarz WHERE id=" + id);
            if (rs.next()) {
                lekarz = new Lekarz(rs.getInt("id"), rs.getString("imie"), rs.getString("nazwisko"), rs.getDate("data_urodzenia").toString(), rs.getString("telefon"), rs.getString("email"), rs.getString("specjalizacja"), rs.getInt("nr_pokoju"), rs.getString("login"), rs.getString("haslo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lekarz;
    }
    
    public Pielegniarka getPielegniarka(int id) {
        Pielegniarka pielegniarka = null;
        try {
            rs = statement.executeQuery("SELECT * FROM pielegniarka WHERE id=" + id);
            if (rs.next()) {
                pielegniarka = new Pielegniarka(rs.getInt("id"), rs.getString("imie"), rs.getString("nazwisko"), rs.getDate("data_urodzenia").toString(), rs.getString("telefon"), rs.getString("email"), rs.getString("login"), rs.getString("haslo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pielegniarka;
    }
    
    public Lekarz getLekarzByLogin(String login) {
        Lekarz lekarz = null;
        try {
            rs = statement.executeQuery("SELECT * FROM lekarz WHERE login='" + login + "'");
            if (rs.next()) {
                lekarz = new Lekarz(rs.getInt("id"), rs.getString("imie"), rs.getString("nazwisko"), rs.getDate("data_urodzenia").toString(), rs.getString("telefon"), rs.getString("email"), rs.getString("specjalizacja"), rs.getInt("nr_pokoju"), rs.getString("login"), rs.getString("haslo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lekarz;
    }

    public Wizyta getWizyta(int id) {
        Wizyta wizyta = null;
        try {
            Statement statement2 = connection.createStatement();
            ResultSet rs2 = statement2.executeQuery("SELECT * FROM wizyta WHERE id=" + id);
            if (rs2.next()) {
                int pId = rs2.getInt("pacjent_id");
                int lId = rs2.getInt("lekarz_id");
                Pacjent p = getPacjent(pId);
                Lekarz l = getLekarz(lId);
                wizyta = new Wizyta(rs2.getInt("id"), p, l, rs2.getString("typ_wizyty"), rs2.getInt("nr_pokoju"), rs2.getDate("data").toString(), rs2.getTime("godzina").toString(), rs2.getInt("numerek"), rs2.getBoolean("czy_potwierdzona"), rs2.getBoolean("czy_odwolana"), rs2.getBoolean("czy_odbyta"), rs2.getString("opis"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return wizyta;
    }
    
    public Wizyta getWizytaByPacjent(int pacjentId, int lekarzId, String data) {
        Wizyta wizyta = null;
        try {
            rs = statement.executeQuery("SELECT id, pacjent_id, lekarz_id, typ_wizyty, nr_pokoju, data, godzina, numerek, czy_potwierdzona, czy_odwolana, czy_odbyta, opis FROM wizyta WHERE pacjent_id=" + pacjentId + " AND lekarz_id=" + lekarzId + " AND data=TO_DATE('" + data + "','DD-MM-YYYY')");
            if (rs.next()) {
                wizyta = new Wizyta(rs.getInt("id"), null, null, rs.getString("typ_wizyty"), rs.getInt("nr_pokoju"), rs.getDate("data").toString(), rs.getTime("godzina").toString(), rs.getInt("numerek"), rs.getBoolean("czy_potwierdzona"), rs.getBoolean("czy_odwolana"), rs.getBoolean("czy_odbyta"), rs.getString("opis"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return wizyta;
    }
    
    public KartaPacjenta getKartaPacjenta(int pacjentId) {
        KartaPacjenta karta = null;
        try {
            rs = statement.executeQuery("SELECT * FROM karta_pacjenta WHERE pacjent_id="+pacjentId);
            if(rs.next()) {
                karta = new KartaPacjenta(rs.getInt("id"), rs.getInt("wzrost"), rs.getInt("waga"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return karta;
    }
    
    public Harmonogram getHarmonogram(int lekarzId) {
        Harmonogram h = null;
        try {
            rs = statement.executeQuery("SELECT * FROM harmonogram WHERE lekarz_id="+lekarzId);
            if(rs.next()) {
                h = new Harmonogram();
                GodzPracy[] gp = new GodzPracy[5];
                gp[0] = new GodzPracy(rs.getString("poniedzialek").split("-")[0], rs.getString("poniedzialek").split("-")[1], Integer.parseInt(rs.getString("poniedzialek").split("-")[2]));
                gp[1] = new GodzPracy(rs.getString("wtorek").split("-")[0], rs.getString("wtorek").split("-")[1], Integer.parseInt(rs.getString("wtorek").split("-")[2]));
                gp[2] = new GodzPracy(rs.getString("sroda").split("-")[0], rs.getString("sroda").split("-")[1], Integer.parseInt(rs.getString("sroda").split("-")[2]));
                gp[3] = new GodzPracy(rs.getString("czwartek").split("-")[0], rs.getString("czwartek").split("-")[1], Integer.parseInt(rs.getString("czwartek").split("-")[2]));
                gp[4] = new GodzPracy(rs.getString("piatek").split("-")[0], rs.getString("piatek").split("-")[1], Integer.parseInt(rs.getString("piatek").split("-")[2]));
                h.setDzienTygodnia(gp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return h;
    }
    
    public Skierowanie getSkierowanie(int id) {
        Skierowanie s = null;
        try {
            Statement statement2 = connection.createStatement();
            ResultSet rs2 = statement2.executeQuery("SELECT * FROM skierowanie WHERE id="+id);
            if(rs2.next()) {
                int pId = rs2.getInt("pacjent_id");
                int lId = rs2.getInt("lekarz_id");
                Pacjent p = getPacjent(pId);
                Lekarz l = getLekarz(lId);
                Vector<String> badania = new Vector<String>();
                String lista = rs2.getString("lista_badan");
                for(int i=0;i<lista.split(",").length;i++) {
                    badania.add(lista.split(",")[i]);
                }
                s = new Skierowanie(rs2.getInt("id"), p, l, rs2.getDate("data_skierowania").toString(), rs2.getString("rodzaj"), (rs2.getString("specjalizacja").equals("")?"-":rs2.getString("specjalizacja")), (rs2.getString("rozpoznanie").equals("")?"-":rs2.getString("rozpoznanie")), badania, (rs2.getString("czy_wewnetrzne").equals("T")?true:false));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return s;
    }
    
    public int getCountOfVisitsByDate(String data) {
        int result=0;
        try {
            rs = statement.executeQuery("SELECT NVL(COUNT(id),0) id FROM wizyta WHERE data=TO_DATE('"+data+"','DD-MM-YYYY')");
            if(rs.next()) {
                result=rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return result;
    }

    public ResultSet getUsersToTable(String typUzytkownika) {
        rs = null;
        if (typUzytkownika.equals("admin")) {
            try {
                rs = statement.executeQuery("SELECT id, imie, nazwisko, NVL(TO_CHAR(data_urodzenia,'DD-MM-YYYY'),'-') DATA_URODZENIA, telefon, NVL(email,'-') email, login FROM admin ORDER BY id");
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getSQLState());
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex);
            }
        } else if (typUzytkownika.equals("lekarz")) {
            try {
                rs = statement.executeQuery("SELECT id, imie, nazwisko, NVL(TO_CHAR(data_urodzenia,'DD-MM-YYYY'),'-') DATA_URODZENIA, telefon, NVL(email,'-') email, specjalizacja, NVL(nr_pokoju,0) nr_pokoju, login FROM lekarz ORDER BY id");
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getSQLState());
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex);
            }
        } else if (typUzytkownika.equals("pielegniarka")) {
            try {
                rs = statement.executeQuery("SELECT id, imie, nazwisko, NVL(TO_CHAR(data_urodzenia,'DD-MM-YYYY'),'-') DATA_URODZENIA, telefon, NVL(email,'-') email, login FROM pielegniarka ORDER BY id");
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getSQLState());
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex);
            }
        } else if (typUzytkownika.equals("recepcjonistka")) {
            try {
                rs = statement.executeQuery("SELECT id, imie, nazwisko, NVL(TO_CHAR(data_urodzenia,'DD-MM-YYYY'),'-') DATA_URODZENIA, telefon, NVL(email,'-') email, login FROM recepcjonistka ORDER BY id");
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getSQLState());
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex);
            }
        } else if (typUzytkownika.equals("pacjent")) {
            try {
                rs = statement.executeQuery("SELECT id, imie, nazwisko, NVL(nazwisko_rodowe,'-') nazwisko_rodowe, NVL(TO_CHAR(data_urodzenia,'DD-MM-YYYY'),'-') DATA_URODZENIA, pesel, telefon, NVL(email,'-') email, miejsce_urodzenia, adres, NVL(plec,'-') plec, login FROM pacjent ORDER BY id");
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return rs;
    }
    
    public ResultSet getPacjentSimpleToTable() {
        rs = null;
        try {
            rs = statement.executeQuery("SELECT id, imie, nazwisko, PESEL, telefon, NVL(email,'-') email, miejsce_urodzenia FROM pacjent ORDER BY nazwisko");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return rs;
    }
    
    public ResultSet getLekarzSimpleToTable() {
        rs = null;
        try {
            rs = statement.executeQuery("SELECT id, imie, nazwisko, telefon, NVL(email,'-') email, specjalizacja, NVL(nr_pokoju,0) nr_pokoju FROM lekarz ORDER BY specjalizacja");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return rs;
    }

    public ResultSet getVisitToTable(char typ, int id) {
        rs = null;
        if (typ == 'p') {
            try {
                rs = statement.executeQuery("SELECT id, pacjent_id, lekarz_id, typ_wizyty, NVL(nr_pokoju,0) nr_pokoju, TO_CHAR(data,'DD-MM-YYYY') data, TO_CHAR(godzina,'HH:MI') godzina, NVL(numerek,0) numerek, czy_potwierdzona, czy_odwolana, czy_odbyta, opis FROM wizyta WHERE pacjent_id=" + id + " ORDER BY id");
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex);
            }
        } else if (typ == 'l') {
            try {
                rs = statement.executeQuery("SELECT id, pacjent_id, lekarz_id, typ_wizyty, NVL(nr_pokoju,0) nr_pokoju, TO_CHAR(data,'DD-MM-YYYY') data, TO_CHAR(godzina,'HH:MI') godzina, NVL(numerek,0) numerek, czy_potwierdzona, czy_odwolana, czy_odbyta, opis FROM wizyta WHERE lekarz_id=" + id + " ORDER BY id");
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return rs;
    }
    
    public ResultSet getActualVisitToTableByPacjent(int pacjentId) {
        rs = null;
        try {
            rs = statement.executeQuery("SELECT id, pacjent_id, lekarz_id, typ_wizyty, NVL(nr_pokoju,0) nr_pokoju, TO_CHAR(data,'DD-MM-YYYY') data, TO_CHAR(godzina,'HH:MI') godzina, NVL(numerek,0) numerek, czy_potwierdzona, czy_odwolana, czy_odbyta, opis FROM wizyta WHERE pacjent_id="+pacjentId+" AND czy_odbyta='N' ORDER BY data");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return rs;
    }
    
    public ResultSet getArchiwumVisitToTableByPacjent(int pacjentId) {
        rs = null;
        try {
            rs = statement.executeQuery("SELECT id, pacjent_id, lekarz_id, typ_wizyty, NVL(nr_pokoju,0) nr_pokoju, TO_CHAR(data,'DD-MM-YYYY') data, TO_CHAR(godzina,'HH:MI') godzina, NVL(numerek,0) numerek, czy_potwierdzona, czy_odwolana, czy_odbyta, opis FROM wizyta WHERE pacjent_id="+pacjentId+" AND czy_odbyta='T' ORDER BY data");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return rs;
    }
    
    public ResultSet getVisitToTableByLekarzAndData(int id, String data) {
        rs = null;
        try {
            rs = statement.executeQuery("SELECT id, pacjent_id, lekarz_id, typ_wizyty, NVL(nr_pokoju,0) nr_pokoju, TO_CHAR(data,'DD-MM-YYYY') data, TO_CHAR(godzina,'HH:MI') godzina, NVL(numerek,0) numerek, czy_potwierdzona, czy_odwolana, czy_odbyta, opis FROM wizyta WHERE lekarz_id=" + id + " AND data=TO_DATE('"+ data + "','DD-MM-YYYY')" +" ORDER BY id");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return rs;
    }
    
    public ResultSet getChorobyToTable(int kartaId) {
        rs = null;
        try {
            rs = statement.executeQuery("SELECT id, TO_CHAR(data,'DD-MM-YYYY') data, rozpoznanie, leki, historia_choroby FROM choroba WHERE karta_pacjenta_id="+kartaId);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return rs;
    }

    public ResultSet getReceptaToTable(int pacjentId) {
        rs = null;
        try {
            rs = statement.executeQuery("SELECT id, lekarz_id, TO_CHAR(data_wystawienia,'DD-MM-YYYY') data_wystawienia, nr_recepty, leki, czy_zrealizowana FROM recepta WHERE pacjent_id="+pacjentId);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return rs;
    }
    
    public ResultSet getSkierowanieToTable(int pacjentId) {
        rs = null;
        try {
            rs = statement.executeQuery("SELECT id, lekarz_id, TO_CHAR(data_skierowania,'DD-MM-YYYY') data_skierowania, rodzaj, specjalizacja, rozpoznanie, lista_badan, czy_wewnetrzne FROM skierowanie WHERE pacjent_id="+pacjentId+" ORDER BY data_skierowania");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return rs;
    }
    
    public ResultSet getSkierowanieWewnetrzneToTable(int pacjentId) {
        rs = null;
        try {
            rs = statement.executeQuery("SELECT id, lekarz_id, TO_CHAR(data_skierowania,'DD-MM-YYYY') data_skierowania, rodzaj, specjalizacja, rozpoznanie, lista_badan, czy_wewnetrzne FROM skierowanie WHERE pacjent_id="+pacjentId+" AND czy_wewnetrzne='T' ORDER BY data_skierowania");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return rs;
    }
    
    public ResultSet getZabiegiToTable(int pacjentId) {
        rs = null;
        try {
            rs = statement.executeQuery("SELECT id, pielegniarka_id, skierowanie_id, TO_CHAR(data,'DD-MM-YYYY') data, rodzaj_zabiegu, NVL(opis_zabiegu,'-') opis_zabiegu FROM zabieg WHERE pacjent_id="+pacjentId);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return rs;
    }
    
    public Vector<String> getLekarze() {
        Vector<String> lekarze = null;
        try {
            rs = getUsersToTable("lekarz");
            lekarze = new Vector<String>();
            while (rs.next()) {
                lekarze.add(rs.getInt("id") + " " + rs.getString("imie") + " " + rs.getString("nazwisko") + " " + rs.getString("specjalizacja"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return lekarze;
    }
    
    public Vector<String> getDateOfVisits(int lekarzId) {
        Vector<String> data = null;
        try {
            rs = statement.executeQuery("SELECT TO_CHAR(data,'DD-MM-YYYY') data FROM wizyta WHERE lekarz_id="+lekarzId+" GROUP BY data ORDER BY data");
            data = new Vector<String>();
            while(rs.next()) {
                data.add(rs.getString("data"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return data;
    }

    public boolean dodajAdmin(Admin a) {
        try {
            statement.execute("INSERT INTO admin VALUES ((SELECT NVL(MAX(id),0) FROM admin)+1, '" + a.getImie() + "', '" + a.getNazwisko() + "', TO_DATE('" + a.getDataUrodzenia() + "', 'DD-MM-YYYY'), '" + a.getTelefon() + "', '" + a.getEmail() + "', '" + a.getLogin() + "', '" + a.getHaslo() + "')");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean dodajPielegniarka(Pielegniarka p) {
        try {
            statement.execute("INSERT INTO pielegniarka VALUES ((SELECT NVL(MAX(id),0) FROM pielegniarka)+1, '" + p.getImie() + "', '" + p.getNazwisko() + "', TO_DATE('" + p.getDataUrodzenia() + "', 'DD-MM-YYYY'), '" + p.getTelefon() + "', '" + p.getEmail() + "', '" + p.getLogin() + "', '" + p.getHaslo() + "')");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean dodajRecepcjonistka(Recepcjonistka r) {
        try {
            statement.execute("INSERT INTO recepcjonistka VALUES ((SELECT NVL(MAX(id),0) FROM recepcjonistka)+1, '" + r.getImie() + "', '" + r.getNazwisko() + "', TO_DATE('" + r.getDataUrodzenia() + "', 'DD-MM-YYYY'), '" + r.getTelefon() + "', '" + r.getEmail() + "', '" + r.getLogin() + "', '" + r.getHaslo() + "')");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean dodajLekarz(Lekarz l) {
        try {
            statement.execute("INSERT INTO lekarz VALUES ((SELECT NVL(MAX(id),0) FROM lekarz)+1, '" + l.getImie() + "', '" + l.getNazwisko() + "', TO_DATE('" + l.getDataUrodzenia() + "', 'DD-MM-YYYY'), '" + l.getTelefon() + "', '" + l.getEmail() + "', '" + l.getSpecjalizacja() + "', " + l.getNrPokoju() + ", '" + l.getLogin() + "', '" + l.getHaslo() + "')");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean dodajPacjent(Pacjent p) {
        try {
            statement.execute("INSERT INTO pacjent VALUES ((SELECT NVL(MAX(id),0) FROM pacjent)+1, '" + p.getImie() + "', '" + p.getNazwisko() + "', '" + p.getNazwiskoRodowe() + "', TO_DATE('" + p.getDataUrodzenia() + "', 'DD-MM-YYYY'), '" + p.getPesel() + "', '" + p.getTelefon() + "', '" + p.getEmail() + "', '" + p.getMiejsceUrodzenia() + "', '" + p.getAdres() + "', '" + p.getPlec() + "', '" + p.getLogin() + "', '" + p.getHaslo() + "')");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean dodajKartaPacjent(KartaPacjenta k, int idPacjenta) {
        try {
            statement.execute("INSERT INTO karta_pacjenta VALUES((SELECT NVL(MAX(id),0) FROM karta_pacjenta)+1, " + idPacjenta + ", " + k.getWzrost() + ", " + k.getWaga() + ")");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean dodajWizyta(Wizyta w) {
        try {
            statement.execute("INSERT INTO wizyta VALUES ((SELECT NVL(MAX(id),0) FROM wizyta)+1, '" + w.getPacjent().getId() + "', '" + w.getLekarz().getId() + "', '" + w.getTypWizyty() + "', '" + w.getNrPokoju() + "', TO_DATE('" + w.getData() + "', 'DD-MM-YYYY'), TO_DATE('" + w.getGodz() + "', 'HH:MI'), (SELECT NVL(MAX(numerek),0) FROM wizyta WHERE lekarz_id="+w.getLekarz().getId()+" AND data=TO_DATE('"+w.getData()+"','DD-MM-YYYY'))+1, '" + ((w.isCzyPotwierdzona()) ? "T" : "N") + "', '" + ((w.isCzyOdwolana()) ? "T" : "N") + "', 'N', '" + w.getOpis() + "')");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }
    
    public boolean dodajHarmonogram(int lekarzId) {
        try {
            statement.execute("INSERT INTO harmonogram VALUES ((SELECT NVL(MAX(id),0) FROM harmonogram)+1, "+lekarzId+", ' - - ', ' - - ', ' - - ', ' - - ', ' - - ', 'h')");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }
    
    public boolean dodajChoroba(Choroba ch, int kartaId) {
        String leki="";
        for(int i=0;i<ch.getLeki().size();i++) {
            if(i==0) leki=leki+ch.getLeki().elementAt(i);
            else{
                leki=leki+","+ch.getLeki().elementAt(i);
            }
        }
        try {
            statement.execute("INSERT INTO choroba VALUES((SELECT NVL(MAX(id),0) FROM choroba)+1, "+kartaId+", TO_DATE('"+ch.getData()+"','DD-MM-YYYY'), '"+ch.getRozpoznanie()+"', '"+leki+"', '"+ch.getHistoriaChoroby()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }
    
    public boolean dodajRecepta(Recepta r) {
        String leki="";
        for(int i=0;i<r.getLeki().size();i++) {
            if(i==0) leki=leki+r.getLeki().elementAt(i);
            else{
                leki=leki+","+r.getLeki().elementAt(i);
            }
        }
        try {
            statement.execute("INSERT INTO recepta VALUES((SELECT NVL(MAX(id),0) FROM recepta)+1, "+r.getPacjent().getId()+", "+r.getLekarz().getId()+", TO_DATE('"+r.getDataWystawienia()+"','DD-MM-YYYY'), '"+r.getNrRecepty()+"', '"+leki+"', '"+(r.isCzyZrealizowana()?"T":"N")+"')");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }
    
    public boolean dodajSkierowanie(Skierowanie s) {
        String badania="-";
        for(int i=0;i<s.getListaBadan().size();i++) {
            if(i==0) badania=s.getListaBadan().elementAt(i);
            else{
                badania=badania+","+s.getListaBadan().elementAt(i);
            }
        }
        try {
            statement.execute("INSERT INTO skierowanie VALUES((SELECT NVL(MAX(id),0) FROM skierowanie)+1, "+s.getPacjent().getId()+", "+s.getLekarz().getId()+", TO_DATE('"+s.getDataSkierowania()+"','DD-MM-YYYY'), '"+s.getRodzaj()+"', '"+s.getSpecjalizacja()+"', '"+s.getRozpoznanie()+"', '"+badania+"', '"+(s.isCzyWewnetrzne()?"T":"N")+"')");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }
    
    public boolean dodajZabieg(Zabieg z) {
        try {
            statement.execute("INSERT INTO zabieg VALUES((SELECT NVL(MAX(id),0) FROM zabieg)+1, "+z.getPacjent().getId()+", "+z.getPielegniarka().getId()+", "+z.getSkierowanie().getId()+", TO_DATE('"+z.getData()+"','DD-MM-YYYY'), '"+z.getRodzajZabiegu()+"', '"+z.getOpisZabiegu()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean aktualizujAdmin(Admin a, int id) {
        try {
            statement.execute("UPDATE admin SET imie='" + a.getImie() + "', nazwisko='" + a.getNazwisko() + "', data_urodzenia=TO_DATE('" + a.getDataUrodzenia() + "', 'DD-MM-YYYY'), telefon='" + a.getTelefon() + "', email='" + a.getEmail() + "', login='" + a.getLogin() + "' WHERE id=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean aktualizujPielegniarka(Pielegniarka p, int id) {
        try {
            statement.execute("UPDATE pielegniarka SET imie='" + p.getImie() + "', nazwisko='" + p.getNazwisko() + "', data_urodzenia=TO_DATE('" + p.getDataUrodzenia() + "', 'DD-MM-YYYY'), telefon='" + p.getTelefon() + "', email='" + p.getEmail() + "', login='" + p.getLogin() + "' WHERE id=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean aktualizujRecepcjonistka(Recepcjonistka r, int id) {
        try {
            statement.execute("UPDATE recepcjonistka SET imie='" + r.getImie() + "', nazwisko='" + r.getNazwisko() + "', data_urodzenia=TO_DATE('" + r.getDataUrodzenia() + "', 'DD-MM-YYYY'), telefon='" + r.getTelefon() + "', email='" + r.getEmail() + "', login='" + r.getLogin() + "' WHERE id=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean aktualizujLekarz(Lekarz l, int id) {
        try {
            statement.execute("UPDATE lekarz SET imie='" + l.getImie() + "', nazwisko='" + l.getNazwisko() + "', data_urodzenia=TO_DATE('" + l.getDataUrodzenia() + "', 'DD-MM-YYYY'), telefon='" + l.getTelefon() + "', email='" + l.getEmail() + "', specjalizacja='" + l.getSpecjalizacja() + "', nr_pokoju=" + l.getNrPokoju() + ", login='" + l.getLogin() + "' WHERE id=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean aktualizujPacjent(Pacjent p, int id) {
        try {
            statement.execute("UPDATE pacjent SET imie='" + p.getImie() + "', nazwisko='" + p.getNazwisko() + "', nazwisko_rodowe='" + p.getNazwiskoRodowe() + "', data_urodzenia=TO_DATE('" + p.getDataUrodzenia() + "', 'DD-MM-YYYY'), pesel='" + p.getPesel() + "', telefon='" + p.getTelefon() + "', email='" + p.getEmail() + "', miejsce_urodzenia='" + p.getMiejsceUrodzenia() + "', adres='" + p.getAdres() + "', plec='" + p.getPlec() + "', login='" + p.getLogin() + "' WHERE id=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean aktualizujKartaPacjenta(KartaPacjenta k, int idPacjenta) {
        try {
            statement.execute("UPDATE karta_pacjenta SET wzrost=" + k.getWzrost() + ", waga=" + k.getWaga() + " WHERE pacjent_id=" + idPacjenta);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }
    
    public boolean aktualizujHarmonogram(Harmonogram h, int lekarzId) {
        try {
            statement.execute("UPDATE harmonogram SET poniedzialek='"+h.getDzienTygodnia()[0].getGodzOd()+"-"+h.getDzienTygodnia()[0].getGodzDo()+"-"+Integer.toString(h.getDzienTygodnia()[0].getLiczWizyt())+"', wtorek='"+h.getDzienTygodnia()[1].getGodzOd()+"-"+h.getDzienTygodnia()[1].getGodzDo()+"-"+Integer.toString(h.getDzienTygodnia()[1].getLiczWizyt())+"', sroda='"+h.getDzienTygodnia()[2].getGodzOd()+"-"+h.getDzienTygodnia()[2].getGodzDo()+"-"+Integer.toString(h.getDzienTygodnia()[2].getLiczWizyt())+"', czwartek='"+h.getDzienTygodnia()[3].getGodzOd()+"-"+h.getDzienTygodnia()[3].getGodzDo()+"-"+Integer.toString(h.getDzienTygodnia()[3].getLiczWizyt())+"', piatek='"+h.getDzienTygodnia()[4].getGodzOd()+"-"+h.getDzienTygodnia()[4].getGodzDo()+"-"+Integer.toString(h.getDzienTygodnia()[4].getLiczWizyt())+"' WHERE lekarz_id="+lekarzId);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean aktualizujHaslo(String typUzytkownika, int id, String haslo) {
        try {
            statement.execute("UPDATE " + typUzytkownika + " SET haslo='" + haslo + "' WHERE id=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean aktualizujWizyta(Wizyta w, int id) {
        try {
            statement.execute("UPDATE wizyta SET typ_wizyty='" + w.getTypWizyty() + "', nr_pokoju=" + w.getNrPokoju() + ", data=TO_DATE('" + w.getData() + "','DD-MM-YYYY'), godzina=TO_DATE('" + w.getGodz() + "','HH-MI'), numerek=" + w.getNumerek() + ", czy_potwierdzona='" + ((w.isCzyPotwierdzona()) ? "T" : "N") + "', czy_odwolana='" + ((w.isCzyOdwolana()) ? "T" : "N") + "', opis='" + w.getOpis() + "' WHERE id=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }
    
    public boolean aktualizujChoroba(Choroba ch, int id) {
        String leki="";
        for(int i=0;i<ch.getLeki().size();i++) {
            if(i==0) leki=leki+ch.getLeki().elementAt(i);
            else{
                leki=leki+","+ch.getLeki().elementAt(i);
            }
        }
        try {
            statement.execute("UPDATE choroba SET data=TO_DATE('"+ch.getData()+"','DD-MM-YYYY'), rozpoznanie='"+ch.getRozpoznanie()+"', leki='"+leki+"', historia_choroby='"+ch.getHistoriaChoroby()+"' WHERE id="+id);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }
    
    public boolean aktualizujRecepta(Recepta r, int id) {
        String leki="";
        for(int i=0;i<r.getLeki().size();i++) {
            if(i==0) leki=leki+r.getLeki().elementAt(i);
            else{
                leki=leki+","+r.getLeki().elementAt(i);
            }
        }
        try {
            statement.execute("UPDATE recepta SET data_wystawienia=TO_DATE('"+r.getDataWystawienia()+"','DD-MM-YYYY'), nr_recepty='"+r.getNrRecepty()+"', leki='"+leki+"', czy_zrealizowana='"+(r.isCzyZrealizowana() ? "T" : "N")+"' WHERE id="+id);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }
    
    public boolean aktualizujSkierowanie(Skierowanie s, int id) {
        String badania="-";
        for(int i=0;i<s.getListaBadan().size();i++) {
            if(i==0) badania=s.getListaBadan().elementAt(i);
            else{
                badania=badania+","+s.getListaBadan().elementAt(i);
            }
        }
        try {
            statement.execute("UPDATE skierowanie SET data_skierowania=TO_DATE('"+s.getDataSkierowania()+"','DD-MM-YYYY'), rodzaj='"+s.getRodzaj()+"', specjalizacja='"+s.getSpecjalizacja()+"', rozpoznanie='"+s.getRozpoznanie()+"', lista_badan='"+badania+"', czy_wewnetrzne='"+(s.isCzyWewnetrzne() ? "T" : "N")+"' WHERE id="+id);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }
    
    public boolean aktualizujZabieg(Zabieg z, int id) {
        try {
            statement.execute("UPDATE zabieg SET data=TO_DATE('"+z.getData()+"','DD-MM-YYYY'), rodzaj_zabiegu='"+z.getRodzajZabiegu()+"', opis_zabiegu='"+z.getOpisZabiegu()+"' WHERE id="+id);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean usunUzytkownika(String typUzytkownika, int id) {
        try {
            statement.execute("DELETE FROM " + typUzytkownika + " WHERE id=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean usunKartaPacjenta(int idPacjenta) {
        try {
            statement.execute("DELETE FROM karta_pacjenta WHERE pacjent_id=" + idPacjenta);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }
    
    public boolean usunHarmnogram(int lekarzId) {
        try {
            statement.execute("DELETE FROM harmonogram WHERE lekarz_id="+lekarzId);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }
    
    public boolean usunChoroba(int id) {
        try {
            statement.execute("DELETE FROM choroba WHERE id="+id);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }
    
    public boolean usunRecepta(int id) {
        try {
            statement.execute("DELETE FROM recepta WHERE id="+id);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }
    
    public boolean usunSkierowanie(int id) {
        try {
            statement.execute("DELETE FROM skierowanie WHERE id="+id);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }
    
    public boolean usunZabieg(int id) {
        try {
            statement.execute("DELETE FROM zabieg WHERE id="+id);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public String test() {
        String res = "";
        try {
            statement.execute("insert into pacjent values (3,'Dawid','Kowalski',null,'95/10/07','95020412996','523456789',null,'Radom','Chleby 56, 09-152 Lublin','m','pacjent3','pacjent3')");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
}
