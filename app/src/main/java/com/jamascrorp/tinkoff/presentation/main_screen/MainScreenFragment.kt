package com.jamascrorp.tinkoff.presentation.main_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jamascrorp.tinkoff.R
import com.jamascrorp.tinkoff.databinding.FragmentMainScreenBinding
import com.jamascrorp.tinkoff.hideAction
import com.jamascrorp.tinkoff.hideKeyboard
import com.jamascrorp.tinkoff.presentation.adapters.AdvertisingRVAdapter
import com.jamascrorp.tinkoff.showAction
import javax.inject.Inject

class MainScreenFragment : Fragment() {

    private var viewBinding: FragmentMainScreenBinding? = null
    private val binding get() = viewBinding!!
    private var adapter: AdvertisingRVAdapter? = null

    @Inject
    lateinit var viewModel: MainScreenFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as com.jamascrorp.tinkoff.di.Inject).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewBinding = FragmentMainScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideAction()
        binding.root.setOnClickListener {
            it.hideKeyboard()
            it.clearFocus()
        }
        viewModel.getOperations()
        viewModel.operationsLiveData.observe(viewLifecycleOwner){
            var count = 0
            it.map {
                count = it.price.toInt()
            }
            Log.d("TAG", "onViewCreated: $count")
        }
        adapter = AdvertisingRVAdapter(viewModel.model)
        binding.advertisingRv.adapter = adapter
        binding.advertisingRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.allTransacts.setOnClickListener {
            val action = MainScreenFragmentDirections.actionMainScreenToOperationScreen()
            findNavController().navigate(action)
        }

        binding.cash.setOnClickListener {
            findNavController().navigate(R.id.action_global_feautures)
        }

        binding.delivery.setOnClickListener {
            findNavController().navigate(R.id.action_global_feautures)
        }
    }

    override fun onStart() {
        super.onStart()
        hideAction()
    }

    override fun onStop() {
        super.onStop()
        showAction()
    }
}