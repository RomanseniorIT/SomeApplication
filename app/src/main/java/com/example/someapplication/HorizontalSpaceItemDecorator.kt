package com.example.someapplication

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpaceItemDecorator(val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val firstItem = 0
        val lastItem = parent.adapter?.itemCount?.minus(1)

        when (parent.getChildAdapterPosition(view)) {
            firstItem -> outRect.right = space
            lastItem -> outRect.left = space
            else -> {
                outRect.right = space
                outRect.left = space
            }
        }

    }
}