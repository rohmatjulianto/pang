package com.joule.panggilin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.marginBottom
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.joule.panggilin.adapter.MenuAdapter
import com.joule.panggilin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val marginSheet = binding.cardParent.marginBottom
        val radiusSheet = binding.cardParent.radius
        val rvMenu = binding.menu.rvMenu
        val rvMenuPreview = binding.menu.rvMenuPreview
        val bottomSheet = BottomSheetBehavior.from(binding.cardSheet)

        bottomSheet.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                val newMargin = marginSheet - (slideOffset * marginSheet).toInt()
                val newRadius = radiusSheet - (slideOffset * radiusSheet).toInt()
                val parameter = binding.cardParent.layoutParams as ViewGroup.MarginLayoutParams
                parameter.setMargins(newMargin, newMargin, newMargin, newMargin)
                binding.cardParent.layoutParams = parameter

                if (newRadius > 50.0) {
                    binding.cardSheet.radius = newRadius
                    binding.cardParent.radius = newRadius
                }

                rvMenu.alpha = slideOffset
                binding.menu.tvFav.alpha = slideOffset
                binding.menu.btnEdit.alpha = slideOffset

//                menu preview
                rvMenuPreview.alpha = 1.0f - ((slideOffset * 5) * 1.0f)
                if (rvMenuPreview.alpha == -4.0f) {
                    rvMenuPreview.visibility = View.GONE
                } else {
                    rvMenuPreview.visibility = View.VISIBLE
                }
            }
        })

        rvMenu.layoutManager = GridLayoutManager(this, 4)
        viewModel.menu.observe(this, {
            it?.let { rvMenu.adapter = MenuAdapter(it) }
        })

        rvMenuPreview.layoutManager = GridLayoutManager(this, 4)
        viewModel.menuPreview.observe(this, {
            it?.let { rvMenuPreview.adapter = MenuAdapter(it) }
        })

        binding.menu.btnEdit.setOnClickListener {
            Toast.makeText(this, "Edit clicked", Toast.LENGTH_SHORT).show()
        }
    }

}