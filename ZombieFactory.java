import greenfoot.*;

public class ZombieFactory  extends Creator
{
    public Actor getActor(){
        Zombie z = new Zombie();
        z.setImage("Zombie1.gif");
        return z;
    }
}
