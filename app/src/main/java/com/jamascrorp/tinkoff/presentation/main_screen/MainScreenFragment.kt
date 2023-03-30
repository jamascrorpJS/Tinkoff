package com.jamascrorp.tinkoff.presentation.main_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import com.jamascrorp.tinkoff.*
import com.jamascrorp.tinkoff.data.network.ConnectivityObserver
import com.jamascrorp.tinkoff.data.network.NetworkConnectivityObserver
import com.jamascrorp.tinkoff.databinding.FragmentMainScreenBinding
import com.jamascrorp.tinkoff.domain.entity.StoriesModel
import com.jamascrorp.tinkoff.presentation.adapters.AdvertisingRVAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainScreenFragment : Fragment() {

    private var viewBinding: FragmentMainScreenBinding? = null
    private val binding get() = viewBinding!!
    private var adapter: AdvertisingRVAdapter? = null
    private lateinit var connectivityObserver: ConnectivityObserver
    private lateinit var db: FirebaseFirestore
    private var storiesList: List<StoriesModel>? = null

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
        getFromFire()
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
        networkCheck()

        showBottom(this)
    }

    override fun onStop() {
        super.onStop()
        showAction()
    }
    fun networkCheck() {
        connectivityObserver = NetworkConnectivityObserver(requireContext())
        CoroutineScope(Dispatchers.IO).launch {
            connectivityObserver.observe().collect(FlowCollector {
                withContext(Dispatchers.Main){
                    if (it.name == "Available") {
                        binding.networkError.visibility = View.GONE
                        binding.advertisingRv.visibility = View.VISIBLE
                    } else {
                        binding.networkError.visibility = View.VISIBLE
                        binding.advertisingRv.visibility = View.GONE
                    }
                }
            })
        }
    }

    private fun getFromFire() {
        CoroutineScope(Dispatchers.IO).launch {
//            binding.progressBar.visibility = View.VISIBLE
            db = FirebaseFirestore.getInstance()
            db.collection("advertising").get().addOnFailureListener {
                Log.d("TAG", "getFromFire: $it")
            }.addOnSuccessListener { doc ->
                if (doc != null) {
                    binding.shimmer.stopShimmer()
                    binding.shimmer.visibility = View.GONE
                    storiesList = doc.toObjects()
                    adapter = AdvertisingRVAdapter(storiesList!!)
                    adapter?.clickOnStoriesItem = {
                        val action = MainScreenFragmentDirections.actionGlobalStoriesScreenFragment(it)
                        findNavController().navigate(action)
                    }
                    binding.advertisingRv.adapter = adapter
                    binding.advertisingRv.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                }
            }
        }
    }
}