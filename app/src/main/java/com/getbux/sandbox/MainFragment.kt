package com.getbux.sandbox

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.ColorInt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getbux.sandbox.common.BaseFragment
import com.getbux.sandbox.databinding.FragmentMainBinding
import com.getbux.sandbox.domain.Product

class MainFragment : BaseFragment() {

    private val productId by lazy { arguments.getString(Arg.PRODUCT_ID) }
    private val backgroundColor by lazy { arguments.getInt(Arg.BACKGROUND_COLOR) }

    private lateinit var viewBinding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = FragmentMainBinding.inflate(inflater)
        viewBinding.viewModel = MainFragmentViewViewModel()
        return viewBinding.mainContainerView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.viewModel?.backgroundColor = backgroundColor

        val viewModel = ViewModelProviders.of(this, MainFragmentViewModel.Factory(productId)).get(MainFragmentViewModel::class.java)
        viewModel.product.observe(this, Observer<Product> { product ->
            println("${product?.name}: ${product?.price}")

            viewBinding.viewModel?.productName = product?.name
            viewBinding.viewModel?.productPrice = product?.price?.toPlainString()
        })
    }

    companion object {
        private object Arg {
            const val PRODUCT_ID = "PRODUCT_ID"
            const val BACKGROUND_COLOR = "BACKGROUND_COLOR"
        }

        fun newInstance(productId: String, @ColorInt backgroundColor: Int) = MainFragment().apply {
            arguments = Bundle().apply {
                putString(Arg.PRODUCT_ID, productId)
                putInt(Arg.BACKGROUND_COLOR, backgroundColor)
            }
        }
    }
}