package com.example.core.data.suggestion.source

import com.example.core.data.suggestion.SuggestionItem
import com.example.core.data.suggestion.source.local.SuggestionLocalDataSource
import com.example.core.data.suggestion.source.remote.SuggestionRemoteDataSource
import io.reactivex.Completable

class SuggestionRepositoryImpl(
    private val localDataSource: SuggestionLocalDataSource,
    private val remoteDataSource: SuggestionRemoteDataSource
) : SuggestionRepository {
    override val email: String
        get() = localDataSource.email

    override val name: String
        get() = localDataSource.name

    override fun upload(item: SuggestionItem): Completable {
        return remoteDataSource.upload(item)
    }
}