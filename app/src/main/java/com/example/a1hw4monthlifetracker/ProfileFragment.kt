package com.example.a1hw4monthlifetracker

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.a1hw4monthlifetracker.databinding.FragmentProfileBinding


@Suppress("DEPRECATION")
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    companion object {
        val IMAGE_REQUEST_CODE = 1_000;
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireContext()as MainActivity).binding.titleTx.text= "Profile"
        (requireContext()as MainActivity).binding.profileImage.load(R.drawable.ic_profile_image)
        (requireContext()as MainActivity).binding.profileImage.setOnClickListener{
            findNavController().navigate(R.id.homeFragment2)

            binding.imageProfile.setOnClickListener{
                pickImageGallery()
            }
        }

    }

    private fun pickImageGallery() {
        val intent =Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            binding.imageProfile.setImageURI(data?.data)
        }
    }


}