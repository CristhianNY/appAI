package com.cristhianbonilla.support.config

object GenericErrorMapper : ErrorMapper() {
    override fun customError(code: Int?, errorBody: String) = ResultDomain.Error(GenericError)
    override fun genericError() = GenericError
}
