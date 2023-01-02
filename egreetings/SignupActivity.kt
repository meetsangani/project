package com.example.egreetings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.widget.Toast
import com.android.volley.Response
import com.example.egreetings.databinding.ActivitySignupBinding
import com.example.egreetings.other.Apiclient
import com.example.egreetings.other.Apiinterface
import javax.security.auth.callback.Callback

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    lateinit var apiinterface: Apiinterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivitySignupBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAlreadyAccount.setOnClickListener {

            startActivity(Intent(this,LoginActivity::class.java))
        }
        binding.btnRegister.setOnClickListener {

            var fname = binding.edtFirstName.text.toString()
            var lname = binding.edtLastName.text.toString()
            var em = binding.edtPhone.text.toString()
            var pass = binding.edtPassword.text.toString()
            var conformpassword = binding.edtConfirmPassword.text.toString()

            if(pass.equals(conformpassword))
            {
                apiinterface = Apiclient.getapiclient().create(Apiinterface::class.java)
                var registercall:Call<Void> = apiinterface.registerdetail(fname,lname,em,pass,conformpassword)
                registercall.enqueue(object :Callback<Void?>{
                    fun onResponse(call: Call<Void?>, response: Response<Void?>, )
                    {



                    }

                    @Override fun onFailure(call: Call<Void?>, t: Throwable)
                    {


                        //Toast.makeText(applicationContext, "" + t, Toast.LENGTH_LONG).show()
                        Log.d("XyzError",t.message.toString())
                    }
                })
                startActivity(Intent(this,LoginActivity::class.java))

                }
            else
            {
                Toast.makeText(applicationContext,"Your Password and confirm password are not same",Toast.LENGTH_LONG).show()
            }
        }

    }
}

