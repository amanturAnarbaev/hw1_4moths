package com.example.a1hw4monthlifetracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.a1hw4monthlifetracker.databinding.ActivityMainBinding
import com.example.a1hw4monthlifetracker.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    var taskAdapter = TaskAdapter(ArrayList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).showToolbar()
        (requireContext()as MainActivity).binding.titleTx.text= "Main"
        (requireContext()as MainActivity).binding.profileImage.load(R.drawable.ic_person)
        initClicker()
        arguments?.let {
            var list = ArrayList<TaskModel>()
            val model = it.getSerializable("model") as TaskModel
            list.add(model)
            taskAdapter = TaskAdapter(list)
            binding.recycler.adapter = taskAdapter
        }
    }

    private fun initClicker() {
        binding.createTaskBtn.setOnClickListener{
            TaskFragment().show(requireActivity().supportFragmentManager,"")

        }
        (requireContext()as MainActivity).binding.profileImage.setOnClickListener{
            findNavController().navigate(R.id.profileFragment)
        }
    }

}