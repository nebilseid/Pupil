package com.bridge.androidtechnicaltest.network

import com.bridge.androidtechnicaltest.db.Pupil
import com.bridge.androidtechnicaltest.db.PupilList
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PupilApi {
    @GET("pupils")
    fun getPupils(@Query("page") page: Int = 1): Single<PupilList>

    @POST("users")
    fun addPupil(@Body pupil: Pupil): Call<Pupil>
}