import java.util.Stack;

/**
 *  This class is the main class of the "Dream Police Academy" application. 
 *  It is a story-driven, text based adventure game.  Users 
 *  can walk around a dream to kill a werewolf.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Stack<Room> path;
    private Player you;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        you = new Player(100);
        createRooms();
        parser = new Parser();
        path = new Stack<Room>();
    }

    public static void main(String[] args)
    {
        Game game = new Game();
        game.play();
    }
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room car, outside, footpath, road, partner, ledge; // real world
        // killer's head. LEGEND: B - basement, L - level, sw - stairwell
        Room B, L1, L2, L3, L4, L5, L6, lift, shaft, swb, sw1, sw2, sw3, sw4, sw6; // dream world
        
        Item dreamGun, file, bottle, cake, knife, blood;
        dreamGun = new Item("dreamgun", "A purple dreamgun", 50);
        file = new Item("file", "A manilla envelope (the wolfman's file)", 2);
        bottle = new Item("bottle", "An empty water bottle", 10);
        cake = new Item("cake", "A piece of chocolate cake", 5);
        knife = new Item("knife", "A swiss army knife", 12);
        blood = new Item("blood", "A packet of blood", 8);
        
        // create the rooms
        car = new Room("in a police car.");
        outside = new Room("out of the police car.");
        footpath = new Room("walking along the footpath.");
        road = new Room("walking onto the road.");
        partner = new Room("in your partner's head.");
        
        ledge = new Room("on a high ledge. You have a direct shot at your target.");
        ledge.addItem(bottle);
        ledge.addItem(cake);
        
        L1 = new Room("in the wolfman's head. There is a sign on a nearby " +
                       "wall saying:\n\t\"ST MAGARET'S HOSPITAL: LEVEL ONE\"");
        L1.addItem(blood);
        sw1 = new Room("on level 1 of the stairwell. A \"2\" is painted on the wall " +
                       "besides an upward arrow. A \"B\" is acompanied with a downward arrow.");
        sw2 = new Room("on level 2 of the stairwell. There is a door leading to level 2.");
        
        L2 = new Room("on the east side of level 2. There is a sign on a nearby " +
                       "wall saying:\n\t\"ST MAGARET'S HOSPITAL: LEVEL TWO\"");
        L2.addItem(knife);
        
        sw3 = new Room("on level 3 of the stairwell. There is a door leading to level 3.");
        L3 = new Room("on the east side of level 3. There is a sign on a nearby " +
                       "wall saying:\n\t\"ST MAGARET'S HOSPITAL: LEVEL THREE\"");
        sw4 = new Room("on level 4 of the stairwell. There is a door leading to level 4.");
        
        L4 = new Room("on the east side of level 4. There is a sign on a nearby " +
                       "wall saying:\n\t\"ST MAGARET'S HOSPITAL: LEVEL FOUR\"");
        
        sw6 = new Room("on level 6 of the stairwell. There is a door leading to level 6.");
        L6 = new Room("on the east side of level 6. There is a sign on a nearby " +
                       "wall saying:\n\t\"ST MAGARET'S HOSPITAL: LEVEL SIX\"");
        L5 = new Room("on the west side of level 5. There is a rumbling noise coming " +
                      "from the east. From sign on a nearby wall you read:" +
                       "\n\t\"ST MAGARET'S HOSPITAL: LEVEL FIVE\"");
        B = new Room("in the basement of the hospital. It is pitch black so you cannot " +
                     "see a thing.");
        swb = new Room("on the basement level of the stairwell. There is a door leading " +
                       "to the basement");
        lift = new Room("in an elevator. By the door is listed the options:\n" +
                        "\tL6\n\tL4\n\tL3\n\tL2\n\tL1\n\tB\n\"L1\" is eluminated.");
        shaft = new Room("in the elevator shaft; on top of the elevator. " +
                         "Just above you see an open doorway you can climb through.");
        
        // initialise room exits
        car.setExit("west", outside);
        outside.setExit("north", footpath);
        outside.setExit("east", car);
        outside.setExit("south", road);
        outside.setExit("west", ledge);
        footpath.setExit("south", outside);
        road.setExit("north", outside);
        road.setExit("east", partner);
        partner.setExit("west", road);
        ledge.setExit("east", outside);
        ledge.setExit("west", L1);
        L1.setExit("east", sw1);
        L1.setExit("west", lift);
        sw1.setExit("up", sw2);
        sw1.setExit("down", swb);
        sw1.setExit("west", L1);
        sw2.setExit("up", sw3);
        sw2.setExit("down", sw1);
        sw2.setExit("west", L2);
        sw3.setExit("up", sw4);
        sw3.setExit("down", sw2);
        sw3.setExit("west", L3);
        sw4.setExit("up", sw6);
        sw4.setExit("down", sw3);
        sw4.setExit("west", L4);
        sw6.setExit("down", sw4);
        sw6.setExit("west", L6);
        L2.setExit("east", sw2);
        L3.setExit("east", sw3);
        L4.setExit("east", sw4);
        L6.setExit("east", sw6);
        B.setExit("east", swb);
        swb.setExit("up", sw1);
        swb.setExit("west", B);
        lift.setExit("up", shaft);
        lift.setExit("east", L1);
        shaft.setExit("east", L5);
        shaft.setExit("down", lift);
        L5.setExit("west", shaft);

        you.setRoom(car);       // start game in the car
        you.addItem(dreamGun);    // start with the dreamgun
        you.addItem(file);      // start with the file
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("\tThank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome!");
        System.out.println("You have been training for the last five years in\nthe Dream Police Academy," +
                           " and your partner is about\nto debrief you on your final assignment...\n");
        System.out.println("You are seated in the passenger seat of a police car with\nyour partner. He" +
                           " hands you a manilla envelope. On the front it reads:\n\tCLASSIFIED: THE WOLFMAN CASE\n\n" +
                           "\"Essentially, your subject is a grade A killer,\" your partner begins,\n\"he" +
                           " murders periodically; every full moon. Hence the nickname.\nYour task is to" +
                           " get into his head by shooting him with this dreamgun\nfrom on top of that high" +
                           " ledge,\" he nods in a westerly direction and\nhands you a purple and black gun." +
                           " You take it. \"Once inside, you will\nhunt and kill the werewolf that resides there.\n\n" +
                           "\"Good luck Charlie,\" he says, \"you're gonna need it.\"\nYou place the dreamgun and file into" +
                           " your police vest. As you turn to\nleave he grabs you by the shoulder.\n\"And remember Charlie:" +
                           " that gun is very confidential; if you veer\nanywhere off course, I will shoot to kill.\"");                  
        System.out.println();                 
        System.out.println("Type 'help' if you need help.");

        printLocationInfo();
    }
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if(commandWord.equals("help")) {
            printHelp();
        }
        else if(commandWord.equals("go")) {
            goRoom(command);
        }
        else if(commandWord.equals("look")) {
            printLocationInfo();
        }
        else if(commandWord.equals("eat")) {
            eat();
        }
        else if(commandWord.equals("back")) {
            if(!path.empty())
            {
                you.setRoom(path.pop());
                printLocationInfo();
            }
            else
            {
                System.out.println("\tYou are at the beginning!");
            }
        }
        else if(commandWord.equals("take")) {
            take(command);
        }
        else if(commandWord.equals("drop")) {
            drop(command);
        }
        else if(commandWord.equals("inventory")) {
            you.items();
        }
        else if(commandWord.equals("drink")) {
            drink(command);
        }
        else if(commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    /**
     * Print out some help information.
     * Here we print a list of the command words.
     */
    private void printHelp() 
    {
        System.out.println("\tYour command words are:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("\tGo where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room currentRoom = you.getRoom();
        Room nextRoom = currentRoom.getExit(direction);
        
        if (nextRoom == null) {
            System.out.println("\tThere is no door!");
        }
        else {
            path.push(currentRoom);
            you.setRoom(nextRoom);
            printLocationInfo();
        }
    }

    /**
     * Try to take something.
     */
    private void take(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("\tTake what?");
            return;
        }
        
        String object = command.getSecondWord();
        
        // Try to take said item.
        you.pickUp(object);
    }
    
    /**
     * Try to drop something.
     */
    private void drop(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("\tDrop what?");
            return;
        }
        
        String object = command.getSecondWord();
        
        //Try to drop said item.
        you.drop(object);
    }
    
    /**
     * Try to drink something.
     */
    private void drink(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drink...
            System.out.println("\tDrink what?");
            return;
        }
        
        String substance = command.getSecondWord();
        
        // Try to drink said item.
        if(substance.equals("blood"))
        {
            you.drinkBlood();
        }
        else
        {
            System.out.println("\tWhat d'you mean \"" + substance + "\"?\n");
        }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("\tQuit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Prints the current location of the user.
     */
    private void printLocationInfo()
    {
        System.out.println(you.getRoom().getLongDescription());
    }
    
    /**
     * Prints a description of eating.
     */
    private void eat()
    {
        System.out.println("\tYou have eaten now and you are not hungry any more.");
    }
}
