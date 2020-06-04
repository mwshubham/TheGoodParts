package app.thegoodparts.data.repository

import app.thegoodparts.data.source.remote.SearchRemoteDataSource
import app.thegoodparts.data.source.remote.response.Address
import app.thegoodparts.data.source.remote.response.SearchData
import app.thegoodparts.data.source.remote.response.SearchResponse
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class SearchRepositoryTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var searchRemoteDataSource: SearchRemoteDataSource

    @Test
    fun getAddressList_mockValidOutput() {
        runBlocking {
            val mockOutput = SearchResponse(
                requestId = "10161706766605102861590206449991",
                data = SearchData(
                    autoCompleteRequestString = "Android",
                    focusWord = "",
                    addressList = listOf(
                        Address(
                            "MMI_ADDRESS_4GB7QW",
                            "No 3B, Kadugodi Industrial Area, Sadaramangala, Whitefield, Bengaluru, Karnataka, 560067"
                        )
                    )
                )
            )
            Mockito.`when`(searchRemoteDataSource.getAddressList(queryString = "Android"))
                .thenReturn(mockOutput)
            Truth.assertThat(searchRemoteDataSource.getAddressList(queryString = "Android").requestId)
                .isEqualTo("10161706766605102861590206449991")
        }

    }
}