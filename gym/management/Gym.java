package gym.management;

import gym.customers.*;
import gym.management.Sessions.Session;
import java.util.*;

public class Gym {

    private static final Gym gym = new Gym();
    private String name;
    private Secretary secretary;
    private final List<Client> clients = new ArrayList<>();
    private final List<Instructor> instructors = new ArrayList<>();
    private final List<Session> sessions = new ArrayList<>();
    Queue<String> actionHistory = new LinkedList<>();
    private int balance;

    private Gym() {
    }

    public static Gym getInstance() {
        return gym;
    }

    public String getName() {
        return name;
    }

    public void setName(String crossFit) {
        name = crossFit;
    }

    public void setSecretary(Person person, int salary) {
        try {
            secretary.setCurrentSecretary(false);
        }catch (NullPointerException ignored){}
        secretary = new Secretary(person);
        secretary.setSalary(salary);
        secretary.setCurrentSecretary(true);
    }

    public Secretary getSecretary() {
        return secretary;
    }
    public void addClient(Client client){
        clients.add(client);
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    public void addSession(Session session){
        sessions.add(session);
    }

    public List<Session> getSessions() {
        return sessions;
    }

    @Override
    public String toString() {
        DataBase dataBase = DataBase.getInstance();
        return dataBase.getAllData();
    }
}
