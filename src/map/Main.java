package map;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import map.BorderPanel;

public class Main {
	
	static JFrame frame;
	
	public static void main(String[] args) {

		frame = new JFrame ("MineCraft Map-Maker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMaximumSize(new Dimension(1150, 930));
		BorderPanel borderPanel = new BorderPanel();
		JScrollPane scroll = new JScrollPane(borderPanel);
		frame.getContentPane().add(scroll);
		

		frame.setLocation(50,10);

		frame.pack();
		frame.setVisible(true);
		
	}

}
