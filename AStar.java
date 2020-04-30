import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

public class AStar extends AbstractSearch implements Search, Runnable {

    HashSet<String> explored;
    PriorityQueue<Puzzle> heap;
    Puzzle initialState;
    VisualizerFrame frame;

    public AStar(String heuristic, Integer[] init, VisualizerFrame frame) {
        this.frame = frame;
        this.explored = new HashSet<>();
        int[][] stateArr = new int[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                stateArr[i][j] = init[i*3+j];
            }
        }
        this.initialState = new Puzzle(stateArr, 0);
        // default is euclidean
        if (heuristic.contains("manhattan")) {
            Comparator<Puzzle> comparator = new Comparator<Puzzle>() {
                @Override
                public int compare(Puzzle p1, Puzzle p2) {
                    // use manhattan distance
                    // goal is (0, 0)
                    int manhattanP1 = Math.abs(p1.row) + Math.abs(p1.col);
                    int manhattanP2 = Math.abs(p2.row) + Math.abs(p2.col);

                    if (manhattanP1 < manhattanP2) {
                        return -1;
                    } else if (manhattanP1 > manhattanP2) {
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

                    if (euclidP1 < euclidP2) {
                        return -1;
                    } else if (euclidP1 > euclidP2) {
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
        int max = -1;
        //SearchVisualizer.isSearching = true;
        //Figure f;
        ArrayList<Puzzle> neighbors = getNeighbors(initialState);
        printState(initialState);
        for (Puzzle neighbor : neighbors) {
            heap.add(neighbor);
        }
        explored.add(initialState.toString());

        while (!heap.isEmpty()) {
            Puzzle current = heap.remove();
            max = Math.max(max, current.depth_at);
            explored.add(current.toString());
            frame.reDrawArray(current.getState1D());
			try {
				Thread.sleep(SearchVisualizer.sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            //f = new Figure(9, current.getState1D());
            printState(current);
            if (current.testState()) {
                System.out.println("---------------Goal---------------");
                System.out.println("BFS max depth = " + Integer.toString(max));
                System.out.println("BFS cost of path = " + Integer.toString(current.depth_at));
                return true;
            }
            /*try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            f.getF().dispose();*/
            for(Puzzle neighbor : getNeighbors(current)) {
                if(!explored.contains(neighbor.toString())) {
                    heap.add(neighbor);
                } else if(heap.contains(neighbor)) {
                    System.out.println(heap.remove(neighbor));
                }
            }

        }
        System.out.println("DFS max depth = " + Integer.toString(max));
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
		search(this.initialState);
		SearchVisualizer.isSearching=false;
	}

}