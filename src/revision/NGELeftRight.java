package revision;
public class NGELeftRight {
    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 10, 8};

        int[] ngeLeft = new int[arr.length];
        int[] ngeRight = new int[arr.length];

        // Find NGE on Left
        for (int i = 0; i < arr.length; i++) {
            int nge = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[i]) {
                    nge = arr[j];
                    break;
                }
            }
            ngeLeft[i] = nge;
        }

        // Find NGE on Right
        for (int i = 0; i < arr.length; i++) {
            int nge = -1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    nge = arr[j];
                    break;
                }
            }
            ngeRight[i] = nge;
        }

        System.out.println("NGE Left: " + java.util.Arrays.toString(ngeLeft));
        System.out.println("NGE Right: " + java.util.Arrays.toString(ngeRight));
    }
}
