package dec_25_practice_session;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.imageio.ImageIO;

public class WeightLossTrayAppDeamon {
    public static void main(String[] args) {
        if (!SystemTray.isSupported()) {
            System.out.println("System tray not supported!");
            return;
        }

        // Create system tray and tray icon
        SystemTray tray = SystemTray.getSystemTray();
        Image image = null;

        try {
            image = ImageIO.read(new File("C:\\Users\\DELL\\Desktop\\icon.png")); // Corrected file path
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (image == null) {
            System.out.println("Error: Icon not found or invalid.");
            return;
        }

        PopupMenu popup = new PopupMenu();
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0)); // Exit the app when selected
        popup.add(exitItem);

        TrayIcon trayIcon = new TrayIcon(image, "Weight Loss Journey", popup);
        trayIcon.setImageAutoSize(true);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("Error adding tray icon: " + e.getMessage());
            return;
        }

        // Add action listener to show a notification when the tray icon is clicked
        trayIcon.addActionListener((ActionEvent e) -> {
            trayIcon.displayMessage("Weight Loss Journey", "Today is Day 2 of your journey!", TrayIcon.MessageType.INFO);
        });

        // Initial notification
        trayIcon.displayMessage("Weight Loss Journey", "Tray app started. Click the icon for updates!", TrayIcon.MessageType.INFO);

        // Keep the app running with an empty daemon thread
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // Keep the thread alive
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }
}
