package com.example.a1hw4monthlifetracker

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.navigation.fragment.findNavController
import com.example.a1hw4monthlifetracker.databinding.FragmentTaskBinding
import com.example.a1hw4monthlifetracker.databinding.RegularDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS


class TaskFragment : BottomSheetDialogFragment() {
    var task = ""
    var date = ""
    var regular = ""

    lateinit var binding: FragmentTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicker()
    }

    private fun showRegularDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding = RegularDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        binding.day.setOnClickListener{
            regular = binding.day.text.toString()
            this.binding.regularBtn.text = regular
            dialog.dismiss()
        }

        binding.cancelBtn.setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()

    }

    private fun initClicker() {
        with(binding) {
            applyBtn.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("model", TaskModel(taskEd.text.toString(), date, regular))
                findNavController().navigate(R.id.homeFragment2, bundle)
            }
            regularBtn.setOnClickListener {
                showRegularDialog()
            }

            dateBtn.setOnClickListener {
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)
                val dpd = DatePickerDialog(
                    requireContext(),
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        dateBtn.setText("$dayOfMonth. ${monthOfYear + 1}. $year")
                        date = dateBtn.text.toString()
                    },
                    year,
                    month,
                    day
                )
                dpd.show()
            }
        }
    }

}