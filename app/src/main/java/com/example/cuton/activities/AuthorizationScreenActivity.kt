package com.example.cuton.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.example.cuton.R
import com.example.cuton.databinding.ActivityAuthorizationScreenBinding
import com.example.cuton.Retrofit.*
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

        login = "380501234567" // binding.editTextLogin.text.toString()
        password = "123456" // binding.editTextPassword.text.toString()

        // #2.2
        Log.i("point #2.2", "Підключаємось до API")
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)

        val call = serviceGenerator.getAnswer(Network.getV())

        call.enqueue(object : Callback<VersionModel> {
            override fun onResponse(call: Call<VersionModel>, response: Response<VersionModel>) {

                if (!response.isSuccessful) throw IOException("Unexpected code $response") // TODO fis first runtime error

                val answer = response.body()!!.answer!!
                when (answer.toInt()) {
                    2 -> updateToast("Версія додатку занадто застаріла, авторизація без оновлення не можлива")
                    1 -> updateToast("Доступна нова версія додатку")
                }
            }

            override fun onFailure(call: Call<VersionModel>, t: Throwable) {
                t.printStackTrace()
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
            Log.i("point #2.3", "Підключаємось до API")

            val call = serviceGenerator.getToken(
                login,
                password,
                devman,
                devmod,
                devavs,
                devaid
            )

            call.enqueue(object : Callback<TokenModel> {
                override fun onResponse(
                    call: Call<TokenModel>,
                    response: Response<TokenModel>
                ) {
                    when (response.code()) {
                        200 -> {
                            Network.setToken(response.body()!!.token!!)

                            // #2.3.3.2
                            Handler(Looper.getMainLooper()).postDelayed({
                                val intent = Intent(
                                    this@AuthorizationScreenActivity,
                                    HomeMenuActivity::class.java
                                )

                                Log.i("point #2.3.3.2", "Відкриваємо наступний екран HomeMenu")

                                startActivity(intent)
                                finish()
                            }, 2000) // This the delayed time in milliseconds.
                        }

                        404 -> {
                            Toast.makeText(
                                this@AuthorizationScreenActivity,
                                "Неправильний логін або пароль",
                                Toast.LENGTH_LONG
                            ).show()
                            Log.i(
                                "point #2.3.4",
                                "TODO --->>> \"detail\":\"Wrong login or password\"  -- ${response.body()}"
                            ) // TODO fix response.body()
                        }

                        422 -> {
                            Toast.makeText(
                                this@AuthorizationScreenActivity,
                                "Помилка запросу",
                                Toast.LENGTH_LONG
                            ).show()
                            Log.i(
                                "point #2.3.4",
                                "Помилка запросу - 422  -- ${response.body()}"
                            ) // TODO fix response.body()
                        }
                    }
                }

                override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("point #2.3", "fun onFailure -> ${t.message.toString()}")
                }
            })
            Log.i(
                "point #2.3",
                "Кінець post запиту"
            ) // TODO fix first close post request after open new activity
        }
    }

    companion object {
        private lateinit var login: String
        private lateinit var password: String

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

    private fun updateToast(mes: String) {
        Toast.makeText(
            this,
            mes,
            Toast.LENGTH_SHORT
        ).show()

        Log.i("point #2.2.x.x", mes)
    }
}





