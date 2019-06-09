package com.krsolutions.daglearrn

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Qualifier

const val NEVER = "Never"
const val LOVE = "Love"
class MainActivity : AppCompatActivity() {

    val component = DaggerMagicBox.create()
    @Inject @field:Choose(NEVER) lateinit var infoNEVER: Info
    @Inject @field:Choose(LOVE) lateinit var infoSays: Info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.poke(this)
        textView.text = " ${infoNEVER.text} ${infoSays.text} "
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
    @Choose(NEVER)
    fun infoSaysNeverSayNever():Info{
        return Info("info says never say never")
    }

    @Provides
    @Choose(LOVE)
    fun infoSaysLove():Info{
        return Info("info says it loves dagger")
    }
}

@Qualifier
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Choose(val value: String = "")