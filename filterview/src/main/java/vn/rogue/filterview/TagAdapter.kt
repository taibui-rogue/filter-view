package vn.rogue.filterview

import android.content.Context
import android.view.View
import java.util.*

abstract class TagAdapter {

    private var filterView: FilterView? = null

    var data = ArrayList<MatchByKeywords>()

    abstract val count: Int
    abstract fun getItem(pos: Int): Any
    abstract fun isSelected(pos: Int): Boolean

    abstract fun createSearchView(context: Context, isChecked: Boolean, pos: Int): View
    abstract fun createTag(context: Context, pos: Int): View

    fun setFilterView(filterView: FilterView) {
        this.filterView = filterView
    }


    fun refresh() {
        filterView?.notifyDataSetChanged()
    }

}
