package com.demo.helloWorld.fragments.leaves

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.demo.helloWorld.databinding.FragmentAlertDialogBinding

class AlertDialogFragment : Fragment() {

    lateinit var binding: FragmentAlertDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAlertDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.exitbtn.setOnClickListener{
            val dialogBox = AlertDialog.Builder(requireActivity())
            dialogBox.setMessage("do you want to exit?")
            dialogBox.setPositiveButton("Yes :("){
                    _, _ -> activity?.finish()
                Toast.makeText(activity, "exit", Toast.LENGTH_LONG).show()
            } .setNegativeButton("No :)"){
                    dialog, _ -> dialog.cancel()
            }

            val alert = dialogBox.create()
            alert.setTitle("want to close this app")
            alert.show()
        }

        binding.submit.setOnClickListener{
            val editableText = binding.edittext.text.toString().trim()

            if(editableText != ""){
                binding.copiedText.text = editableText
                Toast.makeText(activity, "new text: $editableText", Toast.LENGTH_LONG).show()
            }
            binding.edittext.setText("")
        }
    }


}