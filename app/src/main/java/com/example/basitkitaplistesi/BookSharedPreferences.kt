package com.example.basitkitaplistesi

import android.content.Context
import android.content.SharedPreferences

class BookSharedPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("BookListApp", Context.MODE_PRIVATE)

    fun addBook(title: String) {
        val books = getBooks().toMutableSet()
        books.add(title)
        saveBooks(books)
    }

    fun getBooks(): Set<String> {
        return sharedPreferences.getStringSet("book_list", setOf())?: setOf()
    }

    fun removeBook(title: String) {
        val books = getBooks().toMutableSet()
        books.remove(title)
        saveBooks(books)
    }

    private fun saveBooks(books: Set<String>) {
        val editor = sharedPreferences.edit()
        editor.putStringSet("book_list", books)
        editor.apply()
    }
}