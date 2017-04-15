import java.util.ArrayList;
import java.util.Stack;

/**
 * A class for any generic commands to be made.
 * Likely to become a super class later.
 * 
 * @author Phillip Rossetti
 * @version 30/03/2017
 */
public class AllCommands
{
    private String keyWord;     // the command word
    private byte todo;      // controls what happens in the action method
    private ArrayList<String> alternatives;     // alternatives to the command word
    private String goodResponse;        // Strings to be printed if command successful
/*  private String badResponse;*/     // Strings to be printed if command failed

    /**
     * Constructor for objects of class AllCommands
     */
    public AllCommands(String keyWord, byte todo/*, String badResponse*/)
    {
        // initialise instance variables
        this.keyWord = keyWord;
        this.todo = todo;
        goodResponse = "";
/*      this.badResponse = badResponse;*/
        alternatives = new ArrayList<String>();
    }
    
    /**
     * Returns the (main) command word.
     * @return The command word.
     */
    public String getKeyWord()
    {
        return keyWord;
    }
    
    /**
     * Set the response for this command.
     */
    public void setGoodResponse(String goodResponse)
    {
        this.goodResponse = goodResponse;
    }
    
    /**
     * Method do perform for any command.
     */
    public Stack<Room> action(Input command, Player you, Stack<Room> path)
    {
        if((todo & (byte)00000001) == (byte)00000001)    // want to go back
        {
            if(!path.empty()) {
                you.setRoom(path.pop());
                System.out.println(you.getRoom().getLongDescription());
            }
            else
            {
                System.out.println("\tYou are at the beginning!");
            }
        }
        if((todo & (byte)00000010) == (byte)00000010)    // print response
        {
            System.out.println(goodResponse);
        }
        if((todo & (byte)00000100) == (byte)00000100)    // pick up item
        {
            if(!command.hasSecondWord()) {
                // if there is no second word, we don't know what to take...
                System.out.println("Take what?");
            }
            else {
                String object = command.getSecondWord();
                // Try to take said item.
                you.pickUp(object);
            }
        }
        if((todo & (byte)00001000) == (byte)00001000)    // drop item
        {
            if(!command.hasSecondWord()) {
                // if there is no second word, we don't know what to drop...
                System.out.println("Drop what?");
            }
            else {
                String object = command.getSecondWord();
                // Try to drop said item.
                you.drop(object);
            }
        }
        if((todo & (byte)00010000) == (byte)00010000)    // go in that direction
        {
            if(!command.hasSecondWord()) {
                // if there is no second word, we don't know where to go...
                System.out.println("Go where?");
            }

            String direction = command.getSecondWord();

            // Try to leave current room.
            Room currentRoom = you.getRoom();
            Room nextRoom = currentRoom.getExit(direction);
        
            if (nextRoom == null) {
                System.out.println("Cannot go in that direction.");
            }
            else {
                path.push(currentRoom);
                you.setRoom(nextRoom);
                System.out.println(you.getRoom().getLongDescription());
            }
        }
        if((todo & (byte)00100000) == (byte)00100000)    // drink item
        {
            if(!command.hasSecondWord()) {
                // if there is no second word, we don't know what to drink...
                System.out.println("Drink what?");
            }
            
            String substance = command.getSecondWord();
        
            // Try to drink said item.
            if(substance.equals("blood"))
            {
                 you.drinkBlood();
            }
            else
            {
                System.out.println("I'm not sure what you mean...");
            }
        }
        if((todo & (byte)01000000) == (byte)01000000)    // print inventory
        {
            System.out.println(you.items());
        }
        if((todo & (byte)10000000) == (byte)10000000)    // look
        {
            System.out.println(you.getRoom().getLongDescription());
        }
        
        return path;
    }
}
