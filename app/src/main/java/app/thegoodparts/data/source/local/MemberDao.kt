package app.thegoodparts.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.thegoodparts.data.source.remote.response.Member
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(members: List<Member>): List<Long>

    @Query("DELETE FROM members")
    fun deleteAll()

    @Query("SELECT * FROM members where _id =:id")
    fun get(id: String): LiveData<Member>

    @Query("SELECT * FROM members")
    fun getAll(): Flow<List<Member>>

    @Query("SELECT * FROM members where _id =:id")
    suspend fun getMember(id: String): Member

    @Query("UPDATE members SET favourite = 1 WHERE _id = :id")
    fun markFavourite(id: String)

    @Query("UPDATE members SET favourite = 0 WHERE _id = :id")
    fun markUnFavourite(id: String)
}