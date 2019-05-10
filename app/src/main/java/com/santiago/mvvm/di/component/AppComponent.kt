package com.santiago.mvvm.di.component

import dagger.android.support.AndroidSupportInjectionModule
import android.app.Application
import com.santiago.mvvm.AppController
import com.santiago.mvvm.di.module.*
import com.santiago.mvvm.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ApiModule::class,
        DbModule::class,
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