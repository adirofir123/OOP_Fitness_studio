package gym.customers;

import gym.management.DateActions;

public class Person {

    private final String name;
    private int wallet;
    private final Gender gender;
    private final String DOB;
    private final int ID;
    private static int nextID = 1111;
    private final DateActions dateActions = DateActions.getInstance();

    public Person(String name, int wallet, Gender gender, String dob) {
        this.name = name;
        this.wallet = wallet;
        this.gender = gender;
        this.DOB = dob;
        this.ID = nextID;
        nextID++;
    }

    public Person(Person person) {
        this.name = person.getName();
        this.wallet = person.wallet;
        this.gender = person.getGender();
        this.DOB = person.getDOB();
        this.ID = person.getID();
    }

    public int getAge(){
        return dateActions.calculateAge(DOB);
    }

    public String getName() {
        return name;
    }

    public int getWallet() {
        return wallet;
    }

    public void updateWallet(int wallet) {
        this.wallet = wallet;
    }

    public Gender getGender() {
        return gender;
    }

    public String getDOB() {
        return DOB;
    }

    public int getID() {
        return ID;
    }
}
