import java.util.ArrayList;

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
    private ArrayList<AllCommands> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new ArrayList<AllCommands>();
        validCommands.add(new AllCommands("go"));
        validCommands.add(new AllCommands("quit"));
        validCommands.add(new AllCommands("help"));
        validCommands.add(new AllCommands("look"));
        validCommands.add(new AllCommands("eat"));
        validCommands.add(new AllCommands("drink"));
        validCommands.add(new AllCommands("back"));
        validCommands.add(new AllCommands("take"));
        validCommands.add(new AllCommands("drop"));
        validCommands.add(new AllCommands("inventory"));
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(AllCommands command : validCommands)
        {
            if(command.getKeyWord().equals(aString))
            {
                return true;
            }
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    
    /**
     * Return all valid commands as a string.
     * @return The list of command words available.
     */
    public String getCommandList()
    {
        String commands = "";
        for(AllCommands command : validCommands)
        {
            commands += command.getKeyWord() + " ";
        }
        return (commands += "\n");
    }
}
