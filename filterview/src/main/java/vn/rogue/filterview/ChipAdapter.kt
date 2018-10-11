package vn.rogue.filterview

import android.content.Context
import android.view.View
import java.util.*

abstract class ChipAdapter<T: MatchByKeywords> {

    private var chipView: ChipView<T>? = null

    var data = ArrayList<T>()

    abstract val count: Int
    abstract fun getItem(pos: Int): Any
    abstract fun isSelected(pos: Int): Boolean

    abstract fun createSearchView(context: Context, isChecked: Boolean, pos: Int): View
    abstract fun createChip(context: Context, pos: Int): View

    fun setChipView(chipView: ChipView<T>) {
        this.chipView = chipView
    }


    fun refresh() {
        chipView?.notifyDataSetChanged()
    }

}
