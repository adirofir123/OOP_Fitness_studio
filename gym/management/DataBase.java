package gym.management;

import gym.customers.Client;
import gym.management.Sessions.Session;
import java.util.List;

public class DataBase {
    private static final DataBase dataBase = new DataBase(Gym.getInstance());
    private final Gym gym;

    private DataBase(Gym gym) {
        this.gym = gym;
    }

    public static DataBase getInstance() {
        return dataBase;
    }

    public String getAllData(){
        return "%s\nClients Data:\n%s\nEmployees Data:\n%s\nSessions Data:%s".formatted(
                getGymData(gym),
                getClientsData(gym.getClients()),
                getEmployeesData(gym.getInstructors(), gym.getSecretary()),
                getSessionsData(gym.getSessions()));
    }
    public String getGymData(Gym gym) {
        return ("""
                Gym Name: %s
                Gym Secretary: ID: %d | Name: %s | Gender: %s | Birthday: %s | Age: %d | Balance: %d | Role: Secretary | Salary per Month: %d
                Gym Balance: %d
                """).formatted(
                        gym.getName(),
                gym.getSecretary().getID(),
                gym.getSecretary().getName(),
                gym.getSecretary().getGender(),
                gym.getSecretary().getDOB(),
                gym.getSecretary().getAge(),
                gym.getSecretary().getWallet(),
                gym.getSecretary().getSalary(),
                gym.getBalance());
    }

    public String getClientsData(List<Client> clients) {
        StringBuilder clientsData = new StringBuilder();
        for (Client client : clients) {
            clientsData.append("ID: ").append(client.getID()).append(" | ").
                    append("Name: ").append(client.getName()).append(" | ").
                    append("Gender: ").append(client.getGender()).
                    append(" | ").append("Birthday: ").
                    append(client.getDOB()).append(" | ").
                    append("Age: ").append(client.getAge()).
                    append(" | ").append("Balance: ").
                    append(client.getWallet()).
                    append("\n");
        }
        return clientsData.toString();
    }

    public String getEmployeesData(List<Instructor> instructors, Secretary secretary) {
        StringBuilder employeesData = new StringBuilder();
        for (Instructor instructor : instructors) {
            StringBuilder listOfLessons = new StringBuilder();
            for (int i = 0; i < instructor.getQualifiedSessions().size(); i++) {
                listOfLessons.append(instructor.getQualifiedSessions().get(i));
                if (i != instructor.getQualifiedSessions().size()-1) {
                    listOfLessons.append(", ");
                }
            }
            employeesData.append("ID: ").
                    append(instructor.getID()).append(" | ").
                    append("Name: ").append(instructor.getName()).append(" | ").
                    append("Gender: ").append(instructor.getGender()).append(" | ").
                    append("Birthday: ").append(instructor.getDOB()).append(" | ").
                    append("Age: ").append(instructor.getAge()).append(" | ").
                    append("Balance: ").append(instructor.getWallet()).append(" | ").
                    append("Role: Instructor | ").
                    append("Salary per Hour: ").append(instructor.getSalary()).append(" | ").
                    append("Certified Classes: ").append(listOfLessons).
                    append("\n");
        }
        employeesData.append("ID: ").append(secretary.getID()).append(" | ").
                append("Name: ").append(secretary.getName()).append(" | ").
                append("Gender: ").append(secretary.getGender()).append(" | ").
                append("Birthday: ").append(secretary.getDOB()).append(" | ").
                append("Age: ").append(secretary.getAge()).append(" | ").
                append("Balance: ").append(secretary.getWallet()).append(" | ").
                append("Role: Secretary | ").
                append("Salary per Month: ").append(secretary.getSalary()).
                append("\n");
        return employeesData.toString();
    }

    public String getSessionsData(List<Session> sessions) {
        StringBuilder sessionsData = new StringBuilder();
        for (Session session : sessions) {
            sessionsData.append("\n" + "Session Type: ").append(session.getSessionType()).append(" | ").
                    append("Date: ").append(session.getDateOfSession()).append(" | ").
                    append("Forum: ").append(session.getForumType()).append(" | ").
                    append("Instructor: ").append(session.getInstructor().getName()).append(" | ").
                    append("Participants: ").append(session.getNumOfParticipants()).append("/").append(session.getMaxParticipants());
        }
        return sessionsData.toString();
    }
}
