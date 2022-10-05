package com.example.a1hw4monthlifetracker

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.a1hw4monthlifetracker.databinding.FragmentHomeBinding
import com.example.a1hw4monthlifetracker.room.App
import com.example.a1hw4monthlifetracker.room.AppDataBase
import com.example.a1hw4monthlifetracker.room.TaskModel

class HomeFragment : Fragment(), TaskClickListener {

    lateinit var binding: FragmentHomeBinding
    var taskAdapter = TaskAdapter(ArrayList(), this)

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
        (requireContext() as MainActivity).binding.titleTx.text = "Main"
        (requireContext() as MainActivity).binding.profileImage.load(R.drawable.ic_person)
        initClicker()
        App.appDataBase.taskDao().getAll().observe(viewLifecycleOwner, Observer {
            taskAdapter = TaskAdapter(it, this)
            binding.recycler.adapter = taskAdapter
        })

    }


    private fun initClicker() {
        binding.createTaskBtn.setOnClickListener {
            TaskFragment().show(requireActivity().supportFragmentManager, "")

        }
        (requireContext() as MainActivity).binding.profileImage.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }
    }

    override fun itemClick(taskModel: TaskModel) {
        val dailog = TaskFragment()
        val bundle = Bundle()
        bundle.putSerializable("model", taskModel)
        dailog.arguments = bundle
        dailog.show(requireActivity().supportFragmentManager, "update")
    }

    override fun deleteItem(taskModel: TaskModel) {
        showDialog(
            requireContext(),
            "вы хотите удалить ?",

            object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    App.appDataBase.taskDao().delete(taskModel)
                }

            })
    }


    private fun showDialog(
        context: Context, title: String,

        negativeBtnClickListener: DialogInterface.OnClickListener?
    ): AlertDialog {
        val builder = AlertDialog.Builder(context)
            .setTitle(title)
            .setCancelable(true)
//            .setPositiveButton(positiveBtnText, object :DialogInterface.OnClickListener{
//                override fun onClick(p0: DialogInterface?, p1: Int) {
//
//                }
//
//            })

        builder.setPositiveButton("yes", negativeBtnClickListener)
        val alert = builder.create()
        alert.show()
        return alert
    }


}