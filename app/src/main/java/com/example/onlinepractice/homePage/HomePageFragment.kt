
package com.example.onlinepractice.homePage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinepractice.R
import kotlin.random.Random

class HomePageFragment : Fragment(R.layout.fragment_home_page){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val items = getRandomItems()

        val rvNews: RecyclerView = view.findViewById(R.id.rv_homepageRV)
        val adapter = RVAdapter(items)
        val layoutManager = LinearLayoutManager(this.context)

        rvNews.layoutManager = layoutManager
        rvNews.adapter = adapter


    }

    fun getRandomItems(): Array<Item>{
        return Array(100){
            val text = "Random Text"
            val imgSrc = getRandomImg()
            Item(text, imgSrc)
        }
    }

    fun getRandomImg(): String {
        val rand = (50..100).random()
        return "https://picsum.photos/$rand"
    }
}