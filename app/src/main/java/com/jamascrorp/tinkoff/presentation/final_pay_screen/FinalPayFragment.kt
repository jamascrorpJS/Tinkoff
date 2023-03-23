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
import com.jamascrorp.tinkoff.data.network.Network
import com.jamascrorp.tinkoff.data.network.models.operations.OperationsModelItem
import com.jamascrorp.tinkoff.databinding.FragmentFinalPayBinding
import com.jamascrorp.tinkoff.showAction
import java.util.*
import javax.inject.Inject


class FinalPayFragment : Fragment() {

    private var viewBinding: FragmentFinalPayBinding? = null
    private val binding get() = viewBinding!!

    @Inject
    lateinit var viewModel: FinalPayFragmentViewModel

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
        val args = FinalPayFragmentArgs.fromBundle(requireArguments())
        val name = args.model.name
        val category = args.name
        val address = args.model.address
        val color = args.model.color
        Log.d("TAG", "onViewCreated: $color")
        binding.nameCard.setBackgroundColor(Color.parseColor(color))
        Log.d("TAG", "onViewCreated: $args")
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
                viewModel.liveData.observe(viewLifecycleOwner) {
                    when (it) {
                        is Network.Error -> {}
                        is Network.Exception -> {}
                        is Network.Loading -> {}
                        is Network.Success -> {
                            button.isEnabled = true
                            findNavController().navigate(R.id.action_finalPayFragment_to_animationFragment)
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
}