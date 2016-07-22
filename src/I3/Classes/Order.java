package I3.Classes;

/**
 *
 * @author Faysal
 */
public class Order {
    
    int orderId;
    int bookingId;
    String foodItem;
    int price;
    int quantity;
    int total;

    public Order(int b,String f,int p,int q, int t)
    {
        bookingId = b;
        foodItem = f;
        price = p;
        quantity = q;
        total = t;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
}
