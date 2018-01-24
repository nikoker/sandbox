package com.getbux.sandbox.common

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v4.app.Fragment

abstract class BaseFragment : Fragment() {

    private lateinit var suspendingLifecycleField: SuspendingLifecycleRegistry
    private val suspendingLifecycle get() = lifecycle as SuspendingLifecycleRegistry

    override fun getLifecycle(): Lifecycle {
        if (!::suspendingLifecycleField.isInitialized) {
            suspendingLifecycleField = SuspendingLifecycleRegistry(this)

            val parentLifecycle = super.getLifecycle()
            parentLifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
                fun onAnyEvent() {
                    suspendingLifecycleField.markState(parentLifecycle.currentState)
                }
            })
        }
        return suspendingLifecycleField
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        suspendingLifecycle.isSuspended = !isVisibleToUser
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        suspendingLifecycle.isSuspended = hidden
    }
}
