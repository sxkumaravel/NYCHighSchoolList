package com.a20190529_sureshkumarkumaravel_nycschools.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created on 2019-05-29.
 *
 * @author kumars
 */
data class HighSchool(
    val dbn: String,
    @SerializedName("school_name") val schoolName: String,
    @SerializedName("overview_paragraph") val description: String,
    val location: String,
    @SerializedName("phone_number") val phone: String,
    @SerializedName("school_email") val email: String,
    val website: String,
    @SerializedName("total_students") val totalStudents: String
) : Serializable {
}