// Assignment #: 12
//         Name: David Warren II
//    StudentID: 1205006331
//      Lecture: T Th, 3:00 - 4:15 PM
//  Description: The BeamsControlPane class creates 3 buttons and 2 sliders to
//               to control the movement of beams, and also contains a panel
//               displaying beams.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class BeamsControlPanel extends JPanel
 {
      //components of the panel
      private BeamsPanel beamsPanel;
      private JButton start, stop, colorButton;
      private JSlider speed, beam;
      private JLabel label1, label2;
      private JColorChooser chooser;
      private JPanel buttons1;

      private int width, height;
      private Color color;

      //Constructor to create all components, add their listener to them,
      //and arrange them using a layout.
      public BeamsControlPanel(int width, int height, Color initialColor)
       {
           //create a color chooser with the specified initial color
           chooser = new JColorChooser(initialColor);
           color = initialColor;
           this.width = width;
           this.height = height;

           //create a panel displaying beams, with the specified color
           beamsPanel = new BeamsPanel(initialColor, width);

           //create 3 buttons, start, stop, and color chooser for beams.
           start = new JButton("Start");
           stop = new JButton("Stop");
           colorButton = new JButton("Color");


           //add a listener to each button
           start.addActionListener(new ButtonListener());
           stop.addActionListener(new ButtonListener());
           colorButton.addActionListener(new ButtonListener());

           buttons1 = new JPanel();
           buttons1.setLayout(new GridLayout(3,1));

           buttons1.add(start);
           buttons1.add(stop);
           buttons1.add(colorButton);


           //create a slider for the delay with major tick spacing 10,
           //minor tick spacing 1. The minimum value is 0, the maximum
           //is 50, and the initial set value is 20.
           speed = new JSlider(JSlider.VERTICAL, 0, 50, 20);
           speed.setMajorTickSpacing(10);
           speed.setMinorTickSpacing(1);
           speed.setPaintTicks(true);
           speed.setPaintLabels(true);
           speed.setAlignmentX(Component.LEFT_ALIGNMENT);
           speed.addChangeListener(new SpeedListener());

           //create a label for the delay
           label1 = new JLabel("Delay");

           JPanel panel3 = new JPanel();
           panel3.setLayout(new BorderLayout());
           panel3.add(label1, BorderLayout.NORTH);
           panel3.add(speed, BorderLayout.CENTER);


           //create a slider for the number of beams with major tick spacing 4,
           //minor tick spacing 1. The minimum value is 4, the maximum
           //is 36, and the initial set value is 8.
           beam = new JSlider(JSlider.VERTICAL, 4, 36, 8);
           beam.setMajorTickSpacing(4);
           beam.setMinorTickSpacing(1);
           beam.setPaintTicks(true);
           beam.setPaintLabels(true);
           beam.setAlignmentX(Component.LEFT_ALIGNMENT);
           beam.addChangeListener(new BeamListener());

           //create a label for the number of beams
           label2 = new JLabel("Beam Num");

           JPanel panel4 = new JPanel();
           panel4.setLayout(new BorderLayout());
           panel4.add(label2, BorderLayout.NORTH);
           panel4.add(beam, BorderLayout.CENTER);


           JPanel panel6 = new JPanel();
           panel6.setLayout(new GridLayout(1,2));
           panel6.add(panel3);
           panel6.add(panel4);

           JPanel panel5 = new JPanel();
           panel5.setLayout(new BorderLayout());
           panel5.add(buttons1, BorderLayout.CENTER);
           panel5.add(panel6, BorderLayout.EAST);


           setLayout(new BorderLayout());
           panel5.setPreferredSize(new Dimension(width, height/3));
           add(beamsPanel, BorderLayout.CENTER);
           add(panel5, BorderLayout.NORTH);

}

  //ButtonListener defines actions to be performed when each button
  //is pushed.
  private class ButtonListener implements ActionListener
   {
       public void actionPerformed(ActionEvent event)
        {
            Object action = event.getSource();

		if(action == stop) {
			beamsPanel.suspend();
	
		}
		if(action == start) {
			beamsPanel.resume();
		}
            if (action == colorButton)
             {
                 color = chooser.showDialog(null, "Change beam color", color);
                 beamsPanel.changeColor(color);
             }


         }
     } //end of ButtonListener

   //SpeedListener adjust the delay of beams based on
   //the chosen integer in the corresponding slider.
   private class SpeedListener implements ChangeListener
    {
        public void stateChanged(ChangeEvent event)
         {
            	beamsPanel.setDelay(speed.getValue()); // sets the delay for our drawing
         }

     } //end of SpeedListener


     //BeamListener adjust the delay of beams based on
     //the chosen integer in the corresponding slider.
     private class BeamListener implements ChangeListener
      {
          public void stateChanged(ChangeEvent event)
           {
              	beamsPanel.setBeamNumber(beam.getValue()); // sets the amount of beams we are drawing
           }
     } //end of BeamListener
 }
