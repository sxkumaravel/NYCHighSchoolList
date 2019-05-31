package com.a20190529_sureshkumarkumaravel_nycschools.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.a20190529_sureshkumarkumaravel_nycschools.data.RestInterface
import com.a20190529_sureshkumarkumaravel_nycschools.data.SchoolsDataSource
import com.a20190529_sureshkumarkumaravel_nycschools.data.SpringRestInterface
import com.a20190529_sureshkumarkumaravel_nycschools.model.HighSchool
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

/**
 * Created on 2019-05-30.
 *
 * @author kumars
 */
class MainViewModelTest {

    /**
     * In this test, LiveData will immediately post values without switching threads.
     */
    @get:Rule
    val rule = InstantTaskExecutorRule()

    companion object {
        private const val GRADLE_STATIC_RELATIVE_PATH = "src/test/"
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var restInterface: RestInterface

    @Before
    fun setUp() {
        restInterface = Mockito.mock(SpringRestInterface::class.java)
        val dataSource = SchoolsDataSource(restInterface)
        viewModel = MainViewModel(dataSource)
    }

    @Test
    fun loadHighSchoolLists() {
        val configJSON = parseLocalFile("resources/school-list.json")
        val type = object : TypeToken<ArrayList<HighSchool>>() {

        }.type
        val fromJson = Gson().fromJson<ArrayList<HighSchool>>(configJSON, type)
        Mockito.`when`(restInterface.getHighSchoolsData()).thenReturn(fromJson)

        runBlocking {
            viewModel.loadHighSchoolLists()
        }

        viewModel.schoolListLiveDate.observeForever {
            Assert.assertTrue(it!!.isNotEmpty())
        }
    }

    /**
     * TODO: remaining test scenarios
     * Loading
     * Response success, Failure
     */

    private fun parseLocalFile(fileName: String): String {
        val classLoader = this.javaClass.classLoader
        val resource = classLoader!!.getResource(fileName)
        val file = File(if (resource != null) resource.path else GRADLE_STATIC_RELATIVE_PATH + fileName)
        return String(Files.readAllBytes(file.toPath()), Charsets.UTF_8)
    }
}