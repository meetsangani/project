package com.example.egreetings

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.DashboardActivity
import com.example.egreetings.Model.RegisterModel
import com.example.egreetings.databinding.ActivityLoginBinding
import com.example.egreetings.other.Apiclient
import com.example.egreetings.other.Apiinterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity()
{

    private lateinit var binding: ActivityLoginBinding
    lateinit var apiinterface: Apiinterface

    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        var binding: ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences=getSharedPreferences("user_session", Context.MODE_PRIVATE)


        if (sharedPreferences.getBoolean("user_session", false) && !sharedPreferences.getString("mob", "")!!.isEmpty())
        {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener()
        {
            var mobile = binding.edtMobile.text.toString()
            var pass = binding.edtPass.text.toString()

            apiinterface = Apiclient.getapiclient().create(Apiinterface::class.java)
            var call: Call<RegisterModel> = apiinterface.logindata(mobile,pass)
            call.enqueue(object: Callback<RegisterModel> {
                override fun onResponse(
                    call: Call<RegisterModel>,
                    response: Response<RegisterModel>, )
                {
                    sharedPreferences.edit().putString("mob",mobile).commit()

                    sharedPreferences.edit().putBoolean("user_session",true).commit()
                    Toast.makeText(applicationContext,"Success",Toast.LENGTH_LONG).show()

                    startActivity(Intent(applicationContext,DashboardActivity::class.java))
                }

                override fun onFailure(call: Call<RegisterModel>, t: Throwable) {
                    Toast.makeText(applicationContext,"Fail", Toast.LENGTH_LONG).show()
                }
            })

        }

        binding.btnNewUser.setOnClickListener {

            startActivity(Intent(this,SignupActivity::class.java))

        }


    }
}