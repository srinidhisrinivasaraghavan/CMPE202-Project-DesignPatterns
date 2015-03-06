import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage

public class NewYork extends World 
{   
    Creator creator;
    Human h1;
    
    public NewYork() 
    {
        super(1000, 500, 1); // Setting the size of the world, 1000 is the width of world and 500 is height of the world
        prepare();
    }
    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
       
       Message m = new Message();
       addObject(m, 900, 60);
       
       
        //addScoreBox(); // adds a score box at the top right
       addZombiesToWorld(); // adds zombies to the world at random places
       addItems(); // adds Food, Kit, Sword to the world
       h1 = addHumanToWorld(); // adds Human to the world and returns a reference to attach the observers
       attachObservers(h1); // attaches itemCollectionObserver and soundObserver to the human h1.
    }
    
    public void addItems(){
       Creator c1 = new KitFactory(); // This factory returns a kit
       Creator c2 = new SwordFactory(); // This factory returns a sword
       
       addObject(c1.getActor(), Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(500)); // adds kit at a random place to the world
       populateFood();
       
       Sword s1 = (Sword) c2.getActor(); // getting a sword as an actor and casting it into a sword.
       addObject(s1, Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(500));
       s1.setImage("handle.png"); // setting the image of the sword.
       
       Sword s2 = (Sword) c2.getActor();
       addObject(s2, Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(500));
       s2.setImage("blade.png");
    }
    
    public void addScoreBox(){
        // adds a score box at the coordinates x = 900, y = 60
    }
    
    public Human addHumanToWorld(){
       Human h1;
       
       addObject(new Cat(), 50, 450);
        
       h1 = new Human();
       addObject(h1, 120, 420);
       return h1;
    }
    
    public void addZombiesToWorld(){
       creator = new ZombieFactory(); // This factory returns a zombie
       
       addObject(creator.getActor(), Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(250));
       addObject(creator.getActor(), Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(250));
       addObject(creator.getActor(), Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(250));
    }
    
    public void populateFood(){
       creator = new FoodFactory(); // This factory returns a Food
       
       addObject(creator.getActor(), Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(500));
       addObject(creator.getActor(), Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(500));
       addObject(creator.getActor(), Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(500));
    }
    
    
    public void attachObservers(Human h1){
        h1.attach(new ItemCollectionObserver()); //attaching the ItemCollectionObserver
        h1.attach(new SoundObserver()); // attaching the SoundObserver
    }
}