import java.util.List; 
import javax.swing.*; 
import java.util.ArrayList; 
import java.awt.*; 
import java.awt.event.*; 
import java.awt.Graphics; 
import javax.swing.JComponent; 
/*
 * Class used to display a GUI for calculating the convex hull. 
 */
public class ConvexHull{		
	//Method used to create and dsiplay the GUI	
	public static void createAndShowGUI(){
		//Gets the top panel 
		JPanel panel1 = new JPanel();
	 	//Creates a JLabel.	
		JLabel label1 = new JLabel("CONVEX HULL GENERATOR");
		//Sets the font, foreground, positioning of the jlabel. 
		label1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 32));
		label1.setForeground(Color.LIGHT_GRAY);
		label1.setHorizontalAlignment(SwingConstants.CENTER); 
		//Sets the background of the panel color. 	
		panel1.setBackground(Color.DARK_GRAY);
		//Adds the label unto the jlabel. 
		panel1.add(label1);
		
		//Creates a second jpanel with a floylayout. 
		JPanel panel2 = new JPanel(new FlowLayout()); 
		
		//Creates a mainPanel with its layout as borderlayout.
		JPanel mainPanel = new JPanel(); 
		mainPanel.setLayout(new BorderLayout()); 
		//Creates a panel for the west. 
		JPanel panelWest = new JPanel();
		//Creates a penl for the east.
		JPanel panelEast = new JPanel();
		//Sets both the panels size evenly. 
		panelWest.setPreferredSize(new Dimension(250,50)); 
		panelEast.setPreferredSize(new Dimension(250,50)); 
		//Creates a finalized panel.
		final	focusPanel panelCenter = new focusPanel(); 
		//Adds the west, and east panels unto the mainPanel.
		//Then adds the panelCenter unto the mainPanel. 
		mainPanel.add(panelWest, BorderLayout.WEST); 
		mainPanel.add(panelEast, BorderLayout.EAST); 
		mainPanel.add(panelCenter, BorderLayout.CENTER); 	
		
		//Creates Jbutton for start of calculation of convex hull.
		final JButton button1 = new JButton("START"); 
		//Sets background light gray
		button1.setBackground(Color.LIGHT_GRAY);	
		//Sets foreground color black. 
		button1.setForeground(Color.black);
		//Sets size. 
	 	button1.setPreferredSize(new Dimension(250,50));	
		//No focus. 
		button1.setFocusPainted(false); 
		
		//Creates Jbutton for clear. 
		final JButton button2 = new JButton("CLEAR");
		//Same as the start button. 
		button2.setBackground(Color.LIGHT_GRAY); 	
		button2.setForeground(Color.black); 
	 	button2.setPreferredSize(new Dimension(250,50));	
		button2.setFocusPainted(false);

		//Adds mouseListener to button1 
		button1.addMouseListener(new MouseAdapter() {
				//event for when mouseEntered the button
				public void mouseEntered(MouseEvent e){
						button1.setBackground(Color.DARK_GRAY);
					 	button1.setForeground(Color.LIGHT_GRAY); 	
				}
				//Event for when mouseExited the button.
				public void mouseExited(MouseEvent e){
						button1.setBackground(Color.LIGHT_GRAY);	
						button1.setForeground(Color.black); 
				}
				//Event for when mouseClicked the button. 
				public void mouseClicked(MouseEvent e){
					panelCenter.drawConvexHull(); 
				}
		});
		
		//Add mouseListenr to button2
		button2.addMouseListener(new MouseAdapter() {
				//Event for when mouseEntered button. 
				public void mouseEntered(MouseEvent e){
						button2.setBackground(Color.DARK_GRAY); 
						button2.setForeground(Color.LIGHT_GRAY); 
				}
				//Event for when mouseExited the button. 
				public void mouseExited(MouseEvent e){
						button2.setBackground(Color.LIGHT_GRAY);
					 	button2.setForeground(Color.black); 	
				}
				//Event for when mouse Clicked the button. 
				public void mouseClicked(MouseEvent e){
						panelCenter.clearList(); 		
				}	
		});
		//Adds the buttons to the panel. 
		panel2.add(button1); 
		panel2.add(button2); 
		
		//Creates JFrame or window object for the GUI. 
		JFrame frame = new JFrame();
		//Sets the layout as border. 
	 	frame.setLayout(new BorderLayout());
		//Sets the size of the window by default. 	
		frame.setMinimumSize(new Dimension(1200,600));
		//Adds default close operation to exit the GUI. 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 	//Adds to the content pane of the frame the north panel of the title or jlabel. 	
		frame.getContentPane().add(panel1, BorderLayout.NORTH); 
		//Adds to the content pane of the frame the south panel of buttons. 
		frame.getContentPane().add(panel2, BorderLayout.SOUTH);
		//Adds to the content pane of the frame the center panel with all the GUI and functionality. 
	 	frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		//Packs everything together. 
		frame.pack(); 
		//Sets the window visible. 
		frame.setVisible(true); 
	}
	//Main method to run the display of the GUI. 
	public static void main(String[] args){
		createAndShowGUI(); 
	}
}

/*
 *	Class used to act as the main panel in which the GUI for displaying the calculation of the convex_hull takes place. 
 */
class focusPanel extends JPanel {
	//Flag used to determine if the mouse has hoveredOver the content area. 
	private static boolean hoveredOver = false; 
	//Flag used to determine if the user clicked content area. 
	private static boolean clicked = false; 
	//flag used to determine whether to draw or not. 
	private static boolean draw = false; 
	//String used to write the current position the mouse is on the content area. 
	private static String str = "";
	//Used to update the position. 
	private static Point position;
	//Gets the length of the number of points. 
	private static int length = 0; 
	//Maintains a list of points added to the content area. 
 	private static ArrayList<Point> list = new ArrayList<Point>();
	//Maintains the convex_hull of the set of points on the content area. 
	private static List<Point> convex = new ArrayList<Point>(); 
		
	//Main paint method used to draw the content in the content area. 
	public void paintComponent(Graphics g) {
		//Invokes super constructor. 
		super.paintComponent(g); 
		//Draws the points onto the content area. 
		for(int i = 0; i < list.size(); i++){
					g.drawOval(list.get(i).x,list.get(i).y,5,5);
		 			g.fillOval(list.get(i).x, list.get(i).y,5,5);
				}
		//If mouse has hovered over draw string of the position using the Point object x and y fields. 
		if(hoveredOver){
				str = "("+position.x+","+position.y+")";
				g.drawString(str,position.x,position.y); 
		}	
		
		//If draw option been selected draw the lines connecting the convex_hull points on the content area.  
		if(draw){
				for(int i = 0; i < length; i++){
					if(i != convex.size()-1){
							g.drawLine(convex.get(i).x,convex.get(i).y,convex.get(i+1).x,convex.get(i+1).y); 
					}
				}
		}
		//Set the flag as false, however not draw since want it to display unless clear has been selected. 
		clicked = false; 
		hoveredOver = false; 
	}
	//Focus panel consstructor. 
	public focusPanel() {
		//Set the background color as white. 
		setBackground(Color.white); 
		//Adds a mouse listenr with mouseAdapter object which is used to determine when mouse has moved in the content area. 
		addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
					hoveredOver = true; 
				 	position = e.getPoint();
					repaint(); 			
			}
		});
		//Mouse listenr for when the mouse has exited the content area. 
		addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e){
				hoveredOver = false; 
				repaint(); 
			}
			//Mouse listener for when the mouse clicked the content area for drawing points. 
			public void mouseClicked(MouseEvent e){
				clicked = true;
			 	position = e.getPoint(); 	
				list.add(position); 
				repaint(); 	
			}		
		}); 
	}
	//Method used to clear the list of the points and convex hull. 
	public void clearList(){
		Graham_Scan.clear(); 
		list.clear();
		length = 0; 
	 	repaint(); 	
	}
	//Used to draw the Convex_Hull 
	public void drawConvexHull(){
			//Sets the draw flag as true. 
			draw = true;
			//Clears the convex_hull. 
			Graham_Scan.clear();	
			//Re-adds the points. 
			for(int i = 0; i < list.size(); i++){
				Graham_Scan.addPoint(list.get(i)); 
			}
			//Peforms the graham_scan algorithm on the set of points and returns the convex hull. 
			convex = Graham_Scan.scan(); 
			//Gets the length. 
			length = convex.size();
		  //Calls the paint method to draw the updated convex_hull. 	
			repaint(); 
	}
}
