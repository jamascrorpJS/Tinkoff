package com.jamascrorp.tinkoff.presentation.final_pay_screen

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jamascrorp.tinkoff.R
import com.jamascrorp.tinkoff.data.database.models.bookmarks.BookmarksModelDB
import com.jamascrorp.tinkoff.data.network.ConnectivityObserver
import com.jamascrorp.tinkoff.data.network.Network
import com.jamascrorp.tinkoff.data.network.NetworkConnectivityObserver
import com.jamascrorp.tinkoff.data.network.models.operations.OperationsModelItem
import com.jamascrorp.tinkoff.databinding.FragmentFinalPayBinding
import com.jamascrorp.tinkoff.hideKeyboard
import com.jamascrorp.tinkoff.observeOnce
import com.jamascrorp.tinkoff.showAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject


class FinalPayFragment : Fragment() {

    private var viewBinding: FragmentFinalPayBinding? = null
    private val binding get() = viewBinding!!
    @Inject
    lateinit var viewModel: FinalPayFragmentViewModel
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
        viewBinding = FragmentFinalPayBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showAction()
//        networkCheck()
        val args = FinalPayFragmentArgs.fromBundle(requireArguments())
        val name = args.model.name
        val category = args.name
        val address = args.model.address
        val color = args.model.color
        networkCheck()
        binding.nameCard.setBackgroundColor(Color.parseColor(color))
        with(binding) {
            payName.text = name
            pays.setOnClickListener { button ->
                button.isEnabled = false
                val myDate: Date = Calendar.getInstance().time
                val time = myDate.toString().dropLast(15)
                viewModel.postPay(
                    OperationsModelItem(
                        category,
                        "0",
                        name,
                        binding.pay.text.toString(),
                        "Дебетовая карта",
                        address,
                        color,
                        time
                    )
                )
                viewModel.liveData.observe(viewLifecycleOwner){
                    when (it) {
                        is Network.Error -> {
                            Log.d("TAG", "onViewCreated: e")
                            Toast.makeText(requireContext(), "Проверьте подключение к сети", Toast.LENGTH_SHORT).show()
                            binding.pb.visibility = View.GONE
                            button.hideKeyboard()
                            button.isEnabled = true
                            viewModel.liveData.removeObservers(viewLifecycleOwner)
                        }
                        is Network.Exception -> {
                            Log.d("TAG", "onViewCreated: ee")
                            Toast.makeText(requireContext(), "Проверьте подключение к сети", Toast.LENGTH_SHORT).show()
                            binding.pb.visibility = View.GONE
                            button.hideKeyboard()
                            button.isEnabled = true
                            viewModel.liveData.removeObservers(viewLifecycleOwner)
                        }
                        is Network.Loading -> {
                            Log.d("TAG", "onViewCreated: l")
                            binding.pb.visibility = View.VISIBLE
                        }
                        is Network.Success -> {
                            Log.d("TAG", "onViewCreated: s")
                            binding.pb.visibility = View.GONE
                            button.isEnabled = true
                            viewModel.liveData1.observeOnce(viewLifecycleOwner){
                                findNavController().navigate(R.id.action_animationFragment_to_finalPayFragment)
                            }
                            findNavController().navigate(R.id.action_finalPayFragment_to_animationFragment)
                            viewModel.liveData.removeObservers(viewLifecycleOwner)
                        }
                    }
                }
            }
            save.setOnClickListener {
                viewModel.save(
                    BookmarksModelDB(
                        null,
                        R.mipmap.ic_launcher,
                        name,
                        address,
                        color,
                        category
                    )
                )
            }
        }
    }
    fun networkCheck() {
        connectivityObserver = NetworkConnectivityObserver(requireContext())
        CoroutineScope(Dispatchers.IO).launch {
            connectivityObserver.observe().collect(FlowCollector {
                withContext(Dispatchers.Main) {
                    if (it.name == "Available") {
                        binding.networkError.visibility = View.GONE
                        binding.pays.isEnabled = true
                    } else {
                        binding.pays.isEnabled = false
                        binding.networkError.visibility = View.VISIBLE
                    }
                }
            })
        }
    }
}