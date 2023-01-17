package com.adamczajkowski.common.utils

import androidx.annotation.IntRange

class Paginator @JvmOverloads constructor(
    @param:IntRange(from = 0) val initPage: Int = INIT_PAGE,
    val pageSize: Int = PAGE_SIZE,
    var currentPage: Int = initPage,
    var isPaginationEnd: Boolean = false
) {
    var isLoading = false

    val offset: Int get() = (currentPage - initPage) * pageSize

    fun canLoadNextPage(): Boolean = !isLoading && !isPaginationEnd

    fun advance(hasMore: Boolean) {
        this.isPaginationEnd = !hasMore
        if (hasMore) {
            currentPage++
        }
    }

    fun advanceForLimit(offsetLimit: Int) {
        val currentOffset = offset + pageSize
        advance(currentOffset < offsetLimit)
    }

    fun resetPage() {
        currentPage = initPage
    }

    fun reset() {
        resetPage()
        this.isPaginationEnd = false
    }

    companion object {
        private const val PAGE_SIZE = 10
        private const val INIT_PAGE = 1
    }
}