package com.getbux.sandbox.common

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import kotlin.properties.Delegates

class SuspendingLifecycleRegistry(provider: LifecycleOwner) : LifecycleRegistry(provider) {

    var isSuspended by Delegates.observable(false) { _, _, _ -> onSuspendedChange() }

    private fun onSuspendedChange() {
        if (isSuspended) {
            super.handleLifecycleEvent(Event.ON_STOP)
        } else {
            super.handleLifecycleEvent(Event.ON_START)
        }
    }

    // TODO This class has also to cover cases like allowing to go into ON_DESTROY even when isSuspended

    override fun handleLifecycleEvent(event: Event) {
        if (isSuspended) {
            return
        }

        super.handleLifecycleEvent(event)
    }

    override fun markState(state: State) {
        if (isSuspended) {
            return
        }

        super.markState(state)
    }
}