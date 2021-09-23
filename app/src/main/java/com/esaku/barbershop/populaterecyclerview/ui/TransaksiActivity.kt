package com.esaku.barbershop.populaterecyclerview.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.esaku.barbershop.databinding.ActivityTransaksiBinding
import com.esaku.barbershop.populaterecyclerview.Preferences
import com.esaku.barbershop.populaterecyclerview.adapters.TransaksiAdapter
import com.esaku.barbershop.populaterecyclerview.apihelper.ApiClient
import com.esaku.barbershop.populaterecyclerview.intToCurrency
import com.esaku.barbershop.populaterecyclerview.models.ModelInformasi
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TransaksiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransaksiBinding
    var total = 0
    var jmlhanak = 0
    var jmlhdewasa = 0
    var jmlhcuci = 0
    var jmlhcukur = 0
    var nocust:String? = null
    val data = ArrayList<ModelInformasi>()
    //    lateinit var skeleton: Skeleton
    var preferences = Preferences()
    val dataAdapter = TransaksiAdapter()


//    private fun onDataLoaded() {
//        skeleton.showOriginal()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransaksiBinding.inflate(layoutInflater)
        val view = binding.root
        preferences.setPreferences(this)
        setContentView(view)


//        gettrans(tanggal = "2020/10/12", kodeBarber = "TESBRB01")

//        binding.total.setOnClickListener {
//            total = 10000
//            binding.total.text = total.toString()
//        }
        binding.btnminAnak.setOnClickListener {
            total -= 10000
            jmlhanak -= 1
            binding.total.text = intToCurrency(total)
            binding.jmlhAnak.text = jmlhanak.toString()
            data.removeAt(0)
            if (jmlhanak == 0) {
                binding.layoutTbhkrgAnak.visibility = View.GONE
                binding.jmlhAnak.visibility = View.GONE
            }

        }
        binding.menuPotAnak.setOnClickListener {
            if (jmlhanak==0){
                jmlhanak = 1
                total = 10000
                binding.total.text = intToCurrency(total)
                binding.layoutTbhkrgAnak.visibility = View.VISIBLE
                binding.jmlhAnak.visibility = View.VISIBLE
                binding.jmlhAnak.text = jmlhanak.toString()
            }
        }

        binding.btnplusAnak.setOnClickListener {
            total  += 10000
            jmlhanak += 1
            binding.total.text = intToCurrency(total)
            binding.jmlhAnak.text = jmlhanak.toString()
            data.add(ModelInformasi("93746-3975-9320750", "Rp15000"))
        }
        binding.btnminDewasa.setOnClickListener {
            total -= 15000
            jmlhdewasa -= 1
            binding.total.text = intToCurrency(total)
            binding.jmlhDewasa.text = jmlhdewasa.toString()
            data.removeAt(0)
            if (jmlhdewasa == 0) {
                binding.layoutTmbhkrngDewasa.visibility = View.GONE
                binding.jmlhDewasa.visibility = View.GONE
            }
        }

        binding.menuPotDewasa.setOnClickListener {
            if (jmlhdewasa==0) {
                jmlhdewasa = 1
                total = 15000
                binding.total.text = intToCurrency(total)
                binding.layoutTmbhkrngDewasa.visibility = View.VISIBLE
                binding.jmlhDewasa.visibility = View.VISIBLE
                binding.jmlhDewasa.text = jmlhdewasa.toString()
            }
        }

        binding.btnplusDewasa.setOnClickListener {
            total += 15000
            jmlhdewasa += 1
            binding.total.text = intToCurrency(total)
            binding.jmlhDewasa.text = jmlhdewasa.toString()
            data.add(ModelInformasi("93746-3975-9320750", "Rp15000"))
        }
        binding.btnminCuci.setOnClickListener {
            total -= 12000
            jmlhcuci -= 1
            binding.total.text = intToCurrency(total)
            binding.jmlhCuci.text = jmlhcuci.toString()
            data.removeAt(0)
            if (jmlhcuci == 0) {
                binding.layoutTmbhkrngCuci.visibility = View.GONE
                binding.jmlhCuci.visibility = View.GONE
            }
        }
        binding.menuCuci.setOnClickListener {
            if (jmlhcuci==0) {
                jmlhcuci = 1
                total = 12000
                binding.total.text = intToCurrency(total)
                binding.layoutTmbhkrngCuci.visibility = View.VISIBLE
                binding.jmlhCuci.visibility = View.VISIBLE
                binding.jmlhCuci.text = jmlhcuci.toString()
            }
        }


        binding.btnplusCuci.setOnClickListener {
            total += 12000
            jmlhcuci += 1
            binding.jmlhCuci.text = jmlhcuci.toString()
            binding.total.text = intToCurrency(total)
            data.add(ModelInformasi("93746-3975-9320750", "Rp15000"))
        }
        binding.btnminCukur.setOnClickListener {
            total -= 25000
            jmlhcukur -= 1
            binding.total.text = intToCurrency(total)
            binding.jmlhCukur.text = jmlhcukur.toString()
            data.removeAt(0)
            if (jmlhcukur == 0) {
                binding.layoutTmhkrngCukur.visibility = View.GONE
                binding.jmlhCukur.visibility = View.GONE
            }
            binding.total.text = total.toString()
            binding.btnminCukur.visibility = View.VISIBLE
        }

        binding.menuCukur.setOnClickListener {
            if (jmlhcukur==0) {
                jmlhcukur = 1
                total = 25000
                binding.total.text = intToCurrency(total)
                binding.layoutTmhkrngCukur.visibility = View.VISIBLE
                binding.jmlhCukur.visibility = View.VISIBLE
                binding.jmlhCukur.text = jmlhcukur.toString()
            }
        }

        binding.btnplusCukur.setOnClickListener {
            total += 25000
            jmlhcukur += 1
            binding.jmlhCukur.text = jmlhcukur.toString()
            binding.total.text = intToCurrency(total)
            data.add(ModelInformasi("93746-3975-9320750", "Rp15000"))
        }


