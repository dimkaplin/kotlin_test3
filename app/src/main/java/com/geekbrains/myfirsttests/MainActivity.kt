package com.geekbrains.myfirsttests

import android.os.Bundle
import android.view.View
import com.geekbrains.myfirsttests.R
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.myfirsttests.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewContract  {

    private lateinit var binding: ActivityMainBinding
    private val emailValidator = EmailValidator()
    private var totalCount: Int = 0
    private val adapter = SearchResultAdapter()
    private val presenter: PresenterContract = SearchPresenter(this, createRepository())

    private fun setUI() {
        setQueryListener()
        setRecyclerView()
    }

    override fun displayError() {
        Toast.makeText(this, getString(R.string.undefined_error), Toast.LENGTH_SHORT).show()
    }

    override fun displayError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun displayLoading(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun createRepository(): GitHubRepository {
        return GitHubRepository(createRetrofit().create(GitHubApi::class.java))
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun setRecyclerView() {
        binding.recyclerView.setHasFixedSize(true)
        //recyclerView.adapter = adapter
    }

    private fun setQueryListener() {
        binding.searchEditText.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.searchEditText.text.toString()
                if (query.isNotBlank()) {
                    presenter.searchGitHub(query)
                    return@OnEditorActionListener true
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.enter_search_word),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@OnEditorActionListener false
                }
            }
            false
        })
    }

    override fun displaySearchResults(
        searchResults: List<SearchResult>,
        totalCount: Int
    ) {
        this.totalCount = totalCount
        adapter.updateResults(searchResults)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUI()

    }

    companion object {
        const val BASE_URL = "https://api.github.com"
    }
}
