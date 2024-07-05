package com.example.application_flamingo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

class TEST_ACTIVITY : AppCompatActivity() {
    private val tf = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        tf.beginTransaction().replace(R.id.test_fragment, FRAGMENT_TEST()).commit()
    }
}