package com.example.pluang.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pluang.R
import com.example.pluang.Util.Status
import com.example.pluang.data.response.Item
import com.example.pluang.ui.detailedview.DetailedActivity
import com.example.pluang.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recylerview_items.view.*
import org.koin.android.ext.android.inject
import java.io.Serializable


class MainActivity : BaseActivity<MainViewModel>(MainViewModel::class) {

    companion object {
        const val DATA = "DATA"
        const val EXTRA_TRANSACTION_IMAGE = "EXTRA_TRANSACTION"
        const val EXTRA_TRANSACTION_REPO = "EXTRA_TRANSACTION_REPO"
        const val EXTRA_TRANSACTION_DESCRIPTION = "EXTRA+TRANSACTION_DESCRIPTION"
    }


    private val mainAdapter: MainAdapter by inject()


    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun setupView(savedInstanceState: Bundle?) {

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerview.layoutManager = layoutManager
        recyclerview.setHasFixedSize(true)
        recyclerview.adapter = mainAdapter

        viewModel.getList()

        mainAdapter.setClickListener(object : MainAdapter.ClickListener {

            override fun onItemClick(view: View, position: Int, list: ArrayList<Item>) {


                val pair1: Pair<View?, String?> =
                    Pair(view.profile_image, ViewCompat.getTransitionName(view.profile_image))
                val pair2: Pair<View?, String?> =
                    Pair(view.repo_name, ViewCompat.getTransitionName(view.repo_name))
                val pair3: Pair<View?, String?> =
                    Pair(view.repo_description, ViewCompat.getTransitionName(view.repo_description))

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@MainActivity,
                    pair1,
                    pair2,
                    pair3
                )

                Intent(this@MainActivity, DetailedActivity::class.java).apply {
                    putExtra(DATA,list[position] as Serializable)
                    putExtra(
                        EXTRA_TRANSACTION_IMAGE,
                        ViewCompat.getTransitionName(view.profile_image)
                    )
                    putExtra(EXTRA_TRANSACTION_REPO, ViewCompat.getTransitionName(view.repo_name))
                    putExtra(
                        EXTRA_TRANSACTION_DESCRIPTION,
                        ViewCompat.getTransitionName(view.repo_description)
                    )
                }.also {
                    startActivity(it, options.toBundle())
                }
            }

        })
    }

    override fun setupObserver() {
        super.setupObserver()

        viewModel.data.observe(this, Observer { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    recyclerview.visibility = VISIBLE
                    progressBar.visibility = GONE
                    mainAdapter.submitList(response.let { it.data?.items } as ArrayList<Item>)
                }
                Status.LOADING -> {
                    progressBar.visibility = VISIBLE
                    recyclerview.visibility = GONE

                }
                Status.ERROR -> {
                    Toast.makeText(this, resources.getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show()
                }

                else -> {

                }
            }
        })
    }


}