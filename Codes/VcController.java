import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;

public class VcController extends Vc {

    private String UserName, Password, Name, Age, Status1, Status1Date, Status1Batch, Status1Loc, Status2, Status2Date,
            Status2Batch, Status2Loc;
    private CheckBox select;
    private static Scanner x;

    public VcController(String UserName, String Password, String Name, String Age, String Status1, String Status1Date,
            String Status1Batch, String Status1Loc, String Status2, String Status2Date, String Status2Batch,
            String Status2Loc) {
        this.UserName = (UserName);
        this.Password = (Password);
        this.Name = (Name);
        this.Age = (Age);
        this.Status1 = (Status1);
        this.Status1Date = (Status1Date);
        this.Status1Batch = (Status1Batch);
        this.Status1Loc = (Status1Loc);
        this.Status2 = (Status2);
        this.Status2Date = (Status2Date);
        this.Status2Batch = (Status2Batch);
        this.Status2Loc = (Status2Loc);
        this.select = new CheckBox();

    }

    public String getUserName() {
        return UserName;
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }

    public boolean isSelected(CheckBox select) {
        if (select.isSelected()) {
            return true;
        } else {
            return false;
        }
    }

    public String getName() {
        return Name;
    }

    public String getAge() {
        return Age;
    }

    public String getStat1() {
        return Status1;
    }

    public String getStat1Date() {
        return Status1Date;
    }

    public String getStat1Batch() {
        return Status1Batch;
    }

    public String getStat1Loc() {
        return Status1Loc;
    }

    public String getStat2() {
        return Status2;
    }

    public String getStat2Date() {
        return Status2Date;
    }

    public String getStat2Batch() {
        return Status2Batch;
    }

    public String getStat2Loc() {
        return Status2Loc;
    }

    public String toString() {
        return UserName + "," + Password + "," + Name + "," + Age + "," + Status1 + "," + Status1Date + ","
                + Status1Batch + "," + Status1Loc + "," + Status2 + "," + Status2Date + "," + Status2Batch + ","
                + Status2Loc;
    }

    public static ArrayList<VcController> readCSVBangiAppointment() {

        String CsvFile = "Recipent.csv";
        String scan1 = "PENDING";
        String scan = "UKM BANGI";
        String FieldDelimiter = ",";
        BufferedReader br;
        ArrayList<VcController> record = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                for (int i = 7; scan.matches(fields[i])
                        && (scan1.matches(fields[i + 1]) || scan1.matches(fields[i - 3])); i++) {
                    record.add(new VcController(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5],
                            fields[6], fields[7], fields[8], fields[9], fields[10], fields[11]));
                    // dataList.add(record);
                }
            }
            br.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Vc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Vc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return record;
    }

    public static TableView<VcController> checkBoxTable() {
        TableView<VcController> table = new TableView<>();
        ArrayList<VcController> list = readCSVBangiAppointment();
        ObservableList<VcController> tablelist = FXCollections.observableArrayList(list);

        TableColumn select = new TableColumn<>("Select");
        select.setCellValueFactory(new PropertyValueFactory<>("select"));

        TableColumn column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn column2 = new TableColumn<>("Age");
        column2.setCellValueFactory(new PropertyValueFactory<>("Age"));

        TableColumn column3 = new TableColumn<>("Status 1");
        column3.setCellValueFactory(new PropertyValueFactory<>("Stat1"));

        TableColumn column4 = new TableColumn<>("Status 1 Date");
        column4.setCellValueFactory(new PropertyValueFactory<>("Stat1Date"));

        TableColumn column5 = new TableColumn<>("Status 1 Batch");
        column5.setCellValueFactory(new PropertyValueFactory<>("Stat1Batch"));

        TableColumn column6 = new TableColumn<>("Status 1 Location");
        column6.setCellValueFactory(new PropertyValueFactory<>("Stat1Loc"));

        TableColumn column7 = new TableColumn<>("Status 2");
        column7.setCellValueFactory(new PropertyValueFactory<>("Stat2"));

        TableColumn column8 = new TableColumn<>("Status 2 Date");
        column8.setCellValueFactory(new PropertyValueFactory<>("Stat2Date"));

        TableColumn column9 = new TableColumn<>("Status 2 Batch");
        column9.setCellValueFactory(new PropertyValueFactory<>("Stat2Batch"));

        TableColumn column10 = new TableColumn<>("Status 2 Location");
        column10.setCellValueFactory(new PropertyValueFactory<>("Stat2Loc"));

        table.setItems(tablelist);
        table.getColumns().addAll(select, column1, column2, column3, column4, column5, column6, column7, column8,
                column9, column10);
        return table;
    }

    public static TableView<VcController> table() {

        TableView<VcController> table = new TableView<>();
        ArrayList<VcController> list = readCSVBangiAppointment();
        ObservableList<VcController> tablelist = FXCollections.observableArrayList(list);

        // TableColumn select = new TableColumn<>("Select");
        // select.setCellValueFactory(new PropertyValueFactory<>("select"));

        TableColumn column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn column2 = new TableColumn<>("Age");
        column2.setCellValueFactory(new PropertyValueFactory<>("Age"));

        TableColumn column3 = new TableColumn<>("Status 1");
        column3.setCellValueFactory(new PropertyValueFactory<>("Stat1"));

        TableColumn column4 = new TableColumn<>("Status 1 Date");
        column4.setCellValueFactory(new PropertyValueFactory<>("Stat1Date"));

        TableColumn column5 = new TableColumn<>("Status 1 Batch");
        column5.setCellValueFactory(new PropertyValueFactory<>("Stat1Batch"));

        TableColumn column6 = new TableColumn<>("Status 1 Location");
        column6.setCellValueFactory(new PropertyValueFactory<>("Stat1Loc"));

        TableColumn column7 = new TableColumn<>("Status 2");
        column7.setCellValueFactory(new PropertyValueFactory<>("Stat2"));

        TableColumn column8 = new TableColumn<>("Status 2 Date");
        column8.setCellValueFactory(new PropertyValueFactory<>("Stat2Date"));

        TableColumn column9 = new TableColumn<>("Status 2 Batch");
        column9.setCellValueFactory(new PropertyValueFactory<>("Stat2Batch"));

        TableColumn column10 = new TableColumn<>("Status 2 Location");
        column10.setCellValueFactory(new PropertyValueFactory<>("Stat2Loc"));

        table.setItems(tablelist);
        table.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9,
                column10);
        return table;
    }

    public static String totalByDayBangi(String date) {

        String CsvFile = "Recipent.csv";
        String scan = "UKM BANGI";
        String scan1 = date;
        String FieldDelimiter = ",";
        int n =0;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                for (int i = 7; scan.matches(fields[i])
                     && (scan1.matches(fields[i - 2]) || scan1.matches(fields[i + 2])); i++) {
                        n =+ n;
                        n++;
                    
                }
            }
            br.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Moh.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Moh.class.getName()).log(Level.SEVERE, null, ex);
        }
        String fstVaccine = String.valueOf(n);
        System.out.println(fstVaccine);
        return fstVaccine;
    }

    public static String totalBangi() {

        String CsvFile = "Recipent.csv";
        String scan = "UKM BANGI";
        String scan1 = "1ST DOSE COMPLETED";
        String scan2 = "2ND DOSE COMPLETED";
        String FieldDelimiter = ",";
        int n =0;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                for (int i = 7; (fields[i].matches(scan)) && (fields[i-3].matches(scan1)|| fields[i+1].matches(scan2)); i++) {
                        n =+ n;
                        n++;
                    
                }
            }
            br.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Moh.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Moh.class.getName()).log(Level.SEVERE, null, ex);
        }
        String total = String.valueOf(n);
        return total;
    }

    public static void newVaccine(String reqLoc, String reqVaccine) throws IOException {

        String tempFile = "temp.csv";
        File oldFile = new File("VC.CSV");
        File newFile = new File(tempFile);
        String VcLoc = "";
        String VcLoad = "";
        String VcVaccine = "";

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            x = new Scanner(new File("VC.CSV"));
            x.useDelimiter("[,\n]");

            while (x.hasNext()) {
                VcLoc = x.next();
                VcLoad = x.next();
                VcVaccine = x.next();
                if (VcLoc.equals(reqLoc.toUpperCase())) {
                    pw.print(VcLoc + "," + VcLoad + "," + reqVaccine + "\n");
                } else {
                    pw.print(VcLoc + "," + VcLoad + "," + VcVaccine + "\n");
                }
            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File("VC.CSV");
            newFile.renameTo(dump);
            System.out.println("DONE");
        } catch (Exception e) {
            System.out.println(e + "ERROR");
        }
    }

    public static String checkMoh(String VcLoc) throws IOException {

        String check = "ASSIGNED";
        int m = 0;
        ArrayList<mohs> Vaccine = new ArrayList<>();
        String scan1 = VcLoc.trim();
        List<String> lines = Files.readAllLines(Paths.get("MOH.csv"));
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(",");
            for (int n = 1; items[n].matches(scan1.toUpperCase()) && items[n + 1].matches(check); n++) {
                Vaccine.add(new mohs(items[n - 1], items[n], items[n + 1]));
                m++;
            }
        }
        String total = String.valueOf(m);
        return total;
    }

    public static String totalPWTC() {

        String CsvFile = "Recipent.csv";
        String scan = "PWTC";
        String scan1 = "1ST DOSE COMPLETED";
        String scan2 = "2ND DOSE COMPLETED";
        String FieldDelimiter = ",";
        int n =0;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                for (int i = 7; (fields[i].matches(scan))&& (fields[i-3].matches(scan1)|| fields[i+1].matches(scan2)); i++) {
                        n =+ n;
                        n++;
                    
                }
            }
            br.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Moh.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Moh.class.getName()).log(Level.SEVERE, null, ex);
        }
        String total = String.valueOf(n);
        return total;
    }

    public static String totalByDayPWTC(String date) {

        String CsvFile = "Recipent.csv";
        String scan = "PWTC";
        String scan1 = date;
        String FieldDelimiter = ",";
        int n =0;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                for (int i = 7; scan.matches(fields[i])
                     && (scan1.matches(fields[i - 2]) || scan1.matches(fields[i + 2])); i++) {
                        n =+ n;
                        n++;
                    
                }
            }
            br.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Moh.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Moh.class.getName()).log(Level.SEVERE, null, ex);
        }
        String fstVaccine = String.valueOf(n);
        System.out.println(fstVaccine);
        return fstVaccine;
    }

    public static ArrayList<VcController> readCSVPWTCAppointment() {

        String CsvFile = "Recipent.csv";
        String scan1 = "PENDING";
        String scan = "PWTC";
        String FieldDelimiter = ",";
        BufferedReader br;
        ArrayList<VcController> record = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                for (int i = 7; scan.matches(fields[i])
                        && (scan1.matches(fields[i + 1]) || scan1.matches(fields[i - 3])); i++) {
                    record.add(new VcController(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5],
                            fields[6], fields[7], fields[8], fields[9], fields[10], fields[11]));
                    // dataList.add(record);
                }
            }
            br.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Vc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Vc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return record;
    }

    public static TableView<VcController> table1() {

        TableView<VcController> table = new TableView<>();
        ArrayList<VcController> list = readCSVPWTCAppointment();
        ObservableList<VcController> tablelist = FXCollections.observableArrayList(list);

        // TableColumn select = new TableColumn<>("Select");
        // select.setCellValueFactory(new PropertyValueFactory<>("select"));

        TableColumn column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn column2 = new TableColumn<>("Age");
        column2.setCellValueFactory(new PropertyValueFactory<>("Age"));

        TableColumn column3 = new TableColumn<>("Status 1");
        column3.setCellValueFactory(new PropertyValueFactory<>("Stat1"));

        TableColumn column4 = new TableColumn<>("Status 1 Date");
        column4.setCellValueFactory(new PropertyValueFactory<>("Stat1Date"));

        TableColumn column5 = new TableColumn<>("Status 1 Batch");
        column5.setCellValueFactory(new PropertyValueFactory<>("Stat1Batch"));

        TableColumn column6 = new TableColumn<>("Status 1 Location");
        column6.setCellValueFactory(new PropertyValueFactory<>("Stat1Loc"));

        TableColumn column7 = new TableColumn<>("Status 2");
        column7.setCellValueFactory(new PropertyValueFactory<>("Stat2"));

        TableColumn column8 = new TableColumn<>("Status 2 Date");
        column8.setCellValueFactory(new PropertyValueFactory<>("Stat2Date"));

        TableColumn column9 = new TableColumn<>("Status 2 Batch");
        column9.setCellValueFactory(new PropertyValueFactory<>("Stat2Batch"));

        TableColumn column10 = new TableColumn<>("Status 2 Location");
        column10.setCellValueFactory(new PropertyValueFactory<>("Stat2Loc"));

        table.setItems(tablelist);
        table.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9,
                column10);
        return table;
    }

}
