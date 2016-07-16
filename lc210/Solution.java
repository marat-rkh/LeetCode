public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for(int i = 0; i < numCourses; ++i) {
            graph.add(new LinkedList<>());
        }
        for(int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
        }
        Deque<Integer> topSorted = topSort(graph);
        int[] res = new int[topSorted.size()];
        int i = 0;
        for (Integer val : topSorted) {
            res[i] = val;
            i += 1;
        }
        return res;
    }
    
    private Deque<Integer> topSort(List<List<Integer>> graph) {
        int[] color = new int[graph.size()];
        Deque<Integer> postOrdered = new LinkedList<>();
        try {
            for(int n = 0; n < graph.size(); ++n) {
                if(color[n] == 0) {
                    topSortRec(graph, n, color, postOrdered);
                }
            }
            return postOrdered;
        } catch(IllegalArgumentException e) {
            return new LinkedList<>();
        }
    }
    
    private void topSortRec(List<List<Integer>> graph, int n, int[] color, Deque<Integer> postOrdered) {
        color[n] = 1;
        List<Integer> neighbours = graph.get(n);
        for(Integer nb : neighbours) {
            if(color[nb] == 0) {
                topSortRec(graph, nb, color, postOrdered);
            } else if(color[nb] == 1) {
                throw new IllegalArgumentException("cycle detected");
            }
        }
        color[n] = 2;
        postOrdered.addFirst(n);
    }
}