import java.util.*;

class Solution {
    static class Node{
        int idx, cost;
        Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }
    }
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
        for(int i =0; i<N+1; i++){
            graph.add(new ArrayList<Node>());
        }
        for(int i =0; i<road.length; i++){
            int start = road[i][0];
            int end = road[i][1];
            int time = road[i][2];
            graph.get(start).add(new Node(end, time));
            graph.get(end).add(new Node(start, time));
        }
        int [] dist = new int[N+1];
        for(int i=0; i<N+1; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[1] =0;
        PriorityQueue<Node> q = new PriorityQueue<Node>((o1, o2) ->Integer.compare(o1.cost, o2.cost));
        
        q.offer(new Node(1, 0));
        while(!q.isEmpty()){
            Node current = q.poll();
            
            if(dist[current.idx]<current.cost){
                continue;
            }
            for(int i =0; i<graph.get(current.idx).size(); i++){
                Node next = graph.get(current.idx).get(i);
                if(dist[next.idx]>current.cost +next.cost){
                    dist[next.idx]=current.cost +next.cost;
                    q.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
        for(int x: dist){
            System.out.print(x+" ");
            if(x<=K){
                answer++;
            }
        }
        return answer;
    }
}
