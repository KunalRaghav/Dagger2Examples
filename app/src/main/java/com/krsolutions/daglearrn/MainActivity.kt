package com.krsolutions.daglearrn

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dagger.Component
import dagger.Module
import dagger.Provides
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
class Info constructor(text:String){
    val text = text
}

@Component(modules = [Bag::class])
interface MagicBox{
    fun poke(app:MainActivity)
}

@Module
class Bag{
    @Provides
    fun infoSays():Info{
        return Info("info says never say never")
    }
}
