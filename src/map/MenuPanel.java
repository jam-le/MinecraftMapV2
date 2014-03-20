package map;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class MenuPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton open, save, undo;
	static boolean delete = false;
	final JFileChooser choose = new JFileChooser();
	JLabel enter, x, y;
	private JTextField enterX, enterY;
	static JLabel outtaBounds;


	public MenuPanel() {
		
		JPanel optionsBox = new JPanel();
		optionsBox.setBackground(new Color(255, 250, 205));
		
		open = new JButton("Open File");
		open.setSize(90, 27);
		open.setLocation(10, 17);
	
		open.addActionListener(new DialogListener());
		optionsBox.add(open);
	
		
		save = new JButton("Save File");
		save.setLocation(130, 17);
		save.setSize(90, 27);
		save.addActionListener(new DialogListener());
		optionsBox.add(save);
		
		add(Box.createRigidArea(new Dimension(10,0)));
		
		undo = new JButton("Undo");
		undo.setSize(90, 27);
		undo.addActionListener(new DialogListener());
		optionsBox.add(undo);
		
		add(optionsBox);
		add(Box.createRigidArea(new Dimension(50,0)));
		
		JPanel coordBox = new JPanel();
		coordBox.setBackground(new Color(255, 250, 205));
		
		enter = new JLabel("Enter your location: ");
	
		enter.setFont(new Font("Dialog", Font.BOLD, 20));
		coordBox.add(enter);
		coordBox.add(Box.createRigidArea(new Dimension(10,0)));
		
		x = new JLabel("X: ");
		x.setFont(new Font("Dialog", Font.PLAIN, 15));
		coordBox.add(x);
		
		coordBox.add(Box.createRigidArea(new Dimension(10,0)));
		//add(Box.createVerticalGlue());
		
		enterX = new JTextField(5);
		enterX.addActionListener(new coordListener());
		coordBox.add(enterX);
		coordBox.add(Box.createRigidArea(new Dimension(10,0)));
		coordBox.add(Box.createVerticalGlue());
		
		y = new JLabel("Y: ");
		y.setFont(new Font("Dialog", Font.PLAIN, 15));
		coordBox.add(y);
		
		
		enterY = new JTextField(5);
		enterY.addActionListener(new coordListener());
		coordBox.add(enterY);
		
		outtaBounds = new JLabel("Enter X within [-4200 to 4200] and Y within [-3800 to +3800]. ");
		

		outtaBounds.setFont(new Font("Dialog", Font.PLAIN, 12));
		outtaBounds.setForeground(Color.blue);		
		JPanel coordBoxWhole = new JPanel();
		coordBoxWhole.setBackground(new Color(255, 250, 205));
		coordBoxWhole.setLayout(new BorderLayout());
		coordBoxWhole.add(coordBox, BorderLayout.NORTH);
		coordBoxWhole.add(outtaBounds, BorderLayout.EAST);
		

		add (coordBoxWhole);
		
		
		add(Box.createRigidArea(new Dimension(60,0)));
				
		ImageIcon minecraftLogo = Mappers.createImgIcon("Minecraft.gif");
		JLabel minecraft = new JLabel(minecraftLogo);
		minecraft.setLocation(835, 5);
		minecraft.setSize(305, 50);
		add(minecraft);

		
		
		setPreferredSize(new Dimension(850, 60));
		setBackground(new Color(255, 250, 205));
		
	}


//---------------------------------------------------------------------------------------------------------	

	private class DialogListener implements ActionListener {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == open) {
				int val = choose.showOpenDialog(Main.frame);
				if (val == JFileChooser.APPROVE_OPTION) {
					File file = choose.getSelectedFile();
					
					ArrayList<MapObject> mList;

					try
					{
						FileInputStream fileIn = new FileInputStream(file);
						ObjectInputStream in = new ObjectInputStream(fileIn);
						
						mList = (ArrayList<MapObject>) in.readObject();
						in.close();
						fileIn.close();
						Main.main(null);
					
						
						Map.mapList = mList;
					}
					
					catch (IOException i)
					{
						i.printStackTrace();
					}
					catch (ClassNotFoundException e)
					{
						e.printStackTrace();
					}
				}

			}

			if (event.getSource() == save) {
				int val = choose.showSaveDialog(Main.frame);
				if (val == JFileChooser.APPROVE_OPTION) {
					File file = choose.getSelectedFile();

					try
					{
						System.out.println(file);
						FileOutputStream fileOut = new FileOutputStream(file);
						ObjectOutputStream out = new ObjectOutputStream(fileOut);
						out.writeObject(Map.mapList);
						out.close();
						fileOut.close();
						System.out.println("Serialized data is saved in" + file);
					
					}
					
					catch (IOException i) 
					{
						i.printStackTrace();
					}
				}
			}

			if (event.getSource() == undo)
			{	
				ArrayList<MapObject> mList;
				
				int last = (Map.mapList.size()-1);
				Map.mapList.remove(last);
				mList = Map.mapList;
				Map.mapList = mList;
				BorderPanel.map.removeAll();
				BorderPanel.map.add(Map.coordLabel);
				delete = true;
				
			}
		}
	}
	
	private class coordListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			int x, y;
			String xString = enterX.getText();
			String yString = enterY.getText();
			
			try
			{
				
			
			if ((Integer.parseInt(xString, 10) > 4200) || (Integer.parseInt(yString, 10) > 3800))
			{
				MenuPanel.outtaBounds.setForeground(Color.red);
				MenuPanel.outtaBounds.setText("Sorry, out of bounds.      X range: [+ / - 4200]       Y range: [+ / - 3800] ");
			}
			
			if ((Integer.parseInt(xString, 10) < -4200) || (Integer.parseInt(yString, 10) < -3800))
			{
				MenuPanel.outtaBounds.setForeground(Color.red);
				MenuPanel.outtaBounds.setText("Sorry, out of bounds.       X range: [+ / - 4200]       Y range: [+ / - 3800] ");
			}
			
			if ((Integer.parseInt(xString, 10) >= -4200) && (Integer.parseInt(xString, 10) <= 4200))
				if ((Integer.parseInt(yString, 10) <= 3800) && (Integer.parseInt(yString, 10) >= -3800))
				{
				x = Integer.parseInt(xString, 10);
				int xCoord = (x/10) + (Map.max/2);
				
				y = Integer.parseInt(yString, 10);
				int yCoord = (-y/10) + (Map.max/2);
				
				MenuPanel.outtaBounds.setForeground(new Color(0, 148, 0));
				MenuPanel.outtaBounds.setText("You entered: (" + x + ", " + y + ").       " + " X range: [+ / - 4200]       Y range: [+ / - 3800] ");
				MapObject enterMapObj = new MapObject(xCoord, yCoord, Mappers.currentIcon);
				Map.mapList.add(enterMapObj);
				}
			}
			
			catch (NumberFormatException exception)
			{
				MenuPanel.outtaBounds.setForeground(new Color(122, 0, 148));
				MenuPanel.outtaBounds.setText("Are you sure you entered an Integer? ");
			}
			
			finally
			{
			enterX.setText("");
			enterY.setText("");
			}
		}
		
	}
}
