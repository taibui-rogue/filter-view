package vn.rogue.filterview

import android.content.Context
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

open class TagAdapterImpl(searchData: ArrayList<MatchByKeywords>) : TagAdapter() {

    var searchData = ArrayList<MatchByKeywords>()
    var tags = ArrayList<MatchByKeywords>()

    open val listItemResId get() = R.layout.item_search
    open val tagResId get() = R.layout.tag

    override val count: Int
        get() = searchData.size

    init {
        this.searchData = searchData
        this.data = searchData
    }

    override fun getItem(pos: Int): Any {
        return searchData[pos]
    }

    override fun isSelected(pos: Int): Boolean {
        return tags.contains(searchData[pos])
    }

    override fun createSearchView(context: Context, isChecked: Boolean, pos: Int): View {
        val view = View.inflate(context, listItemResId, null)
        val cbCheck = view.findViewById<CheckBox>(R.id.cbCheck)
        cbCheck.text = searchData[pos].toString()
        cbCheck.isChecked = isChecked
        cbCheck.setOnCheckedChangeListener { _, b ->
            if (b) {
                tags.add(searchData[pos])
                refresh()
            } else {
                tags.remove(searchData[pos])
                refresh()
            }
        }
        return view
    }

    override fun createTag(context: Context, pos: Int): View {
        val view = View.inflate(context, tagResId, null)
        val tvChip = view.findViewById<TextView>(R.id.tvChip)
        tvChip.text = searchData[pos].toString()
        val ivClose = view.findViewById<ImageView>(R.id.ivClose)
        ivClose.setOnClickListener {
            tags.remove(searchData[pos])
            refresh()
        }
        return view
    }

}
