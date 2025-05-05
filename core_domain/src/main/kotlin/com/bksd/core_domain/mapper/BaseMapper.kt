package com.bksd.core_domain.mapper

fun interface BaseMapper<T : Any, V : Any> {
    fun map(from: T): V
}

interface ListMapper<T : Any, V : Any> : BaseMapper<List<T>, List<V>>
