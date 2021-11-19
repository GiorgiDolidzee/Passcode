package com.example.sum10.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sum10.R
import com.example.sum10.adapter.SecurityScreenAdapter
import com.example.sum10.databinding.FragmentSecurityScreenBinding

class SecurityScreenFragment : Fragment() {

    private var _binding: FragmentSecurityScreenBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var securityScreenAdapter: SecurityScreenAdapter
    private val numbers = mutableListOf<Number>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentSecurityScreenBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        initRecyclerView()
        collectNumbers()
    }

    private fun initRecyclerView() {
        securityScreenAdapter = SecurityScreenAdapter()
        binding.recyclreView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = securityScreenAdapter
        }
    }


    private fun collectNumbers() {
        securityScreenAdapter.clicked = {
            numbers.add(it.number)
            binding.btn1.setImageResource(R.color.black)
            Toast.makeText(requireContext(), it.number.toString(), Toast.LENGTH_SHORT).show()
        }

    }

//    private fun checkPassword() : Boolean {
//        if(numbers.size == 4) {
//            d("Log", numbers.toString())
//            return numbers[0] == 0 && numbers[1] == 9 && numbers[2] == 3 && numbers[3] == 4
//         } else {
//            d("LogElse", numbers.toString())
//             return false
//        }
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}