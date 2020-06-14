package app.thegoodparts.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import app.thegoodparts.data.source.remote.response.Company
import app.thegoodparts.data.source.remote.response.CompanyMemberRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface CompanyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(companies: List<Company>): List<Long>

    @Transaction
    @Query("SELECT * FROM companies where _id =:id")
    fun get(id: String): Flow<CompanyMemberRelation>

    @Transaction
    @Query("SELECT * FROM companies")
    fun getAll(): Flow<List<CompanyMemberRelation>>

    @Transaction
    @Query("SELECT * FROM companies Order By company ASC")
    fun getAllSortedASC(): LiveData<List<CompanyMemberRelation>>

    @Transaction
    @Query("SELECT * FROM companies Order By company DESC")
    fun getAllSortedDESC(): LiveData<List<CompanyMemberRelation>>

    @Query("SELECT * FROM companies where _id =:id")
    suspend fun getCompany(id: String): Company

    @Query("UPDATE companies SET following = 1 WHERE _id = :id")
    fun markFollowing(id: String)

    @Query("UPDATE companies SET following = 0 WHERE _id = :id")
    fun markUnfollow(id: String)

    @Query("UPDATE companies SET favourite = 1 WHERE _id = :id")
    fun markFavourite(id: String)

    @Query("UPDATE companies SET favourite = 0 WHERE _id = :id")
    fun markUnFavourite(id: String)
}