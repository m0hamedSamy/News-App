package com.example.onlinepractice.homePage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinepractice.R
import com.example.onlinepractice.network.ApiInterface
import com.example.onlinepractice.network.News
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class HomePageFragment : Fragment(R.layout.fragment_home_page), ArticleAdapter.OnArticleListener{

    val topic = "Bitcoin and Tesla"
    val apiKey = "6d70e376552d4c71a6cbdd9eb59791cf"

    var adapter: ArticleAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val rvNews: RecyclerView = view.findViewById(R.id.rv_homepageRV)
        val layoutManager = LinearLayoutManager(this.context)
        adapter = ArticleAdapter(emptyList(), this)
        rvNews.adapter = adapter
        rvNews.layoutManager = layoutManager

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://newsapi.org/")
            .build()

        val apiInterface = retrofit.create(ApiInterface::class.java)

        val call = apiInterface.getEverything(topic, apiKey)
        call?.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                adapter?.articles = response.body()?.articles
                adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("***", "onFailure ${t.localizedMessage}")
            }
        })



    }

    fun getRandomItems(): Array<com.example.onlinepractice.homePage.Item> {
        return Array(100) {
            val text = "Random Text"
            val imgSrc = getRandomImg()
            Item(text, imgSrc)
        }
    }

    fun getRandomImg(): String {
        val rand = (50..100).random()
        return "https://picsum.photos/$rand"
    }

    override fun onArticleClicked(position: Int) {
        val article = adapter?.articles?.get(position)
        val url = article?.url
        val openArticleLink = Intent(Intent.ACTION_VIEW)
        openArticleLink.data = Uri.parse(url)
        startActivity(openArticleLink)

        Log.d("&&&", "item clicked ${adapter?.articles?.get(position)?.title}")
    }

//    fun translateRocket(view: View){
//        val ivRocket = view.findViewById<ImageView>(R.id.iv_rocket)
//        val dyAnimation = ObjectAnimator.ofFloat(ivRocket, "translationY", -800f)
//        dyAnimation.duration = 1000
//        dyAnimation.start()
//    }
}