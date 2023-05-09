package com.example.cuton.activities

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.example.cuton.R
import com.example.cuton.databinding.ActivityAuthorizationScreenBinding
import com.example.cuton.network.*
//import okhttp3.MultipartBody
//import okhttp3.RequestBody
//import okhttp3.RequestBody.Companion.toRequestBody
//import okio.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class AuthorizationScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthorizationScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthorizationScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // add the default theme
        setTheme(R.style.Theme_CutOn)

//        login = binding.editTextLogin.text.toString()
//        password = binding.editTextPassword.text.toString()

        // #2.2
        Log.i(
            "point #2.2",
            "Підключаємось до API ${ServiceGenerator.getApiAddress()}/app/version/latest/"
        )
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)

        Log.i("point #2.2.1", "GET-параметри запиту: ?v=${NetworkObject.getV()}")
        val call = serviceGenerator.getAnswer(NetworkObject.getV())

        call.enqueue(object : Callback<VersionModel> {
            override fun onResponse(call: Call<VersionModel>, response: Response<VersionModel>) {
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                Log.i("point #2.2.2", "Отримана JSON відповідь: ${response.body()}")
                val answer = response.body()!!.answer!!

                when (answer.toInt()) {
                    2 -> {
                        Log.i(
                            "point #2.2.2.1",
                            "answer = 2 - виводемо повідомлення, версія додатку занадто застаріла"
                        )
                        updateToast(
                            this@AuthorizationScreenActivity,
                            "Версія додатку занадто застаріла, авторизація без оновлення не можлива"
                        )
                    }
                    1 -> {
                        Log.i(
                            "point #2.2.2.2",
                            "answer = 1 - виводемо повідомлення, є більш нова версія додатку"
                        )
                        updateToast(
                            this@AuthorizationScreenActivity,
                            "Доступна нова версія додатку"
                        )
                    }
                }
            }

            override fun onFailure(call: Call<VersionModel>, t: Throwable) {
                t.printStackTrace()
                Log.e("point #2.2.x.x", t.message.toString())
            }
        })

        // #2.3
        binding.editTextLogin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                login = s.toString()
                Log.i("point #2.3", "Ведено дані в поле login $login")
            }
        })

        binding.editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                password = s.toString()
                Log.i("point #2.3", "Ведено дані в поле password $password")
            }
        })

        binding.buttonLogin.setOnClickListener {

            Log.i("point #2.3", "Кнопка Логін натиснута")
            Log.i(
                "point #2.3",
                "Підключаємось до API ${ServiceGenerator.getApiAddress()}users/login/"
            )

            val call = serviceGenerator.token(
                login,
                password,
                devman,
                devmod,
                devavs,
                devaid
            )

            Log.i("point #2.3", "Відправлений post запит: ${call.request()}")

            call.enqueue(object : Callback<TokenModel> {
                override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    Log.i("point #2.3", "Отримана відповідь post запиту: ${response.body()}")
                }

                override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("point #2.3", "fun onFailure -> ${t.message.toString()}")
                }
            })
            Log.i("point #2.3", "Кінець post запиту")
        }
    }

    companion object {
        private var login = "380501234567"
        private var password = "123456"

        //#2.1
        private var devman = Build.MANUFACTURER.toString()
        private var devmod = Build.MODEL.toString()
        private var devavs = Build.VERSION.RELEASE.toString()
        private var devaid = Build.ID.toString()

        init {
            Log.i("point #2.1", "Збираємо дані про пристрій")
            Log.i("point #2.1.1", "виробник пристрою - devman > $devman")
            Log.i("point #2.1.2", "модель пристрою - devmod > $devmod")
            Log.i("point #2.1.3", "версія ОС - devavs > $devavs")
            Log.i("point #2.1.4", "ID ОС - devaid > $devaid")
        }
    }
}

private fun updateToast(context: Context, mes: String) {
    Toast.makeText(
        context,
        mes,
        Toast.LENGTH_SHORT
    ).show()

    Log.i("point #2.2.x.x", mes)
}



