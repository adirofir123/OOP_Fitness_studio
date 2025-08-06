package gym.management.Sessions;

import gym.customers.*;
import gym.management.*;
import java.util.ArrayList;

public interface Session {

    void addToSession(Client client);

    int getSessionPrice();

    int getMaxParticipants();

    String getDateOfSession();

    SessionType getSessionType();

    ForumType getForumType();

    int getNumOfParticipants();

    ArrayList<Client> getParticipants();

    Instructor getInstructor();

}
