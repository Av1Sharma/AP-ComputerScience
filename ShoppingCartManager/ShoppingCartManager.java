import java.util.Scanner;

public class ShoppingCartManager {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter customer's name:");
        String name = scan.nextLine();
        System.out.println("Enter today's date:");
        String date = scan.nextLine();
        System.out.println("");
        System.out.println("Customer name: " + name);
        System.out.println("Today's date: " + date);
        ShoppingCart cart = new ShoppingCart(name, date);
        System.out.println("");
        printMenu();
        while (true) {
            System.out.println("Choose an option:");
            String choice = scan.nextLine();
            if (choice.equals("q")) {
                break;
            }
            if (choice.equals("a") || choice.equals("d") || choice.equals("c") || choice.equals("i")
                    || choice.equals("o")) {
                executeMenu(choice.charAt(0), cart, scan);
                System.out.println("");
                printMenu();
            }
        }
    }

    public static void printMenu() {
        System.out.println("MENU");
        System.out.println("a - Add item to cart");
        System.out.println("d - Remove item from cart");
        System.out.println("c - Change item quantity");
        System.out.println("i - Output items' descriptions");
        System.out.println("o - Output shopping cart");
        System.out.println("q - Quit\n");
    }

    public static void executeMenu(char choice, ShoppingCart cart, Scanner scan) {
        switch (choice) {
            case 'a':
                System.out.println("ADD ITEM TO CART");
                System.out.println("Enter the item name:");
                String item = scan.nextLine();
                System.out.println("Enter the item description:");
                String description = scan.nextLine();
                System.out.println("Enter the item price:");
                int price = scan.nextInt();
                scan.nextLine();
                System.out.println("Enter the item quantity:");
                int quantity = scan.nextInt();
                scan.nextLine();
                ItemToPurchase newItem = new ItemToPurchase(item, description, price, quantity);
                cart.addItem(newItem);
                break;
            case 'd':
                System.out.println("REMOVE ITEM FROM CART");
                System.out.println("Enter name of item to remove:");
                String removeItem = scan.nextLine();
                cart.removeItem(removeItem);
                break;
            case 'c':
                System.out.println("CHANGE ITEM QUANTITY");
                System.out.println("Enter the item name:");
                String itemName = scan.nextLine();
                System.out.println("Enter the new quantity:");
                int quant = scan.nextInt();
                scan.nextLine();
                ItemToPurchase anotherOne = new ItemToPurchase();
                anotherOne.setName(itemName);
                anotherOne.setQuantity(quant);
                cart.modifyItem(anotherOne);
                break;
            case 'i':
                System.out.println("OUTPUT ITEMS' DESCRIPTIONS");
                cart.printDescriptions();
                break;
            case 'o':
                System.out.println("OUTPUT SHOPPING CART");
                cart.printTotal();
                break;
        }
    }
}
