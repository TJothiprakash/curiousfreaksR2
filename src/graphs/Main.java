package graphs;

import graphs.bfsanddfs.RottenOrangs;

public class Main {
    public static void main(String[] args) {
        GraphIntro graphIntro   = new GraphIntro();
        graphIntro.printGraph();
        System.out.println("hello world !!!!!!!!!!");

        RottenOrangs rottenOrangs = new RottenOrangs();
        int arr[][]={ {2,1,1} , {1,1,0} , {0,1,1} };
        int rotting = rottenOrangs.rottenoranges(arr);
        System.out.println("Minimum Number of Minutes Required "+rotting);
    }
}


