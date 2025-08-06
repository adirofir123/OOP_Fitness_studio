package gym.management.Sessions;

import java.util.ArrayList;
import gym.management.*;
import gym.customers.*;

public class ThaiBoxing implements Session {

    private final SessionType sessionType;
    private final ForumType forumType;
    private final ArrayList<Client> participants = new ArrayList<>();
    private final String dateOfSession;
    private final Instructor instructor;


    public ThaiBoxing(SessionType sessionType, String date, ForumType forumType, Instructor i) {
        this.sessionType = sessionType;
        this.dateOfSession = date;
        this.forumType = forumType;
        this.instructor = i;
    }
    @Override
    public void addToSession(Client client) {
        participants.add(client);
    }

    @Override
    public int getSessionPrice() {
        return 100;
    }

    @Override
    public int getMaxParticipants() {
        return 20;
    }

    @Override
    public String getDateOfSession() {
        return dateOfSession;
    }

    @Override
    public SessionType getSessionType() {
        return sessionType;
    }

    @Override
    public ForumType getForumType() {
        return forumType;
    }

    @Override
    public int getNumOfParticipants() {
        return participants.size();
    }

    @Override
    public ArrayList<Client> getParticipants() {
        return participants;
    }

    @Override
    public Instructor getInstructor() {
        return instructor;
    }

}
