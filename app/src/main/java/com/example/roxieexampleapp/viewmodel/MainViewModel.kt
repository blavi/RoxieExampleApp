package com.example.roxieexampleapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import com.example.roxieexampleapp.repository.Repository
import com.example.roxieexampleapp.mvi.Action
import com.example.roxieexampleapp.mvi.Change
import com.example.roxieexampleapp.mvi.State
import com.ww.roxie.BaseViewModel
import com.ww.roxie.Reducer
import dagger.hilt.android.scopes.ActivityScoped
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.ofType
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

@ActivityScoped
class MainViewModel @ViewModelInject constructor(private val repository: Repository) : BaseViewModel<Action, State>() {

    override val initialState = State.Idle

    private val reducer: Reducer<State, Change> = { state, change ->
        when (change) {
            is Change.Loading -> {
                State.Loading
            }

            is Change.Images -> {
                State.Images(change.images)
            }

            is Change.Error -> {
                State.LoadError(change.throwable?.message)
            }
        }
    }

    init {
        bindActions()
    }

    private fun bindActions() {

        val loadImages = actions.ofType<Action.LoadImages>()
            .switchMap {
                repository.getImages()
                    .subscribeOn(Schedulers.io())
                    .toObservable()
                    .map<Change> { Change.Images(it) }
                    .defaultIfEmpty(Change.Images(emptyArray()))
                    .onErrorReturn { Change.Error(it) }
                    .startWith(Change.Loading)
            }

        disposables += loadImages
            .scan(initialState, reducer)
//            .filter { it !is State.Loading }
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(state::setValue, Timber::e)
    }

}