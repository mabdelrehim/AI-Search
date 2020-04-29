import java.util.ArrayList;
import java.util.Collections;


public class SearchVisualizer {
	
	private static Thread searchThread;

	public static VisualizerFrame frame;
	public static Integer[] state;
	public static boolean isSearching = false;
	public static int sleep = 20;
	public static int blockWidth;
	// Stepped depicts whether the values are incremental or random. True is incremental.
	public static boolean stepped = false;
	
	public static void main(String[] args) {
		frame = new VisualizerFrame();
		resetArray();
		frame.setLocationRelativeTo(null);
	}
	
	public static void resetArray() {
		// If we are currently in a sorting method, then isSorting should be true
		// We do not want to reinitialize/reset the array mid sort.
		if (isSearching) return;
		state = new Integer[9];
		blockWidth = (int) Math.max(Math.floor(500/9), 1);
		for(int i = 0; i < state.length; i++){
			state[i] = i;
		}
		// If we're using incremental values, they are already sorted. This shuffles it.
		ArrayList<Integer> shuffleThis = new ArrayList<>();
		for (int i = 0; i < state.length; i++) {
			shuffleThis.add(state[i]);
		}
		Collections.shuffle(shuffleThis);
		state = shuffleThis.toArray(state);
		frame.preDrawArray(state);
	}
	
	public static void startSearch(String type){
		
		if (searchThread == null || !isSearching){
			
			resetArray();
			
			isSearching = true;

			switch(type){
			case "Bradth First Search":
				break;

			case "Depth First Search":
				break;

			case "A* Search Manhattan":
				searchThread = new Thread(new AStar("manhattan", new Puzzle(state), frame));
				break;
            
            case "A* Search Euclidean":
                searchThread = new Thread(new AStar("euclidean", new Puzzle(state), frame));
                break;
			default:
				isSearching = false;
				return;
			}
			
			searchThread.start();
			
		}
		
	}

}