package com.example.falahal_wrold.Home.pertemuan9.pertemuan7

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.falahal_wrold.AuthActivity
import com.example.falahal_wrold.MainActivity
import com.example.falahal_wrold.databinding.FragmentHomeBinding
import com.example.falahal_wrold.Home.pertemuan9.pertemuan4.Dashboard
import com.example.falahal_wrold.Home.pertemuan9.pertemuan4.Dashboard2
import com.example.falahal_wrold.Home.pertemuan9.pertemuan6.WebViewActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.apply {

            // BUTTON 1
            btnMenu1.setOnClickListener {
                startActivity(
                    Intent(requireActivity(), MainActivity::class.java)
                )
            }

            // BUTTON 2
            btnMenu2.setOnClickListener {
                startActivity(
                    Intent(requireActivity(), Dashboard::class.java)
                )
            }

            // BUTTON 3
            btnMenu3.setOnClickListener {
                startActivity(
                    Intent(requireActivity(), Dashboard2::class.java)
                )
            }

            // WEBVIEW
            btnWebView.setOnClickListener {

                val intent = Intent(
                    requireActivity(),
                    WebViewActivity::class.java
                )

                intent.putExtra(
                    "url",
                    "https://falahal.alwaysdata.net/"
                )

                startActivity(intent)
            }

            // LOGOUT
            btnMenu4.setOnClickListener {
                showLogoutDialog()
            }
        }

        return binding.root
    }

    private fun showLogoutDialog() {

        AlertDialog.Builder(requireActivity())
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah kamu yakin ingin logout?")
            .setPositiveButton("Ya") { _, _ ->

                startActivity(
                    Intent(requireActivity(), AuthActivity::class.java)
                )

                requireActivity().finish()
            }
            .setNegativeButton("Tidak", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

