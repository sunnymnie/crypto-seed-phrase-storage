package ui;

import model.SeedPhrase;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SeedPhrasesFrame extends Frame implements ListSelectionListener, ActionListener {

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

        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        view = new JButton("view or delete");
        view.setActionCommand("view");
        view.addActionListener(this);

        add = new JButton("add");
        add.setActionCommand("add_seed_phrase");
        add.addActionListener(this);

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
    //EFFECTS: adds seed phrases to list
    private void addSeedPhrases() {
        for (SeedPhrase s : sp) {
            listModel.addElement(s.getId());
        }
    }

    public void updateList() {
        menu.sp = menu.getLatestSeedPhrase();
        listModel.clear();
        addSeedPhrases();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

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
//        listModel.remove(index);

//        int size = listModel.getSize();
//
//        if (size == 0) { //Nobody's left, disable firing.
//            fireButton.setEnabled(false);

//        } else { //Select an index.
//            if (index == listModel.getSize()) {
//                //removed item in last position
//                index--;
//            }
//
//            list.setSelectedIndex(index);
//            list.ensureIndexIsVisible(index);

//        System.out.println(sp.get(index).getId());

        setVisible(false);
        dispose();

    }
}
