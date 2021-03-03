package com.example.core.ui.tutorial

import androidx.lifecycle.LiveData
import com.example.core.base.BaseViewModel
import com.example.core.base.ViewModelType
import com.example.core.data.tutorial.source.TutorialRepository
import com.example.core.utils.SingleLiveEvent
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit

interface TutorialViewModelType :
    ViewModelType<TutorialViewModelType.Input, TutorialViewModelType.Output> {
    interface Input {
        fun onStartClick()
    }

    interface Output {
        var startResult: Boolean
    }
}

class TutorialViewModel(
    private val repository: TutorialRepository
) : BaseViewModel(), TutorialViewModelType, TutorialViewModelType.Input,
    TutorialViewModelType.Output {
    override val input: TutorialViewModelType.Input
        get() = this
    override val outout: TutorialViewModelType.Output
        get() = this

    private val _btnStartSubject: Subject<Unit> = PublishSubject.create()

    private val _start: SingleLiveEvent<Unit> = SingleLiveEvent()
    val start: LiveData<Unit>
        get() = _start

    override var startResult: Boolean
        get() = repository.tutorial
        set(value) {
            repository.tutorial = value
        }

    override fun onStartClick() = _btnStartSubject.onNext(Unit)

    init {
//        _btnStartSubject.throttleFirst(1, TimeUnit.SECONDS)
//            .subscribe { startResult = true; _start.call() }
//            .addTo(compositeDisposable)
    }

    fun bindRx() {
        _btnStartSubject.throttleFirst(1, TimeUnit.SECONDS)
            .subscribe { startResult = true; _start.call() }
            .addTo(compositeDisposable)
    }

}