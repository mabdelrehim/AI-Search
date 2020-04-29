public class Main {
 
    public static void main(String[] args) {
        // write your code here
        Puzzle init = new Puzzle(new int[][]{{1,2,5},
                                    {3,4,0},
                                    {6,7,8}});
        Search search = new AStar("manhattan");
        search.search(init);
    }
}