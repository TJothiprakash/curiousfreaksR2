package recursion;

public class PrintNnumbers {
    public static void main(String[] args) {
        int n = 5;
         printNumbers(n);
        // printNumbers(n,1);
    }

    private static void printNumbers(int n) {
        if(n==0){
            return;
        }
        System.out.println(n);
        printNumbers(n-1);

    }
    private static void printNumbers(int n,int i){
        if(i>n){
            return;
        }
        System.out.println(i);
        printNumbers(n,i+1);
    }
}
