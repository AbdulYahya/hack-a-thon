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

    // Getter Methods
    public static List<Team> getAll() throws Exception {
        return listTeamInstance;
    }

    public List<Members> getAllMembers() throws Exception {
        return listMembers;
    }

    public String getStringName() {
        return stringName;
    }

    public String getStringDescription() {
        return stringDescription;
    }
}
