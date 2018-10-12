package vn.rogue.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import vn.rogue.filterview.FilterView
import vn.rogue.filterview.MatchByKeywords
import vn.rogue.filterview.TagAdapterImpl


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cvTag = findViewById<View>(R.id.cvTag) as FilterView<Data>
        val data = ArrayList<Data>()
        data.add(Data("First Item", "112233"))
        data.add(Data("Second Item", "445566"))
        data.add(Data("Third Item", "778899"))
        data.add(Data("Forth Item", "123456"))
        data.add(Data("Fifth Item", "321456"))
        data.add(Data("Sixth Item", "554433"))
        data.add(Data("Seventh Item", "996633"))
        val adapter = MyAdapter(data)
        cvTag.setAdapter(adapter)
    }
}

class Data(val name: String, val key: String): MatchByKeywords {

    override fun toString(): String = name

    override fun match(keywords: String): Boolean = match(keywords, key)
}


class MyAdapter(searchData: ArrayList<Data>) : TagAdapterImpl<Data>(searchData) {
    override val listItemResId: Int
        get() = super.listItemResId

    override val tagResId: Int
        get() = R.layout.tag

}