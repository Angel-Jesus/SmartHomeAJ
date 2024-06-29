package com.losjardines.smarthomeaj.ui.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.losjardines.smarthomeaj.R
import com.losjardines.smarthomeaj.data.model.Reference
import com.losjardines.smarthomeaj.databinding.FragmentLightBinding
import com.losjardines.smarthomeaj.ui.ViewModel.ActivityViewModel

class LightFragment : Fragment() {
    private lateinit var activityViewModel: ActivityViewModel
    private var _binding : FragmentLightBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityViewModel = ViewModelProvider(requireActivity())[ActivityViewModel::class.java]
        activityViewModel.accesLightInfo()
        // Inflate the layout for this fragment
        _binding = FragmentLightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Code getData viewBinding
        activityViewModel.stateLight1.observe(viewLifecycleOwner){
            if(it)
            {
                // State ON
                binding.Light1.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.ON_LIGHT))
            }
            else
            {
                // State OFF
                binding.Light1.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.OFF_LIGHT))
            }
        }

        activityViewModel.stateLight2.observe(viewLifecycleOwner){
            if(it)
            {
                // State ON
                binding.Light2.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.ON_LIGHT))
            }
            else
            {
                // State OFF
                binding.Light2.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.OFF_LIGHT))
            }
        }

        activityViewModel.stateLight3.observe(viewLifecycleOwner){
            if(it)
            {
                // State ON
                binding.Light3.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.ON_LIGHT))
            }
            else
            {
                // State OFF
                binding.Light3.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.OFF_LIGHT))
            }
        }

        activityViewModel.stateLight4.observe(viewLifecycleOwner){
            if(it)
            {
                // State ON
                binding.Light4.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.ON_LIGHT))
            }
            else
            {
                // State OFF
                binding.Light4.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.OFF_LIGHT))
            }
        }

        activityViewModel.stateLight5.observe(viewLifecycleOwner){
            if(it)
            {
                // State ON
                binding.Light5.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.ON_LIGHT))
            }
            else
            {
                // State OFF
                binding.Light5.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.OFF_LIGHT))
            }
        }

        activityViewModel.stateLight6.observe(viewLifecycleOwner){
            if(it)
            {
                // State ON
                binding.Light6.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.ON_LIGHT))
            }
            else
            {
                // State OFF
                binding.Light6.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.OFF_LIGHT))
            }
        }
        // Code action click carView
        binding.Light1.setOnClickListener {
            val stateCard = binding.Light1.cardBackgroundColor.defaultColor
            Log.d("estado","state: $stateCard")

            if(stateCard == -1)
            {
                //OFF mode
                activityViewModel.setValueLight("1", Reference.LIGHT1)
                binding.Light1.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.ON_LIGHT))
            }
            else {
                //ON mode
                activityViewModel.setValueLight("0", Reference.LIGHT1)
                binding.Light1.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.OFF_LIGHT))
            }

        }

        binding.Light2.setOnClickListener {
            val stateCard = binding.Light2.cardBackgroundColor.defaultColor
            Log.d("estado","state: $stateCard")

            if(stateCard == -1)
            {
                //OFF mode
                activityViewModel.setValueLight("1", Reference.LIGHT2)
                binding.Light2.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.ON_LIGHT))
            }
            else {
                //ON mode
                activityViewModel.setValueLight("0", Reference.LIGHT2)
                binding.Light2.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.OFF_LIGHT))
            }
        }

        binding.Light3.setOnClickListener {
            val stateCard = binding.Light3.cardBackgroundColor.defaultColor
            Log.d("estado","state: $stateCard")

            if(stateCard == -1)
            {
                //OFF mode
                activityViewModel.setValueLight("1", Reference.LIGHT3)
                binding.Light3.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.ON_LIGHT))
            }
            else {
                //ON mode
                activityViewModel.setValueLight("0", Reference.LIGHT3)
                binding.Light3.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.OFF_LIGHT))
            }
        }

        binding.Light4.setOnClickListener {
            val stateCard = binding.Light4.cardBackgroundColor.defaultColor
            Log.d("estado","state: $stateCard")

            if(stateCard == -1)
            {
                //OFF mode
                activityViewModel.setValueLight("1", Reference.LIGHT4)
                binding.Light4.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.ON_LIGHT))
            }
            else {
                //ON mode
                activityViewModel.setValueLight("0", Reference.LIGHT4)
                binding.Light4.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.OFF_LIGHT))
            }
        }

        binding.Light5.setOnClickListener {
            val stateCard = binding.Light5.cardBackgroundColor.defaultColor
            Log.d("estado","state: $stateCard")

            if(stateCard == -1)
            {
                //OFF mode
                activityViewModel.setValueLight("1", Reference.LIGHT5)
                binding.Light5.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.ON_LIGHT))
            }
            else {
                //ON mode
                activityViewModel.setValueLight("0", Reference.LIGHT5)
                binding.Light5.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.OFF_LIGHT))
            }
        }

        binding.Light6.setOnClickListener {
            val stateCard = binding.Light6.cardBackgroundColor.defaultColor
            Log.d("estado","state: $stateCard")

            if(stateCard == -1)
            {
                //OFF mode
                activityViewModel.setValueLight("1", Reference.LIGHT6)
                binding.Light6.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.ON_LIGHT))
            }
            else {
                //ON mode
                activityViewModel.setValueLight("0", Reference.LIGHT6)
                binding.Light6.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.OFF_LIGHT))
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}