package com.example.someapplication.ui.moviedetails

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpaceItemDecorator(val space: String) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val firstItem = 0
        val lastItem = parent.adapter?.itemCount?.minus(1)

        when (parent.getChildAdapterPosition(view)) {
            firstItem -> outRect.right = space.toInt()
            lastItem -> outRect.left = space.toInt()
            else -> {
                outRect.right = space.toInt()
                outRect.left = space.toInt()
            }
        }

    }
}