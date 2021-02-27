package com.example.core.data.suggestion.source.remote

import com.example.core.data.suggestion.SuggestionItem
import io.reactivex.Completable

interface SuggestionRemoteDataSource {
    fun upload(item: SuggestionItem): Completable
}