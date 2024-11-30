package models;

public class Preferences {
    private String name;



    public Preferences(String string) {
        this.name = string;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
