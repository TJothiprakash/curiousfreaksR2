package practicesessions.dec_25_practice_session;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.imageio.ImageIO;

public class WeightLossTrayApp {
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
        exitItem.addActionListener(e -> System.exit(0));
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
    }
}
