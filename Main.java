public class Main {
 
    public static void main(String[] args) {
        // write your code here
        Puzzle init = new Puzzle(new int[][]{{1,0,2},
                                    {3,4,5},
                                    {6,7,8}});
        Search search = new AStar("euclid");
        search.search(init);
    }
}