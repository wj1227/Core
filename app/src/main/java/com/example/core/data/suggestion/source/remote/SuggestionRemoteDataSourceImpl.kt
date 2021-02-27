package com.example.core.data.suggestion.source.remote

import com.example.core.constants.SUGGESTION
import com.example.core.data.suggestion.SuggestionItem
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable

class SuggestionRemoteDataSourceImpl(private val database: FirebaseFirestore) : SuggestionRemoteDataSource {
    override fun upload(item: SuggestionItem): Completable {
        return Completable.create { emitter ->
            database.collection(SUGGESTION)
                .add(item)
                .addOnSuccessListener { emitter.onComplete() }
                .addOnFailureListener { emitter.onError(it) }
        }
    }
}