package com.example.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding?, V : BaseViewModel?> : Fragment() {

    private var activity: BaseActivity<T>? = null
    val baseActivity: BaseActivity<T>?
        get() = activity

    private var rootView: View? = null
    protected var viewDataBinding: T? = null

    abstract fun getViewModelInstance(): V

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getViewModelBindingVariableId(): Int

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    @MainThread
    protected open fun setupDataStateObservers() {}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        rootView = viewDataBinding?.root

        initViews()

        return rootView
    }

    abstract fun initViews()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            val activity: BaseActivity<T> = context as BaseActivity<T>
            this.activity = activity
        }
    }

    override fun onDetach() {
        activity = null
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.setVariable(getViewModelBindingVariableId(), getViewModelInstance())
        viewDataBinding?.lifecycleOwner = this
        viewDataBinding?.executePendingBindings()
    }

    fun hideKeyboard() {
        activity?.hideKeyboard()
    }

    protected fun showLoading() {
        activity?.showLoading()
    }

    protected fun hideLoading() {
        activity?.hideLoading()
    }

    protected fun showStatusBar() {
        activity?.showStatusBar()
    }

    protected fun hideStatusBar() {
        activity?.hideStatusBar()
    }

    fun showToastMsg(msg: String) {
        activity?.showToastMsg(msg)
    }
}