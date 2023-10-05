package com.emmanuel_rono.e_shoppa.presentation.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.emmanuel_rono.e_shoppa.R
import com.emmanuel_rono.e_shoppa.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding:ActivitySplashScreenBinding
    //val navController = findNavController(R.id.nav_host_fragment_content_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }
        else
        {
            @Suppress("DEPRECIATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAGS_CHANGED
            )
        }
        val splas_screen=AnimationUtils.loadAnimation(this, R.anim.animation)
        binding.splashtext.animation=splas_screen
        binding.ImgIcon.animation=splas_screen
        splas_screen.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                /*/TODO("Not yet implemented")*/
            }
            override fun onAnimationEnd(p0: Animation?) {
                Handler(Looper.getMainLooper()).postDelayed(

                    {
                       startActivity(Intent(this@SplashScreen,MainActivity::class.java))
                        finish()},
                    1000)
            }
            override fun onAnimationRepeat(p0: Animation?) {
                TODO("Not yet implemented")
            }
        })


        }


        //Create Splash







        /* val navController = findNavController(R.id.nav_host_fragment_content_main)
         appBarConfiguration = AppBarConfiguration(navController.graph)
         setupActionBarWithNavController(navController, appBarConfiguration)




        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            // Inflate the menu; this adds items to the action bar if it is present.
            menuInflater.inflate(R.menu.menu_main, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            return when (item.itemId) {
                R.id.action_settings -> true
                else -> super.onOptionsItemSelected(item)
            }
        }



           */
}