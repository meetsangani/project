package com.example

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.egreetings.Model.Dashboard_Model
import com.example.egreetings.MyAdapter.MyAdapter
import com.example.egreetings.R
import com.example.egreetings.databinding.ActivityDashboardBinding
import com.example.egreetings.other.Apiclient
import com.example.egreetings.other.Apiinterface
import retrofit2.Response
import javax.security.auth.callback.Callback

class DashboardActivity : AppCompatActivity()
{
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityDashboardBinding
    lateinit var recyclerView: RecyclerView
    lateinit var list: MutableList<Dashboard_Model>
    lateinit var apiinterface: Apiinterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)

        Toast.makeText(applicationContext,
            "Welcome " + sharedPreferences.getString("mob", "error"),
            Toast.LENGTH_LONG).show()

        recyclerView = findViewById(R.id.recycler)
        list = ArrayList<Dashboard_Model>()

        val rl: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 2)
        recyclerView.layoutManager = rl

        apiinterface = Apiclient.getapiclient().create(Apiinterface::class.java)

        val call = apiinterface!!.viewdata

        call!!.enqueue(object : Call<List<Dashboard_Model?>>, Callback<List<Dashboard_Model?>?>,
            retrofit2.Callback<List<Dashboard_Model?>?> {
            override fun onResponse(
                call: retrofit2.Call<List<Dashboard_Model?>?>,
                response: Response<List<Dashboard_Model?>?>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: retrofit2.Call<List<Dashboard_Model?>?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        }
}