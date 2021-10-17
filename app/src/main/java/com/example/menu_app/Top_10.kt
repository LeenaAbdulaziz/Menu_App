package com.example.menu_app

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.HttpURLConnection
import java.net.URL

class Top_10 : AppCompatActivity() {
    var apps = mutableListOf<Apps>()
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top10)
        recyclerView = findViewById(R.id.rv)


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


    private inner class FetchTopApps : AsyncTask<Void, Void, MutableList<Apps>>() {
        val parser = Parser_10()
        override fun doInBackground(vararg params: Void?): MutableList<Apps> {

            val url =
                URL("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml")

            val urlConnection = url.openConnection() as HttpURLConnection

            apps = urlConnection.inputStream?.let {

                parser.parse(it)
            }
                    as MutableList<Apps>
            return apps
        }

        override fun onPostExecute(result: MutableList<Apps>?) {

            super.onPostExecute(result)
            val adapter =
                ArrayAdapter(this@Top_10, android.R.layout.simple_list_item_1, apps)
            recyclerView.adapter = RVAdapter_top10(apps)
            recyclerView.layoutManager = LinearLayoutManager(this@Top_10)

        }

    }

    fun Getfeed(view: View) {
        FetchTopApps().execute()
    }
}