package com.example.myapplication.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.RowLayoutBinding
import com.example.myapplication.model.Category

class CategoryAdapter(private val list : ArrayList<Category>, private val listener : Listener) : RecyclerView.Adapter<CategoryAdapter.RowHolder>() {

    interface Listener {
        fun onItemClick(category: Category)
    }


    class RowHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(binding)
    }

    override fun getItemCount(): Int {
      return list.count()//kac eleman varsa kontrol etmemizi saglar
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClick(list.get(position))
        }

        holder.binding.categoryNameTextView.text = list[position].strCategory
        holder.binding.categoryAciklamaTextView.text = list[position].strCategoryDescription
        Glide.with(holder.binding.root.context).load(list[position].strCategoryThumb).into(holder.binding.categoryImageView);

    }



}