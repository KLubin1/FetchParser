package com.example.fetchparser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import java.net.HttpURLConnection

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textview = findViewById<TextView>(R.id.textView1)

        val queue = Volley.newRequestQueue(this)
        val url = "https://fetch-hiring.s3.amazonaws.com/hiring.json"
        // use get metod to retrieve and store json in stringRequest
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String>{ response-> textview.text = "Response: ${response.substring(0,500)}"

        },
        Response.ErrorListener { textview.text = "No Response" })

        //now that string  request has the json, its time to parse it
        queue.add(stringRequest)
    }


}