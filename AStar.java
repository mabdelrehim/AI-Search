import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar extends AbstractSearch implements Search, Runnable {

    HashSet<String> explored;
    PriorityQueue<Puzzle> heap;
    VisualizerFrame frame;
    Puzzle initial;

    public AStar(String heuristic, Puzzle initial, VisualizerFrame frame) {
        this.explored = new HashSet<>();
        this.initial = initial;
        this.frame = frame;

        // default is euclidean
        if(heuristic.contains("manhattan")) {
            Comparator<Puzzle> comparator = new Comparator<Puzzle>() {
                @Override
                public int compare(Puzzle p1, Puzzle p2) {
                    // use manhattan distance
                    // goal is (0, 0)
                    int manhattanP1 = Math.abs(p1.row) + Math.abs(p1.col);
                    int manhattanP2 = Math.abs(p2.row) + Math.abs(p2.col);

                    if(manhattanP1 < manhattanP2) {
                        return -1;
                    } else if(manhattanP1 > manhattanP2) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            };
            heap = new PriorityQueue<>(comparator);
        } else {
            Comparator<Puzzle> comparator = new Comparator<Puzzle>() {
                @Override
                public int compare(Puzzle p1, Puzzle p2) {
                    // use manhattan distance
                    // goal is (0, 0)
                    double euclidP1 = Math.sqrt(Math.pow(p1.row, 2) + Math.pow(p1.col, 2));
                    double euclidP2 = Math.sqrt(Math.pow(p2.row, 2) + Math.pow(p2.col, 2));

                    if(euclidP1 < euclidP2) {
                        return -1;
                    } else if(euclidP1 > euclidP2) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            };
            heap = new PriorityQueue<>(comparator);
        }

        
        
    }

    public boolean search(Puzzle initialState) {
        ArrayList<Puzzle> neighbors = getNeighbors(initialState);
        printState(initialState);
        for(Puzzle neighbor : neighbors) {
            heap.add(neighbor);
        }
        explored.add(initialState.toString());
        frame.reDrawArray(initialState.getState1D(), initialState.row*3 + initialState.col);

    
        while(!heap.isEmpty()) {
            Puzzle current = heap.remove();
            frame.reDrawArray(current.getState1D(), current.row*3 + current.col);
            explored.add(current.toString());
            printState(current);
            if(current.testState()) {
                return true;
            }
            for(Puzzle neighbor : getNeighbors(current)) {
                if(!explored.contains(neighbor.toString())) {
                    heap.add(neighbor);
                } else if(heap.contains(neighbor)) {
                    System.out.println(heap.remove(neighbor));
                }
            }

            try {
                Thread.sleep(SearchVisualizer.sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    private void printState(Puzzle state) {
        System.out.println("------------------------------State------------------------------");
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++){
                System.out.print(state.state[i][j] + "\t");
            }
        }
        System.out.println();
    }

    public void run() {
        search(this.initial);
        SearchVisualizer.isSearching = false;
    }

}