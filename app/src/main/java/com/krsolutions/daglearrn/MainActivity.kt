package com.krsolutions.daglearrn

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Scope
import javax.inject.Singleton

class MainActivity : AppCompatActivity() {

    private lateinit var magicBox: MagicBox
    private lateinit var mainBox: SingletonBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainBox = DaggerSingletonBox.create()
        btn_create.setOnClickListener {
            magicBox = mainBox.getMagicBox()
            useStorage()
        }
        btn_reuse.setOnClickListener{
            useStorage()
        }
    }
    private fun useStorage() {
        val storage = Storage()
        magicBox.poke(storage)
        textView.text =
            "\nSingletonOne ${storage.singletonOne.count} " +
                    "\nUniqueMagic ${storage.uniqueMagic.count}" +
                    "\nNormalMagic ${storage.normalMagic.count} "
    }
}

@Singleton
@Component
interface SingletonBox {
    fun getMagicBox(): MagicBox
}


@MagicScope
@Subcomponent
interface MagicBox {
    fun poke(storage: Storage)
}

class Storage {
    @Inject lateinit var singletonOne: SingletonOne
    @Inject lateinit var uniqueMagic: UniqueMagic
    @Inject lateinit var normalMagic: NormalMagic
}

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class MagicScope

var staticCounter = 0

@MagicScope
class UniqueMagic @Inject constructor() {
    val count = staticCounter++
}

class NormalMagic @Inject constructor() {
    val count = staticCounter++
}

@Singleton
class SingletonOne @Inject constructor() {
    val count = staticCounter++
}
