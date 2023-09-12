import java.util.ArrayList;
import java.util.List;

public class Bfs {

    public static List<Integer> breadth_first_search(List<List<Integer>> adjacent, Integer start, Integer end) {
        List<Integer> queue = new ArrayList<Integer>();
        queue.add(start);

        List<Boolean> visited = new ArrayList<Boolean>();
        List<Integer> previous = new ArrayList<Integer>();

        for(int i=0; i<adjacent.size(); i++) {
            visited.add(false);
            previous.add(null);
        }

        visited.set(start, true);

        while(!queue.isEmpty()) {
            Integer x = queue.get(0);
            queue.remove(0);

            if(x==end) break;

            for(int i=0; i<adjacent.get(x).size(); i++) {

                if(visited.get(adjacent.get(x).get(i))==false) {
                    queue.add(adjacent.get(x).get(i));
                    previous.set(adjacent.get(x).get(i), x);    //we put the father in the position of its each child
                    visited.set(adjacent.get(x).get(i), true);
                 }
            }

            }

        previous.set(start, -1);
        List<Integer> path = new ArrayList<Integer>();
        path.add(end);

        while(previous.get(path.get(0)) != -1) {    //we search the father in each step until reach the start
            path.add(0, previous.get(path.get(0)));   //we add the previous father
        }

        return path;
    }

    public static List<Integer> openNodes(List<List<Integer>> adjacent, Integer start, Integer end) {
        List<Integer> queue = new ArrayList<Integer>();
        List<Integer> openNodes = new ArrayList<>();
        queue.add(start);

        List<Boolean> visited = new ArrayList<Boolean>();

        for(int i=0; i<adjacent.size(); i++) {
            visited.add(false);
        }

        visited.set(start, true);
        openNodes.add(start);

        while(!queue.isEmpty()) {
            Integer x = queue.get(0);
            queue.remove(0);

            if(x.equals(end)) break;

            for(int i=0; i<adjacent.get(x).size(); i++) {

                if(visited.get(adjacent.get(x).get(i))==false) {
                    queue.add(adjacent.get(x).get(i));
                    visited.set(adjacent.get(x).get(i), true);
                    openNodes.add(adjacent.get(x).get(i));
                }
            }

        }

        return openNodes;

    }

}
