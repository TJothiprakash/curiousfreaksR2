package practicesessions.dec_25_practice_session;

public class CustomArrayList {
    private int[] arraylist;
    private int size;
    private int index = 0;

    public CustomArrayList() {
        int DEFAULT_SIZE = 10;
        this.arraylist = new int[DEFAULT_SIZE];

    }

    public CustomArrayList(int size) {
        this.size = size;
        this.arraylist = new int[size];
    }

    // resize
    private boolean resize(int newSize) {
        int[] oldlist = arraylist;
        if (newSize > Integer.MAX_VALUE) {
            return false;
        }
        arraylist = new int[newSize];
        for (int i = 0; i < index; i++) {
            arraylist[i] = oldlist[i];
        }
        return true;
    }

    // insert data
    public void insert(int data) throws Exception {
        if (index == size) {
            if (!resize(size * 2)) {
                throw new Exception("cannot resize arraylist ");
            }
        }
        arraylist[index] = data;
        index++;
    }

    // print arraylist
    public void printlist() {
        for (int i = 0; i <= index; i++) {
            System.out.print(i + " ");
        }
    }

    //remove data
    public boolean remove(int data) {
        int i;
        for (i = 0; i < index; i++) {
            if (arraylist[i] == data) {
                break;
            }
        }
        if (i == index) {
            return false;
        }
        for (int j = i; j < index - 1; j++) {
            arraylist[j] = arraylist[j + 1];
        }
        index--;
        return true;
    }

    public int getIndex(int data) {
        for (int i = 0; i <= index; i++) {
            if (arraylist[i] == data) {
                return i;
            }

        }
        return -1;
    }
}
