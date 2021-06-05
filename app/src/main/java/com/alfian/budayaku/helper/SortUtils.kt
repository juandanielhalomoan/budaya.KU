package com.alfian.budayaku.helper

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"
    const val RANDOM = "Random"
    const val SEARCH = "Search"
    fun getSortedQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM SenjataEntity ")
        if (filter == NEWEST) {
            simpleQuery.append("ORDER BY id DESC")
        } else if (filter == OLDEST) {
            simpleQuery.append("ORDER BY id ASC")
        } else if (filter == RANDOM) {
            simpleQuery.append("ORDER BY RANDOM()")
        }
        else if (filter == SEARCH) {
            simpleQuery.append("WHERE name LIKE :arg0")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

}