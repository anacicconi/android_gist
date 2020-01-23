package com.cicconi.gist.model

import kotlinx.serialization.Serializable
import java.io.Serializable as JavaSerializable

data class Gist(
    var url: String? = null,
    var description: String? = null,
    var owner: Owner? = null,
    var files: Map<String, File>? = null): JavaSerializable {

    @Serializable
    class Owner(
        var login: String? = null,
        var avatar_url: String? = null): JavaSerializable

    @Serializable
    class File(val filename: String? = null): JavaSerializable

    /*@Serializable
    class Files(val file: Map<String, File>? = null) {
        @Serializable
        class File(val filename: String? = null)
    }*/
}
