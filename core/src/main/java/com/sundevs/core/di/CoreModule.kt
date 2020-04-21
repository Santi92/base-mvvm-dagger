package com.sundevs.core.di

import dagger.Module

@Module(includes = [ApiModule::class, DbModule::class])
class CoreModule {


}