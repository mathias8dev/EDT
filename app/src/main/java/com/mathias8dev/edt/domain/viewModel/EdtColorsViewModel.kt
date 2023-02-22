package com.mathias8dev.edt.domain.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.mathias8dev.edt.domain.data.TSCSState
import com.mathias8dev.edt.utils.lighterVariant

class EdtColorsViewModel : ViewModel() {

    val _colors = listOf(
        Color(0xFFE00D52),
        Color(0xFFBB0CE2),
        Color(0xFF3134E0),
        Color(0xFF8385DA),
        Color(0xFF009688),
        Color(0xFF8BC34A),
        Color(0xFFCA9A06),
        Color(0xFFB65D4D),
        Color(0xFF63A856),
        Color(0xFF21ACA7),
        Color(0xFFC2192C),
        Color(0xFF12BEA5),
        Color(0xFF584F5C),
        Color(0xFFD55B25),
        Color(0xFF0D103D),
    )

    private val _tsColors = _colors.map {
        mutableStateOf(
            TSCSState(
                it,
                it.lighterVariant(0.5f),
                it.lighterVariant(0.7f),
                null
            )
        )
    }

    val tsColors: List<MutableState<TSCSState>>
        get() = _tsColors

    val defaultColor: MutableState<Color>
        get() = mutableStateOf(_colors[4])


    fun update(tscState: MutableState<TSCSState>, selectedColor: Color) {
        val found = tsColors.find { it.value.selectedColor != null }
        found?.value = found?.value!!.copy(selectedColor = null)
        tscState.value = tscState.value.copy(selectedColor = selectedColor)
    }

}