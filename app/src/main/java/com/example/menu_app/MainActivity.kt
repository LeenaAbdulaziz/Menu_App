package com.example.menu_app

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    fun Top10App(item: MenuItem) {
        var info= Intent(this,Top_10::class.java)

        startActivity(info)
    }
    fun Top100App(item: MenuItem){
        var info= Intent(this,Top_100::class.java)

        startActivity(info)
    }

}