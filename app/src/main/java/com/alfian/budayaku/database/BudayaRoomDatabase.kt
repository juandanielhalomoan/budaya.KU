package com.alfian.budayaku.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.alfian.budayaku.helper.DataBudaya
import java.util.concurrent.Executors

@Database(entities = [SenjataEntity::class, RumahEntity::class, PakaianEntity::class], version = 1, exportSchema = false)
abstract class BudayaRoomDatabase : RoomDatabase() {

    abstract fun budayaDao(): BudayaDao

    companion object {
        @Volatile
        private var INSTANCE: BudayaRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): BudayaRoomDatabase {
            if (INSTANCE == null) {
                synchronized(BudayaRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BudayaRoomDatabase::class.java, "budaya_database"
                    )
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                add()
                                addRumah()
                                addPakaian()
                            }
                        })
                        .build()
                }
            }
            return INSTANCE as BudayaRoomDatabase
        }

        fun add() {
            Executors.newSingleThreadExecutor().execute {
                val list: MutableList<SenjataEntity> = ArrayList()
                for (i in 0 until DataBudaya.dataSenjata.size) {
                    val senjata = SenjataEntity()
                    senjata.namaSenjata = DataBudaya.dataSenjata[i].name
                    senjata.descriptionSenjata = DataBudaya.dataSenjata[i].description
                    senjata.provinsiSenjata = DataBudaya.dataSenjata[i].province
                    senjata.gamabarSenjata = DataBudaya.dataSenjata[i].image

                    list.add(senjata)
                }

                INSTANCE?.budayaDao()?.insertAll(list)
            }
        }

        fun addRumah() {
            Executors.newSingleThreadExecutor().execute {
                val list: MutableList<RumahEntity> = ArrayList()

                for (i in 0 until DataBudaya.dataRumah.size) {
                    val rumah = RumahEntity()
                    rumah.namaRumah = DataBudaya.dataRumah[i].nameRumah
                    rumah.descriptionRumah = DataBudaya.dataRumah[i].descriptioRumah
                    rumah.provinsiRumah = DataBudaya.dataRumah[i].provinceRumah
                    rumah.gambarRumah = DataBudaya.dataRumah[i].imageRumah
                    list.add(rumah)
                }

                INSTANCE?.budayaDao()?.insertAllRumah(list)
            }
        }

        fun addPakaian() {
            Executors.newSingleThreadExecutor().execute {
                val list: MutableList<PakaianEntity> = ArrayList()

                for (i in 0 until DataBudaya.dataPakaian.size) {
                    val pakaian = PakaianEntity()
                    pakaian.namaPakaian = DataBudaya.dataPakaian[i].namePakaian
                    pakaian.descriptionPakaian = DataBudaya.dataPakaian[i].descriptioPakaian
                    pakaian.provinsiPakaian = DataBudaya.dataPakaian[i].provincePakaian
                    pakaian.gambarPakaian = DataBudaya.dataPakaian[i].imagePakaian
                    list.add(pakaian)
                }

                INSTANCE?.budayaDao()?.insertAllPakaian(list)
            }
        }


    }
}