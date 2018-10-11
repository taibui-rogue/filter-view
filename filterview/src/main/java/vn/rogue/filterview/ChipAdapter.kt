package vn.rogue.filterview

import android.content.Context
import android.view.View
import java.util.*

abstract class ChipAdapter {

    private var chipView: ChipView? = null

    var data = ArrayList<Any>()

    abstract val count: Int
    abstract fun getItem(pos: Int): Any
    abstract fun isSelected(pos: Int): Boolean

    abstract fun createSearchView(context: Context, is_checked: Boolean, pos: Int): View
    abstract fun createChip(context: Context, pos: Int): View

    fun setChipView(chipView: ChipView) {
        this.chipView = chipView
    }


    fun refresh() {
        chipView?.notifyDataSetChanged()
    }

}
