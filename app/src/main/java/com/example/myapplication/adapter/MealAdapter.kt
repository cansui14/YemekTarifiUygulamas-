package com.example.myapplication.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.RowLayout2Binding
import com.example.myapplication.model.MealEntity
import com.example.myapplication.view.MealActivity

class MealAdapter(private val list2:ArrayList<MealEntity>, private val listener1: MealActivity) : RecyclerView.Adapter<MealAdapter.RowHolder2>() {

    interface Listener1 {
        fun onItemClick(mealEntity: MealEntity)
    }

    class RowHolder2(val binding: RowLayout2Binding) : RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder2 {
        val binding = RowLayout2Binding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder2(binding)
    }

    override fun onBindViewHolder(holder: RowHolder2, position: Int) {

        holder.itemView.setOnClickListener {
            listener1.onItemClick(list2.get(position))
        }
        holder.binding.mealNameTextView.text = list2[position].strMeal
        Glide.with(holder.binding.root.context)
            .load(list2[position].strMealThumb)
            .into(holder.binding.mealResponseImageView);


    }

    override fun getItemCount(): Int {

    return list2.count()
    }

}