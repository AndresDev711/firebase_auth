package com.andre.dev.demo.authfirebase.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.andre.dev.demo.authfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    initListeners()
  }

  private fun initListeners() {
    with(binding) {
      btnSendCode.setOnClickListener {
        val phone = etPhone.text.toString().trim()
        if (validatePhone(phone)) {
          //firebase
        } else {
          Log.e("CHECK_PHONE", "PHONE EMPTY")
        }
      }
    }
  }

  private fun validatePhone(phone: String): Boolean {
    return phone.isNotEmpty()
  }


}