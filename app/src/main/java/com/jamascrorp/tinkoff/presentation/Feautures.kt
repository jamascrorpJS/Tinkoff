package com.jamascrorp.tinkoff.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jamascrorp.tinkoff.R
import com.jamascrorp.tinkoff.databinding.FragmentFeauturesBinding
import com.jamascrorp.tinkoff.hideAction

class Feautures : Fragment() {

    private var viewBinding: FragmentFeauturesBinding? = null
    private val binding get() = viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFeauturesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideAction()
    }
}