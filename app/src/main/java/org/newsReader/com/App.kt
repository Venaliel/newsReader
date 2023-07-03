package org.newsReader.com

import android.content.Context
import android.content.res.Resources
import androidx.multidex.MultiDexApplication
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.BuildConfig
import com.orhanobut.logger.Logger
import org.newsReader.com.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : MultiDexApplication() {

    companion object {

        private var res: Resources? = null

        lateinit var context: Context

        private var instance: App? = null

        fun getInstance(): App? {
            return instance
        }

        fun getRes(): Resources? {
            return res
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        res = resources
        setupLoger()
        setupKoin()
    }

    private fun setupLoger() {
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    fun setupKoin() {
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}