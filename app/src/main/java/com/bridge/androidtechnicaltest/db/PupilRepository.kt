package com.bridge.androidtechnicaltest.db

import com.bridge.androidtechnicaltest.network.PupilApi
import io.reactivex.Single

interface IPupilRepository {
    fun getPupils(): Single<PupilList>
    fun postPupils(pupil: Pupil): Single<Pupil>
}

class PupilRepository(val database: AppDatabase, val pupilApi: PupilApi) : IPupilRepository {

    override fun getPupils(): Single<PupilList> = pupilApi.getPupils(1)
    override fun postPupils(pupil: Pupil): Single<Pupil> = pupilApi.addPupil(pupil)

    /*.onErrorResumeNext {
        database.pupilDao.pupils.map {
            PupilList(it)
        }}*/



}

