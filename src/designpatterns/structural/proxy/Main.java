package designpatterns.structural.proxy;

// Subject interface
interface Image {
    void display();
}

// Real Subject
class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading image " + filename);
        // Simulate heavy loading operation
    }

    @Override
    public void display() {
        System.out.println("Displaying " + filename);
    }
}

// Proxy
class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);  // Lazy initialization
        }
        realImage.display();
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        Image image = new ProxyImage("photo.jpg");

        // Image will be loaded from disk only when display is called
        image.display();  // Loads and displays
        image.display();  // Just displays, no loading this time

        DataBaseExecutor emp1 = new DataBaseProxy("NON ADMIN");
        emp1.executeQuery("READ");
        emp1.executeQuery("WRITE");
        emp1.executeQuery("DELETE");

        DataBaseExecutor emp2 = new DataBaseProxy("ADMIN");
        emp2.executeQuery("READ");
        emp2.executeQuery("WRITE");
        emp2.executeQuery("DELETE");
    }



}
