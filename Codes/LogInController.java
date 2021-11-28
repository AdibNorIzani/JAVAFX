import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioPermission;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class LogInController extends LogIn {

    private String Id,UserName,Password,Name,Age,Status1,Status1Date,Status1Batch,Status1Loc,Status2,Status2Date,Status2Batch,Status2Loc;


    LogInController(){}

    LogInController(String UserName,String Password,String Name,String Age,String Status1,String Status1Date,String Status1Batch,String Status1Loc,String Status2,String Status2Date,String Status2Batch,String Status2Loc){
        this.UserName = UserName;
        this.Password = Password;
        this.Name = Name;
        this.Age =Age;
        this.Status1 = Status1;
        this.Status1Date = Status1Date;
        this.Status1Batch = Status1Batch;
        this.Status1Loc = Status1Loc;
        this.Status2 = Status2;
        this.Status2Date = Status2Date;
        this.Status2Batch = Status2Batch;
        this.Status2Loc = Status2Loc;
    }

    public String toUserName(){
        return UserName;
    }
    
    public String toName(){
        return Name;
    }

    public String toPassword(){
        return Password;
    }

    public String toStatus1(){
        return Status1;
    }
    public String toStatus1Date(){
        return Status1Date;
    }
    public String toStatus1Batch(){
        return Status1Batch;
    }
    public String toStatus2(){
        return Status2;
    }
    public String toStatus2Date(){
        return Status2Date;
    }
    public String toStatus2Batch(){
        return Status2Batch;
    }

    public String toCSVString(){
        return UserName +","+ Password + "," + Name +","+ Age +","+ Status1+ "," + Status1Date + "," + Status1Batch +"," + Status1Loc + "," + Status2 + "," + Status2Date + "," + Status2Batch +  "," + Status2Loc  ;
    }

    public static ArrayList<LogInController> getInfo(String UserName) throws IOException {

        ArrayList<LogInController> User = new ArrayList<>();
        // ArrayList<simulator> Location = new ArrayList<>();
        String scan1 = UserName.trim();
        //String scan2 = Password.trim();
        List<String> lines = Files.readAllLines(Paths.get("Recipent.csv"));
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(",");
            for (int n = 0; items[n].matches(scan1.toUpperCase()); n++) {
                //for (int m = 1; items[m].matches(scan2); m++) {
                    User.add(new LogInController(items[n], items[n + 1], items[n + 2], items[n + 3], items[n + 4],
                            items[n + 5], items[n + 6], items[n + 7], items[n + 8], items[n + 9], items[n + 10], items[n + 11]));
                //}
            }
        }
        return User;
    }

    public static String getName(String UserName) throws IOException{
        ArrayList<LogInController> scan = getInfo(UserName);
        String name = scan.get(0).toName();
        return name;
    }

    public static boolean check(String UserName, String Password) throws IOException{
        ArrayList<LogInController> name = getInfo(UserName);
        if (name.isEmpty()){
            return false;
        }else{
            String pass = name.get(0).toPassword();
            if(pass.equals(Password)){
                return true;
            }else
                return false;
        }
    }

    public static String getStatus1(String UserName) throws IOException{
        ArrayList<LogInController> status = getInfo(UserName);
        String status1 = status.get(0).toStatus1();
        if(status1.equals("PENDING")){
            status1 = "1ST DOSE - PENDING";
            return status1;
        }else
            return status1;
    }

    public static String getStatus1Date(String UserName) throws IOException{
        ArrayList<LogInController> status = getInfo(UserName);
        String status1 = status.get(0).toStatus1Date();
        if(status1.equals(" ")){
            status1 = " ";
            return status1;
        }else
            status1 = " - " + status1;
            return status1;
    }

    public static String getStatus1Batch(String UserName) throws IOException{
        ArrayList<LogInController> status = getInfo(UserName);
        String status1 = status.get(0).toStatus1Batch();
        if(status1.equals(" ")){
            status1 = " ";
            return status1;
        }else
            status1 = "Batch: " + status1;
            return status1;
    }

    public static String getStatus2(String UserName) throws IOException{
        ArrayList<LogInController> status = getInfo(UserName);
        String status1 = status.get(0).toStatus2();
        if(status1.equals("PENDING")){
            status1 = "2ND DOSE - PENDING";
            return status1;
        }
        return status1;
    }

    public static String getStatus2Date(String UserName) throws IOException{
        ArrayList<LogInController> status = getInfo(UserName);
        String status1 = status.get(0).toStatus2Date();
        if(status1.equals(" ")){
            status1 = " ";
            return status1;
        }else
            status1 = " - " + status1;
            return status1;
    }

    public static String getStatus2Batch(String UserName) throws IOException{
        ArrayList<LogInController> status = getInfo(UserName);
        String status1 = status.get(0).toStatus2Batch();
        if(status1.equals(" ")){
            status1 = " ";
            return status1;
        }else
            status1 = "Batch: " + status1;
            return status1;
    }

    public static boolean checkName(String UserName) throws IOException{
        ArrayList<LogInController> scan = getInfo(UserName);
        if(scan.isEmpty()){
            return true;
        }else
            return false;
    }

    public static ArrayList<LogInController> readAccountFromFile() throws IOException{
        ArrayList<LogInController> accounts = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("Recipent.csv"));
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(",");
            accounts.add(new LogInController(items[0],items[1],items[2],items[3],items[4],items[5],items[6],items[7],items[8],items[9],items[10],items[11]));
        }
        return accounts;
    }

    public static void saveAccountToFile(ArrayList<LogInController> accounts) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < accounts.size(); i++) {
            sb.append (accounts.get(i).toCSVString() + "\n");
        }
        Files.write(Paths.get("Recipent.csv"), sb.toString().getBytes());
    }

}
