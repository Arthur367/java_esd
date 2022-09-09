/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package esddesktop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author netbot
 */
public class EsdDesktop {

    private static String dbURL = "\"jdbc:derby:myDB;create=true\", \"APP\" , \"\"";
    private static String tableName = "logs";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Server.main(args);
        createConnection();
        createTable();
//        insertRestaurants("five", "LaVals", "Berkeley");
//        selectRestaurants();
//        shutdown();

    }

    private static void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Get a connection
            conn = DriverManager.getConnection("jdbc:derby:myDB;create=true", "APP", "");
        } catch (Exception except) {
            except.printStackTrace();
        }
    }

    private static void createTable() {
        try {
            stmt = conn.createStatement();
            stmt.execute("create table " + tableName+ " (id varchar(128),name varchar(128), city varchar(128))");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void insertRestaurants(String id, String restName, String cityName) {
        try {
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " values ("
                    + id + ",'" + restName + "','" + cityName + "')");
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    private static void selectRestaurants() {
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i) + "\t\t");
            }

            System.out.println("\n-------------------------------------------------");

            while (results.next()) {
                int id = results.getInt(1);
                String restName = results.getString(2);
                String cityName = results.getString(3);
                System.out.println(id + "\t\t" + restName + "\t\t" + cityName);
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    private static void shutdown() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }
        } catch (SQLException sqlExcept) {

        }

    }

}
