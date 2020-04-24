
object Versions{
    val appcompat = "1.0.2"
    val androidx_core = "1.0.1"
    val androidx_constraint_layout = "1.1.3"
    val android_design = "28.0.0"
    val android_card_view = "28.0.0"
    /* Retrofit using RxJava2, Okhttp, Okhttp logging interceptor, Gson  */
    val retrofi = "2.4.0"
    val retrofiGso = "2.3.0"
    val rxjava = "2.1.1"
    var okHttp_interceptor = "3.9.1"
    val picasso = "2.71828"
    /* Android Architecture Component - LiveData & ViewModel  */
    val lifecycle = "1.1.1"
    /* Android Architecture Component - Room Persistance Lib  */
    val room = "1.1.1"
    val legacy_support = "1.0.0"
    val viewmodel = "2.0.0"
    /* Dagger2 - We are going to use dagger.android which includes
     * support for Activity and fragment injection so we need to include
     * the following dependencies */
    val dagger = "2.17"
    val lifecycle_compiler = "2.0.0"
    /*
     navagation
     */
    val navegation = "2.1.0-alpha03"
    /*
    test
     */
    val junit = "4.12"
    val runner = "1.1.1"
    val espreso = "3.1.1"
    val cardview = "1.0.0"
}

object Deps{

    val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val androidx_constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraint_layout}"
    val android_core_ktx = "androidx.core:core-ktx:${Versions.androidx_core}"
    val android_support_design = "com.android.support:design:${Versions.android_design}"
    val android_support_card_view =  "com.android.support:cardview-v7:${Versions.android_card_view}"

    val squareup_retrofit_retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofi}"
    val squareup_retrofit_convert_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofiGso}"
    val squareup_retrofit_adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofi}"
    val squareup_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp_interceptor}"

    /* RxAndroid  */
    val reactivex_rxjava_rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxjava}"

    /* Picasso lib for image loading  */
    val com_squareup_picasso = "com.squareup.picasso:picasso:${Versions.picasso}"

    /* Android Architecture Component - LiveData & ViewModel  */
    val android_arch_lifecycle_extensions = "android.arch.lifecycle:extensions:${Versions.lifecycle}"

    /* Android Architecture Component - Room Persistance Lib  */
    val android_arch_persistence_rom =  "android.arch.persistence.room:runtime:${Versions.room}"
    val android_arch_persistence_rxjava = "android.arch.persistence.room:rxjava2:${Versions.room}"
    val android_legacy_support_v4 = "androidx.legacy:legacy-support-v4:${Versions.legacy_support}"
    val androidx_lifecycle_extension = "androidx.lifecycle:lifecycle-extensions:${Versions.viewmodel}"
    val androidx_lifecycly_viewmodeil_ktx =  "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodel}"
    val androidx_arch_persistence_room_compiler = "android.arch.persistence.room:compiler:${Versions.room}"

    /* Dagger2 - We are going to use dagger.android which includes
     * support for Activity and fragment injection so we need to include
     * the following dependencies */
    val google_dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val google_dagger_android = "com.google.dagger:dagger-android:${Versions.dagger}"
    val goole_dagger_android_suppoprt = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    val google_dagger_android_processor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    /* Dagger2 - default dependency */
    val google_dagger_compiler = "com.google.dagger:dagger-compiler:$${Versions.dagger}"
    val androidx_lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle_compiler}"

    val androidx_nevigation_fragment = "androidx.navigation:navigation-fragment:${Versions.navegation}"
    val androidx_navegation_ui = "androidx.navigation:navigation-ui:${Versions.navegation}"

    val junit = "junit:junit:${Versions.junit}"
    val androidx_test_runner = "androidx.test:runner:${Versions.runner}"
    val androidx_espresso_core = "androidx.test.espresso:espresso-core:${Versions.espreso}"

    val androidx_cardview =  "androidx.cardview:cardview:${Versions.cardview}"
}

