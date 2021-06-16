package com.example.fetchparser

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity()
{
    //listview
    lateinit var list:ListView
    //list of items
    var itemList:ArrayList<Item> = ArrayList()
    //url
    val url = "https://fetch-hiring.s3.amazonaws.com/hiring.json"

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list = findViewById(R.id.listView)

        //set tool bar title
        supportActionBar?.setTitle(R.string.toolBarTitle)

       // set up a request cue to call request
        val requestQueue = Volley.newRequestQueue(this)

        // use GET method to retrieve and store json in stringRequest
        val stringRequest = StringRequest(
            Request.Method.GET, url,
                { response->
                    //now that response has the json, parse it here
                    // get array from response
                    val jsonArray: JSONArray = JSONArray(response)
                    //loop through array
                    val size:Int = jsonArray.length()
                    for(i in 0 until size-1)
                    {
                        //get object from array
                        var info:JSONObject = jsonArray.getJSONObject(i)
                        // populate item details w/ json info
                        var item = Item(
                                id = info.getLong("id"),
                                listId = info.getInt("listId"),
                                name = info.getString("name")
                        )
                        // if name is "null" or "", don't add to list
                        if(item.name != "null" && item.name != "")
                            itemList.add(item)
                    }
                    // sort the results first by "listId" then by "name"
                    itemList.sortWith(compareBy({it.listId},{it.name}))

                    // pass list into list adapter and display
                    val listAdapter:ListAdapter
                    listAdapter = ListAdapter(this, itemList)
                    list.adapter = listAdapter
                    //Toast.makeText(this, "Error: No Response", Toast.LENGTH_SHORT)

            },
                {
                    Toast.makeText(this, "Error: No Response", Toast.LENGTH_SHORT).show()
                })

        requestQueue.add(stringRequest)

    }


}