package I3.Classes;

import java.util.ArrayList;

/**
 *
 * @author Faysal Ahmed
 */
public class Payment {
    
    //required Object
    private Booking booking;
    ArrayList<ExtraOrders> orders;
    int totalRentPrice;
    int daysStayed;
    private String payment_date;
    private String payment_method;
    
    private boolean hasDiscount;
    private float discount;
    
    int totalBill;
    
    public Payment(Booking b)
    {
        booking = b;
        //totalRentPrice = b.getRoomsFare() * 1;
        
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public boolean isHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
    
    
    public int calculateTotalBill()
    {
        int orderTotal = 0;
        
        for(ExtraOrders order: orders)
        {
            orderTotal += order.getQuantity() * order.getItem().getPrice();
        }
        totalBill = orderTotal+ totalRentPrice;
        
        return totalBill;
    }
    
    
    
    
    
}
