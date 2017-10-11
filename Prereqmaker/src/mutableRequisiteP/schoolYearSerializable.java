package mutableRequisiteP;

import java.io.Serializable;

public class schoolYearSerializable implements Serializable {
    private static final long serialVersionUID = -1051486597332946475L;
    public mutableRequisite[] requisites;
    public short year;
    public String semester;
    public String major;
    public String schoolName;
}