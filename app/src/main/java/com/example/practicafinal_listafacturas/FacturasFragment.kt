package com.example.practicafinal_listafacturas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicafinal_listafacturas.databinding.FragmentFacturasBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FacturasFragment : Fragment() {

    private var _binding: FragmentFacturasBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FacturasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFacturasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FacturasAdapter()
        binding.rvListFact.layoutManager = LinearLayoutManager(requireContext())
        binding.rvListFact.adapter = adapter

        obtenerFacturas()
    }

    private fun obtenerFacturas() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://z3jje.wiremockapi.cloud/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIservice::class.java)

        lifecycleScope.launch {
            try {
                val response = retrofit.getFacturas()
                if (response.isSuccessful) {
                    response.body()?.let {
                        adapter.actualizarLista(it.facturas)
                    }
                } else {
                    Toast.makeText(requireContext(), "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error de red", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
