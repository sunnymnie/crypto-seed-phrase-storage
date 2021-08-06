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

    // Create the window-creation controls that go in the main window.
//    private JComponent createOptionControls() {
//        JLabel label1 = new JLabel("Decoration options for subsequently created frames:");
//        ButtonGroup bg1 = new ButtonGroup();
//        JLabel label2 = new JLabel("Icon options:");
//        ButtonGroup bg2 = new ButtonGroup();
//
//        //Create the buttons
//        JRadioButton rb1 = new JRadioButton();
//        rb1.setText("Look and feel decorated");
//        rb1.setActionCommand("test");
//        rb1.addActionListener(this);
//        rb1.setSelected(true);
//        bg1.add(rb1);
//        //
//        JRadioButton rb2 = new JRadioButton();
//        rb2.setText("Window system decorated");
//        rb2.setActionCommand("test");
//        rb2.addActionListener(this);
//        bg1.add(rb2);
//
//
//        //Add everything to a container.
//        Box box = Box.createVerticalBox();
//        box.add(label1);
//        box.add(Box.createVerticalStrut(5)); //spacer
//        box.add(rb1);
//        box.add(rb2);
//
//        //Add some breathing room.
//        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        return box;
//    }

    //Create the button that goes in the main window.
//    protected JComponent createButtonPane() {
//        JButton button = new JButton("View and edit seed-phrases");
//        button.setActionCommand("cw");
//        button.addActionListener(this);
////        defaultButton = button; //Used later to make this the frame's default button.
//
//        JButton buttonSQ = new JButton("Edit security questions");
//        buttonSQ.setActionCommand("cw");
//        buttonSQ.addActionListener(this);
////        defaultButton = button; //Used later to make this the frame's default button.
//
//        //Center the button in a panel with some space around it.
//        JPanel pane = new JPanel(); //use default FlowLayout
//        pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//        pane.add(button);
//        pane.add(buttonSQ);
//
//        return pane;
//    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {

        //Instantiate the controlling class.
        Frame frame = new MenuFrame("DEMO PROJECT");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        VaultMenu demo = new VaultMenu();

        //Add components to it.
        Container contentPane = frame.getContentPane();
//        contentPane.add(demo.createOptionControls(),
//                BorderLayout.CENTER);
//        contentPane.add(demo.createButtonPane(),
//                BorderLayout.PAGE_END);
        frame.getRootPane().setDefaultButton(defaultButton);

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

        //Calling setIconImage sets the icon displayed when the window
        //is minimized.  Most window systems (or look and feels, if
        //decorations are provided by the look and feel) also use this
        //icon in the window decorations.

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
