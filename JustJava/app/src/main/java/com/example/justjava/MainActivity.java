package com.example.justjava;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int quantity = 0;
    private int priceTea = 5;
    private int priceMilk = 1;
    private int priceCake = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        displayMessage(createOrderSummary(priceTea));
    }

    public void increment(View view) {
        displayQuantity(++quantity);
    }

    public void decrement(View view) {
        if (quantity == 0) {
            displayQuantity(quantity);
            Toast toast = Toast.makeText(getApplicationContext(), "Нельзя заказать меньше 1 чашки чая!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        else
            displayQuantity(--quantity);
    }

    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        String message = "" + quantity;
        quantityTextView.setText(message);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.price_text_view);
        orderSummaryTextView.setText(message);
    }

    private int calculatePrice(int quantity) {
        CheckBox checkBoxMilk = (CheckBox) findViewById(R.id.check_box_milk);
        CheckBox checkBoxCake = (CheckBox) findViewById(R.id.check_box_cheesecake);
        int price = quantity * 5;
        if (checkBoxMilk.isChecked())
            price += quantity * priceMilk;
        if (checkBoxCake.isChecked())
            price += quantity * priceCake;
        return price;
    }

    private String createOrderSummary(int price) {
        EditText name = (EditText) findViewById(R.id.name_edit_text);
        String message = "Name: " + name.getText() + "\n" + "Quantity: " + quantity + "\nTotal: $" + calculatePrice(quantity) + "\nThank You!";
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("*/*");
//        intent.putExtra(Intent.EXTRA_EMAIL, "tea_market@yandex.ru");
//        intent.putExtra(Intent.EXTRA_STREAM, message);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
        return message;
    }

}