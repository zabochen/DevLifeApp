package ua.ck.zabochen.devlifeapp.ui.enties.new

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_entry.view.*
import ua.ck.zabochen.devlifeapp.R
import ua.ck.zabochen.devlifeapp.data.model.Entry

class NewEntryAdapter : RecyclerView.Adapter<NewEntryAdapter.ViewHolder>() {

    private val newEntryList: ArrayList<Entry> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_entry, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newEntryList[position])
    }

    override fun getItemCount(): Int {
        return if (newEntryList.isNotEmpty()) newEntryList.size else 0
    }

    fun setNewEntryList(newEntryList: List<Entry>) {
        this.newEntryList.apply {
            clear()
            addAll(newEntryList)
        }
        notifyDataSetChanged()
    }

    fun getNewEntryList(): ArrayList<Entry> {
        return this.newEntryList
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(entry: Entry) {
            itemView.apply {
                itemEntry_textView_author.text = entry.author
                itemEntry_textView_date.text = entry.date
                itemEntry_textView_description.text = entry.description
                Glide.with(itemView.context)
                    .load(entry.previewImageUrl)
                    .into(itemView_imageView_previewImage)
            }
        }
    }
}