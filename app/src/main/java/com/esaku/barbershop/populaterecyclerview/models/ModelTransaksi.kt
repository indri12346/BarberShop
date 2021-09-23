package com.esaku.barbershop.populaterecyclerview.models

import com.google.gson.annotations.SerializedName

data class ModelTransaksi(

	@field:SerializedName("arrkunj")
	val arrkunj: ArrayList<ArrkunjItem>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class ArrkunjItem(

	@field:SerializedName("kode_barber")
	val kodeBarber: String? = null,

	@field:SerializedName("no_bukti")
	val noBukti: String? = null,

	@field:SerializedName("kode_paket")
	val kodePaket: String? = null,

	@field:SerializedName("total")
	val total: Int? = null
)
