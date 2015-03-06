public class noSwordState implements iSwordState
{
  
    public Sword sword;
    public noSwordState(Sword sword)
    {
    
    this.sword = sword;
    }

    public  boolean hasFull()
    {
        return false;
    }
    
    public boolean hasHalf()
    {
        return false;
    }
    
        
    public boolean hasNoSword()
    {
        return true;
    }
     
    public void setState()
    {
        sword.setState(sword.getHalfSwordState());
    }
    
    public void display()
    {
        System.out.println("No sword is taken");
    }


}