package com.example.smarttasks.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.smarttasks.R
import com.example.smarttasks.databinding.FragmentMainBinding
import com.example.smarttasks.databinding.FragmentStartBinding
import com.example.smarttasks.presenter.viewmodel.MainViewModel


class MainFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        changeFragment(ScheduledFragment())
        buttonNavigationBar()
        mainViewModel.validationauthentication()
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authenticationValidation()
    }
    private fun authenticationValidation(){
        mainViewModel.authenticationStateEvent.observe(viewLifecycleOwner, Observer { authenticationState ->
            when(authenticationState){
                is MainViewModel.AuthenticationState.UnauAuthenticated ->{
                    findNavController().navigate(R.id.startFragment)
                }else -> sendToast("Bem Vindo.")
            }
        })
    }
    fun sendToast(msg:String){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }
    private fun buttonNavigationBar() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.all_task_page -> {
                    changeFragment(AllTaskFragment())
                    true
                }
                R.id.scheduled_page -> {
                    changeFragment(ScheduledFragment())
                    true
                }
                R.id.expired_page -> {
                    changeFragment(ExpiredFragment())
                    true
                }
                else -> false
            }
        }

    }

    private fun changeFragment(fragment: Fragment){
        val fragmentMananger: FragmentManager = parentFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentMananger.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}