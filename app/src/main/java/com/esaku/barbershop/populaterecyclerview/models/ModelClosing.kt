package com.esaku.barbershop.populaterecyclerview.models

import com.google.gson.annotations.SerializedName

data class ModelClosing(

	@field:SerializedName("no_bukti")
	val noBukti: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)
