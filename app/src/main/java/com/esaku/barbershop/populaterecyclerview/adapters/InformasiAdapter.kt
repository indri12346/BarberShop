package com.esaku.barbershop.populaterecyclerview.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esaku.barbershop.databinding.ListPelangganBinding
import com.esaku.barbershop.populaterecyclerview.models.ModelInformasi
import java.util.*

class InformasiAdapter : RecyclerView.Adapter<InformasiAdapter.NamaKelompokViewHolder>() {
    val link="https://api.simkug.com"
    private var dataArray= mutableListOf<ModelInformasi>()
    private val dataTemporary= mutableListOf<ModelInformasi>()
    lateinit var context: Context
    fun addData(data:MutableList<ModelInformasi>){
        dataArray.addAll(data)
        notifyDataSetChanged()
    }
    fun initData(data: ArrayList<ModelInformasi>){
        dataArray.clear()
        dataTemporary.clear()
        dataArray.addAll(data)
        dataTemporary.addAll(data)
        notifyDataSetChanged()
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamaKelompokViewHolder {
        val binding = ListPelangganBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NamaKelompokViewHolder(binding)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: NamaKelompokViewHolder, position: Int) {
//        with(holder){
//            with(dataArray[position]) {
//
//            }
//        }
        holder.binding.number.text=(position+1).toString()
        holder.binding.kodeTransaksi.text=dataArray[position].kodeTransaksi
        holder.binding.nilaiTransaksi.text=dataArray[position].nilaiTransaksi

    }

    override fun getItemCount(): Int {
        return dataArray.size
    }

    inner class NamaKelompokViewHolder(val binding: ListPelangganBinding)
        :RecyclerView.ViewHolder(binding.root)


}