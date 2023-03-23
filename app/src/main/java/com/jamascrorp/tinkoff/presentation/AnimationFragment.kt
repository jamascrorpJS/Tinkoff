package com.jamascrorp.tinkoff.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jamascrorp.tinkoff.R
import com.jamascrorp.tinkoff.databinding.FragmentAnimationBinding

class AnimationFragment : Fragment() {

    private var viewBinding: FragmentAnimationBinding? = null

    private val binding get() = viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentAnimationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.materialButton.setOnClickListener {
            findNavController().navigate(R.id.action_animationFragment_to_mainScreen)
        }
    }
}