import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Vc extends Application {

    @Override
    public void start(Stage main) throws Exception, IOException {

        Label mains = new Label("Welcome VC!");
        mains.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Label phrase = new Label("Choose which VC");

        Button bangi = new Button("UKM BANGI");
        Button pwtc = new Button("PWTC");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setBackground(
                new Background(new BackgroundFill(Color.rgb(172, 216, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(mains, 0, 0, 2, 1);
        grid.add(phrase, 0, 1);
        grid.add(bangi, 0, 2);
        grid.add(pwtc, 1, 2);

        Stage primaryStage = new Stage();
        primaryStage.setTitle("VC");
        primaryStage.setScene(new Scene(grid, 400, 400));
        primaryStage.show();

        bangi.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent bangi) {
                try {
                    String totalnew = VcController.checkMoh("ukm bangi");
                    VcController.newVaccine("ukm bangi", totalnew);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                
                Text vcBangi = new Text("UKM BANGI VC");
                vcBangi.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                Button recipent = new Button("View All Recipents");
                Button appointment = new Button("Set Recipents Appointment");
                Button update = new Button("Update Recipents Status");
                Button stats = new Button("View All Stats");
                Button back = new Button("Back");
                final Text clicky = new Text();
                GridPane grid1 = new GridPane();
                grid1.setAlignment(Pos.CENTER);
                grid1.setHgap(10);
                grid1.setVgap(10);
                grid1.setPadding(new Insets(25, 25, 25, 25));
                grid1.setBackground(
                        new Background(new BackgroundFill(Color.rgb(172, 216, 230), CornerRadii.EMPTY, Insets.EMPTY)));
                grid1.add(vcBangi, 0, 0, 2, 1);
                grid1.add(recipent, 0, 1);
                grid1.add(appointment, 0, 2);
                grid1.add(update, 0, 3);
                grid1.add(stats, 0, 4);
                grid1.add(back, 0, 5);
                grid1.add(clicky, 1, 6);

                Stage secondaryStage = new Stage();
                secondaryStage.setTitle("UKM BANGI");
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

                        Button back = new Button("Back");
                        TableView<VcController> recip = new TableView<>();
                        recip = VcController.table();

                        GridPane recips = new GridPane();
                        recips.setAlignment(Pos.CENTER);
                        recips.add(recip, 0, 0, 2, 1);
                        recips.add(back, 1, 1);

                        Stage forthStage = new Stage();
                        forthStage.setScene(new Scene(recips, 950, 400));
                        forthStage.show();
                        secondaryStage.close();

                        back.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent arg0) {
                                secondaryStage.show();
                                forthStage.close();
                            }

                        });
                    }

                });

                appointment.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent appointmnet) {

                        Button back = new Button("Back");
                        Button next = new Button("Next");
                        // CheckBox cb = new CheckBox();

                        TableView<VcController> table = new TableView<>();
                        table = VcController.table();

                        GridPane list = new GridPane();
                        list.setAlignment(Pos.CENTER);
                        list.add(table, 0, 0, 2, 1);
                        list.add(back, 1, 1);
                        list.add(next, 4, 1);

                        Stage thirdStage = new Stage();
                        thirdStage.setScene(new Scene(list, 950, 400));
                        thirdStage.show();
                        secondaryStage.close();

                        TableViewSelectionModel selectionModel = table.getSelectionModel();
                        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);

                        back.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent back) {
                                secondaryStage.show();
                                thirdStage.close();
                            }

                        });

                        next.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent next) {
                                // ObservableList<String> selectedItems = selectionModel.getSelectedItems();
                                //List<String> selectedItems = selectionModel.getSelectedItems();

                                Button confirm = new Button("Confirm");
                                Button back = new Button("Back");

                                Text main = new Text("Setting Appointment");
                                main.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

                                Label entDate = new Label("Input Date:");
                                TextField date = new TextField();

                                GridPane grid5 = new GridPane();
                                grid5.setAlignment(Pos.CENTER);
                                grid5.setHgap(10);
                                grid5.setVgap(10);
                                grid5.setPadding(new Insets(25, 25, 25, 25));
                                grid5.setBackground(new Background(
                                        new BackgroundFill(Color.rgb(172, 216, 230), CornerRadii.EMPTY, Insets.EMPTY)));
                                grid5.add(main,0,0,2,1);
                                grid5.add(entDate,0,1);
                                grid5.add(date,1,1);
                                grid5.add(confirm,0,2);
                                grid5.add(back,1,2);

                                Stage forthStage = new Stage();
                                forthStage.setScene(new Scene(grid5, 950, 400));
                                forthStage.show();
                                thirdStage.close();

                                confirm.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent arg0) {
                                        forthStage.close();
                                        secondaryStage.show();
                                        //clicky.setFill(Color.BLACK);
                                        //clicky.setText("Changes Saved!");
                                    }
                                    
                                });
                                back.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent arg0) {
                                        forthStage.close();;
                                        thirdStage.show();
                                        
                                    }
                                    
                                });
                            }

                        });
                    }

                });

                update.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent update) {
                        
                        Button back = new Button("Back");
                        Button next = new Button("Next");

                        TableView<VcController> table = new TableView<>();
                        table = VcController.table();

                        GridPane list2 = new GridPane();
                        list2.setAlignment(Pos.CENTER);
                        list2.add(table, 0, 0, 2, 1);
                        list2.add(back, 1, 1);
                        list2.add(next, 4, 1);

                        Stage fifthStage = new Stage();
                        fifthStage.setScene(new Scene(list2, 950, 400));
                        fifthStage.show();
                        secondaryStage.close();

                        TableViewSelectionModel selectionModel = table.getSelectionModel();
                        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);

                        back.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent back) {
                                secondaryStage.show();
                                fifthStage.close();
                            }

                        });

                        next.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent next) {
                                // ObservableList<String> selectedItems = selectionModel.getSelectedItems();
                                //List<String> selectedItems = selectionModel.getSelectedItems();

                                Button confirm1 = new Button("Confirm");
                                Button back1 = new Button("Back");

                                Text main1 = new Text("Setting Appointment");
                                main1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

                                //Label entDate = new Label("Input Date:");
                                //TextField date = new TextField();

                                GridPane grid5 = new GridPane();
                                grid5.setAlignment(Pos.CENTER);
                                grid5.setHgap(10);
                                grid5.setVgap(10);
                                grid5.setPadding(new Insets(25, 25, 25, 25));
                                grid5.setBackground(new Background(
                                        new BackgroundFill(Color.rgb(172, 216, 230), CornerRadii.EMPTY, Insets.EMPTY)));
                                grid5.add(main1,0,0,2,1);
                                //grid5.add(entDate,0,1);
                                //grid5.add(date,1,1);
                                grid5.add(confirm1,0,2);
                                grid5.add(back1,1,2);

                                Stage sixthStage = new Stage();
                                sixthStage.setScene(new Scene(grid5, 950, 400));
                                sixthStage.show();
                                fifthStage.close();

                                confirm1.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent arg0) {
                                        sixthStage.close();
                                        secondaryStage.show();
                                        //clicky.setFill(Color.BLACK);
                                        //clicky.setText("Changes Saved!");
                                    }
                                    
                                });
                                back1.setOnAction(new EventHandler<ActionEvent>() {
        
                                    @Override
                                    public void handle(ActionEvent arg0) {
                                        sixthStage.close();;
                                        fifthStage.show();
                                        
                                    }
                                    
                                });
                        }});

                        

                    }

                });

                stats.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent stats) {
                        
                        Text tot = new Text("TOTAL OF :");
                        tot.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                        Text overall = new Text("Vaccination done : " + VcController.totalBangi());
                        
                        TextField input = new TextField("dd.mm.yyyy");
                        Label date = new Label("Input Date :");
                        Text vac;
                        try {
                            vac = new Text("Vaccine available : " + VcController.checkMoh("ukm bangi"));
                        
                        Button refresh = new Button("Refresh");
                        Button back = new Button("Back");

                        Text amount = new Text();
                        
                        GridPane grid5 = new GridPane();
                        grid5.setAlignment(Pos.CENTER);
                        grid5.setHgap(10);
                        grid5.setVgap(10);
                        grid5.setPadding(new Insets(25, 25, 25, 25));
                        grid5.setBackground(
                            new Background(new BackgroundFill(Color.rgb(172, 216, 230), CornerRadii.EMPTY, Insets.EMPTY)));
                        grid5.add(tot, 0, 0, 2, 1);
                        grid5.add(overall, 0, 1);
                        grid5.add(input, 1, 2);
                        grid5.add(date, 0, 2);
                        grid5.add(refresh, 0, 3);
                        grid5.add(amount, 1, 3);
                        grid5.add(vac, 0, 4);
                        grid5.add(back, 0, 5);
                        
                        Stage sixthStage = new Stage();
                        sixthStage.setTitle("STATS");
                        sixthStage.setScene(new Scene(grid5, 400, 400));
                        sixthStage.show();
                        secondaryStage.close(); 
                         

                        refresh.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent refresh) {
                                String dates = input.getText();
        
                                amount.setText("Vaccination On - " + dates + " : " + VcController.totalByDayBangi(dates));
                            }
        
                        });
                        
                        back.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent back) {
                                secondaryStage.show();
                                sixthStage.close();
        
                            }
                        });
                    }catch (IOException e) {
                        e.printStackTrace();
                    } 
                    }

                });

            }

        });

        pwtc.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent pwtc) {
                try {
                    String totalnew = VcController.checkMoh("pwtc");
                    VcController.newVaccine("pwtc", totalnew);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Text vcPwtc = new Text("PWTC VC");
                vcPwtc.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                Button recipent = new Button("View All Recipents");
                Button appointment = new Button("Set Recipents Appointment");
                Button update = new Button("Update Recipents Status");
                Button stats = new Button("View All Stats");
                Button back = new Button("Back");

                GridPane grid1 = new GridPane();
                grid1.setAlignment(Pos.CENTER);
                grid1.setHgap(10);
                grid1.setVgap(10);
                grid1.setPadding(new Insets(25, 25, 25, 25));
                grid1.setBackground(
                        new Background(new BackgroundFill(Color.rgb(172, 216, 230), CornerRadii.EMPTY, Insets.EMPTY)));
                grid1.add(vcPwtc, 0, 0, 2, 1);
                grid1.add(recipent, 0, 1);
                grid1.add(appointment, 0, 2);
                grid1.add(update, 0, 3);
                grid1.add(stats, 0, 4);
                grid1.add(back, 0, 5);

                Stage secondaryStage = new Stage();
                secondaryStage.setTitle("PWTC");
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

                        Button back = new Button("Back");
                        TableView<VcController> recip = new TableView<>();
                        recip = VcController.table1();

                        GridPane recips = new GridPane();
                        recips.setAlignment(Pos.CENTER);
                        recips.add(recip, 0, 0, 2, 1);
                        recips.add(back, 1, 1);

                        Stage thirdStage = new Stage();
                        thirdStage.setScene(new Scene(recips, 950, 400));
                        thirdStage.show();
                        secondaryStage.close();

                        back.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent arg0) {
                                secondaryStage.show();
                                thirdStage.close();
                            }

                        });
                    }

                });

                appointment.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent appointmnet) {

                        Button back = new Button("Back");
                        Button next = new Button("Next");
                        // CheckBox cb = new CheckBox();

                        TableView<VcController> table = new TableView<>();
                        table = VcController.table1();

                        GridPane list = new GridPane();
                        list.setAlignment(Pos.CENTER);
                        list.add(table, 0, 0, 2, 1);
                        list.add(back, 1, 1);
                        list.add(next, 4, 1);

                        Stage forthStage = new Stage();
                        forthStage.setScene(new Scene(list, 950, 400));
                        forthStage.show();
                        secondaryStage.close();

                        TableViewSelectionModel selectionModel = table.getSelectionModel();
                        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);

                        back.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent back) {
                                secondaryStage.show();
                                forthStage.close();
                            }

                        });

                        next.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent next) {
                                // ObservableList<String> selectedItems = selectionModel.getSelectedItems();
                                //List<String> selectedItems = selectionModel.getSelectedItems();

                                Button confirm = new Button("Confirm");
                                Button back = new Button("Back");

                                Text main = new Text("Setting Appointment");
                                main.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

                                Label entDate = new Label("Input Date:");
                                TextField date = new TextField();

                                GridPane grid5 = new GridPane();
                                grid5.setAlignment(Pos.CENTER);
                                grid5.setHgap(10);
                                grid5.setVgap(10);
                                grid5.setPadding(new Insets(25, 25, 25, 25));
                                grid5.setBackground(new Background(
                                        new BackgroundFill(Color.rgb(172, 216, 230), CornerRadii.EMPTY, Insets.EMPTY)));
                                grid5.add(main,0,0,2,1);
                                grid5.add(entDate,0,1);
                                grid5.add(date,1,1);
                                grid5.add(confirm,0,2);
                                grid5.add(back,1,2);

                                Stage fifthStage = new Stage();
                                fifthStage.setScene(new Scene(grid5, 950, 400));
                                fifthStage.show();
                                forthStage.close();

                                confirm.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent arg0) {
                                        fifthStage.close();
                                        secondaryStage.show();
                                        //clicky.setFill(Color.BLACK);
                                        //clicky.setText("Changes Saved!");
                                    }
                                    
                                });
                                back.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent arg0) {
                                        fifthStage.close();;
                                        forthStage.show();
                                        
                                    }
                                    
                                });
                            }

                        });
                    }

                });

                update.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent update) {
                        
                        Button back = new Button("Back");
                        Button next = new Button("Next");

                        TableView<VcController> table = new TableView<>();
                        table = VcController.table1();

                        GridPane list2 = new GridPane();
                        list2.setAlignment(Pos.CENTER);
                        list2.add(table, 0, 0, 2, 1);
                        list2.add(back, 1, 1);
                        list2.add(next, 4, 1);

                        Stage fifthStage = new Stage();
                        fifthStage.setScene(new Scene(list2, 950, 400));
                        fifthStage.show();
                        secondaryStage.close();

                        TableViewSelectionModel selectionModel = table.getSelectionModel();
                        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);

                        back.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent back) {
                                secondaryStage.show();
                                fifthStage.close();
                            }

                        });

                        next.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent next) {
                                // ObservableList<String> selectedItems = selectionModel.getSelectedItems();
                                //List<String> selectedItems = selectionModel.getSelectedItems();

                                Button confirm1 = new Button("Confirm");
                                Button back1 = new Button("Back");

                                Text main1 = new Text("Setting Appointment");
                                main1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

                                //Label entDate = new Label("Input Date:");
                                //TextField date = new TextField();

                                GridPane grid5 = new GridPane();
                                grid5.setAlignment(Pos.CENTER);
                                grid5.setHgap(10);
                                grid5.setVgap(10);
                                grid5.setPadding(new Insets(25, 25, 25, 25));
                                grid5.setBackground(new Background(
                                        new BackgroundFill(Color.rgb(172, 216, 230), CornerRadii.EMPTY, Insets.EMPTY)));
                                grid5.add(main1,0,0,2,1);
                                //grid5.add(entDate,0,1);
                                //grid5.add(date,1,1);
                                grid5.add(confirm1,0,2);
                                grid5.add(back1,1,2);

                                Stage sixthStage = new Stage();
                                sixthStage.setScene(new Scene(grid5, 950, 400));
                                sixthStage.show();
                                fifthStage.close();

                                confirm1.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent arg0) {
                                        sixthStage.close();
                                        secondaryStage.show();
                                        //clicky.setFill(Color.BLACK);
                                        //clicky.setText("Changes Saved!");
                                    }
                                    
                                });
                                back1.setOnAction(new EventHandler<ActionEvent>() {
        
                                    @Override
                                    public void handle(ActionEvent arg0) {
                                        sixthStage.close();;
                                        fifthStage.show();
                                        
                                    }
                                    
                                });
                        }});

                        

                    }

                });

                stats.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent stats) {
                        
                        Text tot = new Text("TOTAL OF :");
                        tot.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                        Text overall = new Text("Vaccination done : " + VcController.totalPWTC());
                        
                        TextField input = new TextField("dd.mm.yyyy");
                        Label date = new Label("Input Date :");
                        Text vac;
                        try {
                            vac = new Text("Vaccine available : " + VcController.checkMoh("PWTC"));
                        
                        Button refresh = new Button("Refresh");
                        Button back = new Button("Back");

                        Text amount = new Text();
                        
                        GridPane grid5 = new GridPane();
                        grid5.setAlignment(Pos.CENTER);
                        grid5.setHgap(10);
                        grid5.setVgap(10);
                        grid5.setPadding(new Insets(25, 25, 25, 25));
                        grid5.setBackground(
                            new Background(new BackgroundFill(Color.rgb(172, 216, 230), CornerRadii.EMPTY, Insets.EMPTY)));
                        grid5.add(tot, 0, 0, 2, 1);
                        grid5.add(overall, 0, 1);
                        grid5.add(input, 1, 2);
                        grid5.add(date, 0, 2);
                        grid5.add(refresh, 0, 3);
                        grid5.add(amount, 1, 3);
                        grid5.add(vac, 0, 4);
                        grid5.add(back, 0, 5);
                        
                        Stage sixthStage = new Stage();
                        sixthStage.setTitle("STATS");
                        sixthStage.setScene(new Scene(grid5, 400, 400));
                        sixthStage.show();
                        secondaryStage.close(); 
                         

                        refresh.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent refresh) {
                                String dates = input.getText();
        
                                amount.setText("Vaccination On - " + dates + " : " + VcController.totalByDayPWTC(dates));
                            }
        
                        });
                        
                        back.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent back) {
                                secondaryStage.show();
                                sixthStage.close();
        
                            }
                        });
                    }catch (IOException e) {
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
