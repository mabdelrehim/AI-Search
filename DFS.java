import java.util.*;
import java.util.concurrent.TimeUnit;
 
public class DFS extends AbstractSearch implements Search, Runnable{
 
    Deque<Puzzle> frontier = new ArrayDeque<Puzzle>();
    HashSet<String> explored = new HashSet<>();
    ArrayList<Puzzle> neighbors = new ArrayList<>();
    ArrayList<Integer> printState = new ArrayList<>();
    Puzzle initialState;
    VisualizerFrame frame;
 
    public DFS(Integer[] init, VisualizerFrame frame) {
        int[][] stateArr = new int[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                stateArr[i][j] = init[i*3+j];
            }
        }
        this.initialState = new Puzzle(stateArr, 0);
        this.frame = frame;
    }
 
    public boolean search(Puzzle initialState){
        //Figure f;
        int max = -1;
 
        frontier.push(initialState);
 
        while(!frontier.isEmpty()){
            Puzzle currentState = frontier.pop();
            max = Math.max(max, currentState.depth_at);
            //f = new Figure(9, currentState.getState1D());
            /*try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
            //f.getF().dispose();
            frame.reDrawArray(currentState.getState1D());
			try {
				Thread.sleep(SearchVisualizer.sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
 
            for(int i = 0; i < 3; i++) {
                for(int j=0; j<3; j++){
                    printState.add(currentState.state[i][j]);
                }
            }
            explored.add(printState.toString());
 
            System.out.println("---------------State---------------");
 
            System.out.println(printState);
            printState.clear();
            //figure
 
            if(currentState.testState()){
                System.out.println("---------------Goal---------------");
                System.out.println("DFS max depth = " + Integer.toString(max));
                System.out.println("DFS cost of path = " + Integer.toString(currentState.depth_at));
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
                    Puzzle temp = new Puzzle(neighbor.state, neighbor.depth_at + 1);
                    frontier.push(temp);
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