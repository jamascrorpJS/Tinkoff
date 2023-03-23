package com.jamascrorp.tinkoff.presentation.pay_screen_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jamascrorp.tinkoff.R
import com.jamascrorp.tinkoff.databinding.FragmentPayScreenDetailsBinding
import com.jamascrorp.tinkoff.domain.entity.PayModel
import com.jamascrorp.tinkoff.presentation.adapters.PayVerticalRvAdapter
import com.jamascrorp.tinkoff.showAction

class PayScreenDetails : Fragment() {

    private var viewBinding: FragmentPayScreenDetailsBinding? = null
    private val binding get() = viewBinding!!
    private var adapter: PayVerticalRvAdapter? = null
    lateinit var viewModel: PayScreenDetailsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewBinding = FragmentPayScreenDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showAction()
        viewModel = ViewModelProvider(this)[PayScreenDetailsFragmentViewModel::class.java]

        val payModelList = PayScreenDetailsArgs.fromBundle(requireArguments()).model.map {
            PayModel(
                R.mipmap.ic_launcher,
                it.text,
                it.id,
                it.subs
            )
        }
        Log.d("TAG", "onViewCreated: ${
            payModelList.filter { it.id == "1" }.map {
                it.subs
            }
        }")

        adapter = PayVerticalRvAdapter(payModelList)
        adapter?.clickOnPayItem = {
            val action = PayScreenDetailsDirections.actionPayScreenDetailsToPayItemFragment(it)
            findNavController().navigate(action)
        }
        binding.payDetailsRv.adapter = adapter
        binding.payDetailsRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}