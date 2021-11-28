import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Moh extends Application {

    @Override
    public void start(Stage main) throws Exception, IOException {

        Label mains = new Label("Welcome MOH!");
        mains.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Label phrase = new Label("Select a button :");

        Button view = new Button("View All Recipient");
        Button distribute = new Button("Distribute");
        Button totvac = new Button("Total Vaccination");
        Button totday = new Button("Total by Day");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setBackground(
                new Background(new BackgroundFill(Color.rgb(172, 216, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(mains, 0, 0, 2, 1);
        grid.add(phrase, 0, 1);
        grid.add(view, 0, 2);
        grid.add(distribute, 0, 3);
        grid.add(totvac, 0, 4);
        grid.add(totday, 0, 5);

        Stage primaryStage = new Stage();
        primaryStage.setTitle("MOH");
        primaryStage.setScene(new Scene(grid, 400, 400));
        primaryStage.show();

        view.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent view) {

                Button back = new Button("Back");
                TableView<MohController> recip = new TableView<>();
                recip = MohController.table();

                GridPane recips = new GridPane();
                recips.setAlignment(Pos.CENTER);
                recips.add(recip, 0, 0, 2, 1);
                recips.add(back, 1, 1);

                Stage secondaryStage = new Stage();
                secondaryStage.setScene(new Scene(recips, 950, 400));
                secondaryStage.show();
                primaryStage.close();

                back.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent arg0) {
                        primaryStage.show();
                        secondaryStage.close();
                    }

                });
            }
        });

        distribute.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent distribute) {

                Text distri = new Text("DISTRIBUTE");
                distri.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                Button vac = new Button("Vaccine");
                Button recipent = new Button("Recipent");
                Button back = new Button("Back");

                GridPane grid1 = new GridPane();
                grid1.setAlignment(Pos.CENTER);
                grid1.setHgap(10);
                grid1.setVgap(10);
                grid1.setPadding(new Insets(25, 25, 25, 25));
                grid1.setBackground(
                        new Background(new BackgroundFill(Color.rgb(172, 216, 230), CornerRadii.EMPTY, Insets.EMPTY)));
                grid1.add(distri, 0, 0, 2, 1);
                grid1.add(vac, 0, 1);
                grid1.add(recipent, 0, 2);
                grid1.add(back, 0, 3);

                Stage secondaryStage = new Stage();
                secondaryStage.setTitle("DISTRIBUTE");
                secondaryStage.setScene(new Scene(grid1, 400, 400));
                secondaryStage.show();
                primaryStage.close();

                back.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent back) {
                        primaryStage.show();
                        secondaryStage.close();

                    }
                });

                recipent.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent recipent) {
                        
                        Text men = new Text("Distribute Recipent:");
                        men.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                        Button bangi1 = new Button("UKM BANGI");
                        Button pwtc1 = new Button("PWTC");
                        Button back = new Button("Back");
                        TextField number = new TextField("Input Amount Of Recipent");
                        number.setPrefWidth(200);
                        final Text sticky =  new Text();
                        GridPane grid1 = new GridPane();
                        grid1.setAlignment(Pos.CENTER);
                        grid1.setHgap(10);
                        grid1.setVgap(10);
                        grid1.setPadding(new Insets(25, 25, 25, 25));
                        grid1.setBackground(new Background(
                                new BackgroundFill(Color.rgb(172, 216, 230), CornerRadii.EMPTY, Insets.EMPTY)));
                        grid1.add(men, 0, 0, 2, 1);
                        grid1.add(number,0,1);
                        grid1.add(bangi1, 1, 3);
                        grid1.add(pwtc1, 0, 3);
                        grid1.add(back, 0, 4);

                        Stage forthStage = new Stage();
                        forthStage.setTitle("DISTRIBUTING");
                        forthStage.setScene(new Scene(grid1, 400, 400));
                        forthStage.show();
                        secondaryStage.close();
                        
                        back.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent back) {
                                secondaryStage.show();
                                forthStage.close();
        
                            }
                        });

                        bangi1.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent bangi) {
                                String amount = number.getText();
                                String location = "ukm bangi";
                                try {
                                    MohController.newRecipent(location, amount);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                final Text sticky = new Text();
                                sticky.setFill(Color.BLACK);
                                sticky.setText("Recipent Distributed!");
                                grid1.add(sticky,0,5);
                            }
                            
                        });

                        pwtc1.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent pwtc) {
                                String amount = number.getText();
                                String location = "pwtc";
                                try {
                                    MohController.newRecipent(location, amount);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                final Text sticky = new Text();
                                sticky.setFill(Color.BLACK);
                                sticky.setText("Recipent Distributed!");
                                grid1.add(sticky,0,5);
                                
                            }
                            
                        });
                    }
                    
                });

                vac.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent vac) {
                        
                        Text men = new Text("Distribute Vaccine:");
                        men.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                        Button bangi1 = new Button("UKM BANGI");
                        Button pwtc1 = new Button("PWTC");
                        Button back = new Button("Back");
                        TextField number = new TextField("Input Amount Of Vaccine");
                        number.setPrefWidth(200);
                        final Text sticky =  new Text();
                        GridPane grid1 = new GridPane();
                        grid1.setAlignment(Pos.CENTER);
                        grid1.setHgap(10);
                        grid1.setVgap(10);
                        grid1.setPadding(new Insets(25, 25, 25, 25));
                        grid1.setBackground(new Background(
                                new BackgroundFill(Color.rgb(172, 216, 230), CornerRadii.EMPTY, Insets.EMPTY)));
                        grid1.add(men, 0, 0, 2, 1);
                        grid1.add(number,0,1);
                        grid1.add(bangi1, 1, 3);
                        grid1.add(pwtc1, 0, 3);
                        grid1.add(back, 0, 4);

                        Stage thirdStage = new Stage();
                        thirdStage.setTitle("DISTRIBUTING");
                        thirdStage.setScene(new Scene(grid1, 400, 400));
                        thirdStage.show();
                        secondaryStage.close();

                        back.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent back) {
                                secondaryStage.show();
                                thirdStage.close();
        
                            }
                        });

                        bangi1.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent bangi1) {
                                String amount = number.getText();
                                String location = "ukm bangi";
                                try {
                                    MohController.newVaccine(location, amount);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                final Text sticky = new Text();
                                sticky.setFill(Color.BLACK);
                                sticky.setText("Vaccine Distributed!");
                                grid1.add(sticky,0,5);
                                
                            }
                        });

                        pwtc1.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent pwtc1) {
                                String amount = number.getText();
                                String location = "pwtc";
                                try {
                                    MohController.newVaccine(location, amount);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                final Text sticky = new Text();
                                sticky.setFill(Color.BLACK);
                                sticky.setText("Vaccine Distributed!");
                                grid1.add(sticky,0,5);
        
                            }
                        });

                    }
                });

            }
        });

        totvac.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent totvac) {

                Text vacci = new Text("TOTAL VACCINATION");
                vacci.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                Text first = new Text("1st Dose Completed : " + MohController.readCSV1stDoseCompleted());
                Text second = new Text("2nd Dose Completed : " + MohController.readCSV2ndDoseCompleted());
                // Button done = new Button("Completed");
                Button back = new Button("Back");

                GridPane grid1 = new GridPane();
                grid1.setAlignment(Pos.CENTER);
                grid1.setHgap(10);
                grid1.setVgap(10);
                grid1.setPadding(new Insets(25, 25, 25, 25));
                grid1.setBackground(
                        new Background(new BackgroundFill(Color.rgb(172, 216, 230), CornerRadii.EMPTY, Insets.EMPTY)));
                grid1.add(vacci, 0, 0, 2, 1);
                grid1.add(first, 0, 1);
                grid1.add(second, 0, 2);
                // grid1.add(done, 0, 3);
                grid1.add(back, 0, 4);

                Stage secondaryStage = new Stage();
                secondaryStage.setTitle("TOTAL VACCINAION");
                secondaryStage.setScene(new Scene(grid1, 400, 400));
                secondaryStage.show();
                primaryStage.close();

                back.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent back) {
                        primaryStage.show();
                        secondaryStage.close();

                    }
                });

            }
        });

        totday.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent totday) {

                Text vacci = new Text("TOTAL VACCINATION BY DAY");
                vacci.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

                TextField input = new TextField("dd.mm.yyyy");
                Label date = new Label("Input Date :");

                Button check = new Button("Check");
                Button back = new Button("Back");

                Text amount = new Text();

                GridPane grid2 = new GridPane();
                grid2.setAlignment(Pos.CENTER);
                grid2.setHgap(10);
                grid2.setVgap(10);
                grid2.setPadding(new Insets(25, 25, 25, 25));
                grid2.setBackground(
                        new Background(new BackgroundFill(Color.rgb(172, 216, 230), CornerRadii.EMPTY, Insets.EMPTY)));
                grid2.add(vacci, 0, 0, 2, 1);
                grid2.add(input, 1, 1);
                grid2.add(date, 0, 1);
                // grid1.add(done, 0, 3);
                grid2.add(check, 0, 3);

                grid2.add(amount, 1, 3);
                grid2.add(back, 0, 4);

                Stage thirdStage = new Stage();
                thirdStage.setTitle("TOTAL VACCINAION");
                thirdStage.setScene(new Scene(grid2, 400, 400));
                thirdStage.show();
                primaryStage.close();

                check.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent check) {
                        String dates = input.getText();

                        amount.setText("Total Vaccinated On - " + dates + " : " + MohController.totalByDay(dates));
                    }

                });
                back.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent back) {
                        primaryStage.show();
                        thirdStage.close();

                    }
                });
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

}
