package com.a20190529_sureshkumarkumaravel_nycschools.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created on 2019-05-29.
 *
 * @author kumars
 */
data class SchoolSATDetail(
    val dbn: String,
    @SerializedName("school_name") val schoolName: String,
    @SerializedName("num_of_sat_test_takers") val satTestTakers: String,
    @SerializedName("sat_critical_reading_avg_score") val satReadingAverageScore: String,
    @SerializedName("sat_math_avg_score") val satMathAverageScore: String,
    @SerializedName("sat_writing_avg_score") val satWritingAverageScore: String
) : Serializable {
}