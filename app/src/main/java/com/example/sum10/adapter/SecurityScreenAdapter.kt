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
        Number(1),
        Number(2),
        Number(3),
        Number(4),
        Number(5),
        Number(6),
        Number(7),
        Number(8),
        Number(9),
        Number(R.drawable.ic_touchid, false, true),
        Number(0),
        Number(R.drawable.ic_backspace, true))

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
            if(!model.touchId && !model.backSpace) {
                binding.tvNumber.text = model.number.toString()
            } else {
                if(model.touchId && !model.backSpace) {
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