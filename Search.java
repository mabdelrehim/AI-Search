import java.util.ArrayList;

public interface Search {
    ArrayList<Puzzle> getNeighbors(Puzzle currentState);
    void search(Puzzle initialState);

}