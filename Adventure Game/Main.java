import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        //take items from the file and put them in a list
        File inputfile = new File("items.txt");
        List<String> itemslist = new ArrayList<String>();

        try {
            Scanner input = new Scanner(inputfile);
            while (input.hasNext()) {
                itemslist.add(input.nextLine());
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Scanner sc = new Scanner(System.in);

        try {
            //opening scene
            System.out.println("Zzzzzz... Zzzzzzz...");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Wake up! Adventure awaits us!");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Welcome to The Land of Choo.");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("In this game, you will encounter many items that you can collect.");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("But be careful because you may end up losing them, too.");
            TimeUnit.SECONDS.sleep(3);

            //get player's name and create the Player object
            System.out.println("First, what is your name?");
            String name = sc.nextLine();
            Player AdventureGuy = new Player(name);

            //build up to game
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Nice to meet you " + AdventureGuy.getName() + "!");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Are you ready to set forth on your journey?");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("I'll take your silence as a yes.");

            //until the player wants to stop, the game continues
            boolean keepGoing = true;
            while(keepGoing) {
                //the player can only find gold or items until they have gold and items
                if(AdventureGuy.getGold() == 0 || AdventureGuy.getItems().isEmpty()) {
                    int event1 = (int)(Math.random()*2+1);
                    TimeUnit.SECONDS.sleep(2);
                    if(event1 == 1) {
                        System.out.println("You opened a chest and found " + AdventureGuy.foundGold() + " gold.");
                    }
                    else {
                        String item = itemslist.get((int) (Math.random() * itemslist.size()));
                        itemslist = AdventureGuy.foundItem(item, itemslist);
                        System.out.println("You encountered a " + item + " to accompany you on your journey.");
                    }
                }
                //when they have gold and items, they have a chance to lose them
                else {
                    int event2 = (int) (Math.random() * 4 + 1);
                    TimeUnit.SECONDS.sleep(2);
                    if (event2 == 1) {
                        System.out.println("You opened a chest and found " + AdventureGuy.foundGold() + " gold.");
                    } else if (event2 == 2) {
                        String item = itemslist.get((int) (Math.random() * itemslist.size()));
                        itemslist = AdventureGuy.foundItem(item, itemslist);
                        System.out.println("You encountered a " + item + " to accompany you on your journey.");
                    } else if (event2 == 3) {
                        System.out.println("You lost " + AdventureGuy.lostGold() + " gold. Oh well.");
                    } else if (event2 == 4) {
                        String item = AdventureGuy.getItems().get((int) (Math.random() * AdventureGuy.getItems().size()));
                        itemslist = AdventureGuy.lostItem(item, itemslist);
                        System.out.println("Uh oh, you lost your " + item + ". Maybe you'll find it again later.");
                    }
                }

                //ask if the player wants to continue, stop if they say no, continue if they say
                //yes and anything else, ask them again
                System.out.println("Do you wish to continue? Yes or No?");
                boolean wrongAnswer = true;
                while(wrongAnswer) {
                    String answer = sc.next();
                    if (answer.equalsIgnoreCase("Yes")) {
                        wrongAnswer = false;
                    } else if (answer.equalsIgnoreCase("No")) {
                        keepGoing = false;
                        wrongAnswer = false;
                    } else {
                        System.out.println("That's not an answer, silly. Try again.");
                    }
                }
            }

            //display what the player ended with in their collection
            TimeUnit.SECONDS.sleep(2);
            System.out.println("In the end, you collected " + AdventureGuy.getGold() + " gold.");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("You also had these items: " + AdventureGuy.getItems());
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}