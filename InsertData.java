package question_1;

import java.sql.*;
import java.time.LocalDate;

/**
 * This class adds the data of passengers, ports, ships and sailors to the
 * database, and also creates a cruise.
 *
 * @author yueyin2
 */
public class InsertData {

    private int shipID = 0;
    private int cruiseID = 0;

    /**
     * Get ship ID.
     *
     * @return shipID
     */
    public int getShipID() {
        return shipID;
    }

    /**
     * Get cruise ID.
     *
     * @return cruiseID
     */
    public int getCruiseID() {
        return cruiseID;
    }

    /**
     * This method creates Passenger table and adds passenger data.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addPassengerToDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        //Load the driver
        Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/CruiseSystem",
                "ab",
                "123");
        //Connect to the database.

        Statement stat = conn.createStatement();

        try {
            stat.executeUpdate("CREATE TABLE Passenger ("
                    + "PassengerID int, "
                    + "Name varchar(255),"
                    + "DateOfBirth date,"
                    + "Age int,"
                    + "Nationality varchar(255),"
                    + "Address varchar(255),"
                    + "SatisfactionRating int,"
                    + "Consumption int,"
                    + "CruiseID varchar(255),"
                    + "PRIMARY KEY (PassengerID)"
                    + ")");
        } catch (SQLTransactionRollbackException e) {
            System.out.println("Table already exists.");
        } //Create Passenger table.

        try {
            stat.executeUpdate("INSERT INTO Passenger ("
                    + "PassengerID, Name, DateOfBirth, Age, Nationality, Address, SatisfactionRating, Consumption) "
                    + "VALUES"
                    + "(1, 'Passenger1','03/10/1990',28,'Australia','Street1',6,500)");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Record Already exists.");
        } //Insert the data of the first passenger.

        try {
            stat.executeUpdate("INSERT INTO Passenger ("
                    + "PassengerID, Name, DateOfBirth, Age, Nationality, Address, SatisfactionRating, Consumption) "
                    + "VALUES"
                    + "(2, 'Passenger2','03/11/1989',29,'Australia','Street2',8,1000)");
        } catch (SQLIntegrityConstraintViolationException e2) {
            System.out.println("Record Already exists.");
        }//Insert the data of the second passenger.

        try {
            stat.executeUpdate("INSERT INTO Passenger ("
                    + "PassengerID, Name, DateOfBirth, Age, Nationality, Address, SatisfactionRating, Consumption) "
                    + "VALUES"
                    + "(3, 'Passenger3','06/11/1970',48,'U.K.','Street3',6,800)");
        } catch (SQLIntegrityConstraintViolationException e3) {
            System.out.println("Record Already exists.");
        }//Insert the data of the third passenger.

        try {
            stat.executeUpdate("INSERT INTO Passenger ("
                    + "PassengerID, Name, DateOfBirth, Age, Nationality, Address, SatisfactionRating, Consumption) "
                    + "VALUES"
                    + "(4, 'Passenger4','06/11/1960',58,'U.S.','Street4',3,200)");
        } catch (SQLIntegrityConstraintViolationException e4) {
            System.out.println("Record Already exists.");
        }   //Insert the data of the fourth passenger.
    }

    /**
     * This method creates Port table and adds port data.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addPortToDB() throws ClassNotFoundException, SQLException {

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        //Load the driver.
        Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/CruiseSystem",
                "ab",
                "123");
        //Connect to the database.

        Statement stat = conn.createStatement();

        try {
            stat.executeUpdate("CREATE TABLE Port ("
                    + "PortID int, "
                    + "Name varchar(255),"
                    + "Country varchar(255),"
                    + "Population int,"
                    + "DockingFee int,"
                    + "Passport int,"
                    + "PRIMARY KEY (PortID)"
                    + ")");
        } catch (SQLTransactionRollbackException e) {
            System.out.println("Table already exists.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }//Create Port table.

        try {
            stat.executeUpdate("INSERT INTO Port ("
                    + "PortID, Name, Country, Population, DockingFee, Passport) "
                    + "VALUES"
                    + "(1, 'Sourth','Australia',10000,500,1)");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Record Already exists.");
        } //Insert the data of the first port.

        try {
            stat.executeUpdate("INSERT INTO Port ("
                    + "PortID, Name, Country, Population, DockingFee, Passport) "
                    + "VALUES ("
                    + "2, 'North','China',50000,600,1)");
        } catch (SQLIntegrityConstraintViolationException e2) {
            System.out.println("Record Already exists.");
        }//Insert the data of the second port.

        try {
            stat.executeUpdate("INSERT INTO Port ("
                    + "PortID, Name, Country, Population, DockingFee, Passport) "
                    + "VALUES"
                    + "(3, 'East','Singapore',5000,300,0)");
        } catch (SQLIntegrityConstraintViolationException e2) {
            System.out.println("Record Already exists.");
        }//Insert the data of the third port.
    }

    /**
     * This method creates Ship table and adds ship data.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addShipToDB() throws ClassNotFoundException, SQLException {

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        //Load the driver.
        Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/CruiseSystem",
                "ab",
                "123");
        //Connect to the database.

        Statement stat = conn.createStatement();

        try {
            stat.executeUpdate("CREATE TABLE Ship ("
                    + "ShipID int, "
                    + "Name varchar(255),"
                    + "Weight int,"
                    + "BuiltYear int,"
                    + "Capacity int,"
                    + "PRIMARY KEY (ShipID)"
                    + ")");
        } catch (SQLTransactionRollbackException e) {
            System.out.println("Table already exists.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }//Create Ship table.

        try {
            stat.executeUpdate("INSERT INTO Ship ("
                    + "ShipID, Name, Weight, BuiltYear, Capacity) "
                    + "VALUES"
                    + "(1, 'Ship1',500,2014,500)");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Record Already exists.");
        }// Insert the data of the first ship.

        try {
            stat.executeUpdate("INSERT INTO Ship ("
                    + "ShipID, Name, Weight, BuiltYear, Capacity) "
                    + "VALUES"
                    + "(2, 'Ship2',600,2016,600)");
        } catch (SQLIntegrityConstraintViolationException e2) {
            System.out.println("Record Already exists.");
        }// Insert the data of the second ship.
    }

    /**
     * This method creates Ship table and adds ship data.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addSailorToDB() throws ClassNotFoundException, SQLException {

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        //Load the driver.
        Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/CruiseSystem",
                "ab",
                "123");
        //Connect to the database.

        Statement stat = conn.createStatement();

        try {
            stat.executeUpdate("CREATE TABLE Sailor ("
                    + "SailorID int, "
                    + "Name varchar(255),"
                    + "DateOfBirth varchar(255),"
                    + "Nationality varchar(255),"
                    + "SupervisorID int, "
                    + "SupervisorName varchar(255), "
                    + "ShipID int,"
                    + "PRIMARY KEY (SailorID)"
                    + ")");
        } catch (SQLTransactionRollbackException e) {
            System.out.println("Table already exists.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }//Create Sailor table.

        try {
            stat.executeUpdate("INSERT INTO Sailor ("
                    + "SailorID, Name, DateOfBirth, Nationality,SupervisorID, SupervisorName, ShipID) "
                    + "VALUES"
                    + "(1, 'Sailor1','07/01/1990','Australia',1,'Sailor1',1)");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Record Already exists.");
        }// Insert the data of the first sailor.

        try {
            stat.executeUpdate("INSERT INTO Sailor ("
                    + "SailorID, Name, DateOfBirth, Nationality,SupervisorID, SupervisorName, ShipID) "
                    + "VALUES"
                    + "(2, 'Sailor2','12/11/1989','U.K.',1,'Sailor1',1)");
        } catch (SQLIntegrityConstraintViolationException e2) {
            System.out.println("Record Already exists.");
        }// Insert the data of the second sailor.

        try {
            stat.executeUpdate("INSERT INTO Sailor ("
                    + "SailorID, Name, DateOfBirth, Nationality,SupervisorID, SupervisorName,ShipID) "
                    + "VALUES"
                    + "(3, 'Sailor3','07/02/1985','China',4,'Sailor4', 2)");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Record Already exists.");
        }// Insert the data of the third sailor.

        try {
            stat.executeUpdate("INSERT INTO Sailor ("
                    + "SailorID, Name, DateOfBirth, Nationality,SupervisorID, SupervisorName,ShipID) "
                    + "VALUES"
                    + "(4, 'Sailor4','13/10/1989','U.S.',4, 'Sailor4', 2)");
        } catch (SQLIntegrityConstraintViolationException e2) {
            System.out.println("Record Already exists.");
        }// Insert the data of the fourth sailor.
    }

    /**
     * This method creates a cruise table in the database.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addCruiseToDB() throws ClassNotFoundException, SQLException {

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        //Load the driver.
        Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/CruiseSystem",
                "ab",
                "123");
        //Connect to the database.

        Statement stat = conn.createStatement();

        try {
            stat.executeUpdate("CREATE TABLE Cruise ("
                    + "CruiseID varchar(255), "
                    + "SailingDate date,"
                    + "ReturnDate date,"
                    + "PortID int,"
                    + "ShipID int,"
                    + "PRIMARY KEY (CruiseID)"
                    + ")");
        } catch (SQLTransactionRollbackException e) {
            System.out.println("Table already exists.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }//Create Cruise table.
    }

    /**
     * This method creates a cruise.
     *
     * @param cruiseID
     * @param portID
     * @param sailingDate
     * @param returnDate
     * @param shipID
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void createCruise(String cruiseID, LocalDate sailingDate, LocalDate returnDate, int portID, int shipID) throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        //Load the driver.
        Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/CruiseSystem",
                "ab",
                "123");
        //Connect to the database.

        Statement stat = conn.createStatement();
        try {
            String prepareSQL = "INSERT INTO Cruise ("
                    + "CruiseID, SailingDate, ReturnDate, PortID, ShipID)"
                    + "VALUES ("
                    + "?,?,?,?,?"
                    + ")";
            PreparedStatement pres = conn.prepareStatement(prepareSQL);//Prepare statement
            pres.setString(1, cruiseID);
            pres.setDate(2, Date.valueOf(sailingDate));
            pres.setDate(3, Date.valueOf(returnDate));
            pres.setInt(4, portID);
            pres.setInt(5, shipID);
            pres.executeUpdate();// Execute the query.
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Record Already exists.");
        } //Read the input from users and insert the data into table.
    }

    /**
     * This method creates the PassengerCruise table.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addPassengerCruise() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        //Load the driver.
        Connection conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/CruiseSystem",
                "ab",
                "123");
        //Connect to the database.

        Statement stat = conn.createStatement();

        try {
            stat.executeUpdate("CREATE TABLE PassengerCruise ("
                    + "PassengerID int, "
                    + "CruiseID varchar(255)"
                    + ")");
        } catch (SQLTransactionRollbackException e) {
            System.out.println("Table already exists.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }//Create PassengerCruise table.
    }
}//End of class.
