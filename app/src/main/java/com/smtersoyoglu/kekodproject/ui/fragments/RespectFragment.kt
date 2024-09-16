package com.smtersoyoglu.kekodproject.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.smtersoyoglu.kekodproject.R
import com.smtersoyoglu.kekodproject.databinding.FragmentRespectBinding


class RespectFragment : Fragment() {

    private var _binding: FragmentRespectBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRespectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val gifImageView: ImageView = view.findViewById(R.id.imageView)
        //gifImageView.load(R.drawable.respect_gif) // GIF dosyasının adı

        Glide.with(this)
            .load(R.drawable.respect_gif) // GIF dosyasının adı
            .into(binding.imageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}