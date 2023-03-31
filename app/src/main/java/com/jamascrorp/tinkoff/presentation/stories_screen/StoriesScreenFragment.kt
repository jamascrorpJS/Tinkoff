package com.jamascrorp.tinkoff.presentation.stories_screen

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import com.jamascrorp.tinkoff.R
import com.jamascrorp.tinkoff.databinding.FragmentStoriesScreenBinding
import com.jamascrorp.tinkoff.domain.entity.StoriesModel
import com.jamascrorp.tinkoff.hideAction
import com.jamascrorp.tinkoff.hideBottom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StoriesScreenFragment : Fragment() {

    private var viewBinding: FragmentStoriesScreenBinding? = null
    private val binding get() = viewBinding!!
    private var timer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentStoriesScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideAction()
        val args = StoriesScreenFragmentArgs.fromBundle(requireArguments()).model
        Glide.with(this)
            .load(args.image)
            .into(binding.stories)
        timer = object : CountDownTimer(4500, 1000) {
            override fun onTick(p0: Long) {
                binding.progressBar.max = 1000
                val currentProgress = 3000
                ObjectAnimator.ofInt(binding.progressBar, "progress", currentProgress)
                    .setDuration(5000).start()
            }

            override fun onFinish() {
                findNavController().navigate(R.id.action_storiesScreenFragment_to_main)

            }
        }
    }

    override fun onStart() {
        super.onStart()
        timer?.start()
        hideBottom(this)
    }

    override fun onStop() {
        super.onStop()
        timer?.cancel()
        timer = null
    }
}