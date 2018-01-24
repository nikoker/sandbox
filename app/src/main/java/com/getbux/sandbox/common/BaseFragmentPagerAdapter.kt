package com.getbux.sandbox.common

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup

abstract class BaseFragmentPagerAdapter(private val fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val fragments: Array<Fragment?>
    private var currentPrimaryItem: Fragment? = null

    init {
        fragments = arrayOfNulls(count)
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any? {
        val fragment = super.instantiateItem(container, position) as Fragment? ?: return null
        fragments[position] = fragment
        return fragment
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, primaryItem: Any) {
        super.setPrimaryItem(container, position, primaryItem)

        val fragment = primaryItem as Fragment
        if (fragment === currentPrimaryItem) {
            return
        }

        val transaction = fragmentManager.beginTransaction()

        fragments.forEach { childFragment ->
            if (childFragment === fragment) {
                transaction.show(childFragment)
            } else if (childFragment !== null) {
                transaction.hide(childFragment)
            }
        }

        transaction.commitNowAllowingStateLoss()

        currentPrimaryItem = fragment
    }
}