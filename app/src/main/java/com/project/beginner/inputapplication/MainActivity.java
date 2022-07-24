package com.project.beginner.inputapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    boolean hasChocolate = false;
    boolean hasWhippedCream = false;
    String getMailValue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Setting an on click listener for the button billing details
        Button btn_email = findViewById(R.id.sendMailButton);

        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                composeEmail();
            }
        });
        */
    }

    /**
    * This method is used for incrementing the value by 1
    */
    public void increment(View view) {
        quantity++;
        display(quantity);
    }

    /**
     * This method is used for decrementing the view by 1
     */
    public void decrement(View view) {
        quantity--;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        TextView order = (TextView) findViewById(R.id.orderButton);

        if (quantity > 0 && quantity <= 100) {
            if (hasWhippedCream == true && hasChocolate == true) {
                displayPrice(quantity * (640 + 90));
                order.setText("Ordered successfully");
            } else if (hasWhippedCream == true && hasChocolate == false) {
                displayPrice(quantity * (640 + 40));
                order.setText("Ordered successfully");
            } else if (hasWhippedCream == false && hasChocolate == true) {
                displayPrice(quantity * (640 + 50));
                order.setText("Ordered successfully");
            } else {
                displayPrice(quantity * 640);
                order.setText("Ordered successfully");
            }
        } else if (quantity == 0) {
            order.setText("Quantity value can't be empty");
        } else {
            order.setText("Quantity can't be negative");
         }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.number);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.priceValue);
        priceTextView.setText("Name : " + displayName() + " \n" +
                "Quantity " + quantity + "\n" +
            displayHasCream() + "\n" +
                displayHasChocolate() + "\n" +
        "Total: " + number + ".rs\n" + "Thank You!");

        getMailValue = priceTextView.getText().toString();

    }

    public String displayHasCream() {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whippedCream);
        hasWhippedCream = whippedCream.isChecked();
        String cream = null;

        if (hasWhippedCream == true) {
            cream = "Cream is added.";
        } else {
            cream = "Continuing without adding the whipped cream.";
        }

        return cream;
    }

    public String displayHasChocolate() {
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        hasChocolate = chocolate.isChecked();
        String cream = null;

        if (hasChocolate == true) {
            cream = "Chocolate is added.";
        } else {
            cream = "Continuing without adding the chocolate.";
        }

        return cream;
    }

    public String displayName() {
        EditText name = (EditText) findViewById(R.id.editName);
        String nameValue = name.getText().toString();
        return nameValue;
    }

    public void composeEmail(View view) {

        // To retrieve recipient mail with the help of EditText editmail
        TextView sendmail = (EditText) findViewById(R.id.editEmail);
        String recipientEmail = sendmail.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, recipientEmail);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Thank you for ordering with us.");
        intent.putExtra(Intent.EXTRA_TEXT, getMailValue);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}

