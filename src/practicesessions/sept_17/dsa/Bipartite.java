package practicesessions.sept_17.dsa;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Bipartite {
    public boolean isBipartite(int[][] graph) {
        int []vis= new int[graph.length];
        Arrays.fill(vis, -1);
        for(int i =0 ; i<vis.length ; i++){
            if(vis[i]==-1){
                if(bfs(vis,i,graph)==false){
                    return false;
                }
            }
        }
        return true;
    }
    boolean bfs(int[] vis , int start , int[][] graph){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        vis[start]=0;
        while(!queue.isEmpty()){
            int curr = queue.poll();
            for(int i = 0; i<graph[curr].length; i++ ){
                int adj = graph[curr][i];
                if(vis[adj]==-1){
                    vis[adj] = 1 - vis[curr];
                    queue.add(adj);
                }else if(vis[curr]==vis[adj]){
                    return false;
                }
            }
        }
        return true;
    }
}
