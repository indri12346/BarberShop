package com.esaku.barbershop.populaterecyclerview.apihelper

import com.esaku.barbershop.populaterecyclerview.models.ModelLogin
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @FormUrlEncoded
    @POST("barber-auth/login")
    fun login(
        @Field("nik") nik: String?,
        @Field("password") password: String?,

    ): Call<ModelLogin?>?

    @GET("barber-trans/getKunj")
    fun transaksi(
        @Query("tanggal") tanggal:String?,
        @Query("kode_barber") kode_barber:String?,
    ):Call<ResponseBody>

    @GET("barber-auth/profile")
    fun profile():Call<ResponseBody>


    @GET("barber-trans/getNoBukti")
    fun nobukti(
        @Query ("tanggal")tanggal: String
    ):Call<ResponseBody>


    @FormUrlEncoded
    @POST("barber-trans/kunj")
    fun kunj(
        @Field("nilai")nilai:Int?,
        @Field("diskon")diskon:Int?,
        @Field("nik_user")nik_user:String?,
        @Field("kode_cust")kode_cust:String?,
        @Field("nama")nama:String?,
        @Field("alamat")alamat:String?,
        @Field("no_hp")no_hp:String?,
        @Field("kode_paket")kode_paket:String?,
        @Field("tanggal")tanggal:String?,
        @Field("kode_barber")kode_barber:String?
    ): Call<ResponseBody>

    @GET("barber-master/cust")
    fun cust():Call<ResponseBody>


}

