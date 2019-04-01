package mobi.toan.assignment.di

import com.google.gson.Gson
import mobi.toan.assignment.BuildConfig
import mobi.toan.assignment.network.CarApi
import mobi.toan.assignment.repository.ManufacturerRepo
import mobi.toan.assignment.repository.ManufacturerRepoImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


// Developer comment: for simplicity and time-efficiency on this assignment
// I use ServiceLocator pattern instead of DI library (Dagger2/Kodein/etc)
interface AppProvider {
    fun provideCarApi(): CarApi

    fun provideManufacturerRepo(): ManufacturerRepo
}

object AppProviderInstance : AppProvider {
    override fun provideManufacturerRepo(): ManufacturerRepo {
        return ManufacturerRepoImpl(provideCarApi())
    }

    override fun provideCarApi(): CarApi {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.writeTimeout(30, TimeUnit.SECONDS)
        val client = builder.build()
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
        return retrofitBuilder.build().create(CarApi::class.java)
    }
}



