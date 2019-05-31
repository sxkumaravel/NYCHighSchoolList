package com.a20190529_sureshkumarkumaravel_nycschools.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.a20190529_sureshkumarkumaravel_nycschools.data.RestInterface
import com.a20190529_sureshkumarkumaravel_nycschools.data.SchoolsDataSource
import com.a20190529_sureshkumarkumaravel_nycschools.data.SpringRestInterface
import com.a20190529_sureshkumarkumaravel_nycschools.model.HighSchool
import com.a20190529_sureshkumarkumaravel_nycschools.model.SchoolSATDetail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import java.io.File
import java.nio.file.Files
import java.util.ArrayList

/**
 * Created on 2019-05-30.
 *
 * @author kumars
 */
class DetailViewModelTest {

    /**
     * In this test, LiveData will immediately post values without switching threads.
     */
    @get:Rule
    val rule = InstantTaskExecutorRule()

    companion object {
        private const val GRADLE_STATIC_RELATIVE_PATH = "src/test/"
    }

    private lateinit var viewModel: DetailViewModel
    private lateinit var restInterface: RestInterface

    @Before
    fun setUp() {
        restInterface = Mockito.mock(SpringRestInterface::class.java)
        val dataSource = SchoolsDataSource(restInterface)
        viewModel = DetailViewModel(dataSource)
    }

    @Test
    fun loadSchoolSATDataSuccess() {
        val configJSON = parseLocalFile("resources/sat-details.json")
        val type = object : TypeToken<ArrayList<SchoolSATDetail>>() {

        }.type
        val fromJson = Gson().fromJson<ArrayList<SchoolSATDetail>>(configJSON, type)
        Mockito.`when`(restInterface.getSchoolsSATDetails()).thenReturn(fromJson)

        val tempHighSchool = HighSchool("02M260", "", "", "", "", "", "", "")

        runBlocking {
            viewModel.loadSchoolSATData(tempHighSchool)
        }

        // there is an issue with coroutines unit testing - need to fix it.
        // but apart from that this is how I write my unit test cases especially for View models.
        viewModel.schoolSATLiveDate.observeForever {
            Assert.assertTrue(it!!.dbn.isNotBlank())
        }
    }

    /**
     * TODO: I can still write tests to cover all the negative cases.
     * TODO: Loading
     * TODO: filterSATDetail
     *
     * With that all the business logic within the Detail Activity can be covered.
     */

    private fun parseLocalFile(fileName: String): String {
        val classLoader = this.javaClass.classLoader
        val resource = classLoader!!.getResource(fileName)
        val file = File(if (resource != null) resource.path else GRADLE_STATIC_RELATIVE_PATH + fileName)
        return String(Files.readAllBytes(file.toPath()), Charsets.UTF_8)
    }
}