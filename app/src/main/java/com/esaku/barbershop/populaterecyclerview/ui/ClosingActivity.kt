package com.esaku.barbershop.populaterecyclerview.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.esaku.barbershop.databinding.ActivityClosingBinding
import com.esaku.barbershop.populaterecyclerview.adapters.TransaksiAdapter
import com.esaku.barbershop.populaterecyclerview.apihelper.ApiClient
import com.esaku.barbershop.populaterecyclerview.intToCurrency
import com.esaku.barbershop.populaterecyclerview.models.ArrkunjItem
import com.esaku.barbershop.populaterecyclerview.models.ModelInformasi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mazenrashed.printooth.Printooth
import com.mazenrashed.printooth.data.printable.Printable
import com.mazenrashed.printooth.data.printable.RawPrintable
import com.mazenrashed.printooth.data.printable.TextPrintable
import com.mazenrashed.printooth.data.printer.DefaultPrinter
import com.mazenrashed.printooth.ui.ScanningActivity
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*
//import kotlin.collections.ArrayList

class ClosingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClosingBinding
    val dataAdapter= TransaksiAdapter()
    val data=ArrayList<ModelInformasi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClosingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@ClosingActivity)
            adapter = dataAdapter
        }


        val intent = intent
        val args = intent.getBundleExtra("BUNDLE")
        val data = args!!.getSerializable("ARRAYLIST") as ArrayList<ModelInformasi>?

        Printooth.init(this)

        binding.buttondata.setOnClickListener {
            startActivityForResult(Intent(this, ScanningActivity::class.java), ScanningActivity.SCANNING_FOR_PRINTER)
        }


        binding.btnclose.setOnClickListener {
            var printables = ArrayList<Printable>()
            printables.add(
                TextPrintable.Builder()
                    .setText("BARBERSHOP")
                    .setNewLinesAfter(1)
                    .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                    .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                    .build()
            )
            printables.add(
                TextPrintable.Builder()
                    .setNewLinesAfter(1)
                    .setText("Pesona Bali Residence Blok D4 Nomor 7")
                    .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                    .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                    .build()
            )
            printables.add(
                TextPrintable.Builder()
                    .setText(" (022) 87791374")
                    .setNewLinesAfter(1)
                    .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                    .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                    .build()
            )
            printables.add(
                TextPrintable.Builder()
                    .setText(" Customer : SUKIMAN")
                    .setNewLinesAfter(1)
                    .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
                    .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                    .build()
            )
            printables.add(
                TextPrintable.Builder()
                    .setText("==============================")
                    .setNewLinesAfter(1)
                    .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
                    .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                    .build()
            )

            val currentDate: String =
                SimpleDateFormat("dd-MM-yyyy, HH:mm:ss", Locale.getDefault()).format(Date())
            printables.add(
                TextPrintable.Builder()
                    .setText(currentDate)
                    .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
                    .setNewLinesAfter(1)
                    .build()
            )

            printables.add(
                TextPrintable.Builder()
                    .setText("Kode Barber ")
                    .setNewLinesAfter(1)
                    .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
                    .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                    .build()
            )
            printables.add(
                TextPrintable.Builder()
                    .setText("==============================")
                    .setNewLinesAfter(1)
                    .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
                    .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                    .build()
            )
            printables.add(
                TextPrintable.Builder()
                    .setText("Total")
                    .setNewLinesAfter(1)
                    .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
                    .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                    .build()
            )

            printables.add(
                TextPrintable.Builder()
                    .setText("TERIMA KASIH ATAS KUNJUNGAN ANDA")
                    .setNewLinesAfter(1)
                    .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
                    .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                    .build()
            )
//            for (i in 0 until data.size - 1){
//                val current = data [i]
//                var j = i + 1
//                while (j <= data.size - 1){
//                    val compare = data[j]
//                    if (current.kodeTransaksi!!.equals(compare.kodeTransaksi) && current.kodeTransaksi!!.equals(compare.kodeTransaksi)){
//                        current.nilaiTransaksi = current.nilaiTransaksi + compare.nilaiTransaksi
//                        data.remove(compare)
//                        j--
//                    }
//                    j++
//                }
//            }
            if (data != null) {
                for (data in data) {
                    printables.add(
                        TextPrintable.Builder()
                            .setText("kodeTransaksi  : + ${data.kodeTransaksi}")
                            .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
                            .setNewLinesAfter(1)
                            .build()
                    )
        //                printables.add(
        //                    TextPrintable.Builder()
        //                        .setText(" ")
        //                        .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
        //                        .setNewLinesAfter(1)
        //                        .build()
        //                )
                    printables.add(
                        TextPrintable.Builder()
                            .setText("nilaiTransaksi  : + ${data.nilaiTransaksi}")
                            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
                            .setNewLinesAfter(1)
                            .build()
                    )
                }
            }

//            printables.add(
//                TextPrintable.Builder()
//                    .setText(" ")
//                    .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
//                    .setNewLinesAfter(1)
//                    .build()
//            )
            printables.add(
                TextPrintable.Builder()
                    .setText("==============================")
                    .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                    .setNewLinesAfter(1)
                    .build()
            )
//            printables.add(
//                TextPrintable.Builder()
//                    .setText(" ")
//                    .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
//                    .setNewLinesAfter(1)
//                    .build()
//            )

//            printables.add(
//                TextPrintable.Builder()
//                    .setText("Total : $total")
//                    .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
//                    .setNewLinesAfter(1)
//                    .build()
//            )

//            printables.add(
//                TextPrintable.Builder()
//                    .setText(" ")
//                    .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
//                    .setNewLinesAfter(1)
//                    .build()
//            )


//            var printable = TextPrintable.Builder()
//                .setText("Hello World")
//            printables.add(printable)
            Printooth.printer().print(printables)

        }


//        val challenge: java.util.ArrayList<ModelInformasi>? = this.intent.extras?.getParcelableArrayList<ModelInformasi>("Birds")
//
//
//        if (challenge != null) {
//            dataAdapter.initData(challenge)
//        } // <-- ini untuk nampilin data
//

//        binding.buttondata.setOnClickListener {
//            dataAdapter.addData(arrayListOf(ModelInformasi("93747-3975-9320750","Rp30000"))) <-- ini untuk isi adapter
//        }

//TODO        ini udah keinstall kan ? BELUM AKU CEK HP NYA KEPENUHAN OKE
//        suara laptopnya masih di mute

        binding.btnclose.setOnClickListener {
            val intent = Intent(this@ClosingActivity, Closing2Activity::class.java)
            startActivity(intent)
        }
        val currentDate: String =
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        gettrans(tanggal = currentDate, kodeBarber = "TESBRB01")
    }
    fun gettrans(tanggal: String?, kodeBarber: String?) {
        val utilsapi= ApiClient().getApiService(this)
        utilsapi?.transaksi(tanggal, kodeBarber)?.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(
                call: Call<ResponseBody?>,
                response: Response<ResponseBody?>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val obj = JSONObject(response.body()!!.string())
                        val gson = Gson()
                        val myobj: JSONObject = obj.getJSONObject("success")
                        val type: Type = object :
                            TypeToken<java.util.ArrayList<ArrkunjItem>?>() {}.type
                        val dataMapel: java.util.ArrayList<ArrkunjItem> =
                            gson.fromJson(myobj.optString("arrkunj"), type)
//                        val data=response.body()!!.arrkunj
//                        if (data != null) {
                        dataAdapter.initData(dataMapel)
                        var nilai = 0
                        for (i in 0 until dataMapel.size)
                        {
                            if (dataMapel[i].total!=null){
                                nilai+= dataMapel[i].total!!
                            }
                        }

                        binding.input.text= intToCurrency(nilai)
//                        dataMapel[0].arrkunj?.let { dataAdapter.initData(it) }
//                        }else{
//                            Toast.makeText(this@ClosingActivity, "Terjadi kesalahan", Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                    prefe7rences.saveToken(modelLogin.body()!!.token.toString())
//                    preferences.saveTokenType(modelLogin.body()!!.tokenType.toString())
//                    preferences.saveLogStatus(true)
//                    val intent = Intent(this@ClosingActivity, ClosingActivity::class.java)
//                    startActivity(intent)
//                    finish()
                    } else {
                        Toast.makeText(this@ClosingActivity, "Terjadi kesalahan", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else if (response.code() == 422) {
                    Toast.makeText(
                        this@ClosingActivity,
                        "Username/Password masih kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (response.code() == 401) {
                    Toast.makeText(
                        this@ClosingActivity,
                        "Username/Password salah",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (response.code() == 403) {
                    Toast.makeText(this@ClosingActivity, "Token Invalid", Toast.LENGTH_SHORT).show()
                } else if (response.code() == 404 || response.code() == 405) {
                    Toast.makeText(
                        this@ClosingActivity,
                        "Terjadi kesalahan server",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(this@ClosingActivity, "Koneksi Bermasalah", Toast.LENGTH_SHORT).show()
            }
        })
    }

//    override fun onBackPressed() {
//        val builder = AlertDialog.Builder(this)
//        builder.setMessage("Yakin ingin keluar?")
//        builder.setCancelable(true)
//        builder.setPositiveButton("Ya") { _, _ -> super.onBackPressed() }
//        builder.setNegativeButton("Tidak") { _, _ ->
//
//        }
//        val dialog = builder.create()
//        dialog.show()
//    }

}
