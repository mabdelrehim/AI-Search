public class Puzzle {
 
    int [][] state;
    int row;
    int col;
 
    Puzzle(int[][] init) {
        this.state = init;
 
 
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
                count++;
            }
        }
        return retVal;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                builder.append(Integer.toString(this.state[i][j]) + "\t");
            }
        }
        return builder.toString();
    }
 
 
}