package moo.jee.zero.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import moo.jee.zero.adapter.ListItemAdapter
import moo.jee.zero.databinding.ActivityListItemBinding
import moo.jee.zero.viewModel.MainViewModel

class ListItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListItemBinding
    private val viewModel = MainViewModel()
    private var id: String = ""
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getBundle()
        initList()
    }

    private fun initList() {
        binding.apply {
            progressBarList.visibility= View.VISIBLE
            viewModel.recommendations.observe(this@ListItemActivity, Observer {
                viewList.layoutManager=GridLayoutManager(this@ListItemActivity,2)
                viewList.adapter = ListItemAdapter(it)
                progressBarList.visibility = View.GONE
            })
            viewModel.loadFiltered(id)
        }
    }
    private fun getBundle() {
        id = intent.getStringExtra("id") ?: ""
        title = intent.getStringExtra("title") ?: ""
        binding.categoryTxt.text = title
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}