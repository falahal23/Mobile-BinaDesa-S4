package com.example.falahal_wrold.Message

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.falahal_wrold.R
import com.example.falahal_wrold.databinding.ActivityMessageBinding

class MessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.messageFragmentContainer, MessageFragment())
                .commit()
        }
    }
}
