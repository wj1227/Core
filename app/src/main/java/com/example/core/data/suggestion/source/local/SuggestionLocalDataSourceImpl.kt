package com.example.core.data.suggestion.source.local

import com.example.core.utils.PreferenceManager

class SuggestionLocalDataSourceImpl(private val prefs: PreferenceManager) : SuggestionLocalDataSource {
    override val email: String
        get() = prefs.email

    override val name: String
        get() = prefs.name
}