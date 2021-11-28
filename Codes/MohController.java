import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
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

public class MohController extends Moh{
    
    private String batch,loaction,assigned,UserName, Password, Name, Age, Status1, Status1Date, Status1Batch, Status1Loc, Status2, Status2Date,
            Status2Batch, Status2Loc;
    private CheckBox select;
    private static Scanner x;

    public MohController(String UserName, String Password, String Name, String Age, String Status1, String Status1Date,
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

    public MohController(String batch,String location,String assigned){
        this.batch = batch;
        this.loaction = location;
        this.assigned = assigned;
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

    public static ArrayList<MohController> readCSVAppointment() {

        String CsvFile = "Recipent.csv";
        String scan1 = "PENDING";
        String scan2 = " ";
        String FieldDelimiter = ",";
        BufferedReader br;
        ArrayList<MohController> record = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                for (int i = 4;
                     (scan1.matches(fields[i]))&& (scan2.matches(fields[i+3])); i++) {
                    record.add(new MohController(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5],
                            fields[6], fields[7], fields[8], fields[9], fields[10], fields[11]));
                    // dataList.add(record);
                }
            }
            br.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Moh.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Moh.class.getName()).log(Level.SEVERE, null, ex);
        }
        return record;
    }

    public static TableView<MohController> table() {

        TableView<MohController> table = new TableView<>();
        ArrayList<MohController> list = readCSVAppointment();
        ObservableList<MohController> tablelist = FXCollections.observableArrayList(list);

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

    public static String readCSV1stDoseCompleted() {

        String CsvFile = "Recipent.csv";
        String scan1 = "1ST DOSE COMPLETED";
        String FieldDelimiter = ",";
        int n =0;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                for (int i = 4;
                     (scan1.matches(fields[i])); i++) {
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
        return fstVaccine;
    }

    public static String readCSV2ndDoseCompleted() {

        String CsvFile = "Recipent.csv";
        String scan1 = "2ND DOSE COMPLETED";
        String FieldDelimiter = ",";
        int n =0;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                for (int i = 8;
                     (scan1.matches(fields[i])); i++) {
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
        String ndVaccine = String.valueOf(n);
        return ndVaccine;
    }

    public static String totalByDay(String date) {

        String CsvFile = "Recipent.csv";
        String scan1 = date;
        String FieldDelimiter = ",";
        int n =0;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                for (int i = 5;
                     (scan1.matches(fields[i]) || scan1.matches(fields[i + 4])); i++) {
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

    public static String distribute(String amount) {

        String CsvFile = "MOH.csv";
        String scan1 = amount;
        String scan2 = " ";
        String FieldDelimiter = ",";
        int n =0;
        int i = 1;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                while( i != Integer.parseInt(scan1)){
                for ( i = 1;
                     (scan2.matches(fields[i])); i++) {
                        i =+ i;
                        i++;
                        
                }
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
    
    public static void newVaccine(String reqLoc, String reqVaccine) throws IOException {

        String tempFile = "temp.csv";
        File oldFile = new File("Moh.csv");
        File newFile = new File(tempFile);
        String batch = "";
        String location = "";
        String assigned = "";
        int m = Integer.parseInt(reqVaccine);
        int n = 0;
        String scan = "UNASSIGNED";
        String newV = "ASSIGNED";
        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            x = new Scanner(new File("Moh.csv"));
            x.useDelimiter("[,\n]");
            while (x.hasNext()) {
                batch = x.next();
                location = x.next();
                assigned = x.next();
                if (n<= m && assigned.equals(scan.toUpperCase())) {
                    pw.print(batch + "," + reqLoc.toUpperCase() + "," + newV + "\n");
                    n++;
                } else {
                    pw.print(batch + "," + location + "," + assigned + "\n");
                }
        }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File("Moh.csv");
            newFile.renameTo(dump);
            System.out.println("DONE");
        } catch (Exception e) {
            System.out.println(e + "ERROR");
        }
    
    }

    public static void newRecipent(String reqLoc, String reqVaccine) throws IOException {

        String tempFile = "temp.csv";
        File oldFile = new File("Recipent.csv");
        File newFile = new File(tempFile);
        String UserName = "";
        String Password = "";
        String Name = "";
        String Age = "";
        String Status1 = "";
        String Status1Date = "";
        String Status1Batch = "";
        String Status1Loc = "";
        String Status2 = "";
        String Status2Date = "";
        String Status2Batch = "";
        String Status2Loc = "";
        int m = Integer.parseInt(reqVaccine);
        int n = 0;
        String scan = " ";
        //String newV = "";
        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            x = new Scanner(new File("Recipent.csv"));
            x.useDelimiter("[,\n]");
            while (x.hasNext()) {
                UserName = x.next();
                Password = x.next();
                Name = x.next();
                Age = x.next();
                Status1 = x.next();
                Status1Date = x.next();
                Status1Batch = x.next();
                Status1Loc = x.next();
                Status2 = x.next();
                Status2Date = x.next();
                Status2Batch =x.next();
                Status2Loc = x.next();
                if (n!= m && Status1Loc.equals(scan)) {
                    pw.print(UserName + ","+Password+","+Name +","+Age+","+Status1+","+Status1Date+","+Status1Batch+","+reqLoc.toUpperCase()+ ","+ Status2+","+Status2Date+","+Status2Batch+","+reqLoc.toUpperCase()+ "\n");
                    n++;
                } else {
                    pw.print(UserName + ","+Password+","+Name +","+Age+","+Status1+","+Status1Date+","+Status1Batch+","+Status1Loc+ ","+ Status2+","+Status2Date+","+Status2Batch+","+Status2Loc+ "\n");
                }
        }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File("Recipent.csv");
            newFile.renameTo(dump);
            System.out.println("DONE");
        } catch (Exception e) {
            System.out.println(e + "ERROR");
        }
    
    }

}
