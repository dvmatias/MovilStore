package com.matias.domain.mapper

/**
 * Each Mapper class should extend this class.
 */
abstract class Mapper<E, M> {

    abstract fun transformEntityToModel(e: E): M

    open fun transformModelToEntity(m: M): E {
        throw UnsupportedOperationException()
    }

}