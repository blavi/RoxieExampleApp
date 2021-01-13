package com.example.roxieexampleapp.mvi

import com.example.roxieexampleapp.model.Image

sealed class Change {
    object Loading : Change()
    data class Images(val images: Array<Image>) : Change() {
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

    data class Error(val throwable: Throwable?) : Change()
}
