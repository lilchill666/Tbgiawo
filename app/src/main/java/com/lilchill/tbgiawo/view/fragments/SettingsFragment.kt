package com.lilchill.tbgiawo.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.lilchill.tbgiawo.R
import com.lilchill.tbgiawo.presenter.SettingsPresenter
import com.lilchill.tbgiawo.presenter.implementations.MenuPresenterImpl
import com.lilchill.tbgiawo.presenter.implementations.SettingsPresenterImpl
import com.lilchill.tbgiawo.view.AppActivity
import com.lilchill.tbgiawo.view.interfaces.SettingsViewInterface
import com.lilchill.tbgiawo.view.layouts.MenuLayout
import com.lilchill.tbgiawo.view.layouts.SettingsLayout

class SettingsFragment : Fragment(), SettingsViewInterface {
    private val presenter = SettingsPresenterImpl(this)
    private val layout by lazy { SettingsLayout(requireActivity()) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = layout
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    (requireActivity() as AppActivity).supportFragmentManager.beginTransaction().replace(R.id.root, MenuFragment()).commit()
                }
            }
        )
    }
}