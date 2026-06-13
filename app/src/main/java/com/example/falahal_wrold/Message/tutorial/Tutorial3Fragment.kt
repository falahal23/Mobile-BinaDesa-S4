package com.example.falahal_wrold.Message.tutorial

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.falahal_wrold.AuthActivity
import com.example.falahal_wrold.R

class Tutorial3Fragment : Fragment(R.layout.fragment_tutorial3) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val btnMulai =
            view.findViewById<Button>(R.id.btnMulai)

        btnMulai.setOnClickListener {

            startActivity(
                Intent(
                    requireContext(),
                    AuthActivity::class.java
                )
            )

            requireActivity().finish()
        }
    }
}