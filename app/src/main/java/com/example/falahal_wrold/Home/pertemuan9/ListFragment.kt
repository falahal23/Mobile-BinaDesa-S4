package com.example.falahal_wrold.Home.pertemuan9

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.falahal_wrold.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: LaporanAdapter

    // =========================
    // DATA LAPORAN
    // =========================

    private val laporanList = listOf(
        LaporanModel("Banjir Desa Mekar", "Air mulai memasuki rumah warga sejak dini hari", "https://images.unsplash.com/photo-1547683905-f686c993aae5"),
        LaporanModel("Longsor Bukit Raya", "Material tanah menutup akses jalan utama desa", "https://images.unsplash.com/photo-1506744038136-46273834b3fb"),
        LaporanModel("Banjir Sungai Utara", "Debit air meningkat akibat hujan deras", "https://images.unsplash.com/photo-1527489377706-5bf97e608852"),
        LaporanModel("Longsor Lereng Selatan", "Beberapa rumah warga terdampak longsor", "https://images.unsplash.com/photo-1469474968028-56623f02e42e"),
        LaporanModel("Kebakaran Hutan", "Petugas sedang melakukan pemadaman api", "https://images.unsplash.com/photo-1511497584788-876760111969"),
        LaporanModel("Evakuasi Warga", "Tim SAR mulai melakukan proses evakuasi", "https://images.unsplash.com/photo-1521295121783-8a321d551ad2"),
        LaporanModel("Banjir Area Persawahan", "Lahan pertanian warga mulai terendam", "https://images.unsplash.com/photo-1473773508845-188df298d2d1"),
        LaporanModel("Retakan Tanah", "Warga diminta menjauhi area rawan longsor", "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee"),
        LaporanModel("Posko Darurat Dibuka", "Posko bantuan sementara sudah tersedia", "https://images.unsplash.com/photo-1521737604893-d14cc237f11d"),
        LaporanModel("Status Desa Siaga", "Masyarakat diminta tetap waspada cuaca ekstrem", "https://images.unsplash.com/photo-1500375592092-40eb2168fd21")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(laporanList)
    }

    // =========================
    // RECYCLER VIEW
    // =========================

    private fun setupRecyclerView(data: List<LaporanModel>) {
        adapter = LaporanAdapter(ArrayList(data))
        binding.recyclerLaporan.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerLaporan.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
