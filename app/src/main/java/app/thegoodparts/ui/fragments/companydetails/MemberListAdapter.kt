package app.thegoodparts.ui.fragments.companydetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.thegoodparts.data.source.remote.response.Member
import app.thegoodparts.databinding.ItemMemberBinding

class MemberListAdapter(
    private val listener: (MemberListAdapterEvent) -> Unit
) : ListAdapter<Member, MemberListAdapter.ViewHolder>(
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

    class ViewHolder private constructor(private val binding: ItemMemberBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            member: Member,
            listener: (MemberListAdapterEvent) -> Unit
        ) {
            binding.member = member
            binding.executePendingBindings()
            binding.ivFav.setOnClickListener {
                listener(
                    MemberListAdapterEvent.FavEvent(
                        member
                    )
                )
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMemberBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(
                    binding
                )
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Member>() {
            override fun areItemsTheSame(oldItem: Member, newItem: Member): Boolean =
                oldItem._id == newItem._id

            override fun areContentsTheSame(oldItem: Member, newItem: Member): Boolean =
                oldItem == newItem
        }
    }
}