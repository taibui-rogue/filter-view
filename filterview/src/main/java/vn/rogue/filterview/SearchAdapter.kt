package vn.rogue.filterview

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import java.util.*

class SearchAdapter(context: Context, private val adapter: ChipAdapter) : ArrayAdapter<String>(context, -1) {
    private var data = ArrayList<Any>()

    init {
        this.data = adapter.data
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val results = Filter.FilterResults()
                if (charSequence == null || charSequence.isEmpty()) {
                    results.values = adapter.data
                    results.count = adapter.data.size
                } else {
                    val tmp = ArrayList<Any>()
                    for (i in adapter.data.indices) {
                        if (adapter.data[i].toString().toLowerCase().indexOf(charSequence.toString().toLowerCase()) == 0) {
                            tmp.add(adapter.data[i])
                        }
                    }
                    results.values = tmp
                    results.count = tmp.size
                }
                return results
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                @Suppress("UNCHECKED_CAST")
                data = filterResults.values as ArrayList<Any>
                notifyDataSetChanged()
            }
        }
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val pos = adapter.data.indexOf(data[position])
        return if (pos != -1) {
            adapter.createSearchView(context, adapter.isSelected(pos), pos)
        } else {
            null
        }
    }
}