//        binding.btnmin.visibility = View.VISIBLE // ini buat munculin
//        binding.btnmin.visibility = View.GONE // ini buat ngilangin


        binding.btnctk.setOnClickListener {
            val currentDate: String =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            kunj(tanggal = currentDate, kode_barber = "TESBRB01", nik_user="test1", kode_cust = "TESCT01", nama = "Test nama customer", no_hp = "0002", kode_paket = "TESPKT001", alamat = "Test alamat customer", nilai = total, diskon = 0)

        }





    }
    fun getNoCust(){
        val apiClient = ApiClient().getApiService(this)
        apiClient?.cust()?.enqueue(object  : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    if(response.body() != null){
                        try {
                            val obj = JSONObject(response.body()!!.string())
                            val myobj = JSONArray(obj.optString("data"))
                            for (counter in 0 until myobj.length()){
                                val jsonObject = myobj.getJSONObject(counter)
                                nocust = jsonObject.optString("kode_cust")
                            }

                        }catch (e : Exception){
                            e.printStackTrace()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@TransaksiActivity, "Ada permasalahan ", Toast.LENGTH_SHORT).show()
            }

        })
    }


    //post ke api
    fun kunj(tanggal: String?, kode_barber: String?, nik_user:String?, kode_paket:String?, nama:String?, kode_cust: String?,alamat: String?, no_hp: String?,nilai:Int?, diskon:Int?) {
        val utilsapi= ApiClient().getApiService(this)
        utilsapi?.kunj(tanggal =tanggal , kode_barber = kode_barber,nik_user =nik_user,nama=nama,kode_paket=kode_paket,kode_cust=kode_cust,alamat=alamat,no_hp=no_hp,nilai=nilai,diskon=diskon)?.enqueue(
            object : Callback<ResponseBody?> {
            override fun onResponse(
                call: Call<ResponseBody?>,
                response: Response<ResponseBody?>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val obj = JSONObject(response.body()!!.string())
                        val myobj = obj.optString("message")
                        if (myobj=="Data Kunjungan berhasil disimpan"){
                            val intent = Intent(this@TransaksiActivity, ClosingActivity::class.java)
                            val args = Bundle()
                            args.putSerializable("ARRAYLIST", data as Serializable)
                            intent.putExtra("BUNDLE", args)
                            startActivity(intent)
                            finishAffinity()
                        }
//                        val type: Type = object :
//                            TypeToken<java.util.ArrayList<ModelClosing>?>() {}.type
//                        val dataMapel: java.util.ArrayList<ArrkunjItem> =
//                            gson.fromJson(myobj.optString("arrkunj"), type)
////                        val data=response.body()!!.arrkunj
////                        if (data != null) {
//                        dataAdapter.initData(dataMapel)
//                        dataMapel[0].arrkunj?.let { dataAdapter.initData(it) }
//                        }else{
//                            Toast.makeText(this@TransaksiActivity, "Terjadi kesalahan", Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                    prefe7rences.saveToken(modelLogin.body()!!.token.toString())
//                    preferences.saveTokenType(modelLogin.body()!!.tokenType.toString())
//                    preferences.saveLogStatus(true)
//                    val intent = Intent(this@TransaksiActivity, TransaksiActivity::class.java)
//                    startActivity(intent)
//                    finish()
                    } else {
                        Toast.makeText(this@TransaksiActivity, "Terjadi kesalahan", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else if (response.code() == 422) {
                    Toast.makeText(
                        this@TransaksiActivity,
                        "Username/Password masih kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (response.code() == 401) {
                    Toast.makeText(
                        this@TransaksiActivity,
                        "Username/Password salah",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (response.code() == 403) {
                    Toast.makeText(this@TransaksiActivity, "Token Invalid", Toast.LENGTH_SHORT).show()
                } else if (response.code() == 404 || response.code() == 405) {
                    Toast.makeText(
                        this@TransaksiActivity,
                        "Terjadi kesalahan server",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(this@TransaksiActivity, "Koneksi Bermasalah", Toast.LENGTH_SHORT).show()
            }
        })
    }


//
}



//    override fun onBackPressed() {
//        val builder = AlertDialog.Builder(this)
//        builder.setMessage("Yakin ing
//
//        in keluar?")
//        builder.setCancelable(true)
//        builder.setPositiveButton("Ya") { _, _ -> super.onBackPressed() }
//        builder.setNegativeButton("Tidak") { _, _ ->
//
//        }
//        val dialog = builder.create()
//        dialog.show()
//    }
//
//    fun gettrans(tanggal: String?, kodeBarber: String?) {
//        val utilsapi= ApiClient().getApiService(this)
//        utilsapi?.transaksi(tanggal, kodeBarber)?.enqueue(object : Callback<ResponseBody?> {
//            override fun onResponse(
//                call: Call<ResponseBody?>,
//                response: Response<ResponseBody?>
//            ) {
//                if (response.isSuccessful) {
//                    if (response.body() != null) {
//                        val data=response.body()!!.arrkunj
////                    prefe7rences.saveToken(modelLogin.body()!!.token.toString())
////                    preferences.saveTokenType(modelLogin.body()!!.tokenType.toString())
////                    preferences.saveLogStatus(true)
////                    val intent = Intent(this@TransaksiActivity, HomeActivity::class.java)
////                    startActivity(intent)
////                    finish()
//                    } else {
//                        Toast.makeText(this@TransaksiActivity, "Terjadi kesalahan", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                } else if (response.code() == 422) {
//                    Toast.makeText(
//                        this@TransaksiActivity,
//                        "Username/Password masih kosong",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                } else if (response.code() == 401) {
//                    Toast.makeText(
//                        this@TransaksiActivity,
//                        "Username/Password salah",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                } else if (response.code() == 403) {
//                    Toast.makeText(this@TransaksiActivity, "Token Invalid", Toast.LENGTH_SHORT).show()
//                } else if (response.code() == 404 || response.code() == 405) {
//                    Toast.makeText(
//                        this@TransaksiActivity,
//                        "Terjadi kesalahan server",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
//                Toast.makeText(this@TransaksiActivity, "Koneksi Bermasalah", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
//
//}
