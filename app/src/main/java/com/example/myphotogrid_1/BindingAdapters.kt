package com.example.myphotogrid_1


import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myphotogrid_1.network.GridPhoto
import com.example.myphotogrid_1.overview.GridApiStatus
import com.example.myphotogrid_1.overview.PhotoGridAdapter
// updates data in RecyclerView

@BindingAdapter("listData")
fun bindRecyclerView (recyclerView: RecyclerView, data:List<GridPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)

}

//Uses coil library to load image url into imageview
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl:String?)
{
    imgUrl?.let{
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}


@BindingAdapter("gridApiStatus")
fun bindStatus(statusImageView: ImageView, status: GridApiStatus?) {
    when(status){
        GridApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        GridApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        GridApiStatus.DONE ->{
            statusImageView.visibility = View.GONE
        }
    }
}

