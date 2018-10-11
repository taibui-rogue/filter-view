package vn.rogue.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import vn.rogue.filterview.ChipAdapterImpl
import vn.rogue.filterview.ChipView
import vn.rogue.filterview.MatchByKeywords


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cvTag = findViewById<View>(R.id.cvTag) as ChipView<Data>
        val data = ArrayList<Data>()
        data.add(Data("First Item"))
        data.add(Data("Second Item"))
        data.add(Data("Third Item"))
        data.add(Data("Forth Item"))
        data.add(Data("Fifth Item"))
        data.add(Data("Sixth Item"))
        data.add(Data("Seventh Item"))
        val adapter = ChipAdapterImpl(data)
        cvTag.setAdapter(adapter)
    }
}

class Data(val name: String): MatchByKeywords {

    override fun toString(): String = name

    override fun match(keywords: String): Boolean = match(keywords, name)
}
