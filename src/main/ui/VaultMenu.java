package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
CITATION: Main structure of VaultMenu is based on FrameDemo2 from Oracle Swing demo files
 */

public class VaultMenu extends WindowAdapter
        implements ActionListener {

    private Point lastLocation = null;
    private int maxX = 500;
    private int maxY = 500;

    //the main frame's default button
    private static JButton defaultButton = null;

    //Perform some initialization.
    public VaultMenu() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        maxX = screenSize.width - 50;
        maxY = screenSize.height - 50;
    }



    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {

        //Instantiate the controlling class.
        Frame frame = new MenuFrame("Crypto Seed-Phrase Vault");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        VaultMenu demo = new VaultMenu();

        //Add components to it.
//        Container contentPane = frame.getContentPane();
//        contentPane.add(demo.createOptionControls(),
//                BorderLayout.CENTER);
//        contentPane.add(demo.createButtonPane(),
//                BorderLayout.PAGE_END);
//        frame.getRootPane().setDefaultButton(defaultButton);

        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null); //center it
        frame.setVisible(true);
    }



    //Create a new MyFrame object and show it.
    public void showNewWindow() {
        JFrame frame = new VerificationFrame("New title for window");

        //Set window location.
        if (lastLocation != null) {
            //Move the window over and down 40 pixels.
            lastLocation.translate(40, 40);
            if ((lastLocation.x > maxX) || (lastLocation.y > maxY)) {
                lastLocation.setLocation(0, 0);
            }
            frame.setLocation(lastLocation);
        } else {
            lastLocation = frame.getLocation();
        }

        //Show window.
        frame.setSize(new Dimension(170, 100));
        frame.setVisible(true);
    }

    @Override
    //Handle action events from all the buttons.
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        //Handle the New window button.
        if ("cw".equals(command)) {
            showNewWindow();

            //Handle the first group of radio buttons.
        } else if ("asdf".equals(command)) {
            JFrame.setDefaultLookAndFeelDecorated(false);
        }
    }

}
