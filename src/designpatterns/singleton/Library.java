package designpatterns.singleton;

public class Library {
    private static Library libraryInstance;
    //early initialization
//   private static Library libraryInstance = new Library();
    private Library() {

    }

    //lazy with single locking  initialization
    public static Library getLibraryInstance() {
        if (libraryInstance == null) {
            libraryInstance = new Library();
        }
        return libraryInstance;
    }

    // double locking mechanism

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
