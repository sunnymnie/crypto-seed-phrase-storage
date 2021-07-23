package model;

import java.util.List;
import java.util.ArrayList;

public class Vault {
    private ArrayList<SeedPhrase> sp;
    private ArrayList<SecurityQuestion> sq;

    //EFFECTS: Create a new vault with no seed-phrases or security questions
    public Vault() {
        this.sp = new ArrayList();
        this.sq = new ArrayList();
    }
}
