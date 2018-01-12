package models;

import java.util.ArrayList;
import java.util.List;

public class Members {
    private static List<Members> listMemberInstance = new ArrayList<>();
    private String stringFirstName;
    private String stringLastName;
    private String stringShortDesc;
    private int intAge;
    private int id;

    public Members(String stringFirstName, String stringLastName, String stringShortDesc, int intAge) {
        this.stringFirstName = stringFirstName;
        this.stringLastName = stringLastName;
        this.stringShortDesc = stringShortDesc;
        this.intAge = intAge;
        listMemberInstance.add(this);
        this.id = listMemberInstance.size();
    }

    public static void clearAllMembers() {
        listMemberInstance.clear();
    }

    public void deleteMember() { listMemberInstance.remove(id - 1); }

    public static Members findById(int id) { return listMemberInstance.get(id - 1); }

    // Getters
    public static List<Members> getAll() {
        return listMemberInstance;
    }

    public String getStringFirstName() {
        return stringFirstName;
    }

    public String getStringLastName() {
        return stringLastName;
    }

    public String getStringShortDesc() {
        return stringShortDesc;
    }

    public int getIntAge() {
        return intAge;
    }

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
}
