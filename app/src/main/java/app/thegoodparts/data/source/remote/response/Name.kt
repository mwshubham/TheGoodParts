package app.thegoodparts.data.source.remote.response

data class Name(
    var first: String = "",
    var last: String = ""
) {
    fun getFullName() = "$first $last"
}