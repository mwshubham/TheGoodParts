package app.thegoodparts.data.source.remote.response

import android.content.Context
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.thegoodparts.R

@Entity(tableName = Member.Companion.TableInfo.tableName)
data class Member(
    @PrimaryKey
    var _id: String = "",
    var companyId: String = "",
    var age: String = "",
    @Embedded
    var name: Name = Name(),
    var email: String = "",
    var phone: String = "",
    var favourite: Boolean = false
) {
    companion object {
        object TableInfo {
            const val tableName = "members"
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun getAgeFormatted(context: Context) =
        context.resources.getQuantityString(R.plurals.s_year, age.toInt(), age.toInt())

    fun getFullNameWithAge(context: Context) = "${name.getFullName()} (${getAgeFormatted(context)})"
}

