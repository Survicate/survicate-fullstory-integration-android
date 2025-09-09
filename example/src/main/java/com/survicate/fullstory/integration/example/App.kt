package com.survicate.fullstory.integration.example

import android.app.Application
import android.util.Log
import com.fullstory.FS
import com.survicate.fullstory.integration.SurvicateFullStoryIntegration
import com.survicate.surveys.Survicate

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initializeSurvicate()

        FS.setReadyListener {
            Log.d("FullStory", "FullStory is ready")
        }
    }

    private fun initializeSurvicate() {
        Survicate.setWorkspaceKey(getString(R.string.survicate_key))
        Survicate.init(this)
        Survicate.addEventListener(SurvicateFullStoryIntegration())
    }
}
