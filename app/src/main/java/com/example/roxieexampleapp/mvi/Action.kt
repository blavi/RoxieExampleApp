package com.example.roxieexampleapp.mvi

import com.ww.roxie.BaseAction

sealed class Action : BaseAction {
    object LoadImages : Action()
}
