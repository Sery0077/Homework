package com.example.homework.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Target
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet_list_recycler_item.view.*
import kotlinx.android.synthetic.main.horizontal_recycler_item.view.*
import java.lang.Math.abs

class BioprogramsFragment : Fragment() {

    private lateinit var horizontalRecycler: RecyclerView
    private lateinit var bottomListRecycler: RecyclerView

    private val bottomListRecyclerAdapter = object : Adapter() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                layoutInflater.inflate(R.layout.bottom_sheet_list_recycler_item, parent, false)
            if (viewType == 1) {
                view.bottom_line.visibility = View.GONE
            }
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.run {
                if (position == 14) bottom_line.visibility = View.GONE
            }
        }

        override fun getItemViewType(position: Int): Int {
            return if (position == 14) 1 else 0
        }

    }

    private lateinit var bottomSheetList: LinearLayout
    private lateinit var bottomSheetListButton: ImageButton
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private var bottomSheetListener = object: BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            bottomSheetListButton.rotation = slideOffset * 180
            horizontalRecycler.alpha = kotlin.math.abs(1 - slideOffset)
        }

        override fun onStateChanged(bottomSheet: View, newState: Int) {}

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bioprograms, container, false)

        initView(view)

        initRecycler()

        setClickers()

        return view
    }

    private fun initRecycler() {
        horizontalRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        horizontalRecycler.adapter = Adapter()

        bottomListRecycler.layoutManager = LinearLayoutManager(context)
        bottomListRecycler.adapter = bottomListRecyclerAdapter
    }

    private fun initView(view: View) {
        horizontalRecycler = view.findViewById(R.id.courses_recycler)

        bottomListRecycler = view.findViewById(R.id.bottom_list_recycler)
        bottomSheetList = view.findViewById(R.id.bottom_sheet)
        bottomSheetListButton = view.findViewById(R.id.bottom_sheet_list_button)

        //set bottom sheet list peek height according to display size
        val activity = activity
        val displayHeight = activity!!.windowManager.defaultDisplay.height
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetList)
        val bottomSheetListHeight = displayHeight * 0.44
        bottomSheetBehavior.peekHeight = bottomSheetListHeight.toInt()

        bottomSheetBehavior.addBottomSheetCallback(bottomSheetListener)
    }

    open inner class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = layoutInflater.inflate(R.layout.horizontal_recycler_item, parent, false)
            if (viewType == 1) {
                view.background_image.setImageResource(R.drawable.image_course)
            }
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return 15
        }

        override fun getItemViewType(position: Int): Int {
            return if (position % 2 == 0) 1 else 0
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.run {
                if (position % 2 == 0) {
                    val bitmap = BitmapFactory.decodeResource(resources, R.drawable.image_course)
                    val target = Target.Builder().build()
                    val palette = Palette
                        .from(bitmap)
                        .addTarget(target)
                        .generate()
                    if (palette.darkVibrantSwatch?.titleTextColor !== null) {
                        name_course_card.setTextColor(palette.darkMutedSwatch!!.titleTextColor)
                    }

                    background_image.setImageResource(R.drawable.image_course)
                }
            }
        }
    }

    private fun setClickers() {
        bottomSheetListButton.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                bottomSheetListButton.animate().rotation(180F).duration = 500
                horizontalRecycler.animate().alpha(0F).duration = 500
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                bottomSheetListButton.animate().rotation(0F).duration = 500
                horizontalRecycler.animate().alpha(1F).duration = 500
            }
        }
    }
}