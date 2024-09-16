package com.smtersoyoglu.kekodproject.ui.fragments

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator
import com.smtersoyoglu.kekodproject.viewmodel.DashboardViewModel
import com.smtersoyoglu.kekodproject.R
import com.smtersoyoglu.kekodproject.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private val viewModel: DashboardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Switch'lere ViewModel'deki değişiklikleri bağlama
        observeSwitchState(binding.happinessSwitch, R.id.happinessSwitch)
        observeSwitchState(binding.optimismSwitch, R.id.optimismSwitch)
        observeSwitchState(binding.kindnessSwitch, R.id.kindnessSwitch)
        observeSwitchState(binding.givingSwitch, R.id.givingSwitch)
        observeSwitchState(binding.respectSwitch, R.id.respectSwitch)

        binding.egoSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onEgoSwitchChanged(isChecked)
            animateBackgroundAndText(isChecked)
            updateRiveAnimation(isChecked)
            updateLottieAnimation(isChecked)
        }

        // Diğer switch'lerin aktif/pasif durumunu gözlemle
        viewModel.isOtherSwitchesEnabled.observe(viewLifecycleOwner) { isEnabled ->
            binding.happinessSwitch.isEnabled = isEnabled
            binding.optimismSwitch.isEnabled = isEnabled
            binding.kindnessSwitch.isEnabled = isEnabled
            binding.givingSwitch.isEnabled = isEnabled
            binding.respectSwitch.isEnabled = isEnabled
        }
    }

    override fun onResume() {
        super.onResume()
        // Mevcut Ego switch durumu üzerinden animasyonları güncelle
        viewModel.isEgoSwitchChecked.value?.let { isEgoOn ->
            updateRiveAnimation(isEgoOn)
            updateLottieAnimation(isEgoOn)
        }
    }


    private fun observeSwitchState(switch: SwitchCompat, switchId: Int) {
        switch.setOnCheckedChangeListener(null) // Dinleyiciyi temizle
        viewModel.switchStates[switchId]?.observe(viewLifecycleOwner) { isChecked ->
            switch.isChecked = isChecked
        }
        switch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onSwitchChanged(switchId, isChecked)
        }
    }

    @SuppressLint("RestrictedApi")
    private fun animateBackgroundAndText(isEgoOn: Boolean) {
        // Renkleri XML'den al
        // Fragment içinde
        val fromColor = if (isEgoOn) ContextCompat.getColor(requireContext(), R.color.anim_color) else ContextCompat.getColor(requireContext(), R.color.black)
        val toColor = if (isEgoOn) ContextCompat.getColor(requireContext(), R.color.black) else ContextCompat.getColor(requireContext(), R.color.anim_color)

        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), fromColor, toColor)
        colorAnimation.duration = 1000 // 1 saniyelik animasyon süresi

        colorAnimation.addUpdateListener { animator ->
            val animatedColor = animator.animatedValue as Int
            binding.arkaplangecis.setBackgroundColor(animatedColor)

            // Arka plana göre metin rengini değiştir
            val textColor = if (animatedColor == ContextCompat.getColor(requireContext(), R.color.black)) ContextCompat.getColor(requireContext(), R.color.anim_color) else ContextCompat.getColor(requireContext(), R.color.black)
            // Tüm TextView'lerin rengini güncelle
            binding.welcomeTextView.setTextColor(textColor)
            binding.happinessTextView.setTextColor(textColor)
            binding.optimismTextView.setTextColor(textColor)
            binding.kindnessTextView.setTextColor(textColor)
            binding.givingTextView.setTextColor(textColor)
            binding.respectTextView.setTextColor(textColor)
            binding.egoTextView.setTextColor(textColor)

            if (animatedColor == ContextCompat.getColor(requireContext(), R.color.anim_color)) {
                // Arka plan beyaza dönerken metni görünür yap ve yavaşça göster
                binding.welcomeTextView.apply {
                    text = "Işığa hoş geldin" // Mesajı ayarla
                    visibility = View.VISIBLE
                    animate().alpha(1f).duration = 1000 // Metni yavaşça görünür yap
                }

                // 5 saniye sonra metni yavaşça kaybet
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.welcomeTextView.animate().alpha(0f).duration = 1000
                }, 5000)
            } else {
                // Ego switch'i açıkken siyah ekranda metni beyaz yap ve göster
                binding.welcomeTextView.apply {
                    text = "Ego'nun karanlığı içindesin"
                    visibility = View.VISIBLE
                    animate().alpha(1f).duration = 1000 // Metni yavaşça görünür yap
                }
            }
        }

        colorAnimation.start()
    }


    private fun updateRiveAnimation(isEgoOn: Boolean) {
        // Animasyonun Rive kaynağını ve State Machine'ini ayarlayın
        binding.riveView.setRiveResource(
            resId = R.raw.on_off_switch,
            stateMachineName = "State Machine 1"
        )

        // Ego switch durumuna göre animasyonu güncelle
        if (isEgoOn) {
            // Ego açıkken animasyon "off" durumuna geçsin
            binding.riveView.setBooleanState("State Machine 1", "On/Of", true)
        } else {
            // Ego kapalıyken animasyon "on" durumuna geçsin
            binding.riveView.setBooleanState("State Machine 1", "On/Of", false)
        }
    }

    private fun updateLottieAnimation(isEgoOn: Boolean) {

        binding.lottieAnimationView.visibility = View.VISIBLE // Animasyonu göster

        if (isEgoOn) {
            // Ego switch açıkken 'Sad' animasyonunu oynat
            binding.lottieAnimationView.setAnimation(R.raw.sad_anim)

        }else {
            // Ego switch kapalıyken 'Smile' animasyonunu oynat
            binding.lottieAnimationView.setAnimation(R.raw.smile_anim)
        }
        // Animasyonu başlat
        binding.lottieAnimationView.playAnimation()

    }
}

