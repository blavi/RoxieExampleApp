package com.example.roxieexampleapp.mvi

import com.example.roxieexampleapp.model.Image
import com.ww.roxie.BaseState

sealed class State: BaseState {
    object Idle : State()
    object Loading : State()
    data class Images(val images: Array<Image>) : State() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Images

            if (!images.contentEquals(other.images)) return false

            return true
        }

        override fun hashCode(): Int {
            return images.contentHashCode()
        }
    }

    data class LoadError(val error: String?) : State()
}
