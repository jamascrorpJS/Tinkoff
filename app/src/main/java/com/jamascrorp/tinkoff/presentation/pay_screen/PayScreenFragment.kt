package com.jamascrorp.tinkoff.presentation.pay_screen

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
import com.jamascrorp.tinkoff.databinding.FragmentPayScreenBinding
import com.jamascrorp.tinkoff.domain.entity.BookmarksModel
import com.jamascrorp.tinkoff.domain.entity.PayItemModel
import com.jamascrorp.tinkoff.domain.entity.PayModel
import com.jamascrorp.tinkoff.hideAction
import com.jamascrorp.tinkoff.observeOnce
import com.jamascrorp.tinkoff.presentation.adapters.BookmarksRvAdapter
import com.jamascrorp.tinkoff.presentation.adapters.PayRvAdapter
import com.jamascrorp.tinkoff.showAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PayScreenFragment : Fragment() {

    private var viewBinding: FragmentPayScreenBinding? = null
    private val binding get() = viewBinding!!
    private var adapter: PayRvAdapter? = null
    private var adapterBookmarks: BookmarksRvAdapter? = null
    @Inject
    lateinit var viewModel: PayScreenViewModel
    private var array: List<PayModel>? = null
    private var arrayBookmarks: List<BookmarksModel>? = null
    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as com.jamascrorp.tinkoff.di.Inject).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewBinding = FragmentPayScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideAction()

        viewModel.liveData1.observeOnce(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.getPay()

        viewModel.ld.observeOnce(viewLifecycleOwner) {
            if (it?.isEmpty() != true) {
                binding.shimmer.stopShimmer()
                binding.shimmer.visibility = View.GONE
            }
            array = it
            adapter = array?.let { it1 -> PayRvAdapter(it1) }
            binding.priceRv.adapter = adapter
            binding.priceRv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.other.setOnClickListener {
                val action = array?.let { it1 ->
                    PayScreenFragmentDirections.actionPayScreenToPayScreenDetails(it1.toTypedArray())
                }
                if (action != null) {
                    findNavController().navigate(action)
                }
            }
            adapter?.clickOnPayItem = {
                val action =
                    PayScreenFragmentDirections.actionPayScreenToPayItemFragment(it)
                findNavController().navigate(action)
            }
        }

        viewModel.getBookmarks()

        viewModel.ldDB.observeOnce(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.text.visibility = View.VISIBLE
            } else {
                binding.text.visibility = View.GONE
            }
            arrayBookmarks = it.map {
                BookmarksModel(it.address, it.color, it.image, it.name, it.category)
            }
            adapterBookmarks = arrayBookmarks?.let { it2 -> BookmarksRvAdapter(it2) }
            binding.bookmarksRv.adapter = adapterBookmarks
            binding.bookmarksRv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapterBookmarks?.clickOnPayItem = {
                val bookmarksToMapPayItem = PayItemModel(
                    it.address, it.color, it.image, it.name
                )
                val action = PayScreenFragmentDirections.actionPayScreenToFinalPayFragment(
                    bookmarksToMapPayItem,
                    it.category)
                findNavController().navigate(action)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        hideAction()
        networkCheck()
    }

    override fun onStop() {
        super.onStop()
        showAction()
    }
    fun networkCheck() {
        connectivityObserver = NetworkConnectivityObserver(requireContext())
        CoroutineScope(Dispatchers.IO).launch {
            connectivityObserver.observe().collect(FlowCollector {
                withContext(Dispatchers.Main) {
                    if (it.name == "Available") {
                        binding.networkError.visibility = View.GONE
                    } else {
                        binding.networkError.visibility = View.VISIBLE
                    }
                }
            })
        }
    }
}