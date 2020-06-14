package app.thegoodparts.ui.fragments.companylist

import app.thegoodparts.data.source.remote.response.Company

sealed class CompanyListAdapterEvent {
    data class ClickEvent(
        val company: Company
    ) : CompanyListAdapterEvent()

    data class FollowEvent(
        val company: Company
    ) : CompanyListAdapterEvent()
}

