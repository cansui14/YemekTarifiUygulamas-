package com.example.myapplication.view
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.MealAdapter
import com.example.myapplication.constants.AppConstants
import com.example.myapplication.databinding.ActivityMealBinding
import com.example.myapplication.model.MealEntity
import com.example.myapplication.model.MealResponse
import com.example.myapplication.service.MealAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MealActivity : AppCompatActivity(), MealAdapter.Listener1 {
    private lateinit var binding: ActivityMealBinding

    private val BASE_URL = "https://www.themealdb.com/"
    private var mealResponse: MealResponse? = null
    private var mealAdapter: MealAdapter? = null

    private var clickedCategoryName: String? = null

    private var compositeDisposable: CompositeDisposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        clickedCategoryName = intent.getStringExtra(AppConstants.SELECTED_CATEGORY_NAME)

        compositeDisposable = CompositeDisposable()


        val layoutManager1: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recylerView1.layoutManager = layoutManager1



        loadingData()
    }

    private fun loadingData() {

        val retrofit1 = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(MealAPI::class.java)

        compositeDisposable?.add(
            retrofit1.getMeals(clickedCategoryName ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse2)
        )


    }

    private fun handleResponse2(response2: MealResponse) {//meals mealresponcedeki arraylist
        mealResponse = response2

        mealResponse?.meals?.let { list2 ->
            mealAdapter = MealAdapter(list2, this@MealActivity)
            binding.recylerView1.adapter = mealAdapter


        }

    }

    override fun onItemClick(mealEntity: MealEntity) {
        Toast.makeText(this, "Clicked : ${mealEntity.strMeal}", Toast.LENGTH_LONG).show()
        //buraya tıklanma eklicez.


        val intent1 = Intent(this,DetailActivity::class.java)
        intent1.putExtra(AppConstants.SELECTED_DETAILS_NAME,mealEntity.idMeal)

        startActivity(intent1)

    }
}


