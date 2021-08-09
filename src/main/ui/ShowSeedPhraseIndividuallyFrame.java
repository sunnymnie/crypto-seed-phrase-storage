package ui;

import model.SeedPhrase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/*
represents frame that shows seed-phrase word by word
 */

public class ShowSeedPhraseIndividuallyFrame extends Frame {

    private SeedPhrase sp;
    int index;
    private JLabel word;
    private JButton next;

    public ShowSeedPhraseIndividuallyFrame(String title, SeedPhrase sp) {
        super(title);
        this.sp = sp;
        index = 0;

        next = new JButton("next");
        next.setActionCommand("next");
        next.addActionListener(this);

        JPanel box = new JPanel();
        word = new JLabel(sp.getWordAt(index));
        box.add(word);
        box.add(next);
        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(box, BorderLayout.CENTER);

    }

    //EFFECTS: shows each word individually
    @Override
    public void actionPerformed(ActionEvent e) {
        index++;
        if (index < sp.length()) {
            word.setText(sp.getWordAt(index));
            if (index + 1 == sp.length()) {
                next.setText("Finish");
            }
        } else {
            setVisible(false);
            dispose();
        }

    }


}
