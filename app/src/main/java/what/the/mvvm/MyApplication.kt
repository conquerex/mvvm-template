package what.the.mvvm

import android.app.Application
import org.koin.android.ext.android.startKoin
import what.the.mvvm.di.myDiModule

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, myDiModule)
    }
}