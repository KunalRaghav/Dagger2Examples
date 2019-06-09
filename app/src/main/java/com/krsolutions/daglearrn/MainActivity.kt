package com.krsolutions.daglearrn

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dagger.Component
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val component = DaggerMagicBox.create()
    @Inject lateinit var info: Info
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.poke(this)
        textView.text = info.text

    }



}
class Info @Inject constructor(){
    val text = "Hello Info"
}

@Component
interface MagicBox{
    fun poke(app:MainActivity)
}
