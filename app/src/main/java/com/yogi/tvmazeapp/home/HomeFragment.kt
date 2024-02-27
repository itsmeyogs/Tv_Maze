package com.yogi.tvmazeapp.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yogi.tvmazeapp.R
import com.yogi.tvmazeapp.core.data.source.Resource
import com.yogi.tvmazeapp.core.ui.TvMazeAdapter
import com.yogi.tvmazeapp.databinding.FragmentHomeBinding
import com.yogi.tvmazeapp.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val homeViewModel : HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity!=null){
            val tvMazeAdapter = TvMazeAdapter()
            tvMazeAdapter.onItemClick ={data->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DETAIL, data)
                startActivity(intent)
            }

            homeViewModel.listShow.observe(viewLifecycleOwner) {
                if (it != null) {
                    when(it){
                        is Resource.Loading ->{
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success->{
                            binding.progressBar.visibility = View.GONE
                            tvMazeAdapter.setData(it.data)
                        }
                        is Resource.Error->{
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = it.message ?: getString(R.string.error)
                        }
                    }
                }
            }
            with(binding.rvShow){
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter = tvMazeAdapter
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}