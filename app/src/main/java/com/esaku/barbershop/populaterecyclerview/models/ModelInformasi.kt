package com.esaku.barbershop.populaterecyclerview.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class ModelInformasi (

	@field:SerializedName("kode_transaksi")
	val kodeTransaksi: String? = null,

	@field:SerializedName("nilai_transaksi")
	var nilaiTransaksi: String? = null,
) : Parcelable {

    constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(kodeTransaksi)
		parcel.writeString(nilaiTransaksi)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ModelInformasi> {
		override fun createFromParcel(parcel: Parcel): ModelInformasi {
			return ModelInformasi(parcel)
		}

		override fun newArray(size: Int): Array<ModelInformasi?> {
			return arrayOfNulls(size)
		}
	}

}

// kang boleh izin bentar ngg? mau kunci gerbang dulu iyaa silahkan oke bentar 5 mnt