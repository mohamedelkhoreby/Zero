package moo.jee.zero.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import moo.jee.zero.model.SliderModel
class MainViewModel : ViewModel() {
    // Initialize Firebase Database instance
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    // MutableLiveData to hold the list of banners (SliderModel)
    private val _banner = MutableLiveData<List<SliderModel>>()

    // Publicly exposed LiveData for observing the banner list
    val banners: LiveData<List<SliderModel>> = _banner

    // Function to load banners from Firebase Realtime Database
    fun loadBanners() {
        // Get a reference to the "Banner" node in Firebase
        val ref = firebaseDatabase.getReference("Banner")

        // Add a listener to fetch data from Firebase
        ref.addValueEventListener(object : ValueEventListener {

            // Triggered when data in "Banner" changes or is fetched successfully
            override fun onDataChange(snapshot: DataSnapshot) {
                // Create a mutable list to hold the banner data
                val lists = mutableListOf<SliderModel>()

                // Iterate over each child node in the snapshot
                for (childSnapshot in snapshot.children) {
                    // Convert each child node into a SliderModel object
                    val list = childSnapshot.getValue(SliderModel::class.java)
                    // Add to the list if it's not null
                    if (list != null) {
                        lists.add(list)
                    }
                }
                // Update the LiveData with the fetched list
                _banner.value = lists
            }

            // Triggered when there is an error while fetching data from Firebase
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented") // Handle error scenario here
            }
        })
    }
}
