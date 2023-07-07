package dev.tounyu.kmmphotos.usecase

import dev.tounyu.kmmphotos.KmmResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class CoroutineUseCase<P, R : Any>(
    private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(param: P): KmmResult<R> {
        return try {
            withContext(dispatcher) {
                execute(param).let {
                    KmmResult.Success(it)
                }
            }
        } catch (t: Throwable) {
            KmmResult.Error(t)
        }
    }

    protected abstract suspend fun execute(param: P): R
}
