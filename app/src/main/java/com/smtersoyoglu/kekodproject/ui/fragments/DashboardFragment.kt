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
import androidx.appcompat.widget.SwitchCompat
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
        val fromColor = if (isEgoOn) Color.WHITE else Color.BLACK
        val toColor = if (isEgoOn) Color.BLACK else Color.WHITE

        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), fromColor, toColor)
        colorAnimation.duration = 1000 // 1 saniyelik animasyon süresi

        colorAnimation.addUpdateListener { animator ->
            val animatedColor = animator.animatedValue as Int
            binding.arkaplangecis.setBackgroundColor(animatedColor)

            if (animatedColor == Color.WHITE) {
                // Arka plan beyaza dönüyorsa yazıyı görünür hale getir
                binding.welcomeTextView.visibility = View.VISIBLE

                // 5 saniye sonra yazıyı tekrar gizle
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.welcomeTextView.visibility = View.INVISIBLE
                }, 5000) // 5 saniye sonra kaybolacak
            }
        }

        colorAnimation.start()
    }
}



