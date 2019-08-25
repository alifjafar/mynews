package com.alifjafar.mynews.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
abstract class BaseStatedFragment<T : StatedViewModel<*>, S> : Fragment() {

    protected lateinit var viewModel: T

    abstract var viewModelClass : KClass<T>

    abstract fun getLayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(viewModelClass.java)
        viewModel.getState().observe(this, Observer {
            render(it as S)
        })
    }

    abstract fun render(state: S)
}