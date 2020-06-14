package app.thegoodparts.data.repository

import android.content.Context
import app.thegoodparts.ApiState
import app.thegoodparts.data.NetworkBoundResource
import app.thegoodparts.data.source.local.CompanyDao
import app.thegoodparts.data.source.local.MemberDao
import app.thegoodparts.data.source.remote.JsonGeneratorRemoteDataSource
import app.thegoodparts.data.source.remote.response.Company
import app.thegoodparts.data.source.remote.response.CompanyMemberRelation
import app.thegoodparts.data.source.remote.response.Member
import app.thegoodparts.ui.fragments.sortby.SortType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class JsonGeneratorRepository @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val companyDao: CompanyDao,
    private val memberDao: MemberDao,
    private val jsonGeneratorRemoteDataSource: JsonGeneratorRemoteDataSource
) {

    private fun markUnfollow(id: String) = companyDao.markUnfollow(id)
    private fun markFollowing(id: String) = companyDao.markFollowing(id)

    private fun markUnFavourite(id: String) = companyDao.markUnFavourite(id)
    private fun markFavourite(id: String) = companyDao.markFavourite(id)

    suspend fun toggleFollowingState(id: String) {
        if (companyDao.getCompany(id).following) {
            markUnfollow(id)
        } else {
            markFollowing(id)
        }
    }

    suspend fun toggleFavouriteState(id: String) {
        if (companyDao.getCompany(id).favourite) {
            markUnFavourite(id)
        } else {
            markFavourite(id)
        }
    }

    suspend fun toggleMemberFavouriteState(id: String) {
        if (memberDao.getMember(id).favourite) {
            memberDao.markUnFavourite(id)
        } else {
            memberDao.markFavourite(id)
        }
    }

    fun getCompanyDetails(id: String) = companyDao.get(id)

    fun getAllSorted(sortType: SortType) =
        if (sortType == SortType.NAME_ASC) companyDao.getAllSortedASC()
        else companyDao.getAllSortedDESC()

    @ExperimentalCoroutinesApi
    fun getCompanies(): Flow<ApiState<List<CompanyMemberRelation>>> {
        return object : NetworkBoundResource<List<CompanyMemberRelation>, List<Company>>() {
            override suspend fun saveNetworkResult(response: List<Company>) {
                companyDao.insert(response)
                val members = mutableListOf<Member>()
                response.forEach { company ->
                    members.addAll(
                        company.members.map {
                            it.apply { it.companyId = company._id }
                        }
                    )
                }
                memberDao.insert(members)
            }

            override fun shouldFetch(data: List<CompanyMemberRelation>?): Boolean = true
            override fun loadFromDb(): Flow<List<CompanyMemberRelation>> {
                return companyDao.getAll()
            }

            override suspend fun fetchFromNetwork(): Response<List<Company>> {
                return jsonGeneratorRemoteDataSource.getCompanies()
            }
        }
            .asFlow(appContext)
            .flowOn(Dispatchers.IO)
    }
}
