package com.example.sum10.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sum10.R
import com.example.sum10.adapter.SecurityScreenAdapter
import com.example.sum10.databinding.FragmentSecurityScreenBinding

class SecurityScreenFragment : Fragment() {

    private var _binding: FragmentSecurityScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var securityScreenAdapter: SecurityScreenAdapter
    private val passcode = mutableListOf<Number>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSecurityScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initRecyclerView()
        clickers()
    }

    private fun initRecyclerView() {
        securityScreenAdapter = SecurityScreenAdapter()
        binding.recyclreView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = securityScreenAdapter
        }
    }


    private fun clickers() {
        val passcodeDots = listOf<View>(binding.btn1, binding.btn2, binding.btn3, binding.btn4)
        securityScreenAdapter.clicked = {
            if (it.isNumber) {
                passcode.add(it.number)
                passcodeDots[passcode.lastIndex].setBackgroundResource(R.drawable.dots_marked)
                if (passcode.size == 4) {
                    if (checkPassword()) {
                        Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show()
                    } else {
                        passcode.clear()
                        for (i in passcodeDots) {
                            i.setBackgroundResource(R.drawable.dots_regular)
                        }
                    }
                }
//                Toast.makeText(requireContext(), it.number.toString(), Toast.LENGTH_SHORT).show()

            } else if (it.isBackSpace) {
                val numberPosition = passcode.size - 1
                if(passcode.isEmpty()) {
                } else if (numberPosition == 3) {
                    passcode.clear()
                    for (i in passcodeDots) {
                        i.setBackgroundResource(R.drawable.dots_regular)
                    }
                } else {
                    passcode.removeAt(numberPosition)
                    passcodeDots[numberPosition].setBackgroundResource(R.drawable.dots_regular)
                }

            }
        }
    }

    private fun checkPassword(): Boolean {
        if (passcode.size == 4) {
//            d("Log", passcode.toString())
            return passcode[0] == 0 && passcode[1] == 9 && passcode[2] == 3 && passcode[3] == 4
        } else {
//            d("LogElse", passcode.toString())
            return false
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}