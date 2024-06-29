package com.losjardines.smarthomeaj.ui.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.losjardines.smarthomeaj.R
import com.losjardines.smarthomeaj.data.model.Reference
import com.losjardines.smarthomeaj.databinding.FragmentDoorBinding
import com.losjardines.smarthomeaj.ui.ViewModel.ActivityViewModel


class DoorFragment : Fragment() {

    private lateinit var activityViewModel: ActivityViewModel
    private var _binding : FragmentDoorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityViewModel = ViewModelProvider(requireActivity())[ActivityViewModel::class.java]
        activityViewModel.accessDoorInfo()
        // Inflate the layout for this fragment
        _binding = FragmentDoorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Code getData viewBinding
        activityViewModel.stateDoor1.observe(viewLifecycleOwner){
            if(it)
            {
                // State ON
                binding.door1.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.ON_DOOR))
            }
            else
            {
                // State OFF
                activityViewModel.changeValueState(true, Reference.DOOR1)
                binding.door1.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.OFF_DOOR))
            }
        }

        activityViewModel.stateDoor2.observe(viewLifecycleOwner){
            if(it)
            {
                // State ON
                binding.door2.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.ON_DOOR))
            }
            else
            {
                // State OFF
                binding.door2.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.OFF_DOOR))
                activityViewModel.changeValueState(true, Reference.DOOR2)
            }
        }

        activityViewModel.timeoutError.observe(viewLifecycleOwner){
            if(it)
            {
                // TimeOut
                Toast.makeText(requireContext(), "TimeOut Door, Ocurrio un problema", Toast.LENGTH_SHORT).show()
                activityViewModel.setValueDoor("0", Reference.DOOR1)
                activityViewModel.setValueDoor("0", Reference.DOOR2)
                binding.door1.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.OFF_DOOR))
                binding.door2.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.OFF_DOOR))
            }
        }

        // Code action click cardView
        binding.door1.setOnClickListener {
            val stateCard = binding.door1.cardBackgroundColor.defaultColor
            if(stateCard == -1)
            {
                activityViewModel.setValueDoor("1", Reference.DOOR1)
                binding.door1.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.ON_DOOR))
                activityViewModel.changeValueState(false, Reference.DOOR1)
                activityViewModel.isTimeOutDoor1()
            }
        }

        binding.door2.setOnClickListener {
            val stateCard = binding.door2.cardBackgroundColor.defaultColor
            if(stateCard == -1)
            {
                activityViewModel.setValueDoor("1", Reference.DOOR2)
                binding.door2.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.ON_DOOR))
                activityViewModel.changeValueState(false, Reference.DOOR2)
                activityViewModel.isTimeOutDoor2()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}