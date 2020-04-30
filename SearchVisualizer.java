import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SearchVisualizer {
	
	private static Thread searchingThread;

	public static VisualizerFrame frame;
	public static Integer[] toBeSearched;
	public static boolean isSearching = false;
	public static int sleep = 20;
	
	public static void main(String[] args) {
		frame = new VisualizerFrame();
		resetArray();
		frame.setLocationRelativeTo(null);
	}
	
	public static void resetArray(){
		// If we are currently in a sorting method, then isSorting should be true
		// We do not want to reinitialize/reset the array mid sort.
        if (isSearching) return;
        int[] arr = {1, 2, 5, 3, 4, 0, 6, 7, 8};
        toBeSearched = Arrays.stream(arr).boxed().toArray(Integer[]::new);
		// If we're using incremental values, they are already sorted. This shuffles it.
		ArrayList<Integer> shuffleThis = new ArrayList<>();
		for (int i = 0; i < toBeSearched.length; i++) {
			shuffleThis.add(toBeSearched[i]);
		}
		//Collections.shuffle(shuffleThis);
		toBeSearched = shuffleThis.toArray(toBeSearched);
		
		frame.preDrawArray(shuffleThis);
	}
	
	public static void startSearch(String type){
		
		if (searchingThread == null || !isSearching){
			
			resetArray();
			
			isSearching = true;

			switch(type){
            case "A* Manhattan":
				searchingThread = new Thread(new AStar("manhattan", toBeSearched, frame));
				break;

            case "A* Euclidean":
				searchingThread = new Thread(new AStar("euclidean", toBeSearched, frame));
				break;

            case "BFS":
                searchingThread = new Thread(new DFS(toBeSearched, frame));
                break;
            case "DFS":
                searchingThread = new Thread(new BFS(toBeSearched, frame));
				break;
				
			default:
				isSearching = false;
				return;
			}
			
			searchingThread.start();
			
		}
		
	}

}