package vn.rogue.filterview

import android.content.Context
import android.view.View
import java.util.*

abstract class TagAdapter<T: MatchByKeywords> {

    private var filterView: FilterView<T>? = null

    var data = ArrayList<T>()

    abstract val count: Int
    abstract fun getItem(pos: Int): Any
    abstract fun isSelected(pos: Int): Boolean

    abstract fun createSearchView(context: Context, isChecked: Boolean, pos: Int): View
    abstract fun createChip(context: Context, pos: Int): View

    fun setChipView(filterView: FilterView<T>) {
        this.filterView = filterView
    }


    fun refresh() {
        filterView?.notifyDataSetChanged()
    }

}
