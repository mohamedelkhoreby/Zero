package moo.jee.zero.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import moo.jee.zero.databinding.SliderItemContainerBinding
import moo.jee.zero.model.SliderModel
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions;

class SliderAdapter(
    private var sliderItems: List<SliderModel>,  // List of SliderModel items
    private val viewPager2: ViewPager2           // Reference to ViewPager2 for slider functionality
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private lateinit var context: Context  // Context used for image loading

    // Runnable to refresh the data and update the UI when the last item is reached
    private val runnable = Runnable {
        sliderItems = sliderItems  // No changes to the list, just refreshing the adapter
        notifyDataSetChanged()     // Notify adapter to update UI
    }

    // ViewHolder class for binding and displaying the slider image
    class SliderViewHolder(private val binding: SliderItemContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Function to load and set the image in the view using Glide
        fun setImage(sliderItems: SliderModel, context: Context) {
            Glide.with(context)
                .load(sliderItems.url)   // Load image from the URL in SliderModel
                .apply { RequestOptions().transform(CenterInside()) }  // Apply transformation
                .into(binding.imageSlide)  // Set the image to the ImageView in the layout
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
        if (position == sliderItems.lastIndex - 1) {
            viewPager2.post(runnable)
        }
    }

    // Returns the total number of items in the slider
    override fun getItemCount(): Int = sliderItems.size
}
