package com.demo.helloWorld.fragments.leaves

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.demo.helloWorld.R
import com.demo.helloWorld.databinding.FragmentCustomAlertDialogBinding

class CustomAlertDialogFragment : Fragment() {

    lateinit var binding: FragmentCustomAlertDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCustomAlertDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.AlertBoxWithTextField.setOnClickListener {
            val cAlertBox = AlertDialog.Builder(requireActivity())

            val myView = LayoutInflater.from(activity).inflate(R.layout.template_custom_alertbox, view.findViewById(android.R.id.content), false)

            cAlertBox
                .setView(myView)
                .setTitle("Enter Your Name")
                .setMessage("Example: FirstName MiddleName LastName")
                .setPositiveButton("submit") {
                        _, _ ->
                    val txt = myView.findViewById<EditText>(R.id.writeFullName).text.toString()
                    binding.ExtractedText.visibility = View.VISIBLE
                    binding.ExtractedText.text = txt

                }
                .setNegativeButton("Cancel") { _, _ -> binding.ExtractedText.visibility = View.INVISIBLE}

            cAlertBox.create().show()
        }
    }

}