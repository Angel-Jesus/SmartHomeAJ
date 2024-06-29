package com.losjardines.smarthomeaj.ui.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.losjardines.smarthomeaj.R
import com.losjardines.smarthomeaj.databinding.FragmentHomeBinding
import com.losjardines.smarthomeaj.ui.ViewModel.ActivityViewModel


class HomeFragment : Fragment() {
    private lateinit var activityViewModel: ActivityViewModel

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityViewModel = ViewModelProvider(requireActivity())[ActivityViewModel::class.java]
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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