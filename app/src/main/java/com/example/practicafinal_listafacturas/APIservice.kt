package com.example.practicafinal_listafacturas

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface APIservice {
    @GET("facturas/")
    suspend fun getFacturas() : Response<ListaFacturas>
}