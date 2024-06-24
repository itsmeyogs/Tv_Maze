package com.yogi.tvmazeapp.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yogi.tvmazeapp.core.ui.TvMazeAdapter
import com.yogi.tvmazeapp.databinding.FragmentFavoriteBinding
import com.yogi.tvmazeapp.detail.DetailActivity
import com.yogi.tvmazeapp.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("ViewBinding null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        if (activity!=null){
            val tvMazeAdapter = TvMazeAdapter()
            tvMazeAdapter.onItemClick ={data->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DETAIL, data)
                startActivity(intent)
            }

            favoriteViewModel.favoriteShow.observe(viewLifecycleOwner) { data ->
                tvMazeAdapter.setData(data)
                binding.viewEmpty.root.visibility = if (data.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvShow){
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter = tvMazeAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}