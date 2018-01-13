package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private static List<Team> listTeamInstance = new ArrayList<>();
    private static List<Members> listMembers; // might have to initialize this list in the constructor
    private String stringName;
    private String stringDescription;
    private int id;

    public Team(String stringName, String stringDescription) {
        this.stringName = stringName;
        this.stringDescription = stringDescription;
        listMembers = new ArrayList<>();
        listTeamInstance.add(this);
        this.id = listTeamInstance.size();

    }

    public static void deleteAllTeams() {
        listTeamInstance.clear();
    }

    public void deleteTeam() {
        listTeamInstance.remove(id - 1);
    }

    public static Team findById(int id) {
        return listTeamInstance.get(id - 1);
    }

    // Getter Methods
    public static List<Team> getAll() {
        return listTeamInstance;
    }

    public static List<Members> getAllMembers() {
        return listMembers;
    }

    public String getStringName() {
        return stringName;
    }

    public String getStringDescription() {
        return stringDescription;
    }

    public int getId() {
        return id;
    }

    // Setter Methods
    public void setStringName(String stringName) {
        this.stringName = stringName;
    }

    public void setStringDescription(String stringDescription) {
        this.stringDescription = stringDescription;
    }
}
