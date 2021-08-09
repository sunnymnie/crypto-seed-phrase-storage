package ui;

/*
runs the application
 */

public class Main {
    public static void main(String[] args) {
//        new Vault();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VaultMenu().createAndShowGUI();
            }
        });
    }
}
