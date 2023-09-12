import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static List<String> readDataset(Path file) throws IOException {
        List<String> text = Files.readAllLines(file);
        return text;

    }


    public static List<List<Integer>> readVertices(Path file) throws IOException {
        List<String> text = Files.readAllLines(file);

        List<List<Integer>> adjacent = new ArrayList<List<Integer>>();
        for(int i=0; i<text.size()-2; i++) {
            for(int j=0; j<text.get(i).length(); j++) {
                Integer current = i*(text.get(0).length()) + j;

                if (text.get(i).charAt(j) == ' ') {
                    List<Integer> number = new ArrayList<Integer>();

                    if(text.get(i-1).charAt(j) != 'X') {
                        number.add(current - (text.get(0).length()));
                    }

                    if(text.get(i+1).charAt(j) != 'X') {
                        number.add(current + (text.get(0).length()));
                    }

                    if(text.get(i).charAt(j-1) != 'X') {
                        number.add(current - 1);
                    }

                    if(text.get(i).charAt(j+1) != 'X') {
                        number.add(current + 1);

                    }
                    adjacent.add(current, number);

                } else {
                    adjacent.add(current, Collections.emptyList());
                }
            }
        }

        return adjacent;
    }

    public static void print_maze(Path file, List<Integer> path, Integer start, Integer end, List<Integer> openNodes) throws IOException {
        String maze[][] = new String[10000][10000];
        List<String> text = Files.readAllLines(file);

        for(int i=0; i<text.size()-2; i++) {
            for(int j=0; j<text.get(0).length(); j++) {
                if(openNodes.contains(i*text.get(0).length() + j)) {
                    if(path.contains(i*text.get(0).length() + j)) {
                        if ((i * text.get(0).length() + j) == start) {
                            maze[i][j] = "S";
                        } else if ((i * text.get(0).length() + j) == end) {
                            maze[i][j] = "E";
                        } else {
                            maze[i][j] = "o";
                        }
                    } else {
                        maze[i][j] = "#";
                    }

                } else {
                    maze[i][j] = String.valueOf(text.get(i).charAt(j));
                }

                System.out.print(maze[i][j]);
            }
            System.out.println();
        }

        System.out.println("-----------");
        System.out.println("S Start" +
                "\nE End" +
                "\n# Opened node" +
                "\no Path" +
                "\nX Wall" +
                "\nspace Fresh node");
        System.out.println("-----------");
        System.out.println("Node expanded: " + (openNodes.size()+path.size()));
        System.out.println("Path length: " + path.size());
    }





    public static void main(String[] args) throws IOException {
        String filenumber = "36";
        List<String> lines = readDataset(Path.of("C:\\Users\\Usuario\\Downloads\\dataset\\" + filenumber + ".txt"));

        String getStart = lines.get(lines.size()-2).replace("start ","").replace(",", "");
        String[] st = getStart.split(" ");
        Integer start = Integer.parseInt(st[0]) + Integer.parseInt(st[1]) * lines.get(0).length();

        String getEnd = lines.get(lines.size()-1).replace("end ","").replace(",", "");
        String[] nd = getEnd.split(" ");
        Integer end = Integer.parseInt(nd[0]) + Integer.parseInt(nd[1]) * lines.get(0).length();


        System.out.println("----------- BFS -----------");
        List<Integer> bfs = Bfs.breadth_first_search(
                readVertices(Path.of("C:\\Users\\Usuario\\Downloads\\dataset\\" + filenumber + ".txt")),
                start, end);
        //System.out.println("bfs: " + bfs);
        List<Integer> on = Bfs.openNodes(readVertices(Path.of("C:\\Users\\Usuario\\Downloads\\dataset\\" + filenumber + ".txt")),
                start, end);
        print_maze(Path.of("C:\\Users\\Usuario\\Downloads\\dataset\\" + filenumber + ".txt"), bfs, start, end, on);


        System.out.println("\n----------- DFS -----------");
        List<Integer> dfs = Dfs.depth_first_search(
                readVertices(Path.of("C:\\Users\\Usuario\\Downloads\\dataset\\" + filenumber + ".txt")),
                start, end);
        List<Integer> opn = Dfs.openNodes(
                readVertices(Path.of("C:\\Users\\Usuario\\Downloads\\dataset\\" + filenumber + ".txt")),
                start, end);
        //System.out.println("dfs: " + dfs);
        print_maze(Path.of("C:\\Users\\Usuario\\Downloads\\dataset\\" + filenumber + ".txt"), dfs, start, end, opn);


        System.out.println("\n----------- Random Search -----------");
        List<Integer> randomSearch = RandomSearch.randomSearch(
                readVertices(Path.of("C:\\Users\\Usuario\\Downloads\\dataset\\" + filenumber + ".txt")),
                start, end);
        List<Integer> open = RandomSearch.openNodes(
                readVertices(Path.of("C:\\Users\\Usuario\\Downloads\\dataset\\" + filenumber + ".txt")),
                start, end);
        //System.out.println("Random Search: " + randomSearch);
        print_maze(Path.of("C:\\Users\\Usuario\\Downloads\\dataset\\" + filenumber + ".txt"), randomSearch, start, end, open);


        System.out.println("\n----------- Greedy Search -----------");
        List<Integer> greedySearch = GreedySearch.greedySearch(
                readVertices(Path.of("C:\\Users\\Usuario\\Downloads\\dataset\\" + filenumber + ".txt")),
                start, end, lines.get(0).length());
        List<Integer> openN = GreedySearch.openNodes(
                readVertices(Path.of("C:\\Users\\Usuario\\Downloads\\dataset\\" + filenumber + ".txt")),
                start, end, lines.get(0).length());
        //System.out.println("Greedy Search: " + greedySearch);
        print_maze(Path.of("C:\\Users\\Usuario\\Downloads\\dataset\\" + filenumber + ".txt"), greedySearch, start, end, openN);



        System.out.println("\n----------- A Star -----------");
        List<Integer> astar = Astar.astar(
                readVertices(Path.of("C:\\Users\\Usuario\\Downloads\\dataset\\" + filenumber + ".txt")),
                start, end, lines.get(0).length());
        List<Integer> openNo = Astar.openNodes(
                readVertices(Path.of("C:\\Users\\Usuario\\Downloads\\dataset\\" + filenumber + ".txt")),
                start, end, lines.get(0).length());
        print_maze(Path.of("C:\\Users\\Usuario\\Downloads\\dataset\\" + filenumber + ".txt"), astar, start, end, openNo);

    }


}
