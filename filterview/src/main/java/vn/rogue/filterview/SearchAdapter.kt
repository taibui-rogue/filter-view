package vn.rogue.filterview

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import java.util.*

class SearchAdapter<T : MatchByKeywords>(context: Context, private val adapter: TagAdapter<T>) : ArrayAdapter<T>(context, -1) {
    private var data = ArrayList<T>()

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
                    val tmp = ArrayList<T>()
                    for (i in adapter.data.indices) {
                        if (adapter.data[i].match(charSequence.toString())) {
                            tmp.add(adapter.data[i])
                        }
                    }
                    results.values = tmp
                    results.count = tmp.size
                }
                return results
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
//                Log.e("+++", "" + filterResults.values)
//                if (filterResults.values == null) return

                @Suppress("UNCHECKED_CAST")
                data = filterResults.values as ArrayList<T>
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
