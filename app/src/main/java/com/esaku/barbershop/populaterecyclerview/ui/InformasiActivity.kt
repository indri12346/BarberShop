package com.esaku.barbershop.populaterecyclerview.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esaku.barbershop.databinding.ActivityInformasiBinding
import com.esaku.barbershop.populaterecyclerview.adapters.InformasiAdapter
import com.esaku.barbershop.populaterecyclerview.models.ModelInformasi

class InformasiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInformasiBinding
    val dataAdapter= InformasiAdapter()
    val data=ArrayList<ModelInformasi>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformasiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        dataAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                checkEmpty()
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                checkEmpty()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                checkEmpty()
            }

            fun checkEmpty() {
                if (dataAdapter.itemCount == 0) {
                    binding.emptyView.visibility = View.VISIBLE
                    binding.recyclerview.visibility = View.GONE
                } else {
                    binding.emptyView.visibility = View.GONE
                    binding.recyclerview.visibility = View.VISIBLE
                }

            }
        })
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@InformasiActivity)
            adapter = dataAdapter
        }
        // disini ada penjelasan untuk kolomnya, ada isian untuk nobukti, nama dan tanggal
//        disini bisa kita sesuaikan datanya yang dibutuhkan apa aja

        data.add(ModelInformasi("asd","asd"))
        data.add(ModelInformasi("asd","asd"))
        dataAdapter.initData(data)
    }


}