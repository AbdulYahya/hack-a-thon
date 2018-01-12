package models;

import java.util.ArrayList;
import java.util.List;

public class Members {
    private static List<Members> listMemberInstance = new ArrayList<>();
    private String stringFirstName;
    private String stringLastName;
    private String stringShortDesc;
    private int intAge;

    public Members(String stringFirstName, String stringLastName, String stringShortDesc, int intAge) {
        this.stringFirstName = stringFirstName;
        this.stringLastName = stringLastName;
        this.stringShortDesc = stringShortDesc;
        this.intAge = intAge;
        listMemberInstance.add(this);
    }

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

}
