package com.example.jsonpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var incel: EditText
    lateinit var tv: TextView
    var dat = arrayListOf<celep.dat>()
    lateinit var prog: ProgressBar
    lateinit var tvw: TextView
    lateinit var add: Button
    var apif = APIClient().getClient()?.create(APIInterface::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        add.setOnClickListener {
            wait(true)
            CoroutineScope(Dispatchers.IO).launch {
                ref()
                while(true) {
                    if(dat.size>0) {
                        this@MainActivity.runOnUiThread { tv.text = dat[incel.text.toString().toInt()-1].name }
                        break
                    }
                }
            }
        }


    }
    fun ref() {


        if (apif != null) {
            apif!!.getdat()?.enqueue(object : Callback<List<celep.dat>> {
                override fun onResponse(
                    call: Call<List<celep.dat>>,
                    response: Response<List<celep.dat>>
                ) {
                    for (i in response.body()!!) {
                        dat.add(i)

                    }
                    wait(false)

                }

                override fun onFailure(call: Call<List<celep.dat>>, t: Throwable) {

                    wait(false)
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show();
                }

            })
        }
    }

    fun init() {
        tv=findViewById(R.id.tv)
        incel=findViewById(R.id.ed1)
        add=findViewById(R.id.button)
        tvw = findViewById(R.id.wait)
        prog = findViewById(R.id.progressBar)
    }

    fun wait(a: Boolean) {
        if (a) {
            prog.isVisible = true
            tvw.isVisible = true
        } else {
            prog.isVisible = false
            tvw.isVisible = false
        }

    }
}