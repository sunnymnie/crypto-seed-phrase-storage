package ui;

import model.SecurityQuestion;
import model.SeedPhrase;
import model.Verification;

import java.util.*;

/*
Crypto seed-phrase storage application
 */
public class Vault {
    private ArrayList<SeedPhrase> sp;
    private Verification verification;
    private Scanner input;

    //EFFECTS: Create a new vault with no seed-phrases or security questions
    public Vault() {
        boolean keepGoing = true;
        String command = null;

        init();

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
        verification = new Verification();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> view/edit seed-phrases");
        System.out.println("\ta -> add a seed-phrase");
        System.out.println("\ts -> add/edit security questions");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
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

    //REQUIRES: i must be a valid index in seed phrases
    //MODIFIES: this
    //EFFECTS: shows seed phrases and allows editing seed phrase if enough questions are solved correctly
    private void solveSeedPhrase(int i) {
        SeedPhrase seed = sp.get(i);
        boolean allCorrect = true;
        int numQuestions = seed.getSecurity();
        if (verification.length() > 0 || numQuestions == 0) { // If security questions is not empty & enabled questions
            if (numQuestions == -1 || numQuestions >= verification.length()) { //If more questions than exists
                allCorrect = runThroughQuestions(verification.length());
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

    //MODIFIES: this
    //EFFECTS: gives option to show seed phrases and for editing seed phrase
    private void showSeedPhrase(SeedPhrase seed) {
        System.out.println("How do you want the seed phrase shown?");
        System.out.println("\t0 -> show each word individually");
        System.out.println("\t1 -> show all at once (not recommended)");
        System.out.println("Else, you can change the security, rename, or delete the seed-phrase");
        System.out.println("\t2 -> change security of seed phrase");
        System.out.println("\t3 -> change name of seed phrase");
        System.out.println("\t4 -> delete seed-phrase\n");
        int choice = input.nextInt();
        if (choice == 0) {
            showEachSeedPhraseWordIndividually(seed);
        } else if (choice == 1) {
            showSeedPhraseEntirely(seed);
        } else if (choice == 2) {
            changeSecurityOfSeedPhrase(seed);
        } else if (choice == 3) {
            changeNameOfSeedPhrase(seed);
        } else if (choice == 4) {
            deleteSeedPhrase(seed);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //EFFECTS: shows each seed-phrase word individually
    private void showEachSeedPhraseWordIndividually(SeedPhrase seed) {
        for (int i = 0; i < seed.length(); i++) {
            System.out.println("Word at position " + Integer.toString(i) + ":\n");
            System.out.println(seed.getWordAt(i));
            System.out.println("To get next word, type anything and press enter");
            input.next();
            clearScreen();
        }
    }

    //EFFECTS: shows each seed-phrase entirely at once
    private void showSeedPhraseEntirely(SeedPhrase seed) {
        for (int i = 0; i < seed.length(); i++) {
            System.out.println(seed.getWordAt(i));
        }
    }

    //MODIFIES: this
    //EFFECTS: changes security of seed phrase to user input
    private void changeSecurityOfSeedPhrase(SeedPhrase seed) {
        System.out.println("Enter how many questions must be solved correctly to access this seed-phrase");
        System.out.println("Enter 0 to disable security questions for this seed phrase, -1 to enable all");
        int security = input.nextInt();
        seed.changeSecurity(security);
        System.out.println("Security change successful");
    }

    //MODIFIES: this
    //EFFECTS: changes name of seed phrase to user input
    private void changeNameOfSeedPhrase(SeedPhrase seed) {
        System.out.println("Enter a new one word name for seed-phrase. Hyphens are allowed");
        String name = input.next();
        seed.changeId(name);
        System.out.println("Name change successful");
    }

    //MODIFIES: this
    //EFFECTS: deletes seed phrase after user verifies
    private void deleteSeedPhrase(SeedPhrase seed) {
        System.out.println("Are you sure you want to delete seed-phrase. Enter 'YES' to delete");
        String yes = input.next();
        if (yes.equals("YES")) {
            sp.remove(seed);
            System.out.println("Seed-phrase successfully deleted");
        } else {
            System.out.println("Seed-phrase not deleted");
        }
    }



    //EFFECTS: prints many new lines to make it seem a new screen was created
    private void clearScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    //REQUIRES: numQuestions >= 0
    //EFFECTS: returns true if user solved numQuestion number of questions and no mistakes were made
    //          else false
    private boolean runThroughQuestions(int numQuestions) {
        verification.shuffle();
        boolean allCorrect = true;
        for (int i = 0; i < numQuestions; i++) {
            SecurityQuestion q = verification.get(i);
            System.out.println(q.getQuestion() + "\n");
            if (!q.checkAnswer(input.next())) {
                allCorrect = false;
            }
        }
        return allCorrect;
    }

    //MODIFIES: this
    //EFFECTS: adds a new seed phrase from user input
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

    //MODIFIES: this
    //EFFECTS: allows user to add and edit security questions after solving security questions
    private void editSecurityQuestions() {
        if (verification.length() > 0) {
            System.out.print("To add/remove security questions, you must solve all existing questions first\n");
            boolean allCorrect = runThroughQuestions(verification.length());
            if (allCorrect) {
                System.out.println("Select from:");
                System.out.println("\t0 -> Add security question");
                System.out.println("\t1 -> View and edit security questions\n");
                int choice = input.nextInt();
                if (choice == 0) {
                    addSecurityQuestion();
                } else if (choice == 1) {
                    showSecurityQuestions();
                } else {
                    System.out.println("Selection not valid...");
                }
            } else {
                System.out.println("Unfortuately one or more of your answers are incorrect...");
            }
        } else {
            System.out.println("You have no security questions. Please make one now:");
            addSecurityQuestion();
        }
    }

    //MODIFIES: this
    //EFFECTS: adds a security question from user input
    private void addSecurityQuestion() {
        System.out.println("Enter question (note: question solution must be one word):");
        input.nextLine();
        String question = input.nextLine();
        System.out.println("Enter one-word solution, can use hyphens. Case does not matter");
        String answer = input.next();
        verification.addSecurityQuestion(question, answer);
        System.out.println("Security question added successfully");
    }

    //MODIFIES: this
    //EFFECTS: shows list of available security questions for user to choose to edit
    private void showSecurityQuestions() {
        System.out.println("\nSelect from:");
        for (int i = 0; i < verification.length(); i++) {
            System.out.println(Integer.toString(i) + " -> " + verification.get(i).getQuestion());
        }
        System.out.println("Enter -1 to quit\n");
        int index = input.nextInt();
        if (index != -1) {
            if (index >= 0 && index < verification.length()) {
                selectSecurityQuestion(verification.get(index));
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: gives user choices to edit selected security question
    private void selectSecurityQuestion(SecurityQuestion q) {
        System.out.println("Selected question: " + q.getQuestion());
        System.out.println("Select from:");
        System.out.println("\tq -> Edit question");
        System.out.println("\ta -> Edit answer");
        System.out.println("\td -> Delete question");
        System.out.println("Enter any other character to exit");
        String choice = input.next().toLowerCase();
        if (choice.equals("q")) {
            System.out.println("Enter new question:\n");
            q.updateQuestion(input.nextLine());
        } else if (choice.equals("a")) {
            System.out.println("Enter new one word answer:\n");
            q.updateAnswer(input.next());
        } else if (choice.equals("d")) {
            verification.remove(q);
        } else {
            System.out.println("Selection not valid...");
        }
    }

}
