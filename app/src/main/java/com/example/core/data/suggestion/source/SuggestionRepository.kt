package com.example.core.data.suggestion.source

import com.example.core.data.suggestion.SuggestionItem
import io.reactivex.Completable

interface SuggestionRepository {
    val email: String
    val name: String

    fun upload(item: SuggestionItem): Completable
}