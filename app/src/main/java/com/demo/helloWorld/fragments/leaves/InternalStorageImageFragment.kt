package com.demo.helloWorld.fragments.leaves

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.demo.helloWorld.R
import com.demo.helloWorld.databinding.FragmentInternalStorageImageBinding

class InternalStorageImageFragment : Fragment() {

    lateinit var binding: FragmentInternalStorageImageBinding

    private var imageSet: Uri? = null

    // get image from intent
    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            imageSet = data?.data
            binding.imagePreviewLayout.visibility = View.VISIBLE
            binding.IceCreamPreviewImage.setImageURI(imageSet)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentInternalStorageImageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ChoosePhotoButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            resultLauncher.launch(intent)
        }

        binding.removeIceCreamImage.setOnClickListener {
            binding.imagePreviewLayout.visibility = View.GONE
            binding.IceCreamPreviewImage.setImageResource(R.drawable.preview_img)
            imageSet = null
        }
    }
}