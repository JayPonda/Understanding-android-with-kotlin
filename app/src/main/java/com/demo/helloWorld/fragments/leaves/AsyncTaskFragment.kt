package com.demo.helloWorld.fragments.leaves

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.helloWorld.R
import com.demo.helloWorld.databinding.FragmentAsyncTaskBinding
import java.util.concurrent.Executors

class AsyncTaskFragment : Fragment() {

    private lateinit var binding: FragmentAsyncTaskBinding

    private val executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAsyncTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            task(resources.getStringArray(R.array.languages))
        }
    }

    private fun task(arr: Array<String>){
        var index = 0

        executor.execute {
            Log.i("index", index.toString())
            while(true){
                handler.post {
                    binding.taskDetails.text = arr[index++]
                }
                Thread.sleep(1000)
                if(index == arr.size - 1) break
            }
            handler.post {
                binding.taskDetails.text = getString(R.string.task_completed)
            }
        }
    }
}