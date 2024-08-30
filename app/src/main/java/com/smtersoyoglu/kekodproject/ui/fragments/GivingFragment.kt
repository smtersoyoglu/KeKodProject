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


class GivingFragment : Fragment() {

    private var _binding: FragmentGivingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // ViewBinding kullanarak layout'u inflate et
        _binding = FragmentGivingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewBinding ile ImageView'leri ve Button'u bul
        val gifImageView1 = binding.imageView1
        val gifImageView2 = binding.imageView2
        val gifImageView3 = binding.imageView3
        val button = binding.button

        // Glide kullanarak GIF'leri yükle
        Glide.with(this)
            .load(R.drawable.money2_gif)
            .into(gifImageView1)

        Glide.with(this)
            .load(R.drawable.money1_gif)
            .into(gifImageView2)

        Glide.with(this)
            .load(R.drawable.money_gif)
            .into(gifImageView3)

        // Başlangıçta imageView3 görünmez yap
        gifImageView3.visibility = View.INVISIBLE

        // Button'a click listener ekle
        button.setOnClickListener {
            // imageView3'ün görünürlüğünü değiştir
            gifImageView3.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // ViewBinding referansını temizle
        _binding = null
    }
}
