package com.example.myphotogrid_1.network
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// url with json
private const val BASE_URL = "https://jsonplaceholder.typicode.com/albums/"

//moshi object used by retrofit - kotlin adapter for full compatibility
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// retrofit builder to build a retrofit object using moshi converter with moshi object
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// interface for getphoto method
interface GridApiService {
    // returns list of photos and can be called from a coroutine

    @GET("1/photos")
    suspend fun getPhotos(): List<GridPhoto>
}

// public api object can be lazy initialized Retrofit service - delaying object creation when req
object GridApi {
    val retrofitService: GridApiService by lazy {retrofit.create(GridApiService::class.java)}
}




