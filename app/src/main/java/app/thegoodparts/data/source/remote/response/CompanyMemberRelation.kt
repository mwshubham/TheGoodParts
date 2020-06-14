package app.thegoodparts.data.source.remote.response

import androidx.room.Embedded
import androidx.room.Relation

data class CompanyMemberRelation(
    @Embedded val company: Company,
    @Relation(
        parentColumn = "_id",
        entityColumn = "companyId"
    )
    val members: List<Member>
)