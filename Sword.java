import greenfoot.*;
public class Sword extends Actor
{
    iSwordState halfsword;
    iSwordState fullsword;
    iSwordState nosword;
    
    iSwordState state;
     public Sword(){
        nosword = new noSwordState(this);
        halfsword = new halfSwordState(this);
        fullsword = new fullSwordState(this);
        state = nosword;
    }
    
    public boolean hasFull() {
        return state.hasFull();
    }

    public boolean hasHalf() {
        return state.hasHalf();
    }
    
    public boolean hasNoSword() {
        return state.hasNoSword();
    }
    
    public void display() {
        state.display();
    }
    
    void setState(iSwordState state) {
        this.state = state;
    }
    
    public void setSwordState(){ 
        state.setState();
    }
    
    iSwordState getFullSwordState(){
        return fullsword;
    }
       
        
    iSwordState getHalfSwordState(){
        return halfsword;
    }
    
    iSwordState getNoSwordState()
	{
	    return nosword;
	}
    
    public void act() 
    {
        // Add your action code here.
    }
}