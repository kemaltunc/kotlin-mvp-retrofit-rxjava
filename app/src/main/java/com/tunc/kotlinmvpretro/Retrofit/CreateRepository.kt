package com.tunc.kotlinmvpretro.Retrofit

import io.reactivex.Observable

class CreateRepository(val apiService: Api) {

    fun createUsers(params: Map<String, String>): Observable<Result> {
        return apiService.createUser(params)
    }

    fun login(params: Map<String, String>): Observable<Result> {
        return apiService.login(params)
    }
}

object CreateRepositoryProvider {

    fun provideCreateRepository(): CreateRepository {
        return CreateRepository(Api.create())
    }

}