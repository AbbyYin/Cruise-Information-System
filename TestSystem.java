package question_1;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
// Import.

/**
 * This class implements an application to operate on the system.
 *
 * @author yueyin2
 */
public class TestSystem extends Application {

    /**
     * This method asks the users to input passenger ID and add the passenger to
     * the cruise by assigning cruiseID to the record.
     *
     * @param cruiseID ID number of the cruise.
     * @param passengerList
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void addPassenger(String cruiseID, ArrayList<Integer> passengerList) throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");//Load the driver.
        Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/CruiseSystem",
                "ab",
                "123");//Connect to the database.

        for (int i = 0; i < passengerList.size(); i++) {
            try {
                String query = "UPDATE Passenger SET CruiseID = ? WHERE PassengerID = ?";
                PreparedStatement pres = conn.prepareStatement(query);
                pres.setString(1, cruiseID);
                pres.setInt(2, passengerList.get(i));
                pres.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.exit(0);
            }//Set the cruise ID for the selected passenger.

            try {
                String query = "INSERT INTO PassengerCruise ("
                        + "CruiseID, PassengerID)"
                        + "VALUES ("
                        + "?,?"
                        + ")";
                PreparedStatement pres = conn.prepareStatement(query);
                pres.setString(1, cruiseID);
                pres.setInt(2, passengerList.get(i));
                pres.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.exit(0);
            }//Set the cruise ID and passenger ID in the PassengerCruise table.
        }
    }

    private int shipIDnum = 0;
    private String cruiseIDnum;
    private int portIDnum = 0;
    private LocalDate sailingDate, returnDate;
    private ArrayList<Integer> passengerList = new ArrayList<Integer>();
    private InsertData insertData = new InsertData();
    private ObservableList selectedIndices;
    // Declare the private fields.

    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {

        primaryStage.setTitle("CruiseSystem");

        //Scene1, create a cruise
        GridPane gridPane = new GridPane();

        Label cruiseID = new Label("CruiseID");
        Label shipID = new Label("ShipID");
        Label portID = new Label("PortID");
        Label passengerID = new Label("PassengerID" + "\n" + "Press CTRL for multiple choice");
        Label sailingDatel = new Label("SailingDate (dd/mm/yyyy)");
        Label returnDatel = new Label("ReturnDate (dd/mm/yyyy)");

        TextField cruiseField = new TextField();
        TextField sailingField = new TextField();
        TextField returnField = new TextField();
        ChoiceBox shipChoice = new ChoiceBox();
        ChoiceBox portChoice = new ChoiceBox();
        Button button0 = new Button("Create a Cruise");
        ListView pList = new ListView();
        pList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Button button = new Button("Add Passenger");
        Button rbutton = new Button("Return");
        //Declare the elements.

        shipChoice.getItems().add(1);
        shipChoice.getItems().add(2);
        portChoice.getItems().add(1);
        portChoice.getItems().add(2);
        portChoice.getItems().add(3);
        pList.getItems().add(1);
        pList.getItems().add(2);
        pList.getItems().add(3);
        pList.getItems().add(4);
        //Add contents.

        gridPane.add(cruiseID, 1, 1, 1, 1);
        gridPane.add(cruiseField, 2, 1, 1, 1);
        gridPane.add(sailingDatel, 1, 2, 1, 1);
        gridPane.add(sailingField, 2, 2, 1, 1);
        gridPane.add(returnDatel, 1, 3, 1, 1);
        gridPane.add(returnField, 2, 3, 1, 1);
        gridPane.add(shipID, 1, 4, 1, 1);
        gridPane.add(shipChoice, 2, 4, 1, 1);
        gridPane.add(portID, 1, 5, 1, 1);
        gridPane.add(portChoice, 2, 5, 1, 1);
        gridPane.add(button0, 4, 5, 1, 1);
        gridPane.add(passengerID, 1, 6, 1, 1);
        gridPane.add(pList, 2, 6, 1, 1);
        gridPane.add(button, 4, 6, 1, 1);
        gridPane.add(rbutton, 6, 7, 1, 1);
        //Add elements to the pane.

        gridPane.setHgap(10);
        gridPane.setVgap(20);
        //Set the horizontal and vertical gaps of the pane.

        Scene scene1 = new Scene(gridPane, 650, 400);
        //Create a scene.

        button0.setOnAction(e -> {
            try {
                cruiseIDnum = cruiseField.getText();
                shipIDnum = (int) shipChoice.getValue();
                portIDnum = (int) portChoice.getValue();
                sailingDate = ReadInput.readDate3((String) sailingField.getText());
                returnDate = ReadInput.readDate3((String) returnField.getText());

                insertData.createCruise(cruiseIDnum, sailingDate, returnDate, portIDnum, shipIDnum);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TestSystem.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(TestSystem.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception exc) {
                System.out.println("Wrong. Exit.");
                System.exit(0);
            }// Read user input and create a cruise.
        });

        button.setOnAction(e -> {
            selectedIndices = pList.getSelectionModel().getSelectedIndices();
            for (Object o : selectedIndices) {
                passengerList.add(Integer.parseInt(String.valueOf(o)) + 1);
            }
            try {
                addPassenger(cruiseIDnum, passengerList);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(TestSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Read user input and add passengers.
            //Read user input and add passengers.
        });

        //Scene2, generate reports.
        GridPane gridPane2 = new GridPane();
        Button report_1 = new Button("a. Revenue generated from the passengers on cruise by nationality and age");
        Button report_2 = new Button("b. Sailors and their supervisors on cruise");
        Button report_3 = new Button("c. Passengers on cruise sorted by consumption");
        Button report_4 = new Button("d. Passengers on cruise sorted by satisfaction rating");
        Button rbutton2 = new Button("Return");
        //Declare the buttons.

        gridPane2.add(report_1, 2, 2, 1, 1);
        gridPane2.add(report_2, 2, 3, 1, 1);
        gridPane2.add(report_3, 2, 4, 1, 1);
        gridPane2.add(report_4, 2, 5, 1, 1);
        gridPane2.add(rbutton2, 4, 8, 1, 1);
        //Add buttons to the pane.

        gridPane2.setHgap(5);
        gridPane2.setVgap(20);
        //Set the horizontal and vertical gaps of the pane.

        Scene scene2 = new Scene(gridPane2, 600, 300);
        //Create a scene.

        report_1.setOnAction(e -> {
            try {
                primaryStage.setScene(reportGenerate_1(primaryStage, scene2));
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(TestSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
            primaryStage.show();
        });//Show the first report.

        report_2.setOnAction(e -> {
            try {
                primaryStage.setScene(reportGenerate_2(primaryStage, scene2));
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(TestSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
            primaryStage.show();
        });//Show the second report.

        report_3.setOnAction(e -> {
            try {
                primaryStage.setScene(reportGenerate_3(primaryStage, scene2));
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(TestSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
            primaryStage.show();
        });//Show the third report.

        report_4.setOnAction(e -> {
            try {
                primaryStage.setScene(reportGenerate_4(primaryStage, scene2));
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(TestSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
            primaryStage.show();
        });//Show the fourth report.

        //Scene0, ask users to choose what to do.
        Label begin = new Label("\nWelcome! What do you want to do?");
        Button button_a = new Button("Create a Cruise");
        Button button_b = new Button("Generate Reports");
        StackPane stackPane = new StackPane();
        //Declare the elements.

        stackPane.getChildren().addAll(begin, button_a, button_b);
        StackPane.setAlignment(begin, Pos.TOP_CENTER);
        StackPane.setAlignment(button_a, Pos.CENTER_LEFT);
        StackPane.setAlignment(button_b, Pos.CENTER_RIGHT);
        Scene scene0 = new Scene(stackPane, 300, 200);
        //Add elements to the pane and set scene.

        primaryStage.setScene(scene0);
        button_a.setOnAction(e -> {
            primaryStage.setScene(scene1);
            primaryStage.show();
        });//Show scene1

        button_b.setOnAction(e -> {
            primaryStage.setScene(scene2);
            primaryStage.show();
        });//Show scene2

        primaryStage.show();

        rbutton.setOnAction(e -> {
            primaryStage.setScene(scene0);
            primaryStage.show();
        });//Show scene0

        rbutton2.setOnAction(e -> {
            primaryStage.setScene(scene0);
            primaryStage.show();
        });//Show scene0
    }

    /**
     * This method generates the first required report.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private static Scene reportGenerate_1(Stage pStage, Scene scene0) throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");//Load the driver.
        Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/CruiseSystem",
                "ab",
                "123");//Connect to the database.

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Comsumption");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries_n = new XYChart.Series();
        dataSeries_n.setName("Nationality");
        XYChart.Series dataSeries_a = new XYChart.Series();
        dataSeries_a.setName("Age");

        //Create a barchart.
        try {
            String query = "SELECT Nationality, SUM(Consumption) FROM Passenger GROUP BY Nationality";
            PreparedStatement pres = conn.prepareStatement(query);//Prepare statement.
            ResultSet rest = pres.executeQuery();// Execute the query.
            while (rest.next()) {
                dataSeries_n.getData().add(new XYChart.Data(rest.getString(1), Integer.parseInt(rest.getString(2))));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        } //Get the dataseries of nationality

        try {
            String query = "SELECT Age, SUM(Consumption) FROM Passenger GROUP BY Age";
            PreparedStatement pres = conn.prepareStatement(query);//Prepare statement.
            ResultSet rest = pres.executeQuery();// Execute the query.
            while (rest.next()) {
                dataSeries_a.getData().add(new XYChart.Data(rest.getString(1), Integer.parseInt(rest.getString(2))));
            }//Print out the result
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }//Get the dataseries of age

        GridPane gridPane = new GridPane();
        barChart.getData().add(dataSeries_n);
        barChart.getData().add(dataSeries_a);
        gridPane.add(barChart, 1, 1);

        Button button = new Button("Return");
        gridPane.add(button, 2, 4);
        Scene scene = new Scene(gridPane, 600, 500);
        //Create a scene to show this barchart.

        button.setOnAction(e -> {
            pStage.setScene(scene0);
            pStage.show();
        });
        return scene;//Return this scene.
    }

    /**
     * This method generates the second required report.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private static Scene reportGenerate_2(Stage pStage, Scene scene0) throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");//Load the driver.
        Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/CruiseSystem",
                "ab",
                "123");//Connect to the database.        

        GridPane gridPane = new GridPane();

        gridPane.add(new Label("SailorID"), 1, 1, 1, 1);
        gridPane.add(new Label("Name"), 2, 1, 1, 1);
        gridPane.add(new Label("DoB"), 3, 1, 1, 1);
        gridPane.add(new Label("Nationality"), 4, 1, 1, 1);
        gridPane.add(new Label("SupervisorID"), 5, 1, 1, 1);
        gridPane.add(new Label("SupervisorName"), 6, 1, 1, 1);
        //Add labels to the pane.

        try {
            String query = "SELECT * FROM Sailor";
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rest = stmt.executeQuery(query);// Execute the query.

            while (rest.next()) {

                TextField name = new TextField();
                name.setEditable(false);
                TextField id = new TextField();
                id.setEditable(false);
                TextField dob = new TextField();
                dob.setEditable(false);
                TextField nat = new TextField();
                nat.setEditable(false);
                TextField sup = new TextField();
                sup.setEditable(false);
                TextField supn = new TextField();
                supn.setEditable(false);
                //Declare text fields and disable editing.

                name.setText(rest.getString(1));
                id.setText(rest.getString(2));
                dob.setText(rest.getString(3));
                nat.setText(rest.getString(4));
                sup.setText(rest.getString(5));
                supn.setText(rest.getString(6));
                //Set the text values.

                gridPane.add(name, 1, rest.getRow() + 1, 1, 1);
                gridPane.add(id, 2, rest.getRow() + 1, 1, 1);
                gridPane.add(dob, 3, rest.getRow() + 1, 1, 1);
                gridPane.add(nat, 4, rest.getRow() + 1, 1, 1);
                gridPane.add(sup, 5, rest.getRow() + 1, 1, 1);
                gridPane.add(supn, 6, rest.getRow() + 1, 1, 1);
                //Add text fields to the pane.
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Button button = new Button("Return");
        gridPane.add(button, 6, 21);
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        button.setOnAction(e -> {
            pStage.setScene(scene0);
            pStage.show();
        });//Go back to scene0
        Scene scene = new Scene(gridPane, 700, 300);
        return scene;//Return the scene with this report.
    }

    /**
     * This method generates the third required report.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private static Scene reportGenerate_3(Stage pStage, Scene scene0) throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");//Load the driver.
        Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/CruiseSystem",
                "ab",
                "123");//Connect to the database. 
        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Consumption"), 1, 1, 1, 1);
        gridPane.add(new Label("PassengerID"), 2, 1, 1, 1);
        gridPane.add(new Label("Name"), 3, 1, 1, 1);
        gridPane.add(new Label("DateOfBirth"), 4, 1, 1, 1);
        gridPane.add(new Label("Age"), 5, 1, 1, 1);
        gridPane.add(new Label("Nationality"), 6, 1, 1, 1);
        gridPane.add(new Label("Address"), 7, 1, 1, 1);
        gridPane.add(new Label("SatisfRate"), 8, 1, 1, 1);
        //Add labels to the pane.

        try {
            String query = "SELECT * FROM Passenger ORDER BY Consumption DESC";
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rest = stmt.executeQuery(query);// Execute the query.

            while (rest.next()) {
                TextField consumption = new TextField();
                consumption.setEditable(false);
                TextField id = new TextField();
                id.setEditable(false);
                TextField name = new TextField();
                name.setEditable(false);
                TextField dob = new TextField();
                dob.setEditable(false);
                TextField age = new TextField();
                age.setEditable(false);
                TextField nat = new TextField();
                nat.setEditable(false);
                TextField add = new TextField();
                add.setEditable(false);
                TextField satisf = new TextField();
                satisf.setEditable(false);
                //Declare text fields and disable editing.

                consumption.setText(rest.getString(8));
                id.setText(rest.getString(1));
                name.setText(rest.getString(2));
                dob.setText(rest.getString(3));
                age.setText(rest.getString(4));
                nat.setText(rest.getString(5));
                add.setText(rest.getString(6));
                satisf.setText(rest.getString(7));
                //Set the text values.

                gridPane.add(consumption, 1, rest.getRow() + 1, 1, 1);
                gridPane.add(id, 2, rest.getRow() + 1, 1, 1);
                gridPane.add(name, 3, rest.getRow() + 1, 1, 1);
                gridPane.add(dob, 4, rest.getRow() + 1, 1, 1);
                gridPane.add(age, 5, rest.getRow() + 1, 1, 1);
                gridPane.add(nat, 6, rest.getRow() + 1, 1, 1);
                gridPane.add(add, 7, rest.getRow() + 1, 1, 1);
                gridPane.add(satisf, 8, rest.getRow() + 1, 1, 1);
                //Add elements to the pane.
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        Button button = new Button("Return");
        gridPane.add(button, 8, 21);
        button.setOnAction(e -> {
            pStage.setScene(scene0);
            pStage.show();
        });//Go back to scene0
        Scene scene = new Scene(gridPane, 800, 300);
        return scene;//Return the scene with this report.
    }

    /**
     * This method generates the fourth required report.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private static Scene reportGenerate_4(Stage pStage, Scene scene0) throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");//Load the driver.
        Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/CruiseSystem",
                "ab",
                "123");//Connect to the database. 
        GridPane gridPane = new GridPane();
        gridPane.add(new Label("SatisfRate"), 1, 1, 1, 1);
        gridPane.add(new Label("PassengerID"), 2, 1, 1, 1);
        gridPane.add(new Label("Name"), 3, 1, 1, 1);
        gridPane.add(new Label("DateOfBirth"), 4, 1, 1, 1);
        gridPane.add(new Label("Age"), 5, 1, 1, 1);
        gridPane.add(new Label("Nationality"), 6, 1, 1, 1);
        gridPane.add(new Label("Address"), 7, 1, 1, 1);
        gridPane.add(new Label("Consumption"), 8, 1, 1, 1);
        //Add labels to the pane.

        try {
            String query = "SELECT * FROM Passenger ORDER BY SatisfactionRating DESC";
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rest = stmt.executeQuery(query);// Execute the query.

            while (rest.next()) {
                TextField consumption = new TextField();
                consumption.setEditable(false);
                TextField id = new TextField();
                id.setEditable(false);
                TextField name = new TextField();
                name.setEditable(false);
                TextField dob = new TextField();
                dob.setEditable(false);
                TextField age = new TextField();
                age.setEditable(false);
                TextField nat = new TextField();
                nat.setEditable(false);
                TextField add = new TextField();
                add.setEditable(false);
                TextField satisf = new TextField();
                satisf.setEditable(false);
                //Declare text fields and disable editing.

                consumption.setText(rest.getString(7));
                id.setText(rest.getString(1));
                name.setText(rest.getString(2));
                dob.setText(rest.getString(3));
                age.setText(rest.getString(4));
                nat.setText(rest.getString(5));
                add.setText(rest.getString(6));
                satisf.setText(rest.getString(8));
                //Set values.

                gridPane.add(consumption, 1, rest.getRow() + 1, 1, 1);
                gridPane.add(id, 2, rest.getRow() + 1, 1, 1);
                gridPane.add(name, 3, rest.getRow() + 1, 1, 1);
                gridPane.add(dob, 4, rest.getRow() + 1, 1, 1);
                gridPane.add(age, 5, rest.getRow() + 1, 1, 1);
                gridPane.add(nat, 6, rest.getRow() + 1, 1, 1);
                gridPane.add(add, 7, rest.getRow() + 1, 1, 1);
                gridPane.add(satisf, 8, rest.getRow() + 1, 1, 1);
                //Add to the pane.
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        Button button = new Button("Return");
        gridPane.add(button, 8, 21);
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        button.setOnAction(e -> {
            pStage.setScene(scene0);
            pStage.show();
        });//Go back to scene0
        Scene scene = new Scene(gridPane, 800, 300);
        return scene;//Return the scene with this report.
    }

    /**
     * Main method. Add the data into database, create a cruise, and generate
     * reports.
     *
     * @param args Line argument.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        InsertData insertData = new InsertData();

        insertData.addPassengerToDB();
        insertData.addPortToDB();
        insertData.addShipToDB();
        insertData.addSailorToDB();
        insertData.addCruiseToDB();
        insertData.addPassengerCruise();
        //Insert the data of passengers, ports, ships and sailors to the database.
        //Create the cruise table.

        launch(args);//Launch it.

    }//End of main.
}//End of class.
