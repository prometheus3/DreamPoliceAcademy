import java.util.HashMap;
import java.util.Set;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    // an ArrayList that holds all valid command words
    private HashMap<String, AllCommands> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<String, AllCommands>();
        validCommands.put("go", new AllCommands("go",(byte)00010000));
        validCommands.put("quit", new AllCommands("quit",(byte)0));
        validCommands.put("help", new AllCommands("help",(byte)00000010));
        validCommands.put("look", new AllCommands("look",(byte)10000000));
        validCommands.put("eat", new AllCommands("eat",(byte)00000010));
        validCommands.put("drink", new AllCommands("drink",(byte)00100000));
        validCommands.put("back", new AllCommands("back",(byte)00000001));
        validCommands.put("take", new AllCommands("take",(byte)00000100));
        validCommands.put("drop", new AllCommands("drop",(byte)00001000));
        validCommands.put("inventory", new AllCommands("inventory",(byte)01000000));
        
        validCommands.get("help").setGoodResponse("Your command words are:\n" + getCommandList() + "\n");
        validCommands.get("eat").setGoodResponse("You have eaten now and you are not hungry any more.");
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return (validCommands.containsKey(aString));
    }
    
    /**
     * Return all valid commands as a string.
     * @return The list of command words available.
     */
    public String getCommandList()
    {
        String commands = "";
        Set<String> command = validCommands.keySet();
        
        for(String commandWords : command)
        {
            commands += commandWords + " ";
        }
        return (commands += "\n");
    }
    
    /**
     * Return the AllCommands instance corresponding to it's
     * key word. For use by the Game class to allow it to run
     * the command.
     * @return The command corresponding with the specific key word
     */
    public AllCommands getCommand(String command)
    {
        return (validCommands.get(command));
    }
}
