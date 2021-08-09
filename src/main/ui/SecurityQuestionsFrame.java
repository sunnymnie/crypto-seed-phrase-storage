package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
represents a frame for displaying all security questions
 */

public class SecurityQuestionsFrame extends Frame implements ActionListener {
    private JList list;
    private DefaultListModel listModel;

    private JButton edit;
    private JButton add;


    public SecurityQuestionsFrame(String title) {
        super(title);

        initListModel();
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
    //EFFECTS: adds security questions to list
    private void addSecurityQuestions() {
        for (int i = 0; i < menu.verification.length(); i++) {
            listModel.addElement(menu.verification.get(i).getQuestion());
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes list model and adds questions
    private void initListModel() {
        listModel = new DefaultListModel();
        addSecurityQuestions();

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
    }

    //MODIFIES: this
    //EFFECTS: updates list with up-to-date security questions
    public void updateList() {
        menu.verification = menu.getLatestVerification();
        listModel.clear();
        addSecurityQuestions();
    }

    //MODIFIES: this, menu
    //EFFECTS: processes command
    public void actionPerformed(ActionEvent e) {
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
