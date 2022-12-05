package com.cristhianbonilla.support.config

import com.cristhianbonilla.support.config.ResultDomain.Error

abstract class ErrorMapper {

    abstract fun customError(code: Int?, errorBody: String): Error

    abstract fun genericError(): ErrorDomain
}
