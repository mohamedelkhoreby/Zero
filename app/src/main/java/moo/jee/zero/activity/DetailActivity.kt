package moo.jee.zero.activity

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.project1762.Helper.ManagmentCart
import moo.jee.zero.adapter.PicAdapter
import moo.jee.zero.adapter.SelectedModelAdapter

import moo.jee.zero.databinding.ActivityDetailBinding
import moo.jee.zero.model.ItemsModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private var numberOrder = 1
    private lateinit var managmentCart: ManagmentCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)
        managmentCart = ManagmentCart(this)
        getBundle()
        iniList()
    }

    private fun iniList() {
        val modelList = ArrayList<String>()
        for (models in item.model) {
            modelList.add(models)
        }
        binding.modelList.adapter = SelectedModelAdapter(modelList)
        binding.modelList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val picList = ArrayList<String>()
        for (imageUrl in item.picUrl) {
            picList.add(imageUrl)
        }
        Glide.with(this)
            .load(picList[0])
            .into(binding.img)
        binding.picList.adapter = PicAdapter(picList) { selectedImageUrl ->
            Glide.with(this).load(selectedImageUrl).into(binding.img)
        }
        binding.picList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!
        binding.titleTxt.text = item.title
        binding.description.text = item.description
        binding.priceTxt.text = "$" + item.price
        binding.ratingTxt.text = "${item.rating}"
        binding.addToCartBtn.setOnClickListener {
            item.numberInCart = numberOrder
            managmentCart.insertItem(item)

        }
        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.carBtn.setOnClickListener {
            //  startActivity(Intent(DetailActivity))
        }
    }
}