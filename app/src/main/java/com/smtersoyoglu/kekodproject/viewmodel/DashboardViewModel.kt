package com.smtersoyoglu.kekodproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smtersoyoglu.kekodproject.utils.Constants
import com.smtersoyoglu.kekodproject.R
import com.smtersoyoglu.kekodproject.data.MenuItemData

class DashboardViewModel : ViewModel() {

    val isEgoSwitchChecked = MutableLiveData(true) // Ego switch başlangıçta açık
    val isOtherSwitchesEnabled = MutableLiveData(false) // Diğer switch'ler başlangıçta kapalı
    val isBottomNavVisible = MutableLiveData(false) // BottomNavigation başlangıçta gizli

    private val _menuItems = MutableLiveData<List<MenuItemData>>()
    val menuItems: LiveData<List<MenuItemData>> get() = _menuItems

    private val activeSwitches = mutableListOf<MenuItemData>()

    // Switch'lerin durumları (aktif/pasif) tutan map
    val switchStates = mutableMapOf(
        R.id.happinessSwitch to MutableLiveData(false),
        R.id.optimismSwitch to MutableLiveData(false),
        R.id.kindnessSwitch to MutableLiveData(false),
        R.id.givingSwitch to MutableLiveData(false),
        R.id.respectSwitch to MutableLiveData(false)
    )

    private val switchData = listOf(
        MenuItemData(R.id.happinessSwitch, "Happiness", R.drawable.ic_happy),
        MenuItemData(R.id.optimismSwitch, "Optimism", R.drawable.ic_optimism),
        MenuItemData(R.id.kindnessSwitch, "Kindness", R.drawable.ic_kindness),
        MenuItemData(R.id.givingSwitch, "Giving", R.drawable.ic_giving),
        MenuItemData(R.id.respectSwitch, "Respect", R.drawable.icon_respect)
    )

    init {
        updateMenuItems()
    }

    fun onEgoSwitchChanged(isChecked: Boolean) {
        isEgoSwitchChecked.value = isChecked
        isOtherSwitchesEnabled.value = !isChecked
        isBottomNavVisible.value = !isChecked

        if (isChecked) {
            // Ego switch açık olduğunda tüm diğer switch'leri kapat
            activeSwitches.clear()
            switchStates.forEach { (id, state) -> state.value = false }
            updateMenuItems()
        } else {
            updateMenuItems()
        }
    }

    fun onSwitchChanged(switchId: Int, isChecked: Boolean) {
        val switchInfo = switchData.firstOrNull { it.id == switchId }
        switchInfo?.let {
            if (isEgoSwitchChecked.value == false) {
                // Eğer aktif switch sayısı 4'e ulaşmışsa ve başka bir switch açılmaya çalışılıyorsa, engelle
                if (isChecked && activeSwitches.size >= Constants.MAX_ACTIVE_SWITCHES && !activeSwitches.contains(it)) {
                    // 5. switch açılmaya çalışılıyor, engelle
                    switchStates[switchId]?.value = false
                    return
                }

                if (isChecked) {
                    if (!activeSwitches.contains(it) && activeSwitches.size < Constants.MAX_ACTIVE_SWITCHES) {
                        activeSwitches.add(it)
                    }
                } else {
                    activeSwitches.remove(it)
                }
                switchStates[switchId]?.value = isChecked
            }
            updateMenuItems()
        }
    }



    private fun updateMenuItems() {
        val updatedList = mutableListOf<MenuItemData>()

        // "Home" butonunu ekleme
        updatedList.add(MenuItemData(R.id.dashboardFragment, "Home", R.drawable.ic_home))

        if (isEgoSwitchChecked.value == false) {
            updatedList.addAll(activeSwitches.take(Constants.MAX_ACTIVE_SWITCHES))
        }

        _menuItems.value = updatedList
    }
}
