package com.alfian.budayaku.database

import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface BudayaDao {
    //senjata
    @RawQuery(observedEntities = [SenjataEntity::class])
    fun getAllNotes(query: SupportSQLiteQuery): DataSource.Factory<Int, SenjataEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(list: List<SenjataEntity>)

    //rumah
    @Query("SELECT * FROM RumahEntity")
    fun getAllRumah(): DataSource.Factory<Int, RumahEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllRumah(list: List<RumahEntity>)

    //pakaian
    @Query("SELECT * FROM PakaianEntity")
    fun getAllPakaian(): DataSource.Factory<Int, PakaianEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllPakaian(list: List<PakaianEntity>)

    //tambahkan fungsi search
    @Query("SELECT * FROM SenjataEntity WHERE province LIKE '%' || :search || '%'")
    fun searchItem(search: String?): DataSource.Factory<Int, SenjataEntity>
    
}