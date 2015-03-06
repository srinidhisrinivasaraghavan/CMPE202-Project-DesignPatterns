public class halfSwordState implements iSwordState
{
  
    public Sword sword;
    public halfSwordState(Sword sword)
    {
    
    this.sword = sword;
    }

    public  boolean hasFull()
    {
        return false;
    }
    
    public boolean hasHalf()
    {
        return true;
    }
    
     public boolean hasNoSword()
    {
        return false;
    }
     
    public void setState()
    {
        sword.setState(sword.getFullSwordState());
    }
    
    public void display()
    {
        System.out.println("Half sword is taken");
    }


}