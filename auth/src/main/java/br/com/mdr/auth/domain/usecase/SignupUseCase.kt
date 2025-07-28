package br.com.mdr.auth.domain.usecase

import br.com.mdr.auth.domain.repository.AuthRepository
import javax.inject.Inject

class SignupUseCase
@Inject
constructor(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
    ): Boolean {
        return repository.signup(email, password)
    }
}
