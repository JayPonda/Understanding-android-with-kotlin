package com.demo.helloWorld.fragments.leaves

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.helloWorld.R
import com.demo.helloWorld.databinding.FragmentThreadHandlerBinding

class ThreadHandlerFragment : Fragment() {

    lateinit var binding: FragmentThreadHandlerBinding
    private var boolean = false
    private var counter: Int = 0
    private lateinit var thread: Thread
    private lateinit var handler: Handler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentThreadHandlerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressStatusNumber.text = getString(R.string.progressNumber, 0)

        handler = Handler(Looper.getMainLooper())

        binding.startCounter.setOnClickListener {
            if(!boolean){
                boolean = true
                thread = Thread {
                    while (boolean) {
                        handler.post {
                            binding.miniSeekBar.setProgress(counter % 5, false)
                            binding.progressTest.setProgress(counter / 5, false)
                            binding.progressStatusNumber.text = getString(R.string.progressNumber, counter)
                        }
                        Thread.sleep(999)
                        counter++
                        println(counter)
                        if (counter == 60) counter = 0
                    }
                }
                thread.start()
            }
        }

        binding.pauseCounter.setOnClickListener{
            binding.startCounter.text =   getString( if(boolean) R.string.start_counter else R.string.resume_counter )
            boolean = false
        }

        binding.restartCounter.setOnClickListener {
            boolean = false
            counter = 0
            binding.startCounter.text = getString(R.string.start_counter)
            binding.progressStatusNumber.text = getString(R.string.progressNumber, counter)
            binding.progressTest.setProgress(0, false)
            binding.miniSeekBar.setProgress(0, false)
        }
    }

}