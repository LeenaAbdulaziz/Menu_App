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

class Top_100 : AppCompatActivity() {

    var apps = mutableListOf<Apps100>()
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top100)


            recyclerView = findViewById(R.id.rv)
            FetchTopApp().execute()

        }
    fun Top10App(item: MenuItem) {
        var info= Intent(this,Top_10::class.java)

        startActivity(info)
    }
    fun Top100App(item: MenuItem){
        var info= Intent(this,Top_100::class.java)

        startActivity(info)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


        private inner class FetchTopApp : AsyncTask<Void, Void, MutableList<Apps100>>() {
            val parser = Parser_100()
            override fun doInBackground(vararg params: Void?): MutableList<Apps100> {

                val url = URL("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=100/xml")

                val urlConnection = url.openConnection() as HttpURLConnection

                apps = urlConnection.inputStream?.let {

                    parser.parse(it)
                }
                        as MutableList<Apps100>
                return apps
            }

            override fun onPostExecute(result: MutableList<Apps100>?) {
               // recyclerView.isVisible=false
                super.onPostExecute(result)
                val adapter =
                    ArrayAdapter(this@Top_100, android.R.layout.simple_list_item_1, apps)
                recyclerView.adapter = RVAdapter_top100(apps)
                recyclerView.layoutManager = LinearLayoutManager(this@Top_100)

            }

        }

        fun Getfeed_100(view: View) {
            FetchTopApp().execute()
        }

    }
