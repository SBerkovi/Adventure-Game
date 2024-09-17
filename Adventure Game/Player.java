import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Player {
    private String name;
    private int gold;
    private LinkedList<String> collectedItems = new LinkedList<>();
    public Player(String n) {
        name = n;
        gold = 0;
    }
    public String getName() {
        return name;
    }
    public int getGold() {
        return gold;
    }
    public LinkedList<String> getItems() {
        return collectedItems;
    }
    //randomly choose an item to add to the player
    public List<String> foundItem(String item, List<String> itemList) {
        collectedItems.add(item);
        itemList.remove(item);
        return itemList;
    }
    //randomly choose an item the player loses
    public List<String> lostItem(String item, List<String> itemList) {
        itemList.add(item);
        collectedItems.remove(item);
        return itemList;
    }
    //randomly choose from 1-30 how much gold is in a chest
    public int foundGold() {
        int amountFound = (int)(Math.random()*30+1);
        gold += amountFound;
        return amountFound;
    }
    //randomly choose from 1-max how much gold the player loses
    public int lostGold() {
        int amountLost = (int)(Math.random()*gold+1);
        gold -= amountLost;
        return amountLost;
    }
}
