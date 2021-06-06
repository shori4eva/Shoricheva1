package com.example.myapplication

import android.app.DownloadManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val baseurl = "https://developerslife.ru/latest/"
    var des: TextView? = null
    var str: String = ""
    var i = -1
    var j = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        des = findViewById(R.id.description)
    }

    fun getGif(view: View) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(Api::class.java)
        val call = api.us(j)
        call.enqueue(object : Callback<GifList> {

            override fun onResponse(call: Call<GifList>?, response: Response<GifList>?) {
                val ggif = response?.body()
                val giff = ggif?.gif
                val length = giff!!.size
                str = giff[i].description + "\n" + "автор: " + giff[i].author
                des!!.text = str
                Glide.with(this@MainActivity)
                    .asGif()
                    .load(giff[i].gifURL)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(imv)
            }

            override fun onFailure(call: Call<GifList>?, t: Throwable?) {
                Log.v("Error", t.toString())
            }
        })
        i += 1
        if (i == 5) {
            i = 0
            j += 1
        }
    }

    fun getBak(view: View) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(Api::class.java)
        val call = api.us(j)
        call.enqueue(object : Callback<GifList> {

            override fun onResponse(call: Call<GifList>?, response: Response<GifList>?) {
                val ggif = response?.body()
                val giff = ggif?.gif
                val length = giff!!.size
                str = giff[i].description + "\n" + "автор: " + giff[i].author
                des!!.text = str
                Glide.with(this@MainActivity)
                    .asGif()
                    .load(giff[i].gifURL)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(imv)
            }

            override fun onFailure(call: Call<GifList>?, t: Throwable?) {
                Log.v("Error", t.toString())
            }
        })
        i -= 1
        if (i == -1) {
            i = 5
            j -= 1
        }
    }
}




