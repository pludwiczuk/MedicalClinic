package medicalclinic;

import medicalclinic.DBConnection.DBConnection;
import medicalclinic.views.Frame_Logowanie;
import java.util.Scanner;

public class MedicalClinic {

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        DBConnection.getInstance();
        Frame_Logowanie start = new Frame_Logowanie();
        start.setVisible(true);
    }
    
}
