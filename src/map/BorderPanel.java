package map;


import java.awt.*;
import javax.swing.*;

public class BorderPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	static Map map;
	
	public BorderPanel()
		{	
			setLayout(new BorderLayout());
			map = new Map();
			
			add(new MenuPanel(), BorderLayout.NORTH);
			add(map, BorderLayout.WEST);
			add(new Mappers(), BorderLayout.CENTER);
		}	
	

}
