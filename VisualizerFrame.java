
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class VisualizerFrame extends JFrame {

	private final int MAX_SPEED = 1000;
    private final int MIN_SPEED = 1;
    private final int DEFAULT_SPEED = 20;
	
	private final String[] Sorts = {"A* Manhattan", "A* Euclidean", "DFS", "BFS"};

	private JPanel wrapper;
	private JPanel arrayWrapper;
	private JPanel buttonWrapper;
	private JButton[] squarePanels;
	private JButton start;
	private JComboBox<String> selection;
	private JSlider speed;
	private JLabel speedVal;
	private GridBagConstraints c;
	
	public VisualizerFrame(){
		super("Search Visualizer");
		
		start = new JButton("Start");
		buttonWrapper = new JPanel();
		arrayWrapper = new JPanel();
		wrapper = new JPanel();
		selection = new JComboBox<String>();
		speed = new JSlider(MIN_SPEED, MAX_SPEED, DEFAULT_SPEED);
		speedVal = new JLabel("Speed: 20 ms");
		c = new GridBagConstraints();
		
		for(String s : Sorts) selection.addItem(s);
		
		arrayWrapper.setLayout(new GridLayout(3, 3));
		wrapper.setLayout(new BorderLayout());

		c.insets = new Insets(0,1,0,1);
		c.anchor = GridBagConstraints.SOUTH;
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchVisualizer.startSearch((String) selection.getSelectedItem());
			}
		});
		
		speed.setMinorTickSpacing(10);
		speed.setMajorTickSpacing(100);
		speed.setPaintTicks(true);
		
		speed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				speedVal.setText(("Speed: " + Integer.toString(speed.getValue()) + "ms"));
				validate();
				SearchVisualizer.sleep = speed.getValue();
			}
		});

		buttonWrapper.add(speedVal);
		buttonWrapper.add(speed);
		buttonWrapper.add(start);
		buttonWrapper.add(selection);
		
		wrapper.add(buttonWrapper, BorderLayout.SOUTH);
		wrapper.add(arrayWrapper);
		
		add(wrapper);

		setExtendedState(JFrame.MAXIMIZED_BOTH );
		
		addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				// Do nothing
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// Do nothing
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// Do nothing
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// Do nothing
			}
			
		});
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		setLocationRelativeTo(null);
    }
	
	// preDrawArray reinitializes the array of panels that represent the values. They are set based on the size of the window.
	public void preDrawArray(ArrayList<Integer> squares){
		squarePanels = new JButton[9];
		arrayWrapper.removeAll();
		for(int i = 0; i<9; i++){
            squarePanels[i] = new JButton(Integer.toString(squares.get(i)));
            if(squares.get(i) == 0) {
                squarePanels[i].setBackground(Color.GREEN);
            } else {
                squarePanels[i].setBackground(Color.BLUE);
            }
			
			arrayWrapper.add(squarePanels[i], c);
		}
		repaint();
		validate();
	}
	
	// reDrawArray does similar to preDrawArray except it does not reinitialize the panel array.
	public void reDrawArray(ArrayList<Integer> squares){
		arrayWrapper.removeAll();
		for(int i = 0; i<squarePanels.length; i++){
			squarePanels[i] = new JButton(Integer.toString(squares.get(i)));
			if(squares.get(i) == 0) {
                squarePanels[i].setBackground(Color.GREEN);
            } else {
                squarePanels[i].setBackground(Color.BLUE);
            }
			arrayWrapper.add(squarePanels[i], c);
		}
		repaint();
		validate();
	}
	
}