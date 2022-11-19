package com.example.lab_intents

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            txtMain.text = data?.getStringExtra("explicitText")
        }
    }

    private var resultLauncher2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            imageView.setImageURI(data?.data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnExplicitActivity.setOnClickListener {
            Intent(this, ExplicitActivity::class.java).let { intent ->
                resultLauncher.launch(intent)
            }
        }

        btnImplicitActivity.setOnClickListener {
            Intent().apply {
                action = "mk.ukim.finki.mpip.IMPLICIT_ACTION"
            }.let {
                startActivity(Intent.createChooser(it,"Choose the app for your intent"))
            }
        }

        btnSend.setOnClickListener {
            Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
            }.let {
                it.putExtra(Intent.EXTRA_TITLE, "MPiP Send Title")
                it.putExtra(Intent.EXTRA_TEXT, "Content send from MainActivity")
                startActivity(Intent.createChooser(it, "Choose the app for your intent"))
            }


            btnSelectPictures.setOnClickListener {
                Intent().apply {
                    action = Intent.ACTION_GET_CONTENT
                    type = "image/*"
                }.let {
                    //startActivity(Intent.createChooser(it, "Choose the app for your intent"))
                    resultLauncher2.launch(it)
                }
            }

            imageView.setOnClickListener {
                Intent().apply {
                    action = Intent.ACTION_VIEW
                    type = "image/*"
                }.let {
                    startActivity(Intent.createChooser(it, "Choose the app for your intent"))
                }
            }
        }
    }
}