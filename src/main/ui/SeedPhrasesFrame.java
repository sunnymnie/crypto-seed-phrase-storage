package ui;

import model.SeedPhrase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
represents a frame to display all seed-phrases as a list
 */

public class SeedPhrasesFrame extends Frame implements ActionListener {

    private JList list;
    private DefaultListModel listModel;

    private JButton view;
    private JButton add;

    private ArrayList<SeedPhrase> sp;

    public SeedPhrasesFrame(String title, ArrayList<SeedPhrase> sp) {
        super(title);

        this.sp = sp;

        listModel = new DefaultListModel();
        addSeedPhrases();

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        initButtons();

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(add);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(view);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);

    }

    //MODIFIES: this
    //EFFECTS: initializes buttons
    private void initButtons() {
        view = new JButton("view or delete");
        view.setActionCommand("view");
        view.addActionListener(this);

        add = new JButton("add");
        add.setActionCommand("add_seed_phrase");
        add.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: adds seed phrases to list
    private void addSeedPhrases() {
        for (SeedPhrase s : sp) {
            listModel.addElement(s.getId());
        }
    }

    //MODIFIES: this, menu
    //EFFECTS: processes command to allow adding or viewing seed-phrases
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        int index = list.getSelectedIndex();

        if ("add_seed_phrase".equals(command)) {
            Frame frame = new AddSeedPhraseFrame("New Seed-Phrase");
            menu.positionFrame(frame, 300, 300);
        } else if ("view".equals(command)) {
            SeedPhrase seed = sp.get(index);

            if ((seed.getSecurity() >= 1 || seed.getSecurity() == -1)) {
                menu.showVerificationWindow(seed);
            } else {
                menu.showSeedPhrase(seed);
            }
        }
        setVisible(false);
        dispose();
    }
}
