package com.demo.helloWorld.fragments.leaves

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.helloWorld.R
import com.demo.helloWorld.databinding.FragmentBroadcastReceiverBinding

class BroadcastReceiverFragment : Fragment() {

    lateinit var binding: FragmentBroadcastReceiverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBroadcastReceiverBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val receiver = object: BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                val change = intent?.getIntExtra("level", 0)
                binding.powerLevel.text = getString(R.string.battery_level, change)
            }
        }

        requireActivity().registerReceiver(receiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }
}