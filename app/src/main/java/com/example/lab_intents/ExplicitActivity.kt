package com.example.lab_intents

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_explicit.*

class ExplicitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)

        btnOkej.setOnClickListener{ _ ->
            Intent().let{ intent ->
                intent.putExtra("explicitText", explicitEditText.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }
}