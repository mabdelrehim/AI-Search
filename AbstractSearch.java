import java.util.ArrayList;

public class AbstractSearch{

    public ArrayList<Puzzle> getNeighbors(Puzzle currentState) {
        ArrayList<Puzzle> retVal = new ArrayList<>();
        
        // move left
        if(currentState.col - 1 >= 0) {
            Puzzle newState = new Puzzle(currentState.state);
            int temp = newState.state[newState.row][newState.col - 1];
            newState.state[newState.row][newState.col - 1] = newState.state[newState.row][newState.col];
            newState.state[newState.row][newState.col] = temp;
            newState.col = newState.col - 1;
            retVal.add(newState);
        }

        // move right
        if(currentState.col + 1 < 3) {
            Puzzle newState = new Puzzle(currentState.state);
            int temp = newState.state[newState.row][newState.col + 1];
            newState.state[newState.row][newState.col + 1] = newState.state[newState.row][newState.col];
            newState.state[newState.row][newState.col] = temp;
            newState.col = newState.col + 1;
            retVal.add(newState);
        }

        // move up
        if(currentState.row - 1 >= 0) {
            Puzzle newState = new Puzzle(currentState.state);
            int temp = newState.state[newState.row - 1][newState.col];
            newState.state[newState.row - 1][newState.col] = newState.state[newState.row][newState.col];
            newState.state[newState.row][newState.col] = temp;
            newState.row = newState.row - 1;
            retVal.add(newState);
        }

        // move down
        if(currentState.row + 1 < 3) {
            Puzzle newState = new Puzzle(currentState.state);
            int temp = newState.state[newState.row + 1][newState.col];
            newState.state[newState.row + 1][newState.col] = newState.state[newState.row][newState.col];
            newState.state[newState.row][newState.col] = temp;
            newState.row = newState.row + 1;
            retVal.add(newState);
        }

        return retVal;
    }

}