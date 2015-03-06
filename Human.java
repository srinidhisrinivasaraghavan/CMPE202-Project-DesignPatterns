import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
public class Human extends Actor implements Subject
{
    iHumanState humanAliveState, humanDeadState, humanState;
    public Sword swordState;
    private Actor food, kit, sword, zombie, blood;
    private World world;
    private Message m;
    private int delayCount2 = 0;
    private boolean startDelay = false;
    private ArrayList<Observer> observers =  new ArrayList<Observer>();
    private Context context;
    
    private int count = 0;
    
    //constructor 
    public Human(){
        world =   getWorld();
        GreenfootImage image1 = new GreenfootImage("Human.png");
        setImage(image1);
        blood = new Blood();
        
        humanAliveState = new HumanAliveState(this); // * state pattern 1*
        humanDeadState = new HumanDeadState(this); // * state pattern 1*
        humanState = humanAliveState; // * state pattern 1*
        swordState = new Sword(); // * state pattern 2*
        
        context = new Context(new HumanMovement()); // Strategy Pattern
    }
    
    public void act() 
    {
        m = (Message) getWorld().getObjects(Message.class).get(0);
        /**
         * Check if human is intersecting any object
        */
        checkForFood();
        checkForKit();
        checkForSword();
        checkForZombie();
        startDelayManWithKit();
        context.executeMovement(this); // Strategy Pattern for moving human across the world.
    }  
    
    public void checkForFood() 
    {
      food = getOneIntersectingObject(Food.class);
      if(food != null){
            System.out.println("food found");
            notifyObservers("food", m); // check if human intersects food and notify observers *Observer Pattern*
            getWorld().removeObject(food); // Human eats the food, so food should disppear from the world
        }
    }
    
    public void checkForKit()
    {
        kit = getOneIntersectingObject(Kit.class);
        if(kit != null){
             notifyObservers("kit", m); // notify observers for playing sound and updating score box.
             getWorld().removeObject(kit);
        }
    }
    
    public void checkForSword()
    {
        sword = getOneIntersectingObject(Sword.class);
        if(sword != null){
            swordState.setSwordState(); // set the sword state to either half or full depending on the current state of sword.
            notifyObservers("sword", m);
            getWorld().removeObject(sword);
        }
    }
    
    public void checkForZombie()
    {
        zombie = getOneIntersectingObject(Zombie.class);
        if(zombie != null)
        {
            if(ItemCollectionObserver.hasSword){ // If human has a sword, kill and remove zombie
                notifyObservers("zombie", m);
                getWorld().addObject(blood, zombie.getX(), zombie.getY());
                getWorld().removeObject(zombie);
                swordState.setSwordState(); //set swordState = No Sword
                if(ItemCollectionObserver.zombieCount == 0){ // If number of zombies == 0, display "You Win" message! and stop the game.
                    calculateScore();
                    display();
                    ItemCollectionObserver.zombieCount=3;
                    Greenfoot.stop();
                }

            }
            else if(!ItemCollectionObserver.hasKit && !startDelay) // If the human does not have a kit to protect himself.
            {
                setImage("Blood Splatter.png");
                humanState.setState(); // Set humanState to deadState and display "Game Over" message and stop the game.
                //System.out.println(humanState);
                calculateScore();
                display();
                Greenfoot.stop();
            }
            else{ // if the human has a kit, start delay to give some time for human and zombie to move away from each other.
                startDelay = true;
                startDelayManWithKit();
            }
        }
    }
    
    
    public void calculateScore(){
        int score = 0;
        int food = ItemCollectionObserver.foodCount;
        int foodScore = food*5;
        int zombiesKilled = 3 - ItemCollectionObserver.zombieCount;
        int zombieScore = zombiesKilled*10;
        StringBuilder s = new StringBuilder();
        
        s.append("Food Collected : "+food+" X 5 = "+foodScore+"\n");
        s.append("Zombies Killed : "+zombiesKilled+" X 10 = "+zombieScore+"\n");
        s.append("Total Score = "+(foodScore+zombieScore));
        System.out.println(s.toString());
        m.setText(s.toString());
        m.setLocation(550, 350);
    }
    
    public void startDelayManWithKit() // if the human has a kit, start delay to give some time for human and zombie to move away from each other.
    {
         if(startDelay){
            delayCount2++;
        }
        
        if(delayCount2==1)
            notifyObservers("kit", m);
        
            if(delayCount2 > 70){
            startDelay = false;
            delayCount2 = 0;
        }
    }

    // OBSERVER PATTERN
    public void attach(Observer obj){
        observers.add(obj);
     }
   
    public void detach(Observer obj){
        observers.remove(obj);
     }
    
    public void notifyObservers(String item, Message m){
        for(Observer obj : observers)
        {
            obj.update(item, m);
        }
    }
    
    
    //STATE PATTERN
    public void display() {
        humanState.display();
    }
    void setState(iHumanState state) {
        //System.out.println("received"+state);
        this.humanState = state;
    }
    iHumanState getHumanAliveState()
    {
        return humanAliveState;
    }    
    iHumanState getHumanDeadState()
    {
        return humanDeadState;
    }
    
    
}