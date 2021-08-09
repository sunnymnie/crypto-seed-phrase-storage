package ui;

import model.SeedPhrase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SeedPhraseSelectFrame extends Frame {

    SeedPhrase sp;

    public SeedPhraseSelectFrame(String title, SeedPhrase sp) {
        super(title);
        this.sp = sp;
        JButton viewSeparate = new JButton("View seed-phrase individually");
        JButton viewTogether = new JButton("View seed-phrase at once (not recommended)");
        JButton delete = new JButton("Delete seed-phrase");

        viewSeparate.setActionCommand("view_separate");
        viewTogether.setActionCommand("view_once");
        delete.setActionCommand("delete");

        viewSeparate.addActionListener(this);
        viewTogether.addActionListener(this);
        delete.addActionListener(this);

        Box box = Box.createVerticalBox();

        box.add(viewSeparate);
        box.add(viewTogether);
        box.add(delete);
        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(box, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("view_separate".equals(command)) {
            Frame frame = new ShowSeedPhraseIndividuallyFrame(sp.getId(), sp);
            menu.positionFrame(frame, 200, 100);


        } else if ("view_once".equals(command)) {
            JFrame frame = new JFrame();
            String phrase = "";
            for (int i = 0; i < sp.length(); i++) {
                phrase = phrase +  sp.getWordAt(i) + " ";
            }
            JLabel seedPhrase = new JLabel(phrase);
            frame.add(seedPhrase, BorderLayout.CENTER);
            menu.positionFrame(frame, 400, 100);
        } else if ("delete".equals(command)) {
            menu.sp.remove(sp);
            menu.saveAndUpdate();
            setVisible(false);
            dispose();
        }
    }
}
