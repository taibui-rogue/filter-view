package vn.rogue.filterview

import android.content.Context
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class ChipAdapterImpl<T : MatchByKeywords>(searchData: ArrayList<T>) : ChipAdapter<T>() {

    private var searchData = ArrayList<T>()
    private var chips = ArrayList<T>()

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
        return chips.contains(searchData[pos])
    }

    override fun createSearchView(context: Context, isChecked: Boolean, pos: Int): View {
        val view = View.inflate(context, R.layout.item_search, null)
        val cbCheck = view.findViewById<CheckBox>(R.id.cbCheck)
        cbCheck.text = searchData[pos].toString()
        cbCheck.isChecked = isChecked
        cbCheck.setOnCheckedChangeListener { _, b ->
            if (b) {
                chips.add(searchData[pos])
                refresh()
            } else {
                chips.remove(searchData[pos])
                refresh()
            }
        }
        return view
    }

    override fun createChip(context: Context, pos: Int): View {
        val view = View.inflate(context, R.layout.tag, null)
        val tvChip = view.findViewById<TextView>(R.id.tvChip)
        tvChip.text = searchData[pos].toString()
        val ivClose = view.findViewById<ImageView>(R.id.ivClose)
        ivClose.setOnClickListener {
            chips.remove(searchData[pos])
            refresh()
        }
        return view
    }

}
