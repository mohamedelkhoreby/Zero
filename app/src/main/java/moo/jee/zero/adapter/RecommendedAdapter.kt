package moo.jee.zero.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import moo.jee.zero.databinding.ViewholderRecommendedBinding
import moo.jee.zero.model.ItemsModel

class RecommendedAdapter(val items: MutableList<ItemsModel>) :
    RecyclerView.Adapter<RecommendedAdapter.Viewholder>() {

    class Viewholder(val binding: ViewholderRecommendedBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendedAdapter.Viewholder {
        val binding =
            ViewholderRecommendedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        val item = items[position]
        with(holder.binding) {
            textV.text = item.title
            textView8.text = "${item.price}"
            rateTxt.text = item.rating.toString()

            Glide.with(holder.itemView.context)
                .load(item.picUrl[0])
                .into(imageView6)

            root.setOnClickListener {
            }
        }
    }

    override fun getItemCount(): Int = items.size

}