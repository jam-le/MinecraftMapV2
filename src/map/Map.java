package map;


//---------------------------------------------------------------------------------------------------------------
//				File: MapPanel.java												Author: Jamie Lee
//
//
//										Creates the grid for the MineCraft Map
//---------------------------------------------------------------------------------------------------------------

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Map extends JPanel implements java.io.Serializable
	{
		
//---------        variables          ------------------------------------------------------------------------------------
	
	
	private static final long serialVersionUID = 1L;
		private int min = 0;					// The minimum size of the grid in pixels
		static int max = 800;					// The maximum size of the grid in pixels.
		private int midpoint = max/2;
		private int squareSize = 20;			// The variable squareSize allows you to set the size of the height/width
												// of each grid square on the coordinate map. x by x pixels. Must be a
												// a multiple of the max variable.
		
		private int numarray = (max/squareSize) +2; 	// This number calculates the number of array elements will be
														// needed to make the appropriate number of lines, as well as the
														// length of those lines.

		static ArrayList<MapObject> mapList = new ArrayList<MapObject>();
		
		final int SIZE = 3;
		public static JLabel coordinates, coordLabel;

//------------------------------------------------------------------------------------------------------------------------
		
	public Map()
	{
		setPreferredSize(new Dimension(850,870));
		setBackground(Color.white);
		setLayout(null);
		
		addMouseListener(new markListener());
		addMouseMotionListener(new movementListener());

		
		coordLabel = new JLabel("Coordinates: ");
		coordLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		coordLabel.setLocation(580,830);
		coordLabel.setSize(150,30);
		add(coordLabel);

	}	
	
//-----------     drawing the grid     -------------------------------------------------------------------------------

		public void paintComponent (Graphics page)
		{

			int [] array = new int [numarray];			//Creates an array to be used for x and y coordinates for the grid.
		
			super.paintComponent (page);
			page.setColor(new Color(150,220,250));
		
			for (int index=0; index < array.length-1; index++)
			{	
				array [index] = index * squareSize;			//Starting y and x coordinates
				page.drawLine(array[index]+20, min+20, array[index]+20, max+20);
				page.drawLine(min+20, array[index]+20, max+20, array[index]+20);
			}
			
				// Drawing the images onto grid
			
			page.setColor(Color.black);
			JLabel label = new JLabel (Mappers.createImgIcon("bedIcon.gif"));
			label.setLocation(midpoint-18, midpoint-20);
			label.setSize(47,35);
			add(label);
			page.drawString("0, 0", midpoint+15, midpoint+15);
			label.setToolTipText("Initial user-spawn point");
				
				// Drawing the map markers
			
			for (MapObject mapper : mapList)
			{
				JLabel icon = mapper.draw(page);
				add(icon);
			}
	
			if (MenuPanel.delete == true)
			{
				for (MapObject mapper : mapList)
				{
					JLabel icon = mapper.draw(page);
					add(icon);
				}
				MenuPanel.delete = false; 
			}
			
			repaint();
			
			
		}


//-------------------------------------------------------------------------------------------------------------------		
		
	private class markListener implements MouseListener 
	{
		public void mousePressed (MouseEvent event)
		{	
			int x = event.getX();
			int y = event.getY();
			
			if ((x >= 20) && (x <= 820))
			{
				if ((y >= 20) && (y <= 820))
				{	
					MapObject mapObject = new MapObject(x, y, Mappers.currentIcon);
					mapList.add(mapObject);
				}
			}
			
		}
		
		public void mouseClicked (MouseEvent event) 
		{
			//point = event.getPoint();
		}
		public void mouseReleased (MouseEvent event) {}
		public void mouseEntered (MouseEvent event) {}
		public void mouseExited (MouseEvent event) {}
	}	
		
//------------------------------------------------------------------------------------------------------------------

	private class movementListener implements MouseMotionListener
	{	

		public void mouseMoved (MouseEvent event)
		{			
			
			if (coordinates != null)
				remove(coordinates);
			Point point = new Point(event.getPoint());
			
			if (event.getX() >=20 && event.getY()>=20)
			{
				if (event.getX()<=820 && event.getY()<=820)
			
				{
					coordinates = new JLabel();
					int x = (int) point.getX();
					int y = (int) point.getY();
					int adjustedX = ((x-midpoint)*10);
					int adjustedY = ((midpoint-y)*10);
					coordinates.setText(adjustedX + ", " + adjustedY);
					coordinates.setLocation(715, 830);
					coordinates.setSize(150,30);
					coordinates.setFont(new Font("Dialog", Font.BOLD, 20));
					add(coordinates);
					repaint();
				}
				else
					repaint();
			}
			else
				repaint();
			
		}
		
		public void mouseDragged(MouseEvent event)
		{
			
		}
	}
}
