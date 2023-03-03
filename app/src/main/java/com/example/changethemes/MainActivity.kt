package com.example.changethemes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StyleRes

class MainActivity : AppCompatActivity() {

    val APP_THEME_DEFAULT_STYLE = 0
    val APP_THEME_MY_COOL_STYLE = 1
    val APP_THEME_MY_PINK_STYLE = 2
    val APP_THEME_MY_DARK_STYLE = 3

    val PREFERENCES_NAME = "main"
    val THEME_NAME = "theme"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(loadAppTheme())
        setContentView(R.layout.activity_main)

        // сохранение чек-бокса в выбранной теме
        // этот метод еще не до конца доработан (чек-бокс не отображдается)
        val currentTheme = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
            .getInt(THEME_NAME, APP_THEME_DEFAULT_STYLE)
        if (currentTheme == APP_THEME_MY_COOL_STYLE) {
            findViewById<View>(R.id.rb_MyCoolStyle).isSelected = true
        } else if (currentTheme == APP_THEME_MY_PINK_STYLE) {
            findViewById<View>(R.id.rb_MyPinkStyle).isSelected = true
        } else if (currentTheme == APP_THEME_MY_DARK_STYLE) {
            findViewById<View>(R.id.rb_MyDarkStyle).isSelected = true
        }

        // выбор темы
        findViewById<View>(R.id.rb_MyCoolStyle).setOnClickListener { v: View? ->
            saveAppTheme(APP_THEME_MY_COOL_STYLE)
            recreate()
        }

        findViewById<View>(R.id.rb_MyPinkStyle).setOnClickListener { v: View? ->
            saveAppTheme(APP_THEME_MY_PINK_STYLE)
            recreate()
        }

        findViewById<View>(R.id.rb_MyDarkStyle).setOnClickListener { v: View? ->
            saveAppTheme(APP_THEME_MY_DARK_STYLE)
            recreate()
        }


    }


    // переводим конктретную тему в число
    @StyleRes
    private fun codeStyleToStyleID(codeStyle: Int): Int {
        return when (codeStyle) {
            APP_THEME_MY_COOL_STYLE -> R.style.MyCoolStyle
            APP_THEME_MY_PINK_STYLE -> R.style.MyPinkStyle
            APP_THEME_MY_DARK_STYLE -> R.style.MyDarkStyle
            APP_THEME_DEFAULT_STYLE -> R.style.Theme_ChangeThemes
            else -> R.style.Theme_ChangeThemes
        }
    }


    fun saveAppTheme(code: Int) {
        getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
            .edit()
            .putInt(THEME_NAME, code)
            .apply()
    }

    private fun loadAppTheme(): Int {
        val codeTheme = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
            .getInt(THEME_NAME, APP_THEME_DEFAULT_STYLE)
        return codeStyleToStyleID(codeTheme)
    }

}