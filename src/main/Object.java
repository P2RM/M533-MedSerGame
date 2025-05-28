package main;

public class Object {

    private String name;
    private String description;
    private boolean usable; // doit être une variable d'instance, pas static

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