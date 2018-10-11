package vn.rogue.filterview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import com.google.android.flexbox.FlexboxLayout

class ChipView : LinearLayout {

    private var flChips: FlexboxLayout? = null
    private var edtSearch: EditText? = null
    private var lsvList: ListView? = null
    private var adapter: ChipAdapter? = null
    private var searchAdapter: SearchAdapter? = null

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val view = View.inflate(context, R.layout.filter_view, this)
        flChips = view.findViewById(R.id.fblTags)
        edtSearch = view.findViewById(R.id.edtSearch)
        lsvList = view.findViewById(R.id.lsvList)
    }

    fun setAdapter(adapter: ChipAdapter) {
        this.adapter = adapter
        adapter.setChipView(this)
        searchAdapter = SearchAdapter(context, adapter)
        lsvList!!.adapter = searchAdapter
        edtSearch!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                searchAdapter?.filter?.filter(editable.toString())
            }
        })
    }

    fun notifyDataSetChanged() {
        refreshFlexbox()
        searchAdapter!!.notifyDataSetChanged()
    }

    private fun refreshFlexbox() {
        for (i in flChips!!.childCount - 1 downTo 0) {
            if (flChips!!.indexOfChild(edtSearch) != i) {
                flChips?.removeViewAt(i)
            }
        }
        for (i in 0 until adapter!!.count) {
            if (adapter!!.isSelected(i)) {
                val v = adapter!!.createChip(context, i)
                flChips?.addView(v, 0)
            }
        }
    }
}
