package com.example.core.ui.suggestion

import androidx.lifecycle.LiveData
import com.example.core.base.BaseViewModel
import com.example.core.base.ViewModelType
import com.example.core.data.suggestion.SuggestionItem
import com.example.core.data.suggestion.source.SuggestionRepository
import com.example.core.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit

interface SuggestionViewModelType: ViewModelType<SuggestionViewModelType.Input, SuggestionViewModelType.Output> {
    interface Input {
        fun onUploadClick()
        fun onNextText(text: String)
    }

    interface Output {
        val state: LiveData<SuggestionViewModel.SuggestionState>
        val loading: LiveData<Boolean>
    }
}

class SuggestionViewModel(
    private val repository: SuggestionRepository
) : BaseViewModel(), SuggestionViewModelType, SuggestionViewModelType.Input, SuggestionViewModelType.Output {
    override val input: SuggestionViewModelType.Input
        get() = this
    override val outout: SuggestionViewModelType.Output
        get() = this

    private val _btnUploadClick: Subject<Unit> = PublishSubject.create()
    private val _textSubject: BehaviorSubject<String> = BehaviorSubject.createDefault("")

    private val _state: SingleLiveEvent<SuggestionState> = SingleLiveEvent()
    override val state: LiveData<SuggestionState>
        get() = _state

    private val _loading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    override val loading: LiveData<Boolean>
        get() = _loading

    override fun onUploadClick() = _btnUploadClick.onNext(Unit)
    override fun onNextText(text: String) = _textSubject.onNext(text)

    init {
        compositeDisposable.addAll(
            _btnUploadClick.throttleFirst(1, TimeUnit.SECONDS)
                .withLatestFrom(
                    validatorText(),
                    BiFunction { _: Unit, x: Boolean -> x }
                )
                .subscribe { result ->
                    if (result) {
                        upload(createItem())
                    } else {
                        setState(SuggestionState.FAIL_VALIDATOR)
                    }
                },

            _loadingSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe(_loading::setValue)
        )
    }

    private fun validatorText() = _textSubject.map { it.isNotEmpty() }
    private fun setState(state: SuggestionState) = _state.postValue(state)

    private fun upload(item: SuggestionItem) {
        repository.upload(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading() }
            .doAfterTerminate { hideLoading() }
            .subscribe({
                setState(SuggestionState.SUCCESS_UPLOAD)
            }, {
                _errorMessage.value = it.localizedMessage
            }).addTo(compositeDisposable)
    }

    private fun createItem() = SuggestionItem(
        email = repository.email,
        name = repository.name,
        text = _textSubject.value!!,
        date = System.currentTimeMillis()
    )

    enum class SuggestionState {
        FAIL_VALIDATOR,
        SUCCESS_UPLOAD
    }

}