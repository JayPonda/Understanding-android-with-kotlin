package com.demo.helloWorld.fragments.leaves

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.demo.helloWorld.MainActivity
import com.demo.helloWorld.R
import com.demo.helloWorld.databinding.FragmentLocalNotificationBinding
import com.demo.helloWorld.handlers.notification.NotificationHandler

class LocalNotificationFragment : Fragment() {

    companion object{
        const val CHANNEL_ID: String = "msg.notification"
        const val CHANNEL_NAME = "testChannel"
    }

    private lateinit var binding: FragmentLocalNotificationBinding

    private var builder: NotificationCompat.Builder? = null
    private var notificationId = 1
    private lateinit var notificationManager: NotificationManager
    private lateinit var channel: NotificationChannel
    private val buildBuilder = NotificationHandler(5000L, R.drawable.ic_stat_call_white)

    private fun getText(): Array<String?> {
        val title = binding.notificationTitle.editText?.text.toString()
        val smallText = binding.notificationSmallText.editText?.text.toString()
        val bigText = binding.bigEditText.text.toString()
        return arrayOf(title, smallText, bigText)
    }

    private fun handleEmptyEditText(str: Array<String?>): Boolean {
        for (s in str) {

            if (s.isNullOrEmpty()) {
                Toast.makeText(context, "please enter text in input", Toast.LENGTH_LONG).show()
                return false
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationManager = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = "My notification channel description"
        notificationManager.createNotificationChannel(channel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLocalNotificationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.notify.setOnClickListener {
            val str = getText()
            if (handleEmptyEditText(str)) {
                builder =
                    when {
                        binding.showAdditionalImage.isChecked && binding.showAdditionalImageExpandable.isChecked -> {
                            activity?.let { it1 ->
                                buildBuilder.generateNotificationBuilderObjExpandable(
                                    str,
                                    it1,
                                    binding.showTimedNotification.isChecked,
                                    binding.showAdditionalImage.isChecked,
                                    if (binding.TapActionswitch.isChecked)
                                        Intent(it1, MainActivity::class.java) else null)
                            }

                        }
                        else -> {
                            activity?.let { it1 ->
                                buildBuilder.generateNotificationBuilderObj(
                                    str,
                                    it1,
                                    binding.showTimedNotification.isChecked,
                                    binding.showBigText.isChecked,
                                    binding.showAdditionalImage.isChecked,
                                    if (binding.TapActionswitch.isChecked) Intent(it1,
                                        MainActivity::class.java) else null)
                            }
                        }
                    }
                if (binding.singleNotificationSwitch.isChecked) {
                    binding.clearAllNotification.performClick()
                }
                notificationManager.notify(if (binding.UpdateOnChangeswitch.isChecked) notificationId else notificationId++,
                    builder!!.build())
            }
        }
        binding.clearAllNotification.setOnClickListener { notificationManager.cancelAll() }
        binding.showBigText.setOnCheckedChangeListener { _, isChecked ->
            binding.showAdditionalImageExpandable.isEnabled = !isChecked
            binding.showAdditionalImageExpandable.isClickable = !isChecked
        }
        binding.showAdditionalImage.setOnCheckedChangeListener { _, isChecked ->
            binding.showAdditionalImageExpandable.isEnabled = isChecked
            binding.showAdditionalImageExpandable.isClickable = isChecked
        }
    }

    
}