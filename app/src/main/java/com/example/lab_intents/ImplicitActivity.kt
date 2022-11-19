package com.example.lab_intents

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_implicit.*


class ImplicitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        recyclerView.adapter = ViewAdapter(loadData())
    }

    private fun loadData():MutableList<String> {
        val listItems = ArrayList<String>()
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        val pkgAppsList = packageManager.queryIntentActivities(mainIntent, 0)
        for (i in pkgAppsList.indices) {
            listItems.add(pkgAppsList[i].activityInfo.name)
        }
        return listItems
    }
}