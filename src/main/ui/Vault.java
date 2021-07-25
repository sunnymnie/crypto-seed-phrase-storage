package ui;

import model.SecurityQuestion;
import model.SeedPhrase;

import javax.management.remote.rmi._RMIConnection_Stub;
import java.util.*;

public class Vault {
    private ArrayList<SeedPhrase> sp;
    private ArrayList<SecurityQuestion> sq;
    private Scanner input;

    //EFFECTS: Create a new vault with no seed-phrases or security questions
    public Vault() {
        boolean keepGoing = true;
        String command = null;

        init();

        this.sp = new ArrayList();
        this.sq = new ArrayList();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes seed-phrases and security questions
    private void init() {
        sp = new ArrayList();
        sq = new ArrayList();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> view seed-phrases");
        System.out.println("\ta -> add a seed-phrase");
        System.out.println("\ts -> add/edit security questions");
        System.out.println("\tq -> quit");
    }

    private void processCommand(String command) {
        if (command.equals("v")) {
            viewSeedPhrases();
        } else if (command.equals("a")) {
            addSeedPhrase();
        } else if (command.equals("s")) {
            editSecurityQuestions();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //EFFECTS: shows id of available seed-phrases
    private void viewSeedPhrases() {
        System.out.println("\nSelect from:");
        if (sp.size() == 0) {
            System.out.println("You don't have any seed-phrases. Add one first");
        } else {
            for (int i = 0; i < sp.size(); i++) {
                System.out.println(Integer.toString(i) + " -> " + sp.get(i).getId());
            }
            System.out.println("Enter -1 to quit\n");
            int index = input.nextInt();
            if (index != -1) {
                if (index >= 0 && index < sp.size()) {
                    solveSeedPhrase(index);
                }
            }
        }
    }

    private void solveSeedPhrase(int i) {
        SeedPhrase seed = sp.get(i);
        boolean allCorrect = true;
        int numQuestions = seed.getSecurity();
        Collections.shuffle(sq);
        if (sq.size() > 0 || numQuestions == 0) { // If security questions is not empty & enabled questions
            if (numQuestions == -1 || numQuestions >= sq.size()) { //If more questions than exists
                allCorrect = runThroughQuestions(sq.size());
            } else { // If have to pick random questions to ask
                allCorrect = runThroughQuestions(numQuestions);
            }
        }
        if (allCorrect) {
            showSeedPhrase(seed);
        } else {
            System.out.println("Unfortunately, one or more of your responses are wrong");
        }
    }

    private void showSeedPhrase(SeedPhrase seed) {
        System.out.println("How do you want the seed phrase shown?");
        System.out.println("\t0 -> show each word individually");
        System.out.println("\t1 -> show all at once (not recommended)\n");
        int choice = input.nextInt();
        if (choice == 0) {
            for (int i = 0; i < seed.length(); i++) {
                System.out.println("Word at position " + Integer.toString(i) + ":\n");
                System.out.println(seed.getWordAt(i));
                System.out.println("To get next word, type anything and press enter");
                input.next();
                clearScreen();
            }
        } else {
            for (int i = 0; i < seed.length(); i++) {
                System.out.println(seed.getWordAt(i));
            }
        }
    }

    //EFFECTS: prints many new lines to make it seem a new screen was created
    private void clearScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    private boolean runThroughQuestions(int numQuestions) {
        boolean allCorrect = true;
        for (int i = 0; i < numQuestions; i++) {
            SecurityQuestion q = sq.get(i);
            System.out.println(q.getQuestion() + "\n");
            if (!q.checkAnswer(input.next())) {
                allCorrect = false;
            }
        }
        return allCorrect;
    }

    private void addSeedPhrase() {
        System.out.print("Enter a one-word id to refer to this seed-phrase (example: bitcoin-wallet)\n");
        String id = input.next();
        ArrayList<String> phrase = new ArrayList();
        boolean addingWords = true;
        System.out.println("Please enter the first word of your seed-phrase");
        while (addingWords) {
            String w = input.next();
            if (!w.equals(":wq")) {
                phrase.add(w);
                System.out.print("Please enter the next word. If you are finished, enter :wq\n");
            } else {
                addingWords = false;
            }
        }
        System.out.print("Enter how many questions must be answered correct to access this seed?\n");
        System.out.print("Enter 0 to not enable any security questions\n");
        System.out.print("Enter -1 to enable all security questions (or enter an absurdly high integer)\n");
        int security = input.nextInt();
        sp.add(new SeedPhrase(phrase, id, security));
    }

    private void editSecurityQuestions() {
        if (sq.size() > 0) {
            System.out.print("To add/remove security questions, you must solve all existing questions first\n");
            boolean allCorrect = runThroughQuestions(sq.size());
            if (allCorrect) {
                System.out.println("Select from:");
                System.out.println("\t0 -> Add security question");
                System.out.println("\t1 -> View and edit security questions\n");
                int choice = input.nextInt();
                if (choice == 0) {
                    addSecurityQuestion();
                } else {
                    showSecurityQuestions();
                }
            } else {
                System.out.println("Unfortuately one or more of your answers are incorrect...");
            }
        } else {
            System.out.println("You have no security questions. Please make one now:");
            addSecurityQuestion();
        }
    }

    private void addSecurityQuestion() {
        System.out.println("Enter question (note: question solution must be one word):");
        input.nextLine();
        String question = input.nextLine();
        System.out.println("Enter one-word solution, can use hyphens. Case does not matter");
        String answer = input.next();
        sq.add(new SecurityQuestion(question, answer));
        System.out.println("Security question added successfully");
    }

    private void showSecurityQuestions() {
        System.out.println("\nSelect from:");
        if (sq.size() == 0) {
            System.out.println("You don't have any security questions. Add one first");
        } else {
            for (int i = 0; i < sq.size(); i++) {
                System.out.println(Integer.toString(i) + " -> " + sq.get(i).getQuestion());
            }
            System.out.println("Enter -1 to quit\n");
            int index = input.nextInt();
            if (index != -1) {
                if (index >= 0 && index < sq.size()) {
                    selectSecurityQuestion(sq.get(index));
                }
            }
        }
    }

    private void selectSecurityQuestion(SecurityQuestion q) {
        System.out.println("Selected question: " + q.getQuestion());
        System.out.println("Select from:");
        System.out.println("\tq -> Edit question");
        System.out.println("\ta -> Edit answer");
        System.out.println("\td -> Delete question");
        System.out.println("Enter anything to exit");
        String choice = input.next().toLowerCase();
        if (choice.equals("q")) {
            System.out.println("Enter new question:\n");
            q.updateQuestion(input.nextLine());
        } else if (choice.equals("a")) {
            System.out.println("Enter new one word answer:\n");
            q.updateAnswer(input.next());
        } else if (choice.equals("d")) {
            sq.remove(q);
        }
    }

}
