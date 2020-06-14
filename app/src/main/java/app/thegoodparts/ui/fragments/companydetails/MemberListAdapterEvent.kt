package app.thegoodparts.ui.fragments.companydetails

import app.thegoodparts.data.source.remote.response.Member

sealed class MemberListAdapterEvent {
    data class ClickEvent(
        val member: Member
    ) : MemberListAdapterEvent()

    data class FavEvent(
        val member: Member
    ) : MemberListAdapterEvent()
}

