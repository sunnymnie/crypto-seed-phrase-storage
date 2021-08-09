package ui;

import model.Verification;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecurityQuestionsFrame extends Frame implements ListSelectionListener, ActionListener {
    private JList list;
    private DefaultListModel listModel;

    private JButton edit;
    private JButton add;


    public SecurityQuestionsFrame(String title) {
        super(title);


        listModel = new DefaultListModel();
        addSecurityQuestions();

        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        edit = new JButton("Select");
        edit.setActionCommand("select");
        edit.addActionListener(this);

        add = new JButton("add");
        add.setActionCommand("add");
        add.addActionListener(this);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(add);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(edit);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    //MODIFIES: this
    //EFFECTS: adds seed phrases to list
    private void addSecurityQuestions() {
        for (int i = 0; i < menu.verification.length(); i++) {
            listModel.addElement(menu.verification.get(i).getQuestion());
        }
    }

    public void updateList() {
        menu.verification = menu.getLatestVerification();
        listModel.clear();
        addSecurityQuestions();
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        //asdf
    }

    public void actionPerformed(ActionEvent e) {
        //This method can be called only if
        //there's a valid selection
        //so go ahead and remove whatever's selected.
        int index = list.getSelectedIndex();
        String command = e.getActionCommand();
        if ("select".equals(command)) {
            Frame frame = new AddSecurityQuestionFrame("Edit security question", menu.verification.get(index), this);
            menu.positionFrame(frame, 300, 300);
        } else if ("add".equals(command)) {
            Frame frame = new AddSecurityQuestionFrame("Add security question",this);
            menu.positionFrame(frame, 300, 300);

        }


    }


}
