package com.santiago.mvvm.di.component

import dagger.android.support.AndroidSupportInjectionModule
import android.app.Application
import com.santiago.mvvm.AppController
import com.santiago.mvvm.di.module.*
import com.santiago.mvvm.di.module.ViewModelModule
import com.sundevs.core.di.ApiModule
import com.sundevs.core.di.CoreModule
import com.sundevs.core.di.DbModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        CoreModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        FragmentBuildersModule::class,
        AndroidSupportInjectionModule::class]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
    fun inject(appController: AppController)
}