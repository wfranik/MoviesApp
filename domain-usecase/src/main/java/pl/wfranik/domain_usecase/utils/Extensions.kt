package pl.wfranik.domain_usecase.utils

inline fun <reified T> T.toSuccess(): Result<T> = Result.success(this)
