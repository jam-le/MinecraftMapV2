package map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Mappers extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JRadioButton userBase, village, sandTemple, jungleTemple, witchHut, abandonedMineshaft, strongHold, netherPortal, remove;
	static ImageIcon currentIcon;
	static boolean removing, clearAll;
	ImageIcon userIcon, villageIcon, sandIcon, jungleIcon, witchIcon, mineIcon, strongholdIcon, netherIcon, mapIcon, removeIcon;
	static JButton clear;
	
	
	public Mappers()
	
	{	setPreferredSize(new Dimension(300,800));
		setBackground(Color.white);
		setMaximumSize(getPreferredSize());
		
		currentIcon = createImgIcon("bedIcon.gif");
		userBase = new JRadioButton("User Bases", true);
		JLabel userLabel = new JLabel(createImgIcon("bedIconSm.gif"));
		userBase.setBackground(Color.white);

		village = new JRadioButton("Villages");
		village.setBackground(Color.white);
		JLabel villageLabel = new JLabel(createImgIcon(("villagersIconSm.gif")));
		
		sandTemple = new JRadioButton("Sand Temples");
		sandTemple.setBackground(Color.white);
		
		jungleTemple = new JRadioButton("Jungle Temples");
		jungleTemple.setBackground(Color.white);
		
		witchHut = new JRadioButton("Witch Huts");
		witchHut.setBackground(Color.white);
		
		abandonedMineshaft = new JRadioButton("Abandoned\n Mineshafts");
		abandonedMineshaft.setBackground(Color.white);
		
		strongHold = new JRadioButton("Strongholds");
		strongHold.setBackground(Color.white);
		
		netherPortal = new JRadioButton("Nether Portals");
		netherPortal.setBackground(Color.white);
			
		clear = new JButton("New Map");
		clear.addActionListener(new ButtonListener());
		clear.setToolTipText("Create a new map.");
		
		ButtonGroup group = new ButtonGroup();
		group.add(userBase);
		group.add(village);
		group.add(sandTemple);
		group.add(jungleTemple);
		group.add(witchHut);
		group.add(abandonedMineshaft);
		group.add(strongHold);
		group.add(netherPortal);
		LegendListener listener = new LegendListener();
		
		userBase.addActionListener(listener);
		village.addActionListener(listener);
		sandTemple.addActionListener(listener);
		jungleTemple.addActionListener(listener);
		witchHut.addActionListener(listener);
		abandonedMineshaft.addActionListener(listener);
		strongHold.addActionListener(listener);
		netherPortal.addActionListener(listener);
		Dimension buffer = new Dimension(0, 10);
		ImageIcon mapIcon = createImgIcon("mapIcon.jpg");
		JLabel mapLabel = new JLabel(mapIcon);

			

		JLabel legendTitle = new JLabel("Map-Maker");
		legendTitle.setFont(new Font("Dialog", Font.BOLD, 28));
	
		JPanel miniPanel = new JPanel();
		miniPanel.setAlignmentY(CENTER_ALIGNMENT);
		miniPanel.setLocation(new Point(0,150));
		miniPanel.setPreferredSize(new Dimension(300,50));
		miniPanel.add(legendTitle);
		miniPanel.add(mapLabel);

		miniPanel.setBackground(Color.white);
			
		JTextArea description = new JTextArea("Select one of the options below and mark its location on the coordinate map to record your important landmarks found in the game.\n\n * * Press F3 during gameplay to ascertain the coordinates of your current location in the game.* * \n\nUse the coordinate locator at the bottom right-hand side of the grid for accuracy. Hovering over the icons that you place on the grid will allow you to see the recorded X and Y coordinates.\n\nClick the \"New Map\" button to open a new map. You can save maps and open them within this program. The \"Undo\" button will delete your most recently recorded landmark.\n");
		description.setFont(new Font("Dialog", Font.PLAIN, 12));
		description.setSize(250,200);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setEditable(false);
		
		JPanel optionsBox = new JPanel();
		optionsBox.setLayout(new BoxLayout(optionsBox, BoxLayout.Y_AXIS));
		optionsBox.setBackground(Color.white);
		optionsBox.setAlignmentY(CENTER_ALIGNMENT);
		
		optionsBox.add(Box.createRigidArea(buffer));
		optionsBox.add(Box.createRigidArea(buffer));
		
		optionsBox.add (miniPanel);
		optionsBox.add (description);
		
		JPanel option1 = new JPanel();
		option1.add (userBase);
		option1.add (userLabel);
		option1.setBackground(Color.white);
		optionsBox.add(option1);
		optionsBox.add(Box.createRigidArea(buffer));
		
		JPanel option2 = new JPanel();
		option2.add (village);
		option2.add (villageLabel);
		option2.setBackground(Color.white);
		optionsBox.add(option2);
		optionsBox.add(Box.createRigidArea(buffer));
		
		JPanel option3 = new JPanel();
		option3.add (sandTemple);
		option3.add (new JLabel(createImgIcon("sandTempleSm.gif")));
		option3.setBackground(Color.white);
		optionsBox.add(option3);
		optionsBox.add(Box.createRigidArea(buffer));
		
		JPanel option4 = new JPanel();
		option4.add (jungleTemple);
		option4.add (new JLabel(createImgIcon(("jungleTempleSm.gif"))));
		option4.setBackground(Color.white);
		optionsBox.add(option4);
		optionsBox.add(Box.createRigidArea(buffer));
		
		JPanel option5 = new JPanel();
		option5.add (witchHut);
		option5.add (new JLabel(createImgIcon(("witchIconSm.gif"))));
		option5.setBackground(Color.white);
		optionsBox.add(option5);
		optionsBox.add(Box.createRigidArea(buffer));
		
		JPanel option6 = new JPanel();
		option6.add (abandonedMineshaft);
		option6.add (new JLabel(createImgIcon(("MinecartSm.gif"))));
		option6.setBackground(Color.white);
		optionsBox.add(option6);
		optionsBox.add(Box.createRigidArea(buffer));
		
		JPanel option7 = new JPanel();
		option7.add (strongHold);
		option7.add (new JLabel(createImgIcon(("strongholdIcon.gif"))));
		option7.setBackground(Color.white);
		optionsBox.add(option7);
		optionsBox.add(Box.createRigidArea(buffer));
		
		JPanel option8 = new JPanel();
		option8.add (netherPortal);
		option8.add (new JLabel(createImgIcon(("NetherportalSm.gif"))));
		option8.setBackground(Color.white);
		optionsBox.add(option8);
		optionsBox.add(Box.createRigidArea(buffer));
		optionsBox.add(Box.createRigidArea(buffer));
		
		clear.setAlignmentX(CENTER_ALIGNMENT);
		optionsBox.add (clear);
		add (optionsBox);
		optionsBox.add(Box.createRigidArea(buffer));
		
	}	
			
	
	
		private class LegendListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
				removing = false;
			
			if (source == userBase)
					currentIcon = createImgIcon(("bedIcon.gif"));
			else
					if (source == village)
					currentIcon = createImgIcon(("villagersIcon.gif"));
				else
					if (source == sandTemple)
						currentIcon = createImgIcon(("sandTemple.gif"));
						else
						if (source == jungleTemple)
							currentIcon = createImgIcon(("jungleTemple.gif"));
						else
							if (source == witchHut)
									currentIcon = createImgIcon(("witchIcon.gif"));
								else
								if (source == abandonedMineshaft)
									currentIcon = createImgIcon(("Minecart.gif"));
								else
									if (source == strongHold)
											currentIcon = createImgIcon(("stronghold.gif"));
									else
										if (source == netherPortal)
											currentIcon = createImgIcon(("Netherportal.gif"));
												
			}
	}
	
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{	
			
			Map.mapList.clear();
			BorderPanel.map.removeAll();
			BorderPanel.map.add(Map.coordLabel);
			
		}

	}
	
	
//-------      URLs       -------------------------------------------------------------------------------------------------
	
	protected static ImageIcon createImgIcon(String path)
		{
		java.net.URL imgURL = Main.class.getResource(path);
		if (imgURL != null)
			return new ImageIcon(imgURL);
		else
		{
				System.err.println("Couldn't find file: " + path);
			return null;
		}
	
	
	}
}	


