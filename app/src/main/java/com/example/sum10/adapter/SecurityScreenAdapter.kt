package com.example.sum10.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sum10.R
import com.example.sum10.databinding.NumberItemBinding
import com.example.sum10.model.Number

typealias onClick = (number: Number) -> Unit

class SecurityScreenAdapter : RecyclerView.Adapter<SecurityScreenAdapter.SecurityScreenViewHolder>() {

    lateinit var clicked: onClick

    private var numbers = listOf(
        Number(1, isNumber = true),
        Number(2, isNumber = true),
        Number(3, isNumber = true),
        Number(4, isNumber = true),
        Number(5, isNumber = true),
        Number(6, isNumber = true),
        Number(7, isNumber = true),
        Number(8, isNumber = true),
        Number(9, isNumber = true),
        Number(R.drawable.ic_touchid, isTouchId =  true),
        Number(0, isNumber = true),
        Number(R.drawable.ic_backspace, isBackSpace = true))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SecurityScreenViewHolder (
        NumberItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: SecurityScreenViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount() = numbers.size

    inner class SecurityScreenViewHolder(private val binding: NumberItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: Number
        fun onBind() {
            model = numbers[adapterPosition]
            if(model.isNumber) {
                binding.tvNumber.text = model.number.toString()
            } else {
                if(model.isTouchId) {
                    binding.tvNumber.setBackgroundResource(R.drawable.ic_touchid)
                    binding.tvNumber.text = ""
                }
                else {
                    binding.tvNumber.setBackgroundResource(R.drawable.ic_backspace)
                    binding.tvNumber.text = ""
                }
            }

            binding.root.setOnClickListener {
                clicked(model)
            }
        }
    }

}