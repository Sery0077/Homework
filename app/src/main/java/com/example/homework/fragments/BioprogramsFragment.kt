package com.example.homework.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bioprograms, container, false)

        initView(view)

        initRecycler()

        return view
    }

    private fun initRecycler() {
        horizontalRecycler.layoutManager =
            LinearLayoutManager(BioprogramsFragment@ context, LinearLayoutManager.HORIZONTAL, false)
        horizontalRecycler.adapter = Adapter()

        bottomListRecycler.layoutManager = LinearLayoutManager(BioprogramsFragment@ context)
        bottomListRecycler.adapter = bottomListRecyclerAdapter
    }

    private fun initView(view: View) {
        horizontalRecycler = view.findViewById(R.id.courses_recycler)
        bottomListRecycler = view.findViewById(R.id.bottom_list_recycler)

        bottomSheetList = view.findViewById(R.id.bottom_sheet)

        //set bottom sheet list peek height according to display size
        val activity = activity
        val displayHeight = activity!!.windowManager.defaultDisplay.height
        val behavior = BottomSheetBehavior.from(bottomSheetList)
        val bottomSheetListHeight = displayHeight * 0.44
        behavior.peekHeight = bottomSheetListHeight.toInt()
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
}