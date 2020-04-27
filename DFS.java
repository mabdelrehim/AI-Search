import java.util.*;
 
public class DFS extends AbstractSearch implements Search{
 
    Deque<Puzzle> frontier = new ArrayDeque<Puzzle>();
    HashSet<String> explored = new HashSet<>();
    ArrayList<Puzzle> neighbors = new ArrayList<>();
    ArrayList<Integer> printState = new ArrayList<>();
 
    public DFS() {
 
    }
 
    public void search(Puzzle initialState){
 
        frontier.push(initialState);
 
        while(!frontier.isEmpty()){
            Puzzle currentState = frontier.pop();
 
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
                return;
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
                    frontier.push(temp);
                }
                printState.clear();
            }
 
        }
    }
 
}