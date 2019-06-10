package com.krsolutions.daglearrn

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Scope

class MainActivity : AppCompatActivity() {

    private lateinit var magicBox: MagicBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        magicBox = DaggerMagicBox.create()

        btn_create.setOnClickListener {
            val storage = Storage()
            magicBox.poke(storage)
            textView.text = "Unique ${storage.uniqueMagic.count}" + "\nNormal ${storage.normalMagic.count} "
        }
    }
}

@MagicScope
@Component
interface MagicBox {
    fun poke(storage: Storage)
}

class Storage {
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