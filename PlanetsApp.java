import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;



public class PlanetsApp extends JPanel implements ActionListener{
	public Planet planet;
	public Moon moon;
	ArrayList<Moon> ml = new ArrayList<Moon>(); 
	ArrayList<Planet> planetList;
	public static PlanetsApp planetsApp = new PlanetsApp();
	JTextArea textArea = new JTextArea();
	JTextField search;
	
	boolean goingRight;
	Timer tm = new Timer(5, this);
	int JupiterX = 100;
	int JupiterY = 450;
	int PlanetXx=200, PlanetXy=450,
			c1x=150, c1y=425, 
				c2x=100, c2y=400, 
					c3x=50, c3y=425, 
						c4x=0, c4y=450, 
							c5x=50, c5y=475, 
								c6x=100, c6y=500, 
									c7x=150, c7y=475,
										targetX = c1x, targetY = c2x;
	public static int[][] positions = {{200,450},{150,425},{50,425},{0,450},{50,475},{100,500},{150,475}};
	JLabel label2;
	
	float angle = 0; //init angle
	int r = 50;
	int totalPop = 0;
	int totMat = 0;
	int PlanetXwidth = 100;
	int PlanetXheight = 100;
	int dx;
	int dy;
	int move = 2;

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {            
			public void run() { 
				createAndShowGUI();           
			}       
		});
		
	}

	public PlanetsApp() {
		
		
		createPlanets();
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 900, 350);
		add(scrollPane);
		textArea.setLineWrap(false);
		scrollPane.setViewportView(textArea);
		
		//********************************************************DISPLAY HABITABLE**********************
		JButton habitable = new JButton("HABITABLE");
		habitable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder builder = new StringBuilder();
				String displayAll = "";
				for(int i = 0; i < 100; ++i){
					if(planetList.get(i) instanceof Habitable){
						builder.append( planetList.get(i) + "\n");
						displayAll = builder.toString();
					}
				}
				textArea.setText(displayAll);
			}
		});
		habitable.setBounds(10, 20, 100, 23);
		add(habitable);
		
		//********************************************************DISPLAY UNINHABITABLE**********************
				JButton uninhabitable = new JButton("UNINHABITABLE");
				uninhabitable.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						StringBuilder builder = new StringBuilder();
						String displayAll = "";
						for(int i = 0; i < 100; ++i){
							if(planetList.get(i) instanceof Uninhabitable){
								builder.append( planetList.get(i) + "\n");
								displayAll = builder.toString();
							}
						}
						textArea.setText(displayAll);
					}
				});
				uninhabitable.setBounds(120, 20, 100, 23);
				add(uninhabitable);
		
		//********************************************************DISPLAY TOTALPOPULATION**********************
				JButton population = new JButton("POPULATION");
				population.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						StringBuilder builder = new StringBuilder();
						String displayAll = "";
						builder.append("Total population of all planets: " + totalPop);
						displayAll = builder.toString();
						textArea.setText(displayAll);
					}
				});
				population.setBounds(230, 20, 100, 23);
				add(population);

				//********************************************************DISPLAY UNINHABITABLE**********************
						JButton totalMaterials = new JButton("TOTAL MATERIALS");
						totalMaterials.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								StringBuilder builder = new StringBuilder();
								String displayAll = "";
								
										builder.append("Total mineable material is: " + totMat/10);
										displayAll = builder.toString();
									
								
								textArea.setText(displayAll);
							}
						});
						totalMaterials.setBounds(400, 20, 100, 23);
						add(totalMaterials);
		//**********************************************************SEARCH**********************
		search = new JTextField("Search");
		search.setBounds( 10, 410, 290, 20);
		add(search);
		search.setColumns(10);
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String searchFor = search.getText();
				StringBuilder builder = new StringBuilder();
				StringBuilder builder2 = new StringBuilder();
				
				for (int i = 1; i < planetList.size(); i++) {
					
					if(searchFor.equals( planetList.get(i).getName() )){
						
						if(planetList.get(i) instanceof Habitable) {
							builder.append("Habitable Planet Found -> " + planetList.get(i).getName()  );
							String searchResult = builder.toString();
							search.setText(searchResult);
							builder2.append(
									"Planet Name-> " + planetList.get(i).getName() + 
									"\n Size ->" + planetList.get(i).getSize() +" km " + 
									"\n Potential max population -" + planetList.get(i).getPopSize() +
									"\n Number of moons - " + planetList.get(i).moons.size() + 
									"\n Water % - " + ((Habitable) planetList.get(i)).getWater() +
									"\n\n Moon 1 detail - " + planetList.get(i).moons.get(0) +
									"\n Moon 1 detail - " + planetList.get(i).moons.get(1) ); 
							String searchResult2 = builder2.toString();
							textArea.setText(searchResult2);
							break;
						} else if(planetList.get(i) instanceof Uninhabitable){
							builder.append("Uninhabitable Planet Found -> " + planetList.get(i).getName()  );
							String searchResult = builder.toString();
							search.setText(searchResult);
							builder2.append(
									"Planet Name-> " + planetList.get(i).getName() + 
									"\n Size ->" + planetList.get(i).getSize() +" km " + 
									"\n Potential max population -" + planetList.get(i).getPopSize() +
									"\n Number of moons - " + planetList.get(i).moons.size() + 
									"\n Atmospheric composition - " + ((Uninhabitable) planetList.get(i)).getAtmosphericComposition() +
									"\n\n Moon 1 detail - " + planetList.get(i).moons.get(0) +
									"\n Moon 1 detail - " + planetList.get(i).moons.get(1) ); 
							String searchResult2 = builder2.toString();
							textArea.setText(searchResult2);
							break;
						}
						
					} else {
						search.setText("Not Found");
					}
				}			
			}
		});
		
		
		//---------------------------------------Moon-----------------------------------
		Icon icon2 = new ImageIcon("src/planetx.gif");
		label2 = new JLabel(icon2);

		//label2.setBounds(PlanetXx, PlanetXy, 100, 100);
		add(label2);
		
		//---------------------------------------JUPITER-----------------------------------
		Icon icon = new ImageIcon("src/Jupiter.gif");
        JLabel label = new JLabel(icon);
        label.setBounds(JupiterX, JupiterY, 100, 100);
		add(label);

		

	}

	public void createPlanets(){
		planetList = new ArrayList <Planet>();
		ml.add(new Moon("  -> moon TB123", 14356 ,"Iron, Nitrogen"));
		ml.add(new Moon("  -> moon RS678", 14356 ,"Helium, Carbon, Uranium"));
		
		for(int i = 0; i < 100; ++i){
			if(i < 50){
				Habitable p1= new Habitable("Pluto"+i, 5657+i, 500+i, "%"+i, ml);
				planetList.add(p1);
				totalPop += planetList.get(i).getPopSize();
				totMat +=planetList.get(i).getSize();
			} else{
				Uninhabitable p1= new Uninhabitable("AlienPlanet"+i, 5657+i, 500+i, "CarbonMonoxide, Nitrogen", ml);
				planetList.add(p1);
				totalPop += planetList.get(i).getPopSize();
				totMat +=planetList.get(i).getSize();
			}
		}
	}

	public void minePlanets(){
		for(int i = 0; i < 100; ++i){
			planetList.get(i).mineMe();

			for(int j = 0; j < ml.size() ; ++j){
				ml.get(j).mineMe();
			}
		}
		
	}

	
	//***********************************************************************************************************************

		private static void createAndShowGUI() {

			JFrame frame = new JFrame();

			frame.setSize(925, 600);
			frame.add(planetsApp);
			frame.setTitle("Planets App");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   		frame.setVisible(true);
			frame.setResizable(false);


		}
		
		//**********************************************************************************************************************

		public void paintComponent(Graphics g) {

			super.paintComponent(g);


			ImageIcon i = new ImageIcon("src/background.jpg");
			i.paintIcon(this, g, 0, 0);


			label2.setBounds(PlanetXx, PlanetXy, PlanetXwidth, PlanetXheight);
				//g.setColor(Color.RED);
				//g.fillOval(50, 450, 100, 100);
							
			tm.start();

		}

	//*********************************************************************************************************************

		public void actionPerformed(ActionEvent e) {
			
			int distanceX = 0;
			if(PlanetXx > JupiterX) {
				distanceX = PlanetXx - JupiterX;
			} else if(JupiterX > PlanetXx){
				distanceX = JupiterX - PlanetXx;
			}
			int distanceY = 0;
			if(PlanetXy > JupiterY) {
				distanceY = PlanetXy - JupiterY;
			} else if(JupiterY > PlanetXy){
				distanceY = JupiterY - PlanetXy;
			}
			int distance = (int)Math.sqrt((distanceX*distanceX)+(distanceY*distanceY));
			//System.out.println(PlanetXx);
			
			
				
				//PlanetXx = positions[0][0];
				//PlanetXy = positions[0][1];
			//goingRight
			if(PlanetXx >= JupiterX) {
				if(goingRight) {
					dx = move;
				} else {
					dx = -move;
				}
				
				if(PlanetXx + dx > JupiterX + 100) {
					goingRight = false;
					
				}			
				//goingRight
			}
			
			if(PlanetXx < JupiterX) {
				if(goingRight) {
					dx = move;
				} else {
					dx = -move;
				}
				
				if(PlanetXx + dx < JupiterX - 100) {
					goingRight = true;
					
				}
			}
			/*
			if(distance > 60 && PlanetXx > JupiterX){
				System.out.println(distance);
				dx = -move;
			} else if (distance > 60 && PlanetXx < JupiterX){
				dx = move;
			}
			*/
			PlanetXx += dx;
			dx=0;
			
			repaint();
		} //action performed
} 

