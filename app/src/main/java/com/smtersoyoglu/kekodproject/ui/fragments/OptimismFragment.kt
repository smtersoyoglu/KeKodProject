package com.smtersoyoglu.kekodproject.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.smtersoyoglu.kekodproject.R
import com.smtersoyoglu.kekodproject.databinding.FragmentGivingBinding
import com.smtersoyoglu.kekodproject.databinding.FragmentOptimismBinding


class OptimismFragment : Fragment() {

    private var _binding: FragmentOptimismBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOptimismBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load(R.drawable.optimism_gif) // GIF dosyasının adı
            .into(binding.optimismImageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // ViewBinding referansını temizle
        _binding = null
    }
}