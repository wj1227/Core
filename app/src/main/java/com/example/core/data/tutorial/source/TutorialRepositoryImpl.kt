package com.example.core.data.tutorial.source

import com.example.core.data.tutorial.source.local.TutorialLocalDataSource

class TutorialRepositoryImpl(private val localDataSource: TutorialLocalDataSource) :
    TutorialRepository {
    override var tutorial: Boolean
        get() = localDataSource.tutorial
        set(value) {
            localDataSource.tutorial = value
        }
}