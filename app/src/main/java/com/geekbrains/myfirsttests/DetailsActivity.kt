package com.geekbrains.myfirsttests

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.util.*

class DetailsActivity : AppCompatActivity(), ViewDetailsContract {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setUI()
    }

    private fun setUI() {
        val count = intent.getIntExtra(TOTAL_COUNT_EXTRA, 0)
        //totalCountTextView.text = String.format(Locale.getDefault(), getString(R.string.results_count), count)
        Toast.makeText(this, String.format(Locale.getDefault(), getString(R.string.results_count), count), Toast.LENGTH_SHORT).show()
    }

    companion object {

        const val TOTAL_COUNT_EXTRA = "TOTAL_COUNT_EXTRA"

        fun getIntent(context: Context, totalCount: Int): Intent {
            return Intent(context, DetailsActivity::class.java).apply {
                putExtra(TOTAL_COUNT_EXTRA, totalCount)
            }
        }
    }

    override fun setCount(count: Int) {
        TODO("Not yet implemented")
    }

    override fun displaySearchResults(searchResults: List<SearchResult>, totalCount: Int) {
        TODO("Not yet implemented")
    }

    override fun displayError() {
        TODO("Not yet implemented")
    }

    override fun displayError(error: String) {
        TODO("Not yet implemented")
    }

    override fun displayLoading(show: Boolean) {
        TODO("Not yet implemented")
    }
}
