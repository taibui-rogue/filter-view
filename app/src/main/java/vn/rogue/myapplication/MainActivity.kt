package vn.rogue.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import vn.rogue.filterview.ChipView
import vn.rogue.filterview.ChipAdapterImpl



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cvTag = findViewById<View>(R.id.cvTag) as ChipView
        val data = ArrayList<Any>()
        data.add("First Item")
        data.add("Second Item")
        data.add("Third Item")
        data.add("Fourth Item")
        data.add("Fifth Item")
        data.add("Sixth Item")
        data.add("Seventh Item")
        val adapter = ChipAdapterImpl(data)
        cvTag.setAdapter(adapter)
    }
}
