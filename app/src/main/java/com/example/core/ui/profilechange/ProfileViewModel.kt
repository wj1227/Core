package com.example.core.ui.profilechange

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.base.BaseViewModel
import com.example.core.base.ViewModelType
import com.example.core.data.profile.ProfileUser
import com.example.core.data.profile.source.ProfileRepository
import com.example.core.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit

interface ProfileViewModelType : ViewModelType<ProfileViewModelType.Input, ProfileViewModelType.Output> {
    interface Input {
        fun onProfileChange()
        fun onNextCompany(company: String)
        fun onNextPosition(position: String)
        fun onNextCellPhone(cellPhone: String)
    }

    interface Output {
        val user: LiveData<ProfileUser>
        val company: LiveData<String>
        val cellPhone: LiveData<String>
        val position: LiveData<String>
        val profileState: LiveData<ProfileViewModel.ProfileState>
        val loading: LiveData<Boolean>
    }
}

class ProfileViewModel(
    private val repository: ProfileRepository
) : BaseViewModel(), ProfileViewModelType, ProfileViewModelType.Input, ProfileViewModelType.Output {
    override val input: ProfileViewModelType.Input
        get() = this
    override val outout: ProfileViewModelType.Output
        get() = this

    private val _btnProfileChangeSubject: Subject<Unit> = PublishSubject.create()
    private val _cellPhoneSubject: BehaviorSubject<String> = BehaviorSubject.createDefault(repository.getUser().cellPhone)
    private val _companySubject: BehaviorSubject<String> = BehaviorSubject.createDefault(repository.getUser().company)
    private val _positionSubject: BehaviorSubject<String> = BehaviorSubject.createDefault(repository.getUser().position)

    private val _user: MutableLiveData<ProfileUser> = MutableLiveData()
    override val user: LiveData<ProfileUser>
        get() = _user

    private val _company: MutableLiveData<String> = MutableLiveData()
    override val company: LiveData<String>
        get() = _company

    private val _cellPhone: MutableLiveData<String> = MutableLiveData()
    override val cellPhone: LiveData<String>
        get() = _cellPhone

    private val _position: MutableLiveData<String> = MutableLiveData()
    override val position: LiveData<String>
        get() = _position

    private val _profileState: SingleLiveEvent<ProfileState> = SingleLiveEvent()
    override val profileState: LiveData<ProfileState>
        get() = _profileState

    private val _loading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    override val loading: LiveData<Boolean>
        get() = _loading

    override fun onProfileChange() = _btnProfileChangeSubject.onNext(Unit)
    override fun onNextCompany(company: String) = _companySubject.onNext(company)
    override fun onNextPosition(position: String) = _positionSubject.onNext(position)
    override fun onNextCellPhone(cellPhone: String) = _cellPhoneSubject.onNext(cellPhone)

    init {
        _user.postValue(repository.getUser())

        compositeDisposable.addAll(
            _btnProfileChangeSubject.throttleFirst(1, TimeUnit.SECONDS)
                .subscribe {
                    if (validatorUser()) {
                        setState(ProfileState.PROFILE_CHANGE)
                    } else {
                        updateUser(newUserModel())
                    }
                },

            _companySubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe(_company::setValue),

            _positionSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe(_position::setValue),

            _cellPhoneSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe(_cellPhone::setValue),

            _loadingSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe(_loading::setValue)
        )
    }

    private fun validatorUser(): Boolean {
        val currentUser = user.value
        val newUser = ProfileUser(
            email = user.value?.email!!,
            name = user.value?.name!!,
            company = company.value!!,
            position = position.value!!,
            cellPhone = _cellPhone.value!!
        )

        return currentUser == newUser
    }

    private fun newUserModel() = ProfileUser(
        email = user.value?.email!!,
        name = user.value?.name!!,
        company = company.value!!,
        position = position.value!!,
        cellPhone = _cellPhone.value!!
    )

    private fun updateUser(user: ProfileUser) {
        repository.updateUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading() }
            .doAfterTerminate { hideLoading() }
            .subscribe({
                setState(ProfileState.PROFILE_CHANGE)
            }, {
                _errorMessage.value = it.localizedMessage
            }).addTo(compositeDisposable)
    }

    private fun setState(state: ProfileState) = _profileState.postValue(state)

    enum class ProfileState {
        PROFILE_CHANGE
    }

}