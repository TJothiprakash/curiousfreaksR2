package designpatterns.singleton;

public class Main {
    public static void main(String[] args) {
        //  Library library = new Library();
        Library library = Library.getLibraryInstance();
        Library library1 = Library.getLibraryInstance();
        System.out.println(library1 == library);


    }
}

