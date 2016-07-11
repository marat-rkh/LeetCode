public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>(n);
        for(int i = 0; i < n; ++i) {
            graph.add(new LinkedList<>());
        }
        for(int i = 0; i < edges.length; ++i) {
            int edgeNode1 = edges[i][0];
            int edgeNode2 = edges[i][1];
            graph.get(edgeNode1).add(edgeNode2);
            graph.get(edgeNode2).add(edgeNode1);
        }
        int leaf = getAnyLeaf(graph);
        int maxPathBeg = farthestLeafFrom(graph, leaf);
        int maxPathEnd = farthestLeafFrom(graph, maxPathBeg);
        List<Integer> maxPath = pathFromTo(graph, maxPathBeg, maxPathEnd);
        List<Integer> centers = new LinkedList<>();
        int pathLength = maxPath.size();
        int halfLength = pathLength / 2;
        centers.add(maxPath.get(halfLength));
        if(pathLength % 2 == 0) {
            centers.add(maxPath.get(halfLength - 1));
        }
        return centers;
    }
    
    private int getAnyLeaf(List<List<Integer>> graph) {
        for(int i = 0; i < graph.size(); ++i) {
            List<Integer> nodeNeighbours = graph.get(i);
            if(nodeNeighbours.size() <= 1) {
                return i;
            }
        }
        throw new IllegalArgumentException("Graph is not a tree");
    }
    
    private int farthestLeafFrom(List<List<Integer>> graph, int root) {
        boolean[] visited = new boolean[graph.size()];
        IntIntPair max = new IntIntPair(root, 0);
        Deque<IntIntPair> nodes = new LinkedList<>();
        nodes.addFirst(max);
        while(!nodes.isEmpty()) {
            IntIntPair cur = nodes.removeFirst();
            visited[cur.fst] = true;
            if(cur.snd > max.snd) {
                max = cur;
            }
            List<Integer> neighbours = graph.get(cur.fst);
            for(Integer n : neighbours) {
                if(!visited[n]) {
                    nodes.addFirst(new IntIntPair(n, cur.snd + 1));
                }
            }
        }
        return max.fst;
    }
    
    private List<Integer> pathFromTo(List<List<Integer>> graph, int from, int to) {
        int[] pred = collectPredsFrom(graph, to);
        List<Integer> path = new LinkedList<>();
        int cur = from;
        path.add(cur);
        int before = pred[cur];
        while(before != -1) {
            cur = before;
            path.add(cur);
            before = pred[cur];
        }
        return path;
    }
    
    private int[] collectPredsFrom(List<List<Integer>> graph, int root) {
        int[] pred = new int[graph.size()];
        for(int i = 0; i < pred.length; ++i) {
            pred[i] = -1;
        }
        boolean[] visited = new boolean[graph.size()];
        Deque<Integer> nodes = new LinkedList<>();
        nodes.addFirst(root);
        while(!nodes.isEmpty()) {
            int cur = nodes.removeFirst();
            visited[cur] = true;
            List<Integer> neighbours = graph.get(cur);
            for(Integer n : neighbours) {
                if(!visited[n]) {
                    pred[n] = cur;
                    nodes.addFirst(n);
                }
            }
        }
        return pred;
    }
    
    private static class IntIntPair {
        int fst;
        int snd;
        
        public IntIntPair(int f, int s) {
            this.fst = f;
            this.snd = s;
        }
    }
}