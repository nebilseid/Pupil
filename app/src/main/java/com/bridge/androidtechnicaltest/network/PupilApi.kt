package com.bridge.androidtechnicaltest.network

import com.bridge.androidtechnicaltest.db.Pupil
import com.bridge.androidtechnicaltest.db.PupilList
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface PupilApi {
    @GET("pupils")
    fun getPupils(@Query("page") page: Int = 1): Single<PupilList>

    @Headers("Content-Type: application/json-patch+json")
    @POST("pupils")
    fun addPupil(@Body pupil: Pupil) : Single<Pupil>
}