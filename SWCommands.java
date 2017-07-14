import java.util.Stack;

/**
 * Commands which require a second word (and optionally a room and/or item).
 * 
 * @author Phillip Rossetti
 * @version 05/07/2017
 */
public class SWCommands extends AllCommands
{
    private String requiredRoom;    // room may be specified for a command to work.
    private String requiredItem;
    
    /**
     * Constructor for objects of class SecondWordCommands
     */
    public SWCommands(String keyWord, byte todo, String option1, String option2)
    {
        super(keyWord, todo);
        requiredRoom = option1;
        requiredItem = option2;
    }

    /**
    * Method to perform for any command.
    */
    public Stack<Room> action(Input command, Player you, Stack<Room> path)
    {
        boolean finished = false;
        if(!command.hasSecondWord())
        {
            String response = "\t" + toUpper(command.getCommandWord());
            if((super.getTodo() & 1) == 1)    // 00000001
            {
                response += " what?\n";    // e.g. Take what?
            }
            else
            {
                response += " where?\n";    // e.g. Go where?
            }
            System.out.println(response);
        }
        else
        {
            if((super.getTodo() & 2) == 2 & !finished)    // pick up item 00000010
            {
                finished = you.pickUp(command.getSecondWord());
            }
            if((super.getTodo() & 4) == 4 & !finished)    // remove item from room 00000100
            {
                you.getRoom().removeItem(command.getSecondWord());
            }
            if((super.getTodo() & 8) == 8 & !finished)    // remove item from backpack 00001000
            {
                Item removed = you.drop(command.getSecondWord());
                if(removed == null)
                {
                    finished = true;
                }
                if((super.getTodo() & 16) == 16 & !finished)    // add item to room 00010000
                {
                    you.getRoom().addItem(removed);
                }
            }
            if((super.getTodo() & 32) == 32 & !finished)    // go to room 00100000
            {
                String direction = command.getSecondWord();

                // Try to leave current room.
                Room currentRoom = you.getRoom();
                Room nextRoom = currentRoom.getExit(direction);
        
                if(nextRoom == null) {
                    System.out.println("\tCannot go in that direction.\n");
                }
                else if(nextRoom.isLocked()) {
                    System.out.println("\tThat direction is locked.\n");
                }
                else{
                    path.push(currentRoom);
                    you.setRoom(nextRoom);
                    System.out.println(you.getRoom().getLongDescription());
                }
            }
            if((super.getTodo() & 64) == 64 & !finished)    // unlock a direction 01000000
            {
                String direction = command.getSecondWord();
                Room currentRoom = you.getRoom();
                Room nextRoom = currentRoom.getExit(direction);
        
                if(nextRoom == null) {
                    System.out.println("\tThere is nothing in that direction.\n");
                }
                else if(!nextRoom.isLocked()) {
                    System.out.println("\tThat way is not locked.\n");
                }
                else if(nextRoom.getName().equals(requiredRoom) & (you.hasItem(requiredItem) == null)) {
                    System.out.println("\tYou require the \"" + requiredItem + "\" which you do not possess.\n");
                }
                else
                {
                    System.out.println("\tYour wish is my command!\n");
                    nextRoom.unlock();
                    you.drop(requiredItem);
                }  
            }
            if((super.getTodo() & 128) == 128 & !finished)    // drink item 10000000
            {
                
            }
        }
        
        return path;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String toUpper(String aString)
    {
        String firstLetter = aString.substring(0, 1);
        String capitalise = firstLetter.toUpperCase();
        
        return capitalise + aString.substring(1, aString.length());
    }
}
