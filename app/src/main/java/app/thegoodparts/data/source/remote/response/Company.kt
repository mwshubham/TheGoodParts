package app.thegoodparts.data.source.remote.response

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = Company.Companion.TableInfo.tableName)
data class Company(
    @PrimaryKey
    var _id: String = "",
    var company: String = "",
    var website: String = "",
    var logo: String = "",
    var about: String = "",
    var favourite: Boolean = false,
    var following: Boolean = false,
    @Ignore
    var members: List<Member> = emptyList()
) {
    companion object {
        object TableInfo {
            const val tableName = "companies"
        }
    }
}
