package moo.jee.zero.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import moo.jee.zero.databinding.SliderItemContainerBinding
import moo.jee.zero.model.SliderModel
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions;

class SliderAdapter(
    private var sliderItems: List<SliderModel>,  // List of SliderModel items
    private val viewPager2: ViewPager2           // Reference to ViewPager2 for slider functionality
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private lateinit var context: Context  // Context used for image loading


    // ViewHolder class for binding and displaying the slider image
    class SliderViewHolder(private val binding: SliderItemContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // Function to load and set the image in the view using Glide
        fun setImage(sliderItems: SliderModel, context: Context) {
            Glide.with(context)
                .load(sliderItems.url)
                .apply(
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)  // Cache images to avoid reload
                        .transform(CenterInside())// Apply transformation
                )
                .into(binding.imageSlide)// Load the image into ImageView
        }
    }

    // Called to inflate the view for each item in the slider
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderViewHolder {
        // Initialize context from parent view
        context = parent.context

        // Inflate the layout for each slider item using View Binding
        val binding =
            SliderItemContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding)
    }

    // Binds data to each ViewHolder
    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        // Set the image for the current slider item
        holder.setImage(sliderItems[position], context)

        // When reaching the second last item, post the runnable to refresh the adapter

    }

    // Returns the total number of items in the slider
    override fun getItemCount(): Int = sliderItems.size
}
