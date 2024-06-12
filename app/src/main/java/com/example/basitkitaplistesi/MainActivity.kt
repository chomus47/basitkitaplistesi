package com.example.basitkitaplistesi

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : Activity() {
    private lateinit var bookSharedPreferences: BookSharedPreferences
    private lateinit var bookAdapter: ArrayAdapter<String>
    private lateinit var bookListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookSharedPreferences = BookSharedPreferences(this)
        bookListView = findViewById(R.id.book_list_view)
        val addButton = findViewById<Button>(R.id.add_button)
        val bookTitleEditText = findViewById<EditText>(R.id.book_title_edit_text)

        addButton.setOnClickListener {
            val title = bookTitleEditText.text.toString()
            if (title.isNotEmpty()) {
                bookSharedPreferences.addBook(title)
                loadBooks()
                bookTitleEditText.text.clear()
            }
        }

        loadBooks()
    }

    private fun loadBooks() {
        val bookList = bookSharedPreferences.getBooks().toList()
        bookAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bookList)
        bookListView.adapter = bookAdapter
        bookListView.setOnItemClickListener { _, _, position, _ ->
            val book = bookAdapter.getItem(position)
            if (book != null) {
                bookSharedPreferences.removeBook(book)
                loadBooks()
            }
        }
    }
}