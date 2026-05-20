package com.example.falahal_wrold.Home.pertemuan9.pertemuan10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.falahal_wrold.databinding.FragmentTabBBinding

class TabBFragment : Fragment() {

    private var _binding: FragmentTabBBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productList = mutableListOf<ProductModel>()
        for (i in 1..30) {
            productList.add(
                ProductModel(
                    "Logistik: Sembako Paket $i",
                    "Tersedia: ${10 * i} Box",
                    "https://picsum.photos/seed/logistik$i/200/200"
                )
            )
        }

        val adapter = ProductAdapter(productList) { product ->
            Toast.makeText(requireContext(), "Stok: ${product.name}", Toast.LENGTH_SHORT).show()
        }

        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProducts.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}