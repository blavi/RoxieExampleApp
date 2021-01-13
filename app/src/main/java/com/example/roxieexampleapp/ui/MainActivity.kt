package com.example.roxieexampleapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roxieexampleapp.adapter.ImagesAdapter
import com.example.roxieexampleapp.databinding.ActivityMainBinding
import com.example.roxieexampleapp.model.Image
import com.example.roxieexampleapp.mvi.Action
import com.example.roxieexampleapp.mvi.State
import com.example.roxieexampleapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private lateinit var imagesAdapter: ImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.observableState.observe(this, Observer { state ->
            state?.let {
                renderState(state)
            }
        })

        setUI()

        viewModel.dispatch(Action.LoadImages)
    }

    private fun setUI() {
        imagesAdapter = ImagesAdapter()
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = imagesAdapter
        }
    }

    private fun renderState(state: State) {
        if (state is State.Loading) {
            renderLoadingState()
        }
        if (state is State.LoadError) {
            renderErrorState()
        }
        if (state is State.Images) {
            renderImages(state.images)
        }
    }

    private fun renderImages(images: Array<Image>) {
        binding.progressBar.visibility = View.GONE

        binding.recyclerView.visibility = View.VISIBLE

        imagesAdapter.apply {
            addData(images)
            notifyDataSetChanged()
        }
    }

    private fun renderErrorState() {
        Toast.makeText(this, "An error occurred.", Toast.LENGTH_LONG).show()
    }

    private fun renderLoadingState() {
        binding.progressBar.visibility = View.VISIBLE
    }

}