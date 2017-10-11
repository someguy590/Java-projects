package mutableRequisiteP;

import java.io.Serializable;


public class mutableRequisite implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2689170507168371947L;
    public long dueDate = 0;
    public String name = "Unnamed requisite";
    public String details = "";
    public int weight = 1;
    public int id;
    public int[][] prereqlists;

    public mutableRequisite(String name, String details, int weight, int id, long dueDate) {
        this.name = name;
        this.details = details;
        this.weight = weight;
        this.id = id;
    }

}
