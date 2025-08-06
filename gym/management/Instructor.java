package gym.management;

import gym.customers.*;
import gym.management.Sessions.*;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person {

    private int salary;
    private List<SessionType> qualifiedSessions = new ArrayList<>();
    private final List<Session> sessionsToDo = new ArrayList<>();
    Person person;

    public Instructor(Person person) {
        super(person);
        this.person = person;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public List<SessionType> getQualifiedSessions() {
        return qualifiedSessions;
    }

    public void setQualifiedSessions(ArrayList<SessionType> qualifiedSessions) {
        this.qualifiedSessions = qualifiedSessions;
    }
    @Override
    public int getWallet() {
        return person.getWallet();
    }

    @Override
    public void updateWallet(int wallet) {
        person.updateWallet(wallet);
    }

    public List<Session> getSessionsToDo() {
        return sessionsToDo;
    }

    public void addSessionsToDo(Session session) {
        sessionsToDo.add(session);
    }
    //for add new qualifiedSession
//    public void addSessions(SessionType session){
//        qualifiedSessions.add(session);
//    }
}
