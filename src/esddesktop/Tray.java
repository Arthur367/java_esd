/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esddesktop;

import com.sun.net.httpserver.HttpServer;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author netbot
 */
public class Tray {

    public static TrayIcon trayIcon;
    static boolean alive = true;
    public static HttpServer server = Server.server;
    public static final int PORT = Server.PORT;
    public static SystemTray tray = SystemTray.getSystemTray();
    private static String statusS = "";

    public static void main(String args[]) throws IOException {
        startSystemTray(server);

    }

    public static void startSystemTray(HttpServer server) throws IOException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        if (SystemTray.isSupported()) {
            String dir = System.getProperty("user.dir");
//            
//            ImageIcon img = new ImageIcon();
            Image image = Toolkit.getDefaultToolkit().getImage(dir + "src/images/icon.png");
//            Image image = ImageIO.read(new File("/images/icon.png"));
//            String path = new File("").getPath();
//            System.out.println(path);

            MouseListener mouseListener = new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse clicked!");
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse entered!");
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse exited!");
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse pressed!");
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse released!");
                }
            };

            ActionListener exitListener = (ActionEvent e) -> {
                System.out.println("Exiting...");
                System.exit(0);
            };

            ActionListener stopServer = (ActionEvent e) -> {
                statusS = "Dead";
                alive = false;
                server.stop(PORT);

                System.out.println("Server Stopped at " + PORT);

            };

            ActionListener startServer = (ActionEvent e) -> {
                server.start();
                statusS = "Running";
                System.out.println("Server Started at " + PORT);

            };
            ActionListener restartServer = (ActionEvent e) -> {
                System.out.println("Restart Service " + PORT);
                String args[] = null;
                MainFrame ser = new MainFrame();
                ser.RemoveKey();
                try {
                    ser.setVisible(true);
                } catch (Exception ex) {

                }
                server.stop(PORT);

            };
            ActionListener logServer = (ActionEvent e) -> {
                System.out.println("Restart Service" + PORT);
                String args[] = null;
                Log.main(args);
            };
            ActionListener aboutServer = (ActionEvent e) -> {
                System.out.println("Restart Service" + PORT);
                JFrame newFrame = new JFrame();
                JOptionPane.showMessageDialog(newFrame, "about us", "About", JOptionPane.INFORMATION_MESSAGE);
            };

            ActionListener statusString = (ActionEvent e) -> {

            };
            PopupMenu popup = new PopupMenu();
            MenuItem start = new MenuItem("Start Server");
            MenuItem stop = new MenuItem("Stop Server");
            MenuItem about = new MenuItem("About");
            MenuItem restartService = new MenuItem("Restart Service");
            MenuItem log = new MenuItem("Log");
            MenuItem status = new MenuItem("Status:" + statusS);
            MenuItem defaultItem = new MenuItem("Exit");
            defaultItem.addActionListener(exitListener);
            stop.addActionListener(stopServer);
            start.addActionListener(startServer);
            restartService.addActionListener(restartServer);
            log.addActionListener(logServer);
            about.addActionListener(aboutServer);
            if (alive) {
                popup.add(status);
                popup.add(stop);
                popup.add(restartService);
                popup.add(log);
                popup.add(about);
                popup.add(defaultItem);
            } else {
                popup.add(status);
                popup.add(start);
                popup.add(restartService);
                popup.add(log);
                popup.add(about);
                popup.add(defaultItem);
            }

            trayIcon = new TrayIcon(image, "ESD Tray", popup);

            ActionListener actionListener = (ActionEvent e) -> {
                trayIcon.displayMessage("Action Event",
                        "An Action Event Has Been Performed!",
                        TrayIcon.MessageType.INFO);
            };

            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(actionListener);
            trayIcon.addMouseListener(mouseListener);

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }

        }

    }

}
