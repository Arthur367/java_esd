/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esddesktop;

import TremolZFP.FP;
import TremolZFP.OptionVATClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import static esddesktop.Tray.alive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author netbot
 */
public class Server {

    private static final String USER_AGENT = "Mozilla/5.0";
    public static final int PORT = 35040;
    private static final String GET_URL = "http://localhost:" + PORT;
    public static LocalDateTime now = LocalDateTime.now();
    public static HttpServer server = new HttpServer() {
        @Override
        public void bind(InetSocketAddress isa, int i) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void start() {

        }

        @Override
        public void setExecutor(Executor exctr) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public Executor getExecutor() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void stop(int i) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public HttpContext createContext(String string, HttpHandler hh) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public HttpContext createContext(String string) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void removeContext(String string) throws IllegalArgumentException {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void removeContext(HttpContext hc) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public InetSocketAddress getAddress() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    };

    public static void main(String[] args) throws Exception {
//      getResponse();
        createServer();

    }

    public static void createServer() throws IOException {

        server = HttpServer.create(new InetSocketAddress(PORT), 0);
        try {
            System.out.println(alive);
           while(alive){
                server.createContext("/", new StartServerResponse());
            server.createContext("/esd", new RunESDDevice());
            server.createContext("/device", new RunDevice());
            server.createContext("/dtr", new RunDTRDevice());
            server.createContext("/ace", new RunACEDevice());
            server.createContext("/datecs", new RunDatecsDevice());
            server.createContext("/total", new RunTotalDevice());
            server.setExecutor(null);
            server.start();
            System.out.println("Server Started at " + PORT);
            String args[] = null;
            Tray.main(args);
            break;
           }

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    private static class StartServerResponse implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            String response = "ESD Working ... ";
            he.sendResponseHeaders(200, response.length());
            try ( OutputStream out = he.getResponseBody()) {
                out.write(response.getBytes());
            }

        }
    }

    private static class RunESDDevice implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException, JsonProcessingException {

            try {

                if ("GET".equals(he.getRequestMethod())) {
                    String response = "Fail GET can not be used in this function";
                    he.sendResponseHeaders(400, response.length());
                    try ( OutputStream os = he.getResponseBody()) {
                        os.write(response.getBytes());
                    }

                }

                InputStreamReader isr = new InputStreamReader(he.getRequestBody());
                Headers headers = he.getRequestHeaders();
                String query;
                StringBuilder buffer = new StringBuilder();
                try ( BufferedReader br = new BufferedReader(isr)) {
//                query = br.readLine();
                    while ((query = br.readLine()) != null) {
                        buffer.append(query);
                    }
                }

                String result = buffer.toString();
                JSONParser parser = new JSONParser();
                JSONObject obj = new JSONObject();
                try {
                    obj = (JSONObject) parser.parse(result);
                } catch (ParseException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }

                Map<String, Object> _hea = new HashMap<>();

                Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
                for (Map.Entry<String, List<String>> entry : entries) {
                    _hea.put(entry.getKey(), entry.getValue());
                }

                RequestESDSignature(_hea, obj, he);

            } catch (UnsupportedEncodingException | MalformedURLException | ParseException | URISyntaxException | InterruptedException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private static class RunDevice implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            if ("GET".equals(he.getRequestMethod())) {
                String response = "Fail GET can not be used in this function";
                he.sendResponseHeaders(400, response.length());
                try ( OutputStream os = he.getResponseBody()) {
                    os.write(response.getBytes());
                }

            }

            InputStreamReader isr = new InputStreamReader(he.getRequestBody());
            Headers headers = he.getRequestHeaders();
            String query;
            StringBuilder buffer = new StringBuilder();
            try ( BufferedReader br = new BufferedReader(isr)) {
//                query = br.readLine();                
                while ((query = br.readLine()) != null) {
                    buffer.append(query);
                }
            }

            String result = buffer.toString();
            JSONParser parser = new JSONParser();
            JSONObject obj = new JSONObject();
            try {
                obj = (JSONObject) parser.parse(result);
            } catch (ParseException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }

            Map<String, Object> _hea = new HashMap<>();

            Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
            for (Map.Entry<String, List<String>> entry : entries) {
                _hea.put(entry.getKey(), entry.getValue());
            }

            RequestDevice(_hea, obj);

            he.getResponseHeaders().set("Content-Type", "application/json");
            he.sendResponseHeaders(200, obj.toString().length());

            try ( OutputStream os = he.getResponseBody()) {
                os.write(obj.toString().getBytes());
                os.flush();
            }
        }

    }

    private static class RunDTRDevice implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            if ("POST".equals(he.getRequestMethod())) {
                String response = "Fail POST can not be used in this function";
                he.sendResponseHeaders(400, response.length());
                try ( OutputStream os = he.getResponseBody()) {
                    os.write(response.getBytes());
                }

            }

            InputStreamReader isr = new InputStreamReader(he.getRequestBody());
            Headers headers = he.getRequestHeaders();
            String query;
            StringBuilder buffer = new StringBuilder();
            try ( BufferedReader br = new BufferedReader(isr)) {
//                query = br.readLine();                
                while ((query = br.readLine()) != null) {
                    buffer.append(query);
                }
            }

//             String result = buffer.toString();
//             JSONParser parser= new JSONParser();
//             JSONObject obj = new JSONObject();             
//            try {
//                obj = (JSONObject)parser.parse(result);
//            } catch (ParseException ex) {
//                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//            }
            Map<String, Object> _hea = new HashMap<>();

            Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
            for (Map.Entry<String, List<String>> entry : entries) {
                _hea.put(entry.getKey(), entry.getValue());
            }

//            RequestDevice(_hea, obj);
            System.out.println(_hea);
            String importL = _hea.get("Importpath").toString().replaceAll("\\[", "").replaceAll("\\]", "");
            System.out.println(importL);
//     String export = _hea.get("Exportpath").toString().replaceAll("\\[", "").replaceAll("\\]","");         
//     String error = _hea.get("Errorpath").toString().replaceAll("\\[", "").replaceAll("\\]","");
//     String file = _hea.get("Filename").toString().replaceAll("\\[", "").replaceAll("\\]",""); String invoiceN = _hea.get("Invoicenumber").toString().replaceAll("\\[", "").replaceAll("\\]","");
//     String printD = _hea.get("Printdelay").toString().replaceAll("\\[", "").replaceAll("\\]","");
//     String qr_path = _hea.get("Qrimagepath").toString().replaceAll("\\[", "").replaceAll("\\]","");

            Map<String, Object> parameters = new HashMap();
            String scannedline = new String();
            String cu_date = null;
            String cu_inv = null;
            String cu_ser = null;
            String http = null;

            File file = new File(importL);
            try {
                Scanner scan = new Scanner(file);
                while (scan.hasNextLine()) {
                    scannedline = scan.nextLine();
//                        System.out.println(scannedline);                
//                        System.out.println(scannedline);  
                    if (scannedline.contains("DATE")) {
                        String date = scannedline.trim();
                        cu_date = date.replace("|", "").replace("DATE:", "");
                        System.out.println(cu_date);
                    }
                    if (scannedline.contains("CUSN")) {
                        String serial = scannedline.trim();
                        cu_ser = serial.replace("|", "").replace("CUSN:", "");
                        System.out.println(cu_ser);
                    }
                    if (scannedline.contains("CUIN")) {
                        String serial = scannedline.trim();
                        cu_inv = serial.replace("|", "").replace("CUIN:", "");
                        System.out.println(cu_inv);
                    }
                    if (scannedline.contains("https")) {
                        http = scannedline;
                        System.out.println(http);
                    }

                }
                scan.close();

            } catch (FileNotFoundException e) {
                String response = "Fail GET can not find file";
                he.getResponseHeaders().set("Content-Type", "application/json");
                he.sendResponseHeaders(200, response.length());

                try ( OutputStream os = he.getResponseBody()) {
                    os.write(response.getBytes());
                    os.flush();
                }
                e.printStackTrace();
            }

            JSONObject json = new JSONObject();
            json.put("invoice_number", cu_inv.trim());
            json.put("cu_serial_number", cu_ser.trim());
            json.put("cu_invoice_number", cu_inv.trim());
            json.put("verify_url", http.trim());
            json.put("description", "Invoice Signed Success");

            he.getResponseHeaders().set("Content-Type", "application/json");
            he.sendResponseHeaders(200, json.toString().length());

            try ( OutputStream os = he.getResponseBody()) {
                os.write(json.toString().getBytes());
                os.flush();
            }
        }

    }

    private static class RunACEDevice implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException, UnsupportedEncodingException {

            try {

                if ("GET".equals(he.getRequestMethod())) {
                    String response = "Fail GET can not be used in this function";
                    he.sendResponseHeaders(400, response.length());
                    try ( OutputStream os = he.getResponseBody()) {
                        os.write(response.getBytes());
                    }

                }

                InputStreamReader isr = new InputStreamReader(he.getRequestBody());
                Headers headers = he.getRequestHeaders();
                String query;
                StringBuilder buffer = new StringBuilder();
                try ( BufferedReader br = new BufferedReader(isr)) {
//                query = br.readLine();
                    while ((query = br.readLine()) != null) {
                        buffer.append(query);
                    }
                }

                String result = buffer.toString();
                JSONParser parser = new JSONParser();
                JSONObject obj = new JSONObject();
                try {
                    obj = (JSONObject) parser.parse(result);
                } catch (ParseException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }

                Map<String, Object> _hea = new HashMap<>();

                Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
                for (Map.Entry<String, List<String>> entry : entries) {
                    _hea.put(entry.getKey(), entry.getValue());
                }
                RequestACE(_hea, obj, he);

            } catch (ParseException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private static class RunDatecsDevice implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
//            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

            try {

                if ("GET".equals(he.getRequestMethod())) {
                    String response = "Fail GET can not be used in this function";
                    he.sendResponseHeaders(400, response.length());
                    try ( OutputStream os = he.getResponseBody()) {
                        os.write(response.getBytes());
                    }

                }

                InputStreamReader isr = new InputStreamReader(he.getRequestBody());
                Headers headers = he.getRequestHeaders();
                String query;
                StringBuilder buffer = new StringBuilder();
                try ( BufferedReader br = new BufferedReader(isr)) {
//                query = br.readLine();
                    while ((query = br.readLine()) != null) {
                        buffer.append(query);
                    }
                }

                String result = buffer.toString();
                JSONParser parser = new JSONParser();
                JSONObject obj = new JSONObject();
                try {
                    obj = (JSONObject) parser.parse(result);
                } catch (ParseException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }

                Map<String, Object> _hea = new HashMap<>();

                Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
                for (Map.Entry<String, List<String>> entry : entries) {
                    _hea.put(entry.getKey(), entry.getValue());
                }
                RequestDATECS(_hea, obj, he);

            } catch (ParseException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private static void RequestDATECS(Map<String, Object> headers, JSONObject payload, HttpExchange he) throws UnsupportedEncodingException, MalformedURLException, ParseException {
        String hostname = headers.get("Hostname").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String requestid = headers.get("Requestid").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        System.out.println(hostname);
        System.out.println(requestid);
        JSONArray array = new JSONArray();
        JSONArray items;
        int transactionType = 0;
        if (payload.get("vouchertype") == "Tax Invoice") {
            transactionType = 0;
        } else if (payload.get("vouchertype") == "Credit Note") {
            transactionType = 1;
        } else if (payload.get("vouchertype") == "Debit Note") {
            transactionType = 2;
        }
        System.out.println(transactionType);
        if (payload.get("items_list") == null) {
            items = JSONArray.fromObject(payload.get("led_list"));
        } else {
            items = JSONArray.fromObject(payload.get("items_list"));
        }
        System.out.println(payload.get("hscode"));
        for (Object item : items) {
            HashMap it = new HashMap();
            it.putAll((Map) item);
//            String array_items = it.get("hscode").toString() + " " + it.get("stockitemname") + " " + it.get("qty") + " " + it.get("rate") + " " + it.get("amt");
//            array.add();
            String hscode = "";
            if (it.get("hscode") != null) {
                hscode = it.get("hscode").toString();
            }
            Map<String, Object> ite = new HashMap();
            ite.put("name", it.get("stockitemname"));
            ite.put("unitPrice", it.get("rate"));
            ite.put("hsCode", hscode);
            ite.put("quantity", it.get("qty"));
            array.add(ite);
        }
        JSONObject buyer = new JSONObject();
        JSONObject payment = new JSONObject();
        buyer.put("pinOfBuyer", payload.get("customer_pin").toString().trim());
        buyer.put("buyerName", "");
        buyer.put("buyerAddress", "");
        buyer.put("buyerPhone", "");
        payment.put("amount", payload.get("grand_total"));
        payment.put("paymentType", "Cash");

        System.out.println(array);
        JSONObject json = new JSONObject();
        json.put("invoiceType", 0);
        json.put("transactionType", transactionType);
        json.put("cashier", "name");
        json.put("buyer", buyer);
        json.put("items", array);
        json.put("payment", payment);
        json.put("relevantNumber", payload.get("rel_doc_number"));
        json.put("TotalTaxableAmount", payload.get("net_subtotal"));
        json.put("TraderSystemInvoiceNumber", payload.get("invoice_number"));
        json.put("ExemptionNumber", payload.get("customer_exid"));

        System.out.println(json.toString());
        JSONObject res = new JSONObject();

        var objectMapper = new ObjectMapper();
        JSONParser parser = new JSONParser();
        try {

            String requestBody = objectMapper.writeValueAsString(json);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(hostname))
                    .header("Content-Type", "application/json")
                    .setHeader("RequestId", requestid)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                res = (JSONObject) parser.parse(response.body());
                System.out.println(response.body());
                System.out.println(response.statusCode());
                System.out.println(res.get("message"));
                if ("Bad Request".equals(res.get("message").toString())) {
                    JSONObject cor = new JSONObject();
                    cor.put("error_status", res.get("message"));
                    cor.put("verify_url", "");
                    EsdDesktop.insertLog(payload.get("invoice_number").toString(), payload.get("invoice_date").toString(), "POST", "dataces", hostname, now.toString(), json.toString(), "Failure", "11ms", res.toString());
                    EsdDesktop.selectLog();
                    he.getResponseHeaders().set("Content-Type", "application/json");
                    he.sendResponseHeaders(400, cor.toString().length());

                    System.out.println(res);
                    try ( OutputStream os = he.getResponseBody()) {
                        os.write(cor.toString().getBytes());
                        os.flush();
                    }
                } else {
                    JSONObject cor = new JSONObject();
                    HashMap exe = new HashMap();
                    exe.putAll((Map) res.get("Existing"));
                    cor.put("error_status", "");
                    cor.put("invoice_number", res.get("invoiceExtension"));
                    cor.put("cu_serial_number", res.get("msn").toString() + " " + res.get("DateTime"));
                    cor.put("cu_invoice_number", res.get("mtn"));
                    cor.put("verify_url", res.get("verificationUrl"));
                    cor.put("description", "Invoice Signed Successfully");
                    EsdDesktop.insertLog(payload.get("invoice_number").toString(), payload.get("invoice_date").toString(), "POST", "dataces", hostname, now.toString(), json.toString(), "Sucessfull", "11ms", cor.toString());
                    EsdDesktop.selectLog();
                    he.getResponseHeaders().set("Content-Type", "application/json");
                    he.sendResponseHeaders(200, cor.toString().length());

                    System.out.println(response);
                    try ( OutputStream os = he.getResponseBody()) {
                        os.write(cor.toString().getBytes());
                        os.flush();
                    }

                }

            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void RequestACE(Map<String, Object> headers, JSONObject payload, HttpExchange he) throws UnsupportedEncodingException, MalformedURLException, ParseException {

        String hostname = headers.get("Hostname").toString().replaceAll("\\[", "").replaceAll("\\]", "");

        System.out.println(hostname);
        JSONArray array = new JSONArray();
        JSONArray items;
        if (payload.get("items_list") == null) {
            items = JSONArray.fromObject(payload.get("led_list"));
        } else {
            items = JSONArray.fromObject(payload.get("items_list"));
        }
        System.out.println(payload.get("hscode"));
        for (Object item : items) {
            HashMap it = new HashMap();
            it.putAll((Map) item);
//            String array_items = it.get("hscode").toString() + " " + it.get("stockitemname") + " " + it.get("qty") + " " + it.get("rate") + " " + it.get("amt");
//            array.add();
            String hscode = "";
            if (it.get("hscode") != null) {
                hscode = it.get("hscode").toString();
            }
            Map<String, Object> ite = new HashMap();
            ite.put("HSDEC", it.get("stockitemname"));
            ite.put("TaxRate", it.get("taxrate"));
            ite.put("ItemAmount", it.get("amt"));
            ite.put("TaxAmount", it.get("taxamount"));
            ite.put("TransactionType", "1");
            ite.put("UnitPrice", it.get("rate"));
            ite.put("HSCode", hscode);
            ite.put("Quantity", it.get("qty"));
            array.add(ite);
        }

        System.out.println(array);
        JSONObject json = new JSONObject();
        json.put("SenderId", payload.get("senderid"));
        json.put("InvoiceTimestamp", payload.get("timestamp"));
        json.put("InvoiceCategory", "Tax Invoice");
        json.put("TraderSystemInvoiceNumber", payload.get("invoice_number"));
        json.put("RelevantInvoiceNumber", payload.get("rel_doc_number"));
        json.put("PINOfBuyer", payload.get("customer_pin"));
        json.put("Discount", payload.get("net_discount_total"));
        json.put("InvoiceType", "Original");
        json.put("ItemDetails", array);
        json.put("TotalInvoiceAmount", payload.get("grand_total"));
        json.put("TotalTaxableAmount", payload.get("net_subtotal"));
        json.put("TotalTaxAmount", payload.get("tax_total"));
        json.put("ExemptionNumber", payload.get("customer_exid"));

        JSONObject jsonR = new JSONObject();
        jsonR.put("Invoice", json);
        System.out.println(jsonR.toString());
        try {
            dbClass.CreateNitrite(jsonR);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        var objectMapper = new ObjectMapper();
        JSONParser parser = new JSONParser();
        JSONObject cor = new JSONObject();
        try {

            String requestBody = objectMapper.writeValueAsString(jsonR);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(hostname))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                JSONObject res = (JSONObject) parser.parse(response.body());
                System.out.println(response.body());
                System.out.println(response.statusCode());
                if (response.statusCode() != 200) {
                    HashMap exe = new HashMap();
                    exe.putAll((Map) res.get("Error"));
                    cor.put("error_status", exe.get("message"));
                    cor.put("verify_url", "");
                    EsdDesktop.insertLog(payload.get("invoice_number").toString(), payload.get("invoice_date").toString(), "POST", "ace", hostname, now.toString(), json.toString(), "Failure", "11ms", res.get("Error").toString());
                    EsdDesktop.selectLog();
                    he.getResponseHeaders().set("Content-Type", "application/json");
                    he.sendResponseHeaders(400, cor.toString().length());

                    try ( OutputStream os = he.getResponseBody()) {
                        os.write(cor.toString().getBytes());
                        os.flush();
                    }
                } else {
                    HashMap exe = new HashMap();
                    exe.putAll((Map) res.get("Existing"));

                    System.out.println(exe.get("TraderSystemInvoiceNumber"));
                    String cuS = payload.get("deviceno").toString() + exe.get("CommitedTimestamp").toString();
                    System.out.println(cuS);
                    try {
                        cor.put("error_status", "");
                        cor.put("invoice_number", exe.get("TraderSystemInvoiceNumber"));
                        cor.put("cu_serial_number", cuS);
                        cor.put("cu_invoice_number", exe.get("ControlCode"));
                        cor.put("verify_url", exe.get("QRCode").toString().replaceAll("\\/", ""));
                        cor.put("description", "Invoice Signed Successfully");
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    EsdDesktop.insertLog(payload.get("invoice_number").toString(), payload.get("invoice_date").toString(), "POST", "ace", hostname, now.toString(), json.toString(), "Successfull", "11ms", cor.toString());
                    EsdDesktop.selectLog();
                    he.getResponseHeaders().set("Content-Type", "application/json");
                    he.sendResponseHeaders(200, cor.toString().length());

                    System.out.println(response);
                    try ( OutputStream os = he.getResponseBody()) {
                        os.write(cor.toString().getBytes());
                        os.flush();
                    }
                }

            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JsonProcessingException ex) {

            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static class RunTotalDevice implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            if ("GET".equals(he.getRequestMethod())) {
                String response = "Fail GET can not be used in this function";
                he.sendResponseHeaders(400, response.length());
                try ( OutputStream os = he.getResponseBody()) {
                    os.write(response.getBytes());
                }

            }

            InputStreamReader isr = new InputStreamReader(he.getRequestBody());
            Headers headers = he.getRequestHeaders();
            String query;
            StringBuilder buffer = new StringBuilder();
            try ( BufferedReader br = new BufferedReader(isr)) {
//                query = br.readLine();                
                while ((query = br.readLine()) != null) {
                    buffer.append(query);
                }
            }

            Map<String, Object> _hea = new HashMap<>();

            Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
            for (Map.Entry<String, List<String>> entry : entries) {
                _hea.put(entry.getKey(), entry.getValue());
            }

            String result = buffer.toString();
            JSONParser parser = new JSONParser();
            JSONObject obj = new JSONObject();
            try {
                obj = (JSONObject) parser.parse(result);
            } catch (ParseException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }

            JSONObject response = RequestTotal(_hea, obj);

            he.getResponseHeaders().set("Content-Type", "application/json");
            he.sendResponseHeaders(200, response.toString().length());

            try ( OutputStream os = he.getResponseBody()) {
                os.write(response.toString().getBytes());
                os.flush();
            }
        }

    }

    private static void RequestESDSignature(Map<String, Object> headers, JSONObject payload, HttpExchange he) throws UnsupportedEncodingException, MalformedURLException, ParseException, URISyntaxException, JsonProcessingException, IOException, InterruptedException {

        String token = headers.get("Authorization").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String hostname = headers.get("Hostname").toString().replaceAll("\\[", "").replaceAll("\\]", "");

        List<String> array = new ArrayList<>();
        JSONArray items = JSONArray.fromObject(payload.get("items_list"));
        for (Object item : items) {
            HashMap it = new HashMap();
            it.putAll((Map) item);
            String array_items = it.get("hscode").toString() + " " + it.get("stockitemname") + " " + it.get("qty") + " " + it.get("rate") + " " + it.get("amt");
            array.add(array_items);
        }
        System.out.println(array);
        payload.remove("items_list");
        payload.put("items_list", array);
        System.out.println(payload);

        var objectMapper = new ObjectMapper();

        String requestBody = objectMapper.writeValueAsString(payload);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(hostname))
                .setHeader("Authorization", token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONParser parser = new JSONParser();
        if (response.statusCode() != 200) {
            JSONObject res = (JSONObject) parser.parse(response.body().replaceAll("\\\\", ""));
            try {

                EsdDesktop.insertLog(payload.get("invoice_number").toString(), payload.get("invoice_date").toString(), "POST", "esd", hostname, now.toString(), payload.toString(), "Failure", "11ms", res.toString());
                EsdDesktop.selectLog();
            } catch (Exception ex) {
                System.out.println(ex);
            }
            he.getResponseHeaders().set("Content-Type", "application/json");
            he.sendResponseHeaders(400, res.toString().length());

            try ( OutputStream os = he.getResponseBody()) {
                os.write(res.toString().getBytes());
                os.flush();
            }

        } else {

            JSONObject res = (JSONObject) parser.parse(response.body().replaceAll("\\\\", ""));
            EsdDesktop.insertLog(payload.get("invoice_number").toString(), payload.get("invoice_date").toString(), "POST", "esd", hostname, now.toString(), payload.toString(), "Successfull", "11ms", res.toString().replaceAll("\\/", ""));
            EsdDesktop.selectLog();
            System.out.println(response.uri());
            he.getResponseHeaders().set("Content-Type", "application/json");
            he.sendResponseHeaders(200, res.toString().length());
            try ( OutputStream os = he.getResponseBody()) {
                os.write(res.toString().getBytes());
                os.flush();
            }
        }

//        try {
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoOutput(true);
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type", "application/json;charset=" + "UTF-8");
//            connection.setRequestProperty("Authorization", token);
//            try(OutputStream output = connection.getOutputStream()){
//                output.write(payload.toString().getBytes());            
//            }
//            InputStreamReader response = new InputStreamReader(connection.getInputStream());
//            BufferedReader res = new BufferedReader(response);
//            String rs = res.readLine();
//            System.out.println(rs);
//            
//            
//        } catch (IOException ex) {
//            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private static void RequestDevice(Map<String, Object> headers, JSONObject payload) throws MalformedURLException {
        String token = headers.get("Authorization").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String hostname = headers.get("Hostname").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        URL url = new URL(hostname);
        List<String> array = new ArrayList<>();
        JSONArray items = JSONArray.fromObject(payload.get("items_list"));
        for (Object item : items) {
            HashMap it = new HashMap();
            it.putAll((Map) item);
            String array_items = it.get("hscode").toString() + " " + it.get("stockitemname") + " " + it.get("qty") + " " + it.get("rate") + " " + it.get("amt");
            array.add(array_items);
        }
        System.out.println(array);
        payload.remove("items_list");
        payload.put("items_list", array);
        System.out.println(payload);
        var objectMapper = new ObjectMapper();

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json;charset=" + "UTF-8");

            try ( OutputStream output = connection.getOutputStream()) {
                output.write(payload.toString().getBytes());
            }
            InputStreamReader response = new InputStreamReader(connection.getInputStream());
            BufferedReader res = new BufferedReader(response);
            String rs = res.readLine();
            System.out.println(rs);

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {
        if (query != null) {
//                 System.out.println(Arrays.toString(query.split("\\W+")));

            String pairs[] = query.split("[,}]");
            for (String pair : pairs) {
                String par = pair.trim();
                String param[] = par.split("[:]");
                String key = null;
                String value = null;
                if (param.length > 0) {
                    key = URLDecoder.decode(param[0], System.getProperty("file.encoding"));
                }
                if (param.length > 1) {
                    value = URLDecoder.decode(param[1], System.getProperty("file.encoding"));
                }
                if (parameters.containsKey(key)) {
                    Object obj = parameters.get(key);
                    if (obj instanceof List<?>) {
                        List<String> values = (List<String>) obj;
                        values.add(value);

                    } else if (obj instanceof String) {
                        List<String> values = new ArrayList<>();
                        values.add((String) obj);
                        values.add(value);
                        parameters.put(key, values);
                    }
                } else {
                    parameters.put(key, value);
                }
                System.out.println(parameters.get("DATE"));
            }
        }
    }

    private static JSONObject RequestTotal(Map<String, Object> headers, JSONObject payload) throws UnsupportedEncodingException, MalformedURLException {
        String host = headers.get("Original").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String ip = headers.get("Hostname").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        String password = headers.get("Password").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        int port = Integer.parseInt(headers.get("Port").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
        System.out.println(host);
        System.out.println(ip);
        System.out.println(password);
        System.out.println(port);
        FP fp = new FP();
        try {
            fp.ServerAddress = host;
            fp.ServerSetDeviceTcpSettings(ip, port, password);
            try {
                var status = fp.ReadStatus();
                if (status.CU_fiscalized) {
                    fp.OpenInvoiceWithFreeCustomerData("", payload.get("customer_pin").toString(), "", "", "", "", "");
                    JSONArray items = JSONArray.fromObject(payload.get("items_list"));
                    for (Object item : items) {
                        HashMap it = new HashMap();
                        it.putAll((Map) item);
                        String hscode = "";
                        if (it.get("hscode") != "") {
                            hscode = it.get("hscode").toString();
                        }
                        fp.SellPLUfromExtDB(it.get("stockitemname").toString(), OptionVATClass.VAT_Class_A, Double.parseDouble(it.get("rate").toString()), " ", hscode, " ", 16.0, Double.parseDouble(it.get("qty").toString()), 0.0);
                    }
                    var close = fp.CloseReceipt();
                    System.out.println(close.QRcode);
                    JSONObject json = new JSONObject();
                    json.put("cu_invoice_number", close.InvoiceNum);
                    json.put("qr_code", close.QRcode);
                    return json;
                }
                System.out.println(status);
            } catch (Exception ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
