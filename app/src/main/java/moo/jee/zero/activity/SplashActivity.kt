package moo.jee.zero.activity

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.os.Handler
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import moo.jee.zero.R
import moo.jee.zero.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val splashTextView: TextView = findViewById(R.id.splashTextView)
        window.navigationBarColor = resources.getColor(R.color.green, theme)
        window.statusBarColor = resources.getColor(R.color.green, theme)
        // Calculate the target Y position to drop to the center
        val targetY = (resources.displayMetrics.heightPixels / 2) - splashTextView.height / 2

        // Create drop-down animation
        ObjectAnimator.ofFloat(splashTextView, "translationY", targetY.toFloat()).apply {
            duration = 1000 // Animation duration in ms
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }
        // Delay for splash screen duration, then start the MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        }, 2000) // 2 seconds delay
    }
}