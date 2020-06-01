package android.thegoodparts.data.source.local

import androidx.room.ColumnInfo

data class NewsSource(

    @ColumnInfo(name = Column.id)
    val id: String? = null,

    @ColumnInfo(name = Column.name)
    val name: String? = null
) {
    companion object {

        object Column {
            const val id = "id"
            const val name = "name"
        }
    }
}