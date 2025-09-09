package com.survicate.fullstory.integration.example

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.survicate.surveys.Survicate

class MainActivity : AppCompatActivity() {

    private lateinit var editTextEventName: EditText
    private lateinit var buttonLog: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val root = findViewById<View>(R.id.root)
        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupViews()
    }

    private fun setupViews() {
        editTextEventName = findViewById(R.id.edittext_event_name)
        buttonLog = findViewById(R.id.button_log_event)
        buttonLog.setOnClickListener {
            val eventName = editTextEventName.text.toString()
            Survicate.invokeEvent(eventName)
        }
    }
}
