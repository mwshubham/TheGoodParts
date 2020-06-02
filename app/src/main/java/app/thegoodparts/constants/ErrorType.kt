package app.thegoodparts.constants

sealed class ErrorType {
    sealed class NetworkError : ErrorType() {
        object InternetUnavailable : ErrorType()
    }

    object UnknownError : ErrorType()
}