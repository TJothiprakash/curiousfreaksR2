package practicesessions.sept_8;

public class SortedArraytoBST {
    static void main() {
        int arr[] = new int []{1,2,3,4,5,6,7,8,9};
        Node root = arraytoBST(arr);
      printTree ( root);
    }
    private static void printTree(Node root){
        inorder(root);
        System.out.println("null");

    }
    private static  void inorder (Node root){
        if(root == null) {
            return;
        }
        System.out.print(root.data+ "-> ");
        inorder(root.left);
        inorder(root.right);

    }

    static class Node {
        int data;
        Node left;
        Node right;
        public Node(int data)
        {
            this.data = data;
            this.left = null;
            this.right = null;

        }
    }

    public static Node arraytoBST(int[] arr) {
        Node root =build(arr, 0, arr.length-1);
        return  root;
    }
    private static Node build(int arr[], int start, int end){
        if(start > end){
            return null;
        }
        int mid = start + (end - start) /2;
        Node root = new Node(arr[mid]);
        root.left = build(arr, start, mid-1);
        root.right = build(arr,mid+1, end);
        return  root;
    }

}
