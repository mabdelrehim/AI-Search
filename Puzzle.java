import java.util.ArrayList;

public class Puzzle {
    
    int [][] state;
    ArrayList<Puzzle> neighbors;
    int row;
    int col;

    Puzzle(int[][] init) {
        this.state = init;
        this.neighbors = new ArrayList<>();
        this.row = 0;
        this.col = 0;

        // get the position of zero
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(init[i][j] == 0) {
                    this.row = i;
                    this.col = j;
                }
            }
        }
        
    }

    boolean testState(){
        int count = 0;
        boolean retVal = true;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(this.state[i][j] != count) {
                    retVal = false;
                }
            }
        }
        return retVal;
    }


}