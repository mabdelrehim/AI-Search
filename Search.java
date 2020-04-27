import java.util.ArrayList;

public interface Search {
    ArrayList<Puzzle> getNeighbors(Puzzle currentState);
    boolean search(Puzzle initialState);

}