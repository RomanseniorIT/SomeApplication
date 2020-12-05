package com.example.someapplication

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Actor(@StringRes val name: Int, @DrawableRes val photo: Int): Parcelable