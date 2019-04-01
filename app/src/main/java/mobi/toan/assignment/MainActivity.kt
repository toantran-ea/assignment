package mobi.toan.assignment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mobi.toan.assignment.di.AppProviderInstance

class MainActivity : AppCompatActivity() {
    lateinit var disposable: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        disposable = AppProviderInstance.provideManufacturerRepo()
            .getManufacturers(pageIndex = 1, pageSize = PAGE_SIZE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("ITEMS", it.toString())
            }, {
                Log.e("AAAA", it.message)
            })
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
