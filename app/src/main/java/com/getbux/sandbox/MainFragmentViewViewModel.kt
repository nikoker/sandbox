package com.getbux.sandbox

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.graphics.Color
import com.getbux.sandbox.common.observable.observableProperty

class MainFragmentViewViewModel : BaseObservable() {
    // This complicated construction is needed if you want to be able to change a field in the model
    // and have the corresponding UI element updates. Opposed to building a new model instance and
    // setting that instance in the Binding instance
    @get:Bindable var backgroundColor: Int by observableProperty(BR.productName, Color.TRANSPARENT)
    @get:Bindable var productName: String? by observableProperty(BR.productName)
    @get:Bindable var productPrice: String? by observableProperty(BR.productName)
}

