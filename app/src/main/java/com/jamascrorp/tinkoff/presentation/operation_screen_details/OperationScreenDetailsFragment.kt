package com.jamascrorp.tinkoff.presentation.operation_screen_details

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jamascrorp.tinkoff.databinding.FragmentOperationScreenDetailsBinding
import com.jamascrorp.tinkoff.showAction

class OperationScreenDetailsFragment : Fragment() {

    private var viewBinding: FragmentOperationScreenDetailsBinding? = null
    private val binding get() = viewBinding!!
    lateinit var viewModel: OperationsScreenDetailsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentOperationScreenDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showAction()
        viewModel = ViewModelProvider(this)[OperationsScreenDetailsFragmentViewModel::class.java]
        val args = OperationScreenDetailsFragmentArgs.fromBundle(requireArguments()).model
        with(binding){
            costName.text = args.costName
            costType.text = args.costType
            cost.text = args.price
            cardName.text = args.priceType
            address.text = args.address
            dateTime.text = args.time
            coloredBoard.setBackgroundColor(Color.parseColor(args.color))
            time.text = args.time.drop(10)
            costs.text = args.price
        }
    }
}