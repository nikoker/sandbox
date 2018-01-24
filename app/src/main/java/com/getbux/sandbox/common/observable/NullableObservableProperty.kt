package com.getbux.sandbox.common.observable

import android.databinding.BaseObservable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class NullableObservableProperty<VM : BaseObservable, T>(val fieldId: Int) : ReadWriteProperty<VM, T?> {
    private var value: T? = null

    override fun getValue(thisRef: VM, property: KProperty<*>) = value

    override fun setValue(thisRef: VM, property: KProperty<*>, value: T?) {
        this.value = value
        thisRef.notifyPropertyChanged(fieldId)
    }
}