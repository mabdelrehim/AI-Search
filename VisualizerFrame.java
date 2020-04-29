
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
	
	private final String[] Searches = {"Bradth First Search", "Depth First Search", "A* Search Manhattan", "A* Search Euclidean"};
	
	private int sizeModifier;

	private JPanel wrapper;
	private JPanel arrayWrapper;
	private JPanel buttonWrapper;
	private JPanel[] squarePanels;
	private JButton start;
	private JComboBox<String> selection;
	private JSlider speed;
	private JLabel speedVal;
	private GridBagConstraints c;
	private JCheckBox stepped;
	
	public VisualizerFrame(){
		super("AI Search Visualizer");
		
		start = new JButton("Start");
		buttonWrapper = new JPanel();
		arrayWrapper = new JPanel();
		wrapper = new JPanel();
		selection = new JComboBox<String>();
		speed = new JSlider(MIN_SPEED, MAX_SPEED, DEFAULT_SPEED);
		speedVal = new JLabel("Speed: 20 ms");
		stepped = new JCheckBox("Stepped Values");
		c = new GridBagConstraints();
		
		for(String s : Searches) selection.addItem(s);
		
		arrayWrapper.setLayout(new GridBagLayout());
		wrapper.setLayout(new BorderLayout());

		c.insets = new Insets(0,1,0,1);
		c.anchor = GridBagConstraints.SOUTH;
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchVisualizer.startSearch((String) selection.getSelectedItem());
			}
		});
		
		stepped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchVisualizer.stepped = stepped.isSelected();
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
		
		

		buttonWrapper.add(stepped);
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
				// Reset the sizeModifier
				// 90% of the windows height, divided by the size of the sorted array.
				sizeModifier = (int) ((getHeight()*0.9)/(squarePanels.length));
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
		setLocationRelativeTo(null);
	}
	
	// preDrawArray reinitializes the array of panels that represent the values. They are set based on the size of the window.
	public void preDrawArray(Integer[] squares){
		squarePanels = new JPanel[9];
		arrayWrapper.removeAll();
		// 90% of the windows height, divided by the size of the sorted array.
		sizeModifier =  (int) ((getHeight()*0.9)/(squarePanels.length));
		for(int i = 0; i < 9; i++){
			squarePanels[i] = new JPanel();
			squarePanels[i].setPreferredSize(new Dimension(SearchVisualizer.blockWidth, squares[i]*sizeModifier));
			squarePanels[i].setBackground(Color.blue);
			arrayWrapper.add(squarePanels[i], c);
		}
		repaint();
		validate();
	}
	
	public void reDrawArray(Integer[] x){
		reDrawArray(x, -1);
	}
	
	// reDrawArray does similar to preDrawArray except it does not reinitialize the panel array.
	public void reDrawArray(Integer[] squares, int zeroPos){
		arrayWrapper.removeAll();
		for(int i = 0; i < squarePanels.length; i++){
			squarePanels[i] = new JPanel();
			squarePanels[i].setPreferredSize(new Dimension(SearchVisualizer.blockWidth, squares[i]*sizeModifier));
			if (i == zeroPos){
				squarePanels[i].setBackground(Color.green);				
			} else {
				squarePanels[i].setBackground(Color.blue);
			}
			arrayWrapper.add(squarePanels[i], c);
		}
		repaint();
		validate();
	}
	
}