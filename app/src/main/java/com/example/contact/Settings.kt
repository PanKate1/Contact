package com.example.contact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class Settings : AppCompatActivity() {
    private val dbHelper = DBHelper(this)
    private val list = mutableListOf<Contact>()
    private lateinit var adapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        title = "Информация"

        val textViewName = findViewById<TextView>(R.id.Name)
        val textViewSurname = findViewById<TextView>(R.id.Surname)
        val textViewDR = findViewById<TextView>(R.id.bd)
        val textViewNumber = findViewById<TextView>(R.id.phoneNum)

        val buttonESC = findViewById<Button>(R.id.Back)
        buttonESC.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val buttonSave = findViewById<Button>(R.id.save)
        buttonSave.setOnClickListener {
            val title = textViewName.text.toString()
            val surname = textViewSurname.text.toString()
            val dr = textViewDR.text.toString()
            val number = textViewNumber.text.toString()
            val id = dbHelper.add(title, surname, dr, number)
            list.add(Contact(id, title, surname, dr, number))
            adapter.notifyItemInserted(list.lastIndex)
            val intent = Intent(this, Information::class.java)
            startActivity(intent)
        }
    }
}