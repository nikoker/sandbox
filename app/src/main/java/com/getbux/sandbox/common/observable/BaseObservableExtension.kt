package com.getbux.sandbox.common.observable

import android.databinding.BaseObservable
import kotlin.properties.ReadWriteProperty

@Suppress("unused")
fun <O : BaseObservable, T> BaseObservable.observableProperty(fieldId: Int): ReadWriteProperty<O, T?> {
    return NullableObservableProperty(fieldId)
}

@Suppress("unused")
fun <O : BaseObservable, T> BaseObservable.observableProperty(fieldId: Int, initialValue: T): ReadWriteProperty<O, T> {
    return ObservableProperty(fieldId, initialValue)
}