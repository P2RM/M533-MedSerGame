package main;
 
public class Cle extends Object {
    private String unlockTargetName;
 

    public Cle(String name, String unlockTargetName) {
        super(name, unlockTargetName, usable);
        super(name, unlockTargetName, true);
        this.unlockTargetName = unlockTargetName;
    }

     public String getUnlockTargetName() {
        return unlockTargetName;
    }
    

    @Override
    public String getDescription() {
        return "A key labeled \"" + unlockTargetName + "\".";
    }
}


 

   
 