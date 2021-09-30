package com.stareme.ocbcsimple.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stareme.ocbcsimple.R
import com.stareme.ocbcsimple.http.model.Order
import kotlin.collections.ArrayList

class CustomAdapter(private val dataSet: ArrayList<Order>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateTv: TextView
        val contentTv: TextView
        val moneyTv: TextView
        init {
            // Define click listener for the ViewHolder's View.
            dateTv = view.findViewById(R.id.date)
            contentTv = view.findViewById(R.id.text)
            moneyTv = view.findViewById(R.id.money)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_transaction_layout, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val order = dataSet[position]
        viewHolder.dateTv.text = order.getFormatDate()
        viewHolder.contentTv.text = order.description
        if (order.isReceived()) {
            viewHolder.moneyTv.text = order.amount.toString()
            viewHolder.moneyTv.setTextColor(viewHolder.moneyTv.resources.getColor(android.R.color.holo_red_light))
        } else {
            viewHolder.moneyTv.text = "-${order.amount}"
            viewHolder.moneyTv.setTextColor(viewHolder.moneyTv.resources.getColor(android.R.color.holo_blue_light))
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
