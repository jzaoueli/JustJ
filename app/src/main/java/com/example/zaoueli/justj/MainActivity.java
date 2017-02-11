package com.example.zaoueli.justj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    public static final int PRICE_PER_CUP = 5;
    private int quantity = 2;

    /**
     * String title = "Today\'s Specials";
     * String specialOfTheDay = "Caffe Latte";
     * String nutritionInfo = "500 calories or less";
     * String drinkName = "iced coffee";
     * ***
     * String orderNumber = "Order number: " + 23;
     * String barista = "You were served by " + "Jack";
     * String sneackyPromotion = "You are" + 2 + "cups away from free drink.";
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        int price = calculatePrice();

        String orderSummary = createOrderSummary(price);
        displayMessage(orderSummary);

        String ToastMessage = "Oder submitted with " + quantity + " item(s)";
        Toast.makeText(getApplicationContext(), ToastMessage, Toast.LENGTH_SHORT).show();

    }

    private String createOrderSummary(int price) {
        String summary = "Name: Mr. Muster\n";
        summary = summary + "Quantity = " + quantity + "\n";
        summary = summary + "Total: " + price + "\n";
        summary = summary + "Thank you!";
        return summary;
    }

    private int calculatePrice() {
        return quantity * PRICE_PER_CUP;
    }

    private void displayQuantity(int numberOfCoffees) {
        Log.e("display_called", " display called with number = " + numberOfCoffees);
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }


    public void increment(View view) {
        quantity++;
        displayQuantity(quantity);
        displayPrice(5 * quantity);
    }

    public void decrement(View view) {
        quantity--;
        displayQuantity(quantity);
        displayPrice(5 * quantity);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}
