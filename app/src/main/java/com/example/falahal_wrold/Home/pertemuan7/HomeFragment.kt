package com.example.falahal_wrold.Home.pertemuan7

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.falahal_wrold.AuthActivity
import com.example.falahal_wrold.MainActivity
import com.example.falahal_wrold.Message.MessageActivity
import com.example.falahal_wrold.databinding.FragmentHomeBinding
import com.example.falahal_wrold.Home.pertemuan4.Dashboard
import com.example.falahal_wrold.Home.pertemuan4.Dashboard2
import com.example.falahal_wrold.Home.pertemuan6.WebViewActivity
import com.example.falahal_wrold.data.api.DisasterApiClient
import com.example.falahal_wrold.data.model.DisasterEventModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

            btnMessage.setOnClickListener {
                startActivity(
                    Intent(requireActivity(), MessageActivity::class.java)
                )
            }

            btnRefresh.setOnClickListener {
                loadDisasterInfo()
            }

            // LOGOUT
            btnMenu4.setOnClickListener {
                showLogoutDialog()
            }
        }

        loadDisasterInfo()
    }

    private fun loadDisasterInfo() {
        binding.tvDisasterInfo.text = "Memuat info kebencanaan..."
        binding.btnRefresh.isEnabled = false

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = DisasterApiClient.apiService.getDisasterEvents()
                val event = response.events.firstOrNull()

                binding.tvDisasterInfo.text = if (event != null) {
                    formatDisasterEvent(event)
                } else {
                    "Belum ada kejadian alam aktif yang tercatat saat ini."
                }
            } catch (exception: Exception) {
                binding.tvDisasterInfo.text =
                    "Gagal mengambil info kebencanaan. Periksa koneksi internet lalu coba lagi."
            } finally {
                binding.btnRefresh.isEnabled = true
            }
        }
    }

    private fun formatDisasterEvent(event: DisasterEventModel): String {
        val category = event.categories.firstOrNull()?.title ?: "Kejadian alam"
        val date = event.geometry.firstOrNull()?.date ?: "Waktu belum tersedia"
        val title = event.title ?: "Informasi bencana terbaru"

        return "Kategori: $category\n$title\nUpdate: $date"
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
