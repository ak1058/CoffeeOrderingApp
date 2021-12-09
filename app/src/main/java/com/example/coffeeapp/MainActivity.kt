package com.example.coffeeapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var spinner: Spinner
    lateinit var coffeeImage: ImageView
    lateinit var incrementBtn: Button
    lateinit var decrementBtn: Button
    lateinit var orderBtn: Button
    lateinit var quantityTextView: TextView
    lateinit var priceTextView: TextView
    lateinit var name: EditText
    lateinit var phone: EditText
    lateinit var address: EditText

    var quantity = 0
    var price = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //casting Views
        spinner = findViewById(R.id.spinner)
        coffeeImage = findViewById(R.id.coffee_imageView)
        incrementBtn = findViewById(R.id.incrementBtn)
        decrementBtn = findViewById(R.id.decrement_button)
        orderBtn = findViewById(R.id.orderBtn)
        quantityTextView = findViewById(R.id.quantityTextView)
        priceTextView = findViewById(R.id.priceTextView)
        name = findViewById(R.id.editText_name)
        phone = findViewById(R.id.editText_phone)
        address = findViewById(R.id.editText_address)

        setupSpinner()

        incrementBtn.setOnClickListener {
            increment()
            displayPrice()
        }

        decrementBtn.setOnClickListener {
            decrement()
            displayPrice()
        }

        orderBtn.setOnClickListener {
            order()
        }

    }

    // SETTING UP SPINNER CODE
    private fun setupSpinner() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.coffees, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.selectedItem

                when(selectedItem){
                    "Latte" -> {
                        coffeeImage.setImageResource(R.drawable.latte)
                        price = 90
                        quantity = 0
                        displayQuantity()
                        displayPrice()
                    }
                    "Cappuccino" -> {
                        coffeeImage.setImageResource(R.drawable.cappuccino)
                        price = 120
                        quantity = 0
                        displayQuantity()
                        displayPrice()
                    }
                    "Espresso" -> {
                        coffeeImage.setImageResource(R.drawable.espresso)
                        price = 125
                        quantity = 0
                        displayQuantity()
                        displayPrice()
                    }
                    "Macciato" -> {
                        coffeeImage.setImageResource(R.drawable.macciato)
                        price = 85
                        quantity = 0
                        displayQuantity()
                        displayPrice()
                    }
                    "Mocha" -> {
                        coffeeImage.setImageResource(R.drawable.mocha)
                        price = 75
                        quantity = 0
                        displayQuantity()
                        displayPrice()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    // increment btn function
    fun increment(){
        if (quantity in 0..9){
            quantity++
            displayQuantity()
        }
    }

    // decrement btn function
    fun decrement(){
        if (quantity > 0){
            quantity --
            displayQuantity()
        }
    }

    //displayQuantity Function
    fun displayQuantity(){
        quantityTextView.text = quantity.toString()
    }

    //priceCalculator
    fun price(): String {
        var finalPrice = price * quantity
        return finalPrice.toString()
    }

    // display the price
    fun displayPrice(){
        var price = price()
        priceTextView.text = "Rs. $price"
    }

    //order Function
    fun order(){
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
        }
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("contactus@deskmateai.tech"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order from " + (name.text))
        intent.putExtra(Intent.EXTRA_TEXT, "Click send Button for placing the order" + "\n"+ "Name: " + (name.text)
        + "\nPhone: " + (phone.text)
        + "\nAddress: " + (address.text)
        + "Thank You!!")

        startActivity(intent)
    }

}