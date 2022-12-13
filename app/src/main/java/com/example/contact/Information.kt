package com.example.contact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView

class Information : AppCompatActivity() {
    private val dbHelper = DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        title = "Информация"

        val id = intent.getLongExtra("id", 0)
        val item = dbHelper.getById(id)
        println(item)
        val textViewName = findViewById<TextView>(R.id.name)
        textViewName.text = item?.title
        val textViewSurname = findViewById<TextView>(R.id.surname)
        textViewSurname.text = item?.surname
        val textViewDR = findViewById<TextView>(R.id.birthD)
        textViewDR.text = item?.dr
        val textViewNumber = findViewById<TextView>(R.id.PhoneN)
        textViewNumber.text = item?.number


        val buttonESC = findViewById<Button>(R.id.back)
        buttonESC.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val buttonRED = findViewById<Button>(R.id.red)
        buttonRED.setOnClickListener {

            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }
        val buttonDelete = findViewById<Button>(R.id.del)
        buttonDelete.setOnClickListener {
            dbHelper.remove(id)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}