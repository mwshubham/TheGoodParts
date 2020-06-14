package app.thegoodparts.ui.fragments.companylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.thegoodparts.data.source.remote.response.Company
import app.thegoodparts.databinding.ItemCompanyBinding

class CompanyListAdapter(
    private val listener: (CompanyListAdapterEvent) -> Unit
) : ListAdapter<Company, CompanyListAdapter.ViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener)
    }

    class ViewHolder private constructor(private val binding: ItemCompanyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(company: Company, listener: (CompanyListAdapterEvent) -> Unit) {
            binding.company = company
            binding.executePendingBindings()
            binding.chipFollow.setOnClickListener {
                listener(
                    CompanyListAdapterEvent.FollowEvent(
                        company
                    )
                )
            }
            binding.root.setOnClickListener {
                listener(
                    CompanyListAdapterEvent.ClickEvent(
                        company
                    )
                )
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCompanyBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(
                    binding
                )
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Company>() {
            override fun areItemsTheSame(oldItem: Company, newItem: Company): Boolean =
                oldItem._id == newItem._id

            override fun areContentsTheSame(oldItem: Company, newItem: Company): Boolean =
                oldItem == newItem
        }
    }
}