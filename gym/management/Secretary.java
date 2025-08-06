package gym.management;

import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.*;

import java.util.ArrayList;

public class Secretary extends Person {
    private int salary;
    private final Gym gym;
    private boolean currentSecretary;
    Person person;
    private final DateActions dateActions = DateActions.getInstance();

    public Secretary(Person person) {
        super(person);
        this.person = person;
        gym = Gym.getInstance();
        gym.actionHistory.add("A new secretary has started working at the gym: " + person.getName());
    }

    public boolean hasSessionPassed(Session session) {
        return dateActions.isFutureDate(session.getDateOfSession());
    }

    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        Client client = new Client(person);
        if (person.getAge() < 18)
            throw new InvalidAgeException("Error: Client must be at least 18 years old to register");
        else if (gym.getClients().contains(client))
            throw new DuplicateClientException("Error: The client is already registered");
        else {
            gym.addClient(client);
            gym.actionHistory.add("Registered new client: " + client.getName());
            return client;
        }
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!gym.getClients().contains(client))
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        gym.getClients().remove(client);
        gym.actionHistory.add("Unregistered client: " + client.getName());
    }

    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessions) throws InvalidAgeException {
        if (person.getAge() < 18) {
            throw new InvalidAgeException("Error: Client must be at least 18 years old to register");
        } else {
            Instructor instructor = new Instructor(person);
            instructor.setQualifiedSessions(sessions);
            instructor.setSalary(salary);
            gym.addInstructor(instructor);
            gym.actionHistory.add("Hired new instructor: " + person.getName() + " with salary per hour: " + salary);
            return instructor;
        }
    }

    public void registerClientToLesson(Client client, Session session) throws DuplicateClientException, ClientNotRegisteredException {
        boolean success = true;
        if (!isCurrentSecretary()) {
            throw new NullPointerException();
        }
        if (!gym.getClients().contains(client)) {
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        }
        if (hasSessionPassed(session)) {
            gym.actionHistory.add("Failed registration: Session is not in the future");
            success = false;
        }
        if (session.getNumOfParticipants() >= session.getMaxParticipants()) {
            gym.actionHistory.add("Failed registration: No available spots for session");
            success = false;

        }
        if (client.getAge() < 65 && session.getForumType() == ForumType.Seniors) {
            gym.actionHistory.add("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
            success = false;
        }
        if ((session.getForumType() == ForumType.Female && client.getGender() == Gender.Male) ||
                (session.getForumType() == ForumType.Male && client.getGender() == Gender.Female)) {
            gym.actionHistory.add("Failed registration: Client's gender doesn't match the session's gender requirements");
            success = false;
        }
        if (client.getWallet() < session.getSessionPrice()) {
            gym.actionHistory.add("Failed registration: Client doesn't have enough balance");
            success = false;
        }
        if (session.getParticipants().contains(client)) {
            throw new DuplicateClientException("Error: The client is already registered for this lesson");
        }
        if (success) {
            session.addToSession(client);
            client.updateWallet(client.getWallet() - session.getSessionPrice());
            gym.setBalance(gym.getBalance() + session.getSessionPrice());
            client.addToRegisteredSessions(session);
            gym.actionHistory.add("Registered client: " + client.getName() + " to session: " + session.getSessionType() +
                    " on " + dateActions.formatDate(session.getDateOfSession()) + " for price: " + session.getSessionPrice());
        }

    }

    public void paySalaries() {
        //this is for the instructors
        for (Instructor instructor : gym.getInstructors()) {
            instructor.updateWallet(instructor.getWallet() + (instructor.getSalary() * instructor.getSessionsToDo().size()));
            gym.setBalance(gym.getBalance() - (instructor.getSalary() * instructor.getSessionsToDo().size()));
        }

        //this is for the secretary
        person.updateWallet(getWallet() + salary);
        gym.setBalance(gym.getBalance() - salary);
        gym.actionHistory.add("Salaries have been paid to all employees");
    }

    public void printActions() {
        for (String action : gym.actionHistory) {
            System.out.println(action);
        }
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Session addSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        if (!instructor.getQualifiedSessions().contains(sessionType)) {
            throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");
        }

        gym.actionHistory.add("Created new session: " + sessionType + " on " + dateActions.formatDate(date) + " with instructor: " + instructor.getName());
        Session session = SessionFactory.createSession(sessionType, date, forumType, instructor);
        gym.addSession(session);
        instructor.addSessionsToDo(session);
        return session;
    }

    public void notify(Session session, String notification) {
        gym.actionHistory.add("A message was sent to everyone registered for session " + session.getSessionType() +
                " on " + dateActions.formatDate(session.getDateOfSession()) + " : " + notification);
        for (Client client : session.getParticipants()) {
            client.addNotification(notification);
        }
    }

    public void notify(String date, String notification) {
        gym.actionHistory.add("A message was sent to everyone registered for a session on " + dateActions.flipDate(date) + " : " + notification);
        for (Client client : gym.getClients()) {
            client.addNotification(notification);
        }
    }

    public void notify(String notification) {
        gym.actionHistory.add("A message was sent to all gym clients: " + notification);
        for (Client client : gym.getClients()) {
            client.addNotification(notification);
        }
    }

    public boolean isCurrentSecretary() {
        return currentSecretary;
    }

    public void setCurrentSecretary(boolean currentSecretary) {
        this.currentSecretary = currentSecretary;
    }

    @Override
    public int getWallet() {
        return person.getWallet();
    }

    @Override
    public void updateWallet(int wallet) {
        person.updateWallet(wallet);
    }
}