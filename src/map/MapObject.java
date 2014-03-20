package map;

import java.awt.*;

import javax.swing.*;

public class MapObject implements java.io.Serializable {
	

	private static final long serialVersionUID = 1L;
	private Point point;
	private ImageIcon mapperImage;
	int x, y;
	final int MAX_MAPSIZE = 800;
	
	public MapObject(int xCoord, int yCoord, ImageIcon image)
	{
		//This should create "map objects" to be stored in an array. Each map object
		//should have its own xy coordinates, image and can have functions performed
		//on it!
		
		x = xCoord;
		y = yCoord;
		mapperImage = image;
	}
	
	public Point getPoint()
	{
		return point;
	}
	
	public int X()
	{	
		return x;
	}

	public int Y()
	{
		int y = (int) point.getY();
		return y;
	}

	public ImageIcon getImage()
	{
		return mapperImage;
	}
	
	public JLabel draw(Graphics page)
	{

		int midx = x - (MAX_MAPSIZE/2);
		int midy = y - (MAX_MAPSIZE/2);
		int xCoord = midx*10;
		int yCoord = -midy*10;
		
		int imageHeight = mapperImage.getIconHeight();
		int imageWidth = mapperImage.getIconWidth();
		
		JLabel iconLabel = new JLabel(mapperImage);
		iconLabel.setLocation(x-(imageWidth/2), y-(imageHeight/2));
		iconLabel.setSize(imageWidth, imageHeight);
		
		iconLabel.setToolTipText(xCoord + ", " + yCoord);
		return iconLabel;
	
	}
	
//-------------------------------------------------------------------------------------------------------------
	

}