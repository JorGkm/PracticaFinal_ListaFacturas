package com.example.practicafinal_listafacturas

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url


interface APIservice {
    @GET("facturaRafa")
    suspend fun getFacturas() : Response<ListaFacturas>
}