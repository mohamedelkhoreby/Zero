package moo.jee.zero.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import moo.jee.zero.adapter.SliderAdapter
import moo.jee.zero.databinding.ActivityMainBinding
import moo.jee.zero.model.SliderModel
import moo.jee.zero.viewModel.MainViewModel
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
   private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        initBanner()
    }

    private fun banners(image: List<SliderModel>) {
        binding.viewPager2.adapter = SliderAdapter(image, binding.viewPager2)
        binding.viewPager2.clipToPadding = false
        binding.viewPager2.clipChildren = false
        binding.viewPager2.offscreenPageLimit = 3
        binding.viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPager2.setPageTransformer(compositePageTransformer)
        if (image.size > 1) {
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPager2)
        }

    }

    private fun initBanner() {
        binding.progressBarSlider.visibility = View.VISIBLE
        viewModel.banners.observe(this) {
            banners(it)
            binding.progressBarSlider.visibility = View.GONE
        }
        viewModel.loadBanners()
    }
}