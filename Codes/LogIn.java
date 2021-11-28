import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Action;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.*;

public class LogIn extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception, IOException {
        Label lblText = new Label("Welcome To Sejahtera!");
        lblText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Label userName = new Label("User Name:");
        Label pw = new Label("Password:");

        TextField userNameBox = new TextField();
        PasswordField pwBox = new PasswordField();

        final Text clicky = new Text();

        Button btnLogIn = new Button("Log In");
        Button btnRegister = new Button("Register");

        HBox hbLogIn = new HBox(10);
        hbLogIn.setAlignment(Pos.BOTTOM_RIGHT);
        hbLogIn.getChildren().add(btnLogIn);
        HBox hbPw = new HBox(10);
        hbPw.setAlignment(Pos.BOTTOM_RIGHT);
        hbPw.getChildren().add(btnRegister);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setBackground(
                new Background(new BackgroundFill(Color.rgb(172, 216, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(lblText, 0, 0, 2, 1);
        grid.add(userName, 0, 1);
        grid.add(userNameBox, 1, 1);
        grid.add(pw, 0, 2);
        grid.add(pwBox, 1, 2);
        grid.add(hbLogIn, 1, 4);
        grid.add(hbPw, 0, 4);
        grid.add(clicky, 1, 6);

        primaryStage.setTitle("Sejahtera");
        primaryStage.setScene(new Scene(grid, 400, 400));
        primaryStage.show();

        btnLogIn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                String UserName = userNameBox.getText();
                String password = pwBox.getText();
                boolean pass;
                try {
                    pass = LogInController.check(UserName, password);
                    if (pass == true) {
                        Text admin = new Text("WELCOME " + LogInController.getName(UserName) + "!");
                        Button logout = new Button("Log Out");
                        Label Status1 = new Label(
                                LogInController.getStatus1(UserName) + LogInController.getStatus1Date(UserName));
                        // Label Status1Date = new Label(LogInController.getStatus1Date(UserName));
                        Label Status1Batch = new Label(LogInController.getStatus1Batch(UserName));
                        Label Status2 = new Label(
                                LogInController.getStatus2(UserName) + LogInController.getStatus2Date(UserName));
                        // Label Status2Date = new Label(LogInController.getStatus2Date(UserName));
                        Label Status2Batch = new Label(LogInController.getStatus2Batch(UserName));

                        GridPane grid1 = new GridPane();
                        grid1.setAlignment(Pos.TOP_LEFT);
                        grid1.setHgap(10);
                        grid1.setVgap(10);
                        grid1.setPadding(new Insets(25, 25, 25, 25));
                        grid1.setBackground(new Background(
                                new BackgroundFill(Color.rgb(172, 216, 230), CornerRadii.EMPTY, Insets.EMPTY)));
                        grid1.add(admin, 0, 0, 2, 1);
                        grid1.add(logout, 4, 0);
                        grid1.add(Status1, 1, 4);
                        grid1.add(Status1Batch, 1, 5);
                        grid1.add(Status2, 1, 6);
                        grid1.add(Status2Batch, 1, 7);

                        Stage secondaryStage = new Stage();
                        secondaryStage.setTitle("Sejahtera");
                        secondaryStage.setScene(new Scene(grid1, 400, 400));
                        secondaryStage.show();
                        userNameBox.clear();
                        pwBox.clear();
                        primaryStage.close();

                        logout.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent logout) {
                                primaryStage.show();
                                secondaryStage.close();
                            }

                        });

                    } else if (UserName.isEmpty() || password.isEmpty()) {
                        clicky.setFill(Color.FIREBRICK);
                        clicky.setText("Please Fill In All Box!");
                    } else {
                        clicky.setFill(Color.FIREBRICK);
                        clicky.setText("Wrong Username or Password!");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }

        });

        btnRegister.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent reg) {
                Text register = new Text("REGISTER NEW ACCOUNT");

                TextField username = new TextField();
                PasswordField password = new PasswordField();
                TextField namebox = new TextField();
                TextField agebox = new TextField();

                Label name = new Label("Name:");
                Label age = new Label("Age:");
                Label usern = new Label("User Name:");
                Label pass = new Label("Password:");

                Button regi = new Button("Register");
                Button back = new Button("Back");

                final Text clicky1 = new Text();

                GridPane grid2 = new GridPane();
                grid2.setAlignment(Pos.CENTER);
                grid2.setHgap(10);
                grid2.setVgap(10);
                grid2.setPadding(new Insets(25, 25, 25, 25));
                grid2.setBackground(
                        new Background(new BackgroundFill(Color.rgb(172, 216, 230), CornerRadii.EMPTY, Insets.EMPTY)));
                grid2.add(register, 0, 0, 2, 1);
                grid2.add(usern,0,1);
                grid2.add(username,1,1);
                grid2.add(pass,0,2);
                grid2.add(password,1,2);
                grid2.add(name,0,3);
                grid2.add(namebox,1,3);
                grid2.add(age,0,4);
                grid2.add(agebox,1,4);
                grid2.add(regi, 2, 5);
                grid2.add(back, 0, 5);
                grid2.add(clicky1, 1, 6);

                Stage thirdStage = new Stage();
                thirdStage.setTitle("Sejahtera");
                thirdStage.setScene(new Scene(grid2, 400, 400));
                thirdStage.show();
                primaryStage.close();

                back.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent back) {
                        userNameBox.clear();
                        pwBox.clear();
                        primaryStage.show();
                        thirdStage.close();
                        
                    }
                    
                });
                    
                regi.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent regi) {
                        String UserName = username.getText();
                        String Password = password.getText();
                        String Name = namebox.getText();
                        String Age = agebox.getText();
                        if(UserName.isEmpty() || Password.isEmpty() || Name.isEmpty() || Age.isEmpty()){
                            clicky1.setFill(Color.FIREBRICK);
                            clicky1.setText("Please Fill In All Box!");
                        }else
                        try {
                            Boolean pass = LogInController.checkName(UserName);
                            if(pass == true){
                                
                                String Status1 = "PENDING",Status1Date = " ",Status1Batch = " ",Status1Loc = " ",Status2 = "PENDING",Status2Date = " ",Status2Batch = " ",Status2Loc = " ";

                                ArrayList<LogInController> account = LogInController.readAccountFromFile();
                                account.add(new LogInController(UserName.toUpperCase(),Password,Name.toUpperCase(),Age,Status1,Status1Date,Status1Batch,Status1Loc,Status2,Status2Date,Status2Batch,Status2Loc));
                                LogInController.saveAccountToFile(account);
                                primaryStage.show();
                                clicky.setFill(Color.BLACK);
                                clicky.setText("Account Cretead! Please Log In");
                                thirdStage.close();
                            } else {
                                clicky1.setFill(Color.FIREBRICK);
                                clicky1.setText("User Name Already Exist In Database!");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        
                    }
                    
                });
            }    
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

}