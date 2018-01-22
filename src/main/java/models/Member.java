package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Member {
    private StringBuilder stringBuilderMemberInitials = new StringBuilder();
    private String firstName;
    private String lastName;
    private String description;
    private int age;
    private int id;
    private int teamId;

    public Member(String firstName, String lastName, String description, int age, int teamId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.age = age;
        this.teamId = teamId;

        setStringBuilderMemberInitials(firstName, lastName);
    }

    // Getters
    public StringBuilder getStringBuilderMemberInitials() { return stringBuilderMemberInitials; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getDescription() { return description; }

    public int getAge() { return age; }

    public int getId() { return id; }

    public int getTeamId() { return teamId; }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void setStringBuilderMemberInitials(String firstName, String lastName) {
        String fullName = firstName + ' ' + lastName;
        for (String initial : fullName.split(" ")) {
            stringBuilderMemberInitials.append(initial.charAt(0));
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return age == member.age &&
                id == member.id &&
                teamId == member.teamId &&
                Objects.equals(firstName, member.firstName) &&
                Objects.equals(lastName, member.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, id, teamId);
    }
}
