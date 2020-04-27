import java.util.ArrayList;

public class AbstractSearch{

    public ArrayList<Puzzle> getNeighbors(Puzzle currentState) {
        ArrayList<Puzzle> retVal = new ArrayList<>();

        // move right
        if(currentState.col + 1 < 3) {
            int [][] copy = new int[3][3];
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    copy[i][j] = currentState.state[i][j];
                }
            }
            Puzzle newState = new Puzzle(copy);
            int temp = newState.state[newState.row][newState.col + 1];
            newState.state[newState.row][newState.col + 1] = newState.state[newState.row][newState.col];
            newState.state[newState.row][newState.col] = temp;
            newState.col = newState.col + 1;
            retVal.add(newState);
        }

        // move up
        if(currentState.row - 1 >= 0) {
            int [][] copy = new int[3][3];
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    copy[i][j] = currentState.state[i][j];
                }
            }
            Puzzle newState = new Puzzle(copy);
            int temp = newState.state[newState.row - 1][newState.col];
            newState.state[newState.row - 1][newState.col] = newState.state[newState.row][newState.col];
            newState.state[newState.row][newState.col] = temp;
            newState.row = newState.row - 1;
            retVal.add(newState);
        }

        // move down
        if(currentState.row + 1 < 3) {
            int [][] copy = new int[3][3];
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    copy[i][j] = currentState.state[i][j];
                }
            }
            Puzzle newState = new Puzzle(copy);
            int temp = newState.state[newState.row + 1][newState.col];
            newState.state[newState.row + 1][newState.col] = newState.state[newState.row][newState.col];
            newState.state[newState.row][newState.col] = temp;
            newState.row = newState.row + 1;
            retVal.add(newState);
        }

        // move left
        if(currentState.col - 1 >= 0) {
            int [][] copy = new int[3][3];
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    copy[i][j] = currentState.state[i][j];
                }
            }
            Puzzle newState = new Puzzle(copy);
            int temp = newState.state[newState.row][newState.col - 1];
            newState.state[newState.row][newState.col - 1] = newState.state[newState.row][newState.col];
            newState.state[newState.row][newState.col] = temp;
            newState.col = newState.col - 1;
            retVal.add(newState);
        }

        return retVal;
    }

}