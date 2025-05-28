package main;

public class Object {
    private String name;
    private String description;
    private boolean usable; 

    public Object(String name, String description, boolean usable) {
        this.name = name;
        this.description = description;
        this.usable = usable;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isUsable() {
        return usable;
    }
}