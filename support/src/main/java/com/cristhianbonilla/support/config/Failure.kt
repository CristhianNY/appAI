package com.cristhianbonilla.support.config

sealed class Failure {
    object NetworkConnection : Failure()
    abstract class FeatureFailure : Failure()
}
