package models;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private static List<Member> listMemberInstance = new ArrayList<>();
    private StringBuilder stringBuilderMemberInitials = new StringBuilder();
    private String stringFirstName;
    private String stringLastName;
    private String stringShortDesc;
    private int intAge;
    private int id;

    public Member(String stringFirstName, String stringLastName, String stringShortDesc, int intAge) {
        this.stringFirstName = stringFirstName;
        this.stringLastName = stringLastName;
        this.stringShortDesc = stringShortDesc;
        this.intAge = intAge;
        listMemberInstance.add(this);
        this.id = listMemberInstance.size();

        setStringBuilderMemberInitials(stringFirstName, stringLastName);
    }

    public static void deleteAllMembers() { listMemberInstance.clear(); }

    public void deleteMember() { listMemberInstance.remove(id - 1); }

    public static Member findById(int id) { return listMemberInstance.get(id - 1); }

    // Getters
    public static List<Member> getAll() { return listMemberInstance; }

    public String getStringFirstName() { return stringFirstName; }

    public String getStringLastName() { return stringLastName; }

    public String getStringShortDesc() { return stringShortDesc; }

    public StringBuilder getStringBuilderMemberInitials() { return stringBuilderMemberInitials; }

    public int getIntAge() { return intAge; }

    public int getId() { return id; }

    // Setters
    public void setStringFirstName(String stringFirstName) {
        this.stringFirstName = stringFirstName;
    }

    public void setStringLastName(String stringLastName) {
        this.stringLastName = stringLastName;
    }

    public void setStringShortDesc(String stringShortDesc) {
        this.stringShortDesc = stringShortDesc;
    }

    private void setStringBuilderMemberInitials(String stringFirstName, String stringLastName) {
        String fullName = stringFirstName + ' ' + stringLastName;
        for (String initial : fullName.split(" ")) {
            stringBuilderMemberInitials.append(initial.charAt(0));
        }
    }
}
