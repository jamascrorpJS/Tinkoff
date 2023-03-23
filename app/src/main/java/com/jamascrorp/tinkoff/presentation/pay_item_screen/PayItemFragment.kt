package com.jamascrorp.tinkoff.presentation.pay_item_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jamascrorp.tinkoff.R
import com.jamascrorp.tinkoff.databinding.FragmentPayItemBinding
import com.jamascrorp.tinkoff.domain.entity.PayItemModel
import com.jamascrorp.tinkoff.presentation.adapters.PayItemRvAdapter
import com.jamascrorp.tinkoff.showAction

class PayItemFragment : Fragment() {

    private var viewBinding: FragmentPayItemBinding? = null
    private val binding get() = viewBinding!!
    private var adapter: PayItemRvAdapter? = null
    lateinit var viewModel: PayItemFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewBinding = FragmentPayItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[PayItemFragmentViewModel::class.java]
        showAction()
        val args = PayItemFragmentArgs.fromBundle(requireArguments()).model
        val subs = args.subs
        val list = subs.map {
            PayItemModel(
                it.address,
                it.color,
                R.mipmap.ic_launcher,
                it.name
            )
        }
        adapter = PayItemRvAdapter(list)

        adapter?.clickOnPayItem = {
            val action =
                PayItemFragmentDirections.actionPayItemFragmentToFinalPayFragment(it, args.text)
            findNavController().navigate(action)
        }
        binding.payItemRv.adapter = adapter
        binding.payItemRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

}