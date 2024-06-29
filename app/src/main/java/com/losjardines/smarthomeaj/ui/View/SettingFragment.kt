package com.losjardines.smarthomeaj.ui.View

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.losjardines.smarthomeaj.R
import com.losjardines.smarthomeaj.databinding.FragmentLightBinding
import com.losjardines.smarthomeaj.databinding.FragmentSettingBinding
import com.losjardines.smarthomeaj.ui.ViewModel.ActivityViewModel

class SettingFragment : Fragment() {

    private var _binding : FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Code viewbinding


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}