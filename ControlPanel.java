// Assignment #: 12
//         Name: David Warren II
//    StudentID: 1205006331
//      Lecture: T Th, 3:00 - 4:15 PM
//  Description: The ControlPanel class creates 2 beams panels and
//               control panels that control their movement.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ControlPanel extends JPanel
 {
  private int width, height;
  private int panelNum;

  //The constructor creates creates 2 beams panels and
  //control panels that control their movement, and organize them using a layout
  public ControlPanel(int width, int height)
   {
       this.width = width;
       this.height = height;
       panelNum = 2; //the number of beams panels is 2

       //create 2 panels to control the movement of beams
       BeamsControlPanel[] beamsPanels;
       beamsPanels = new BeamsControlPanel[panelNum];
       beamsPanels[0] = new BeamsControlPanel(width/panelNum, height, Color.RED);
       beamsPanels[1] = new BeamsControlPanel(width/panelNum, height, Color.BLUE);

       //add two beams panels into this control panel using GridLayout
       setLayout(new GridLayout(1, panelNum));
       for (int i = 0; i < panelNum; i++)
            add(beamsPanels[i]);

       //set preferred size of this panel
       setPreferredSize(new Dimension(width,height));
    }
}
