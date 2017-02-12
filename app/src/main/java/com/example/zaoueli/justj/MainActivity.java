package com.example.zaoueli.justj;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    public static final int PRICE_PER_CUP = 5;
    private int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        CheckBox hasWhippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        Boolean hasWhippedCream = hasWhippedCreamCheckBox.isChecked();
        Log.i("+_+ MyTrack -_-", "hasWhippedCream = " + hasWhippedCream);

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);

        EditText nameTextView = (EditText) findViewById(R.id.name_edit_text);
        String name = nameTextView.getText().toString();

        String orderSummary = createOrderSummary(name, price, hasWhippedCream, hasChocolate);
        displayMessage(orderSummary);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order from " + name);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private String createOrderSummary(String name, int price, Boolean hasWhippedCream, Boolean hasChocolate) {
        String summary = name + "\n";
        summary = summary + "has Whipped Cream? " + hasWhippedCream + "\n";
        summary = summary + "has Chocolate? " + hasChocolate + "\n";
        summary = summary + "Quantity = " + quantity + "\n";
        summary = summary + "Total: " + price + "\n";
        summary = summary + "Thank you!";
        return summary;
    }

    private int calculatePrice(Boolean hasWhippedCream, boolean hasChocolate) {
        int BasePrice = PRICE_PER_CUP;
        if (hasWhippedCream) {
            BasePrice = BasePrice + 1;
        }
        if (hasChocolate) {
            BasePrice = BasePrice + 2;
        }
        return BasePrice * quantity;
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
        if (quantity == 100) {
            Toast.makeText(this, "you cannot order more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        displayQuantity(quantity);
        displayPrice(5 * quantity);

    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "you cannot have less than one coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        displayQuantity(quantity);
        displayPrice(5 * quantity);

    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}
