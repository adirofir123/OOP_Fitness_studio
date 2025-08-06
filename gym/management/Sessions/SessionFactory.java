package gym.management.Sessions;
import gym.management.*;

public class SessionFactory {
    public static Session createSession(SessionType sessionType, String date, ForumType forumType, Instructor i) {
        return switch (sessionType) {
            case Pilates -> new Pilates(sessionType, date, forumType, i);
            case MachinePilates -> new MachinePilates(sessionType, date, forumType, i);
            case ThaiBoxing -> new ThaiBoxing(sessionType, date, forumType, i);
            case Ninja -> new Ninja(sessionType, date, forumType, i);
        };
    }
}
