package com.mrcd.router

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mrcd.router.annotation.Router

@Router(path = "测试 url")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}