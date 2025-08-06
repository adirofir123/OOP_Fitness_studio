package gym.customers;


import gym.management.Notifications;
import gym.management.Sessions.Session;

import java.util.ArrayList;

public class Client extends Person implements Notifications {
    Person person;

    private final ArrayList<String> notifications = new ArrayList<>();
    private final ArrayList<Session> sessions = new ArrayList<>();

    public Client(Person person) {
        super(person);
        this.person = person;
    }

    @Override
    public void addNotification(String notification) {
        notifications.add(notification);
    }

    public void addToRegisteredSessions(Session session) {
        sessions.add(session);
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public String getNotifications() {

        return "["+String.join(", ", notifications)+"]";
    }
    @Override
    public int getWallet() {
        return person.getWallet();
    }

    @Override
    public void updateWallet(int wallet) {
        person.updateWallet(wallet);
    }

    @Override
    public boolean equals(Object obj) {
        return obj.equals(person);
    }
}