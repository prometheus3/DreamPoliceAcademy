/**
 * This class creates an item which has both a description
 * and a weight.
 * 
 * @author Phillip Rossetti 
 * @version 08/02/2017
 */
public class Item
{
    private String name;
    private String description;
    private int weight;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, int weight)
    {
        // initialise instance variables
        this.name = name;
        this.description = description;
        this.weight = weight;
    }
    
    /**
     * Retrieve the description of the item.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Retrieve the weight of the item.
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * Return the name of the item.
     */
    public String getName()
    {
        return name;
    }
}
