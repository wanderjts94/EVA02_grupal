package pe.edu.idat.eva02_grupal.otros
import android.app.Application
class Myapp: Application() {
    companion object{
        lateinit var instant: Myapp
    }
    override fun onCreate() {
        super.onCreate()
        instant=this
    }
}