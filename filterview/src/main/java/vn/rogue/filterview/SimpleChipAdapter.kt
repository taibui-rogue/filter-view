package vn.rogue.filterview

import android.content.Context
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class SimpleChipAdapter(search_data: ArrayList<Any>) : ChipAdapter() {

    private var searchData = ArrayList<Any>()
    private var chips = ArrayList<Any>()

    override val count: Int
        get() = searchData.size

    init {
        this.searchData = search_data
        this.data = search_data
    }

    override fun getItem(pos: Int): Any {
        return searchData[pos]
    }

    override fun isSelected(pos: Int): Boolean {
        return chips.contains(searchData[pos])
    }

    override fun createSearchView(context: Context, is_checked: Boolean, pos: Int): View {
        val view = View.inflate(context, R.layout.item_search, null)
        val cbCheck = view.findViewById<CheckBox>(R.id.cbCheck)
        cbCheck.text = searchData[pos] as String
        cbCheck.isChecked = is_checked
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
        tvChip.text = searchData[pos] as String
        val ivClose = view.findViewById<ImageView>(R.id.ivClose)
        ivClose.setOnClickListener {
            chips.remove(searchData[pos])
            refresh()
        }
        return view
    }

}
