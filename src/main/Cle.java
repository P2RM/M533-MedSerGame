package main;
 
public class Cle extends Object {
    private String unlockTargetName;
 

    public Cle(String name, String description, boolean usable, String unlockTargetName) {
        super(name, description, usable);
        this.unlockTargetName = unlockTargetName;
    }

     public String getUnlockTargetName() {
        return unlockTargetName;
    }
    

    @Override
    public String getDescription() {
        return "Une clé nommée \"" + unlockTargetName + "\".";
    }
}


 

   
 