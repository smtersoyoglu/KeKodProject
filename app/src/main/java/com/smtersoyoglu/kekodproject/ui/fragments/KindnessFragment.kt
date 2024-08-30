package com.smtersoyoglu.kekodproject.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.smtersoyoglu.kekodproject.R


class KindnessFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kindness, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gifImageView: ImageView = view.findViewById(R.id.imageView)
        Glide.with(this)
            .load(R.drawable.kindness_gif) // GIF dosyasının adı
            .into(gifImageView)

    }

}