package com.jamascrorp.tinkoff.presentation.operation_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jamascrorp.tinkoff.data.network.ConnectivityObserver
import com.jamascrorp.tinkoff.data.network.NetworkConnectivityObserver
import com.jamascrorp.tinkoff.databinding.FragmentOperationScreenBinding
import com.jamascrorp.tinkoff.domain.entity.OperationsModel
import com.jamascrorp.tinkoff.observeOnce
import com.jamascrorp.tinkoff.presentation.adapters.OperationsRvAdapter
import com.jamascrorp.tinkoff.showAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject


class OperationScreenFragment : Fragment() {

    private var viewBinding: FragmentOperationScreenBinding? = null
    private val binding get() = viewBinding!!
    private var adapter: OperationsRvAdapter? = null
    private lateinit var connectivityObserver: ConnectivityObserver
    @Inject
    lateinit var viewModel: OperationScreenViewModel
    private var array: List<OperationsModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as com.jamascrorp.tinkoff.di.Inject).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewBinding = FragmentOperationScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showAction()

        binding.monthCost.text = viewModel.getMonth()

        viewModel.liveData1.observeOnce(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.getOperations()
        viewModel.ld.observe(viewLifecycleOwner) {
            if (it?.isEmpty() != true) {
                binding.shimmer.stopShimmer()
                binding.shimmer.visibility = View.GONE
            }
            array = it
            val cost = it.map { it.price }
            val sum = cost.map { it.toInt() }.sum()
            binding.costs.text = sum.toString()
            adapter = array?.reversed()?.let { it1 -> OperationsRvAdapter(it1) }
            binding.costsRv.adapter = adapter
            binding.costsRv.setHasFixedSize(true)
            binding.costsRv.setItemViewCacheSize(20)
            binding.costsRv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter?.clickOnTransactionsItem = {
                val action =
                    OperationScreenFragmentDirections.actionOperationScreenToOperationScreenDetails(
                        it)
                findNavController().navigate(action)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        networkCheck()
    }

    fun networkCheck() {
        connectivityObserver = NetworkConnectivityObserver(requireContext())
        CoroutineScope(Dispatchers.IO).launch {
            connectivityObserver.observe().collect(FlowCollector {
                withContext(Dispatchers.Main) {
                    if (it.name == "Available") {
                        binding.networkError.visibility = View.GONE
                        viewModel.updateOperations()
                        binding.root.setOnRefreshListener {
                            viewModel.updateOperations()
                            binding.root.isRefreshing = false
                        }
                    } else {
                        binding.networkError.visibility = View.VISIBLE
                    }
                }
            })
        }
    }
}