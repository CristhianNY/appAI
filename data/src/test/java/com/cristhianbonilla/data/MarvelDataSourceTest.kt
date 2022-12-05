package com.cristhianbonilla.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cristhianbonilla.TestCoroutineRule
import com.cristhianbonilla.TestDispatcherProvider
import com.cristhianbonilla.data.characters.api.MarvelApi
import com.cristhianbonilla.data.characters.data_source.MarvelDataSource
import com.cristhianbonilla.data.characters.data_source.MarvelDataSourceImpl
import com.cristhianbonilla.mocks.characterDetailResponseEntity
import com.cristhianbonilla.mocks.characterListResultEntityMock
import com.cristhianbonilla.support.config.EMPTY
import com.cristhianbonilla.support.config.Result
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MarvelDataSourceTest {
    @Rule
    @JvmField
    val testRule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule: TestCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var api: MarvelApi

    private lateinit var dataSource: MarvelDataSource

    @Before
    fun setUp() {
        dataSource = MarvelDataSourceImpl(api).apply {
            dispatcherProvider = TestDispatcherProvider()
        }
    }

    @Test
    fun `Get invoke api`() = testCoroutineRule.runBlockingTest {
        // when
        dataSource.getCharacterList()

        // then
        verify(api).getCharacterList()
    }

    @Test
    fun `Get marvel characters list success`() = testCoroutineRule.runBlockingTest {
        val expectedResult = Result.success(characterListResultEntityMock)
        // given
        whenever(api.getCharacterList()).thenReturn(
            Response.success(
                characterListResultEntityMock
            )
        )

        // when
        val result = dataSource.getCharacterList()

        // then
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `Get marvel characters list error`() = testCoroutineRule.runBlockingTest {

        val expectedResult = String.EMPTY.toResponseBody("application/json".toMediaType())
        // given
        whenever(api.getCharacterList()).thenReturn(
            Response.error(
                HttpURLConnection.HTTP_INTERNAL_ERROR,
                expectedResult
            )
        )

        // when
        val result = dataSource.getCharacterList()

        // then
        Assert.assertTrue(result.status is Result.Status.ERROR)
    }

    @Test
    fun `Get marvel characters Details success`() = testCoroutineRule.runBlockingTest {
        val characterId = "1"
        val expectedResult = Result.success(characterDetailResponseEntity)
        // given
        whenever(api.getCharacterById(characterId)).thenReturn(
            Response.success(
                characterDetailResponseEntity
            )
        )

        // when
        val result = dataSource.getCharacterById(characterId)

        // then
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `Get marvel characters details error`() = testCoroutineRule.runBlockingTest {
        val characterId = "1"
        val expectedResult = String.EMPTY.toResponseBody("application/json".toMediaType())
        // given
        whenever(api.getCharacterById(characterId)).thenReturn(
            Response.error(
                HttpURLConnection.HTTP_INTERNAL_ERROR,
                expectedResult
            )
        )

        // when
        val result = dataSource.getCharacterById(characterId)

        // then
        Assert.assertTrue(result.status is Result.Status.ERROR)
    }
}
