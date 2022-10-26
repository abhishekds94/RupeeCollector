package com.avidprogrammers.rupeecollector.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.avidprogrammers.rupeecollector.R
import com.avidprogrammers.rupeecollector.databinding.ActivityHomeBinding
import com.avidprogrammers.rupeecollector.databinding.FragmentUserDetailsBinding
import com.avidprogrammers.rupeecollector.model.BaseResponse
import com.avidprogrammers.rupeecollector.model.userDetails.UserDetailsResponse
import com.avidprogrammers.rupeecollector.util.SessionManager
import com.avidprogrammers.rupeecollector.viewmodel.userDetails.UserDetailsViewModel


class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding
    private val viewModel by viewModels<UserDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserDetailsBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userDetailsResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    Log.d("userDetailScreen123", "BR.Loading")
                    showLoading()
                }

                is BaseResponse.Success -> {
                    Log.d("userDetailScreen123", "BR.Success")
                    stopLoading()
                    processUserDetails(it.data)
                }

                is BaseResponse.Error -> {
                    Log.d("userDetailScreen123", "BR.Error")
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }

        binding.userDetailsBtn.setOnClickListener {
            uploadUserDetails()
        }

    }

    private fun uploadUserDetails() {
        val userIdValue = SessionManager.getUserId(requireContext())
        val userId = userIdValue?.toInt()
        val userPin = 1234
        val address = binding?.address?.text.toString()
        val imei = binding?.imei?.text.toString()
        val description = binding?.description?.text.toString()
        val timezone = binding?.timezone?.text.toString()
        val company = binding?.company?.text.toString()
        viewModel.userDetails(
            userId = userId!!,
            userPin = userPin,
            address = address,
            imei = imei,
            description = description,
            timezone = timezone,
            company = company
        )
    }

    private fun showLoading() {
        binding.userProgressBarContainer.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.userProgressBarContainer.visibility = View.GONE
    }

    private fun processUserDetails(data: UserDetailsResponse?) {
        if (data!!.message == "OK") {
            Log.d("userDetailScreen123", "pud.success")
            showToast("Success!!: ${data.message}")
            stopLoading()
        } else {
            processError(data.message)
        }
    }

    private fun processError(msg: String?) {
        showToast("Error:$msg")
        Log.d("userDetailScreen123", "ErrorMessage- $msg")
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

}