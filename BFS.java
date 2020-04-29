import java.util.*;
public class BFS extends AbstractSearch implements Search, Runnable{
    Queue<Puzzle> frontier = new LinkedList<Puzzle>();
    HashSet<String> explored = new HashSet<>();
    ArrayList<Puzzle> neighbors = new ArrayList<>();
    ArrayList<Integer> printState = new ArrayList<>();
    public BFS() {
 
    }
 
 
    public boolean search(Puzzle initialState) {
        frontier.add(initialState);
 
        while(!frontier.isEmpty()){
            Puzzle currentState = frontier.remove();
 
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
                    Puzzle temp = new Puzzle(neighbor.state);
                    frontier.add(temp);
                }
                printState.clear();
            }
 
 
        }
        
        return false;
 
    }

    public void run() {
        
    }
 
}