package pl.wfranik.moviesapp.domain

import javax.inject.Inject

class FooUseCase @Inject constructor() {

    operator fun invoke(): String {
        return "Hello from FooUseCase"
    }
}
