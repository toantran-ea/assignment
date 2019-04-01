package mobi.toan.assignment.repository

import io.reactivex.Observable
import mobi.toan.assignment.TOKEN
import mobi.toan.assignment.network.CarApi

interface ManufacturerRepo {
    fun getManufacturers(pageSize: Int, pageIndex: Int): Observable<List<String>>
}

class ManufacturerRepoImpl(private val carApi: CarApi) : ManufacturerRepo {
    override fun getManufacturers(pageSize: Int, pageIndex: Int): Observable<List<String>> {
        return carApi.getManufacturers(page = pageIndex, pageSize = pageSize, token = TOKEN)
            .map {
                it.data.values.toList()
            }
    }
}
