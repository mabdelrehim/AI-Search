
import java.util.*;
import java.util.concurrent.TimeUnit;
public class BFS extends AbstractSearch implements Search, Runnable{
    Queue<Puzzle> frontier = new LinkedList<Puzzle>();
    HashSet<String> explored = new HashSet<>();
    ArrayList<Puzzle> neighbors = new ArrayList<>();
    ArrayList<Integer> printState = new ArrayList<>();
    Puzzle initialState;
    VisualizerFrame frame;
    public BFS(Integer[] init, VisualizerFrame frame) {
        int[][] stateArr = new int[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                stateArr[i][j] = init[i*3+j];
            }
        }
        this.initialState = new Puzzle(stateArr, 0);
        this.frame = frame;
    }
 
 
    public boolean search(Puzzle initialState) {
        int max = -1;
        frontier.add(initialState);
        //Figure f;
 
        while(!frontier.isEmpty()){
            Puzzle currentState = frontier.remove();
            max = Math.max(max, currentState.depth_at);
            /*f = new Figure(9, currentState.getState1D());
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            f.getF().dispose();*/
 
            for(int i = 0; i < 3; i++) {
                for(int j=0; j<3; j++){
                    printState.add(currentState.state[i][j]);
                }
            }
            explored.add(printState.toString());
            frame.reDrawArray(currentState.getState1D());
			try {
				Thread.sleep(SearchVisualizer.sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
 
            System.out.println("---------------State---------------");
 
            System.out.println(printState);
            printState.clear();
            //figure
 
            if(currentState.testState()){
                System.out.println("---------------Goal---------------");
                System.out.println("BFS max depth = " + Integer.toString(max));
                System.out.println("BFS cost of path = " + Integer.toString(currentState.depth_at));
                return true;
            }
            neighbors = getNeighbors(currentState);
            for(Puzzle neighbor: neighbors){
                for(int i = 0; i < 3; i++) {
                    for(int j=0; j<3; j++){
                        printState.add(neighbor.state[i][j]);
                    }
                }
 
                if(!explored.contains(printState.toString())) {
                    Puzzle temp = new Puzzle(neighbor.state, neighbor.depth_at);
                    frontier.add(temp);
                }
                printState.clear();
            }
 
 
        }
        System.out.println("DFS max depth = " + Integer.toString(max));
        return false;
    }
    public void run() {
		search(this.initialState);
		SearchVisualizer.isSearching=false;
	}
 
}