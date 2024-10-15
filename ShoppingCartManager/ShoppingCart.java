import java.util.ArrayList;

public class ShoppingCart {
    private String customerName;
    private String currentDate;
    private ArrayList<ItemToPurchase> cartItems;

    public ShoppingCart() {
        customerName = "none";
        currentDate = "January 1, 2016";
        cartItems = new ArrayList<>();
    }

    public ShoppingCart(String customerName, String currentDate) {
        this.customerName = customerName;
        this.currentDate = currentDate;
        cartItems = new ArrayList<>();
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDate() {
        return currentDate;
    }

    public void addItem(ItemToPurchase item) {
        cartItems.add(item);
    }

    public void removeItem(String itemName) {
        boolean found = false;
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getName().equals(itemName)) {
                cartItems.remove(i);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Item not found in cart. Nothing removed.");
        }
    }

    public void modifyItem(ItemToPurchase item) {
        boolean itemFound = false;

        for (ItemToPurchase cartItem : cartItems) {
            if (cartItem.getName().equals(item.getName())) {
                itemFound = true;

                if (!item.getDescription().equals("none")) {
                    cartItem.setDescription(item.getDescription());
                }

                if (item.getPrice() != 0) {
                    cartItem.setPrice(item.getPrice());
                }

                if (item.getQuantity() != 0) {
                    cartItem.setQuantity(item.getQuantity());
                }

                break;
            }
        }

        if (!itemFound) {
            System.out.println("Item not found in cart. Nothing modified.");
        }
    }

    public int getNumItemsInCart() {
        int quantity = 0;
        for (ItemToPurchase item : cartItems) {
            quantity += item.getQuantity();
        }
        return quantity;
    }

    public int getCostOfCart() {
        int price = 0;
        for (ItemToPurchase item : cartItems) {
            price += item.getPrice() * item.getQuantity();
        }
        return price;
    }

    public void printTotal() {
        System.out.println(getCustomerName() + "'s Shopping Cart - " + getDate());
        System.out.println("Number of Items: " + getNumItemsInCart() + "\n");
        if (getNumItemsInCart() == 0) {
            System.out.println("SHOPPING CART IS EMPTY");
        }
        for (ItemToPurchase item : cartItems) {
            item.printItemCost();
        }
        System.out.println("\nTotal: $" + getCostOfCart());
    }

    public void printDescriptions() {
        if (getNumItemsInCart() == 0) {
            System.out.println("SHOPPING CART IS EMPTY");
        } else {
            System.out.println(getCustomerName() + "'s Shopping Cart - " + getDate() + "\n\nItem Descriptions");
            for (ItemToPurchase item : cartItems) {
                item.printItemDescription();
            }
        }
    }
}
