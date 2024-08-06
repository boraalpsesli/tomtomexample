package com.example.tomtomexample

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tomtomexample.databinding.FragmentLandingBinding
import com.example.tomtomexample.response.Result
import com.example.tomtomexample.response.testResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LandingFragment : Fragment() {
    private  lateinit var binding: FragmentLandingBinding
    private var tAdapter=LandingFragmentAdapter(arrayListOf()){
            item->
        val browserIntent= Intent(Intent.ACTION_VIEW, Uri.parse("http://${item.poi.url}"))
        requireContext().startActivity(browserIntent)
    }

    private val viewmodel:LandingFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    private fun setupRecyclerView(){
        binding.Recycler.layoutManager= LinearLayoutManager(context)
        binding.Recycler.adapter=tAdapter
        binding.Recycler.addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentLandingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        searchSetup()
        if(binding.Search.text.isEmpty()){
            val jsonString=viewmodel.readJsonFromAssets(requireContext(),"testAPI.json")
            val location=viewmodel.parseJsonToModel(jsonString)
            tAdapter.updateData(location.results)
        }
        binding.searchButton.setOnClickListener {
            if(viewmodel.isNetworkAvailable(requireContext())){
            viewmodel.search(binding.Search.text.toString())}

        }

    }

    private fun searchSetup(){
        viewmodel.items.observe(viewLifecycleOwner, Observer { item->
            item?.results?.let { tAdapter.updateData(it) }
        })
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            LandingFragment()
    }
}