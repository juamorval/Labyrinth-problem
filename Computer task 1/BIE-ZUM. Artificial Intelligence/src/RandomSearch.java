import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomSearch {

    public static List<Integer> randomSearch(List<List<Integer>> adjacent, Integer start, Integer end) {
        List<Integer> queue = new ArrayList<Integer>();
        List<Boolean> visited = new ArrayList<Boolean>();
        List<Integer> previous = new ArrayList<Integer>();

        for(int i=0; i<adjacent.size(); i++) {
            visited.add(i, false);
            previous.add(i, null);
        }

        queue.add(start);
        visited.set(start, true);

        while(!queue.isEmpty()) {
            Random aleatory = new Random();
            Integer x = queue.get(aleatory.nextInt(queue.size()));
            queue.remove(x);
            
            if(x==end) break;

            for(int i=0; i<adjacent.get(x).size(); i++) {
                if(visited.get(adjacent.get(x).get(i)) == false) {
                    queue.add(adjacent.get(x).get(i));
                    visited.set(adjacent.get(x).get(i), true);
                    previous.set(adjacent.get(x).get(i), x);
                }
            }

        }

        List<Integer> path = new ArrayList<Integer>();
        path.add(end);

        previous.set(start, -1);

        while(previous.get(path.get(0))!=-1) {
            path.add(0, previous.get(path.get(0)));
        }

        return path;
    }


    public static List<Integer> openNodes(List<List<Integer>> adjacent, Integer start, Integer end) {
        List<Integer> queue = new ArrayList<Integer>();
        List<Boolean> visited = new ArrayList<Boolean>();
        List<Integer> openNodes = new ArrayList<Integer>();

        for(int i=0; i<adjacent.size(); i++) {
            visited.add(i, false);
        }

        queue.add(start);
        visited.set(start, true);
        openNodes.add(start);

        while(!queue.isEmpty()) {
            Random aleatory = new Random();
            Integer x = queue.get(aleatory.nextInt(queue.size()));
            queue.remove(x);

            if(x.equals(end)) break;

            for(int i=0; i<adjacent.get(x).size(); i++) {
                if(visited.get(adjacent.get(x).get(i)) == false) {
                    queue.add(adjacent.get(x).get(i));
                    visited.set(adjacent.get(x).get(i), true);
                    openNodes.add(adjacent.get(x).get(i));
                }
            }

        }

        return openNodes;
    }
}
