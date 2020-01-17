package com.cicconi.gist.model

import kotlinx.serialization.Serializable

data class Gist(var url: String? = null, var owner: Owner? = null) {
    @Serializable
    class Owner {
        var login: String? = null
    }
}
