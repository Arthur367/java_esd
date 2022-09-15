/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package esddesktop;

import com.fasterxml.jackson.databind.ObjectMapper;
import static esddesktop.MainFrame.frame;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author netbot
 */
public class EsdDesktop {

    private static String dbURL = "\"jdbc:derby:myDB;create=true\", \"APP\" , \"\"";
    public static String tableName = "logs";
    private static String trialTable = "trial";
    // jdbc Connection
    public static Connection conn = null;
    public static Statement stmt = null;
    public Preferences pref = Preferences.userRoot().node(getClass().getName());

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        MainFrame main = new MainFrame();

        if (checkInternetConnection()) {

            String key = main.CheckKey();
            if (!key.equals("")) {
                var objectMapper = new ObjectMapper();
                String url = "https://esd.netbotapp.com/project/getUsers/";
                JSONObject body = new JSONObject();
                body.put("key", key);
                System.out.println(key);

                String requestBody = objectMapper.writeValueAsString(body);

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                        .build();

                HttpResponse<String> response;

                response = client.send(request, HttpResponse.BodyHandlers.ofString());

                JSONParser parser = new JSONParser();
                try {
                    JSONObject res = (JSONObject) parser.parse(response.body());
                    System.out.println(res);
                    if (response.statusCode() != 200) {
                        if (res.get("message").equals("No Such License Key")) {
//                            Notify.create()
//                            .title("Error")
//                            .text(res.get("message").toString())
//                            .position(Pos.TOP_RIGHT)
//                            .hideAfter(5000)
//                            .shake(250, 5)
//                            .darkStyle() // There are two default themes darkStyle() and default.
//                            .showError();
                            JOptionPane.showMessageDialog(frame, res.get("message").toString(), "Error", JOptionPane.ERROR_MESSAGE);
                            MainFrame.main(args);
                        }
                        System.out.println(res);
                    } else {

                        Server.main(args);
                        createConnection();
                        tableExists(tableName, conn);

                    }

                } catch (HeadlessException | ParseException ex) {
                    System.out.println(ex);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please reenter LicenseKey to Start Service again", "Error", JOptionPane.ERROR_MESSAGE);
                Main.main(args);
            }

        } else {
            JOptionPane.showMessageDialog(frame, "No Internet.Check Connectivity", "Error", JOptionPane.ERROR_MESSAGE);
        }

//        createTable();
//        shutdown();
    }

    public static boolean checkInternetConnection() {
        boolean status = false;
        Socket sock = new Socket();
        InetSocketAddress address = new InetSocketAddress("www.google.com", 80);

        try {
            sock.connect(address, 3000);
            if (sock.isConnected()) {
                status = true;
            }
        } catch (IOException e) {
            status = false;
        } finally {
            try {
                sock.close();
            } catch (IOException e) {
            }
        }

        return status;
    }

    private String CheckPref() {
        String key = "KEY";
        String value = pref.get(key, "");
        System.out.println("Key: " + value);
        return value;
    }

    public static boolean tableExists(String tableName, Connection conn) throws SQLException {
        boolean found = false;
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet rs = databaseMetaData.getTables(null, null, tableName.toUpperCase(), null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            found = true;
//            JSONObject obj = new JSONObject();
//            obj.put("name", "Berkeley");
//            insertRestaurants("LaVals", obj.toString());
//            selectRestaurants();
            break;
        }
        if (found == false) {
//            createTable();
            createLogTable();
            JSONObject obj = new JSONObject();
            obj.put("name", "Berkeley");
//            insertRestaurants("LaVals", obj.toString());
//            selectRestaurants();

        }

        return found;
    }

    private static void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Get a connection
            conn = DriverManager.getConnection("jdbc:derby:myDB;create=true", "APP", "");
        } catch (ClassNotFoundException | SQLException except) {
            except.printStackTrace();
        }
    }

    private static void createTable() {
        try {

            stmt = conn.createStatement();
            stmt.execute("create table " + trialTable + " (id INT not null primary key\n"
                    + " GENERATED ALWAYS AS IDENTITY\n"
                    + " (START WITH 1, INCREMENT BY 1), name varchar(128), city varchar(250))");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void createLogTable() {
        try {

            stmt = conn.createStatement();
            stmt.execute("create table " + tableName + " (id INT not null primary key\n"
                    + " GENERATED ALWAYS AS IDENTITY\n"
                    + " (START WITH 1, INCREMENT BY 1),createdinvoiceno varchar(128),"
                    + " createdinvoicedate varchar(128),"
                    + " auth_headers varchar(128),"
                    + " device varchar(128), end_point varchar(128),"
                    + " request_date_time varchar(128), request_body varchar(2000), "
                    + "response_type varchar(128), "
                    + "response_time varchar(128), "
                    + "response_body varchar(2000))");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void insertRestaurants(String restName, String cityName) {
        try {
            stmt = conn.createStatement();
            stmt.execute("insert into " + trialTable + "(name, city ) values ("
                    + "'" + restName + "','" + cityName + "')");
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    public static void insertLog(String createdinvoiceno,
            String createdinvoicedate, String auth_headers,
            String device, String end_point, String request_date_time,
            String request_body, String response_type,
            String response_time, String response_body) {
        try {
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + "(createdinvoiceno, "
                    + "createdinvoicedate, "
                    + "auth_headers, device, "
                    + " end_point,request_date_time,"
                    + "request_body, response_type, "
                    + "response_time, response_body  ) values ("
                    + "'" + createdinvoiceno + "','"
                    + createdinvoicedate + "','"
                    + auth_headers + "','" + device
                    + "','" + end_point + "','"
                    + request_date_time + "','" + request_body
                    + "','" + response_type + "','" + response_time
                    + "','" + response_body + "')");
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    private static void selectRestaurants() {
        try {
            stmt = conn.createStatement();
            try ( ResultSet results = stmt.executeQuery("select * from " + trialTable)) {
                ResultSetMetaData rsmd = results.getMetaData();
                int numberCols = rsmd.getColumnCount();
                for (int i = 1; i <= numberCols; i++) {
                    //print Column Names
                    System.out.print(rsmd.getColumnLabel(i) + "\t\t");
                }

                System.out.println("\n-------------------------------------------------");
                ArrayList str = new ArrayList();
                while (results.next()) {

                    String id = results.getString(1);
                    String restName = results.getString(2);
                    String cityName = results.getString(3);
                    System.out.println(id + "\t\t" + restName + "\t\t" + cityName);

                    str.add(id + "\t\t" + restName + "\t\t" + cityName);
                }
                System.out.println(str.toString());

            }
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    public static void selectLog() {
        try {
            stmt = conn.createStatement();
            try ( ResultSet results = stmt.executeQuery("select * from " + tableName)) {
                ResultSetMetaData rsmd = results.getMetaData();
                int numberCols = rsmd.getColumnCount();
                for (int i = 1; i <= numberCols; i++) {
                    //print Column Names
                    System.out.print(rsmd.getColumnLabel(i) + "\t\t");
                }

                System.out.println("\n-------------------------------------------------");
                ArrayList str = new ArrayList();
                while (results.next()) {

                    String id = results.getString(1);
                    String restName = results.getString(2);
                    String cityName = results.getString(3);
                    String headers = results.getString(4);
                    String device = results.getString(5);
                    String requestdate = results.getString(6);
                    String requestdata = results.getString(7);
                    String responseType = results.getString(8);
                    String responseTime = results.getString(9);
                    String responsedata = results.getString(10);
                    String response = results.getString(11);

                    System.out.println(id + "\t\t" + restName + "\t\t" + cityName + "\t\t"
                            + headers + "\t\t" + device + "\t\t" + requestdate + "\t\t" + requestdata
                            + "\t\t" + responseType + "\t\t" + responseTime + responsedata + response);

                    str.add(id + "\t\t" + restName + "\t\t" + cityName);
                }
                System.out.println(str.toString());

            }
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
