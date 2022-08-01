package com.example.curency_converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.example.curency_converter.databinding.ActivityMainBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val database = Firebase.database
        database.reference.child("Users").setValue("5")
        if (! Python.isStarted()) {
            Python.start(AndroidPlatform(baseContext));
            execute()
        }else{
            execute()
        }
    }
    fun execute(){
        binding.button.setOnClickListener {
            val py = Python.getInstance()
            val pyobj = py.getModule("main")
//            val obj = pyobj.callAttr("main", binding.c1.text.trim(), binding.amount.text.toString().trim(), binding.c2.text.toString().trim())
            val amount:Float = binding.amount.text.toString().trim().toFloat()
            val obj = pyobj.callAttr("main", binding.c1.text.toString().trim(), amount, binding.c2.text.toString().trim())
            binding.result.text = obj.toString()
        }
    }
}