@file:Suppress("DEPRECATION")

package com.example.contact

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.content.Intent
import android.provider.Settings

class MainActivity : AppCompatActivity() {
    private val list = mutableListOf<Contact>()
    private lateinit var adapter: RecyclerAdapter
    private val dbHelper = DBHelper(this)

    val REQUEST_CODE = 1

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        title = "Телефонная книга"

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringExtra(SecondActivity.RESULT_KEY)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText = findViewById<EditText>(R.id.editText)

        list.addAll(dbHelper.getAll())

        adapter = RecyclerAdapter(list) {

            val intent = Intent(this, Information::class.java)
            intent.putExtra("id", list[it].id)
            startActivityForResult(intent, REQUEST_CODE)

        }

        val recyclerView = findViewById<RecyclerView>(R.id.list_item)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        val buttonAdd = findViewById<Button>(R.id.newcontact)
        buttonAdd.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }
    }
}
