package com.example.zaoueli.justj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zaoueli.justj.R;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

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
        int price = quantity * 5;
        String priceMessage = "Total: " + price;
        priceMessage = priceMessage + "\nThank you";
        displayMessage(priceMessage);

        String message = "Oder submitted with " + quantity + " item(s)";
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    private void display(int number) {
        Log.e("display_called", " display called with number = " + number);
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void increment(View view) {
        quantity++;
        display(quantity);
        displayPrice(5 * quantity);
    }

    public void decrement(View view) {
        quantity--;
        display(quantity);
        displayPrice(5 * quantity);
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}
