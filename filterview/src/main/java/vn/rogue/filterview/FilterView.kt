package vn.rogue.filterview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout

class FilterView<T : MatchByKeywords> : LinearLayout {

    private var fblChips: FlexboxLayout? = null
    private var edtSearch: EditText? = null
    private var lsvList: ListView? = null
    private var adapter: TagAdapter<T>? = null
    private var searchAdapter: SearchAdapter<T>? = null

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        orientation = VERTICAL

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.FilterView)
        val searchBoxResId = attributes.getResourceId(R.styleable.FilterView_search_box, View.NO_ID)
        val tagsViewResId = attributes.getResourceId(R.styleable.FilterView_tags_view, View.NO_ID)
        val lsvListResId = attributes.getResourceId(R.styleable.FilterView_list_item, View.NO_ID)

        val searchView = if (searchBoxResId == View.NO_ID) {
            View.inflate(context, R.layout.search_box, null)
        } else {
            View.inflate(context, searchBoxResId, null)
        }
        addView(searchView)
        edtSearch = searchView.findViewById(R.id.edtSearch)


        val tagsView = if (tagsViewResId == View.NO_ID) {
            fblChips = FlexboxLayout(context)
            fblChips?.layoutParams?.height = ViewGroup.LayoutParams.WRAP_CONTENT
            fblChips?.flexWrap = FlexWrap.WRAP
            fblChips?.id = R.id.fblTags
            fblChips
        } else {
            val view = View.inflate(context, tagsViewResId, null)
            fblChips = view.findViewById(R.id.fblTags)
            view
        }
        addView(tagsView)

        val listView = if (lsvListResId == View.NO_ID) {
            lsvList = ListView(context)
            lsvList?.id = R.id.lsvList
            lsvList
        } else {
            val view = View.inflate(context, lsvListResId, null)
            lsvList = findViewById(R.id.lsvList)
            view
        }
        addView(listView)

        attributes.recycle()
    }

    fun setAdapter(adapter: TagAdapter<T>) {
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
        for (i in fblChips!!.childCount - 1 downTo 0) {
            if (fblChips!!.indexOfChild(edtSearch) != i) {
                fblChips?.removeViewAt(i)
            }
        }
        for (i in 0 until adapter!!.count) {
            if (adapter!!.isSelected(i)) {
                val v = adapter!!.createChip(context, i)
                fblChips?.addView(v, 0)
            }
        }
    }
}
