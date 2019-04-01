package mobi.toan.assignment.network

import io.reactivex.Observable
import mobi.toan.assignment.network.model.BuildDate
import mobi.toan.assignment.network.model.MainType
import mobi.toan.assignment.network.model.Manufacturer
import retrofit2.http.GET
import retrofit2.http.Query

interface CarApi {
    @GET("v1/car-types/manufacturer")
    fun getManufacturers(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("wa_key") token: String
    ): Observable<Manufacturer>

    @GET("v1/car-types/main-types")
    fun getMainTypes(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("manufacturer") manufacturer: Int,
        @Query("wa_key") token: String
    ): Observable<MainType>

    @GET("v1/car-types/built-dates")
    fun getBuiltDates(
        @Query("wa_key") token: String,
        @Query("manufacturer") manufacturer: String,
        @Query("main-type") mainType: String
    ): Observable<BuildDate>
}
