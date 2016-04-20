// Assignment #: 12
//         Name: David Warren II
//    StudentID: 1205006331
//      Lecture: T Th, 3:00 - 4:15 PM
//  Description: The BeamsPanel class contains all the methods and variables in drawing the beams.



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class BeamsPanel extends JPanel
{
	private Color color; // color of the beams
	private Timer timer; // an object of timer 
	private int delay; // delay of timer
	private int step; // step that beams move 
	private int centerX; // x coordinate of the beams
	private int centerY; // y coordinate of the beams
	private int diameterLimit; // longest diameter of beams
	private int diameter; // diameters of the beams
	private int beamNum; // amount of beams
	private int angleSize; // angle size of each beam

	// constructor for the class
	public BeamsPanel(Color color, int width)
	{
		this.color = color;
		delay = 20;
		step = 3;
		setBackground(Color.BLACK);
		centerX = width/2;
		centerY = width/2;
		diameterLimit = (width - 10)/2;
		diameter = 0;
		beamNum = 8;
		angleSize = 360/(beamNum * 2);
		timer = new Timer(delay, new MoveListener());
		timer.start();
	}
	// method that resumes the timer
	public void resume()
	{
		timer.start();
		repaint();
	}
	// method that stops the timer
	public void suspend()
	{
		timer.stop();
		repaint();
	}
	// changes the color of the beams
	public void changeColor(Color anotherColor)
	{
		color = anotherColor;
	}
	// sets the number of beams
	public void setBeamNumber(int beam)
	{
		beamNum = beam;
	}
	// set delay of the beams
	public void setDelay(int delayValue)
	{
		timer.setDelay(delayValue);
	}
	// the paintComponent method of the class that draws and redraws
	public void paintComponent(Graphics page)
	{
		int currentAngle = 0;

		super.paintComponent(page);
		page.setColor(color); // sets the color we want

		angleSize = 360/(beamNum * 2);
		for(int x = 0; x < beamNum; x++) {
			page.fillArc(centerX - diameter / 2, centerY - diameter / 2, diameter, diameter, currentAngle, angleSize); // draws the arc
			currentAngle = currentAngle + 2 * angleSize; // changes the angle at which the arc is drawn
		}
	}
	// moveListener class that controls how the beams move
	private class MoveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			diameter = diameter + step; // makes the diameter larger
			if(diameter > diameterLimit) // makes sure we don't go over the diameter limit
				diameter = 0;	
			repaint();
		}
	}
}
