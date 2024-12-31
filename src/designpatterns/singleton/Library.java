package designpatterns.singleton;

public class Library {
    private static Library libraryInstance;

    private Library() {

    }

    public static Library getLibraryInstance() {
        if (libraryInstance == null) {
            libraryInstance = new Library();
        }
        return libraryInstance;
    }

    /* // Public method to provide access to the single instance
    public static Library getLibraryInstance() {
        if (libraryInstance == null) { // First check
            synchronized (Library.class) {
                if (libraryInstance == null) { // Second check
                    libraryInstance = new Library();
                }
            }
        }
        return libraryInstance;
    }*/
}
