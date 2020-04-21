package com.sundevs.base.di.component

import dagger.android.support.AndroidSupportInjectionModule
import android.app.Application
import com.sundevs.base.di.module.ActivityModule
import com.sundevs.base.di.module.FragmentBuildersModule
import com.sundevs.base.di.module.ViewModelModule


import com.sundevs.core.di.CoreModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
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
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}