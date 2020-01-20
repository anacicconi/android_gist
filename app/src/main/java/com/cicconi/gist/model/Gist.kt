package com.cicconi.gist.model

import kotlinx.serialization.Serializable

data class Gist(
    var url: String? = null,
    var description: String? = null,
    var owner: Owner? = null,
    var files: Map<String, File>? = null) {

    @Serializable
    class Owner(
        var login: String? = null,
        var avatar_url: String? = null)

    @Serializable
    class File(val filename: String? = null)

    /*@Serializable
    class Files(val file: Map<String, File>? = null) {
        @Serializable
        class File(val filename: String? = null)
    }*/
}
