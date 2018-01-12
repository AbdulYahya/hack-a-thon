package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private static List<Team> listTeamInstance = new ArrayList<>();
    private List<Members> listMembers = new ArrayList<>(); // might have to initialize this list in the constructor
    private String stringName;
    private String stringDescription;
    private int id;

    public Team(String stringName, String stringDescription) {
        this.stringName = stringName;
        this.stringDescription = stringDescription;
        listTeamInstance.add(this);
        this.id = listTeamInstance.size();
    }

    public static void clearAllTeams() {
        listTeamInstance.clear();
    }

    public void deleteTeam() {
        listTeamInstance.remove(id - 1);
    }

    public Team findById() {
        return listTeamInstance.get(id - 1);
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

    public int getId() {
        return id;
    }
}
