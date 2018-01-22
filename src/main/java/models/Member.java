package models;

import java.util.Objects;

public class Member {
//    private StringBuilder memberInitials = new StringBuilder();
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

//        setMemberInitials(firstName, lastName);
    }

    // Getters
//    public StringBuilder getMemberInitials() { return memberInitials; }

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

//    private void setMemberInitials(String firstName, String lastName) {
//        String fullName = firstName + ' ' + lastName;
//        for (String initial : fullName.split(" ")) {
//            memberInitials.append(initial.charAt(0));
//        }
//    }

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

        if (age != member.age) return false;
        if (id != member.id) return false;
        if (teamId != member.teamId) return false;
        if (!firstName.equals(member.firstName)) return false;
        if (!lastName.equals(member.lastName)) return false;
        return description != null ? description.equals(member.description) : member.description == null;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + id;
        result = 31 * result + teamId;
        return result;
    }
}
