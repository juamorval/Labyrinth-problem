import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class GreedySearch {

    public static List<Integer> greedySearch(List<List<Integer>> adjacent, Integer start, Integer end, Integer size) {
        PriorityQueue<Tuple> queue = new PriorityQueue<>();
        List<Boolean> visited = new ArrayList<>();
        List<Integer> previous = new ArrayList<>();

        for(int i=0; i<adjacent.size(); i++) {
            visited.add(false);
            previous.add(null);
        }

        Integer man_distance = Math.abs((end / size) - (start / size)) + Math.abs((end % size) - (start % size));

        queue.add(new Tuple(man_distance,start));
        visited.set(start, true);


        while(!queue.isEmpty()) {
            Tuple tp  = queue.poll();
            Integer x = tp.getN2();

            if(x==end) break;

            for(int i=0; i<adjacent.get(x).size(); i++) {
                if(visited.get(adjacent.get(x).get(i))==false) {
                    visited.set(adjacent.get(x).get(i), true);
                    previous.set(adjacent.get(x).get(i), x);

                    int minDistance = Math.abs((end / size) - (adjacent.get(x).get(i) / size))
                            + Math.abs((end % size) - (adjacent.get(x).get(i) % size));

                    queue.add(new Tuple(minDistance, adjacent.get(x).get(i)));

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


    public static List<Integer> openNodes(List<List<Integer>> adjacent, Integer start, Integer end, Integer size) {
        PriorityQueue<Tuple> queue = new PriorityQueue<>();
        List<Boolean> visited = new ArrayList<>();
        List<Integer> openNodes = new ArrayList<>();

        for(int i=0; i<adjacent.size(); i++) {
            visited.add(false);
        }

        Integer man_distance = Math.abs((end / size) - (start / size)) + Math.abs((end % size) - (start % size));

        queue.add(new Tuple(man_distance,start));
        visited.set(start, true);
        openNodes.add(start);


        while(!queue.isEmpty()) {
            Tuple tp  = queue.poll();
            Integer x = tp.getN2();

            if(x.equals(end)) break;

            for(int i=0; i<adjacent.get(x).size(); i++) {
                if(visited.get(adjacent.get(x).get(i))==false) {
                    visited.set(adjacent.get(x).get(i), true);
                    openNodes.add(adjacent.get(x).get(i));

                    int minDistance = Math.abs((end / size) - (adjacent.get(x).get(i) / size))
                            + Math.abs((end % size) - (adjacent.get(x).get(i) % size));

                    queue.add(new Tuple(minDistance, adjacent.get(x).get(i)));

                }

            }



        }

        return openNodes;
    }
}
