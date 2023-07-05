package dev.tounyu.kmmphotos.usecase

import dev.tounyu.kmmphotos.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class CoroutineUseCase<P, R>(
    private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(param: P): Result<R> {
        return try {
            withContext(dispatcher) {
                execute(param).let {
                    Result.Success(it)
                }
            }
        } catch (t: Throwable) {
            Result.Error(t)
        }
    }

    protected abstract suspend fun execute(param: P): R
}
