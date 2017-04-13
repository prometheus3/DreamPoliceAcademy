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
        validCommands.put("go", new AllCommands("go"));
        validCommands.put("quit", new AllCommands("quit"));
        validCommands.put("help", new AllCommands("help"));
        validCommands.put("look", new AllCommands("look"));
        validCommands.put("eat", new AllCommands("eat"));
        validCommands.put("drink", new AllCommands("drink"));
        validCommands.put("back", new AllCommands("back"));
        validCommands.put("take", new AllCommands("take"));
        validCommands.put("drop", new AllCommands("drop"));
        validCommands.put("inventory", new AllCommands("inventory"));
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return (validCommands.containsKey(aString));
        /*for(AllCommands command : validCommands)
        {
            if(command.getKeyWord().equals(aString))
            {
                return true;
            }
        }
        // if we get here, the string was not found in the commands
        return false;*/
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
