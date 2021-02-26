package com.example.core.data.tutorial.source.local

import com.example.core.utils.PreferenceManager

class TutorialLocalDataSourceImpl(private val preferenceManager: PreferenceManager) :
    TutorialLocalDataSource {
    override var tutorial: Boolean
        get() = preferenceManager.tutorial
        set(value) {
            preferenceManager.tutorial = value
        }
}