package models;

import java.util.Objects;

public class Team {
    private String stringName;
    private String stringDescription;
    private int id;

    public Team(String stringName, String stringDescription) {
        this.stringName = stringName;
        this.stringDescription = stringDescription;
    }

    // Getter Methods
    public int getId() {
        return id;
    }

    public String getStringName() {
        return stringName;
    }

    public String getStringDescription() {
        return stringDescription;
    }

    // Setter Methods
    public void setId(int id) { this.id = id; }

    public void setStringName(String stringName) {
        this.stringName = stringName;
    }

    public void setStringDescription(String stringDescription) {
        this.stringDescription = stringDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id &&
                Objects.equals(stringName, team.stringName) &&
                Objects.equals(stringDescription, team.stringDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringName, stringDescription, id);
    }
}
