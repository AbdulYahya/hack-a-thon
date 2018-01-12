package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private static List<Team> listTeamInstance = new ArrayList<>();
    private List<Members> listMembers = new ArrayList<>(); // might have to initialize this list in the constructor
    private String stringName;
    private String stringDescription;

    public Team(String stringName, String stringDescription) {
        this.stringName = stringName;
        this.stringDescription = stringDescription;
        listTeamInstance.add(this);
    }

    public static void clearAllTeams() {
        listTeamInstance.clear();
    }

    // Getter Methods
    public static List<Team> getAll() {
        return listTeamInstance;
    }

    public List<Members> getAllMembers() {
        return listMembers;
    }

    public String getStringName() {
        return stringName;
    }

    public String getStringDescription() {
        return stringDescription;
    }
}
