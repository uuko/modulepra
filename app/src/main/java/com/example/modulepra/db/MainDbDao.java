package com.example.modulepra.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
interface MainDbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertUsers(MainTable... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertAllPrivateData(List<MainTable> friends);

    @Update
    public Completable updateUsers(MainTable... users);

    @Delete
    Completable deletePrivateDatas(List<MainTable> photoEntityList);

    @Delete
    public Completable deleteUsers(MainTable... users);

    @Query("SELECT * FROM MainTable")
    Single<List<MainTable>> loadAllPrivateData();

    @Query("DELETE FROM MainTable WHERE id = :id")
    public Completable deleteAlertByUserId(String id);
    @Query("SELECT * FROM MainTable WHERE id = :qbeeId " )
    Single<List<MainTable>> loadAllSharedDataByQbeeID(int qbeeId);
//    @Query("SELECT * FROM PRIVATE_ENTITY")
//    Single<List<PhotoEntity>> loadAllPrivateData();
//    @Query("SELECT * FROM PRIVATE_ENTITY WHERE folder_content =(:folder_content) AND " + "item_id = (:item_id) LIMIT 1")
//    Flowable<PhotoEntity> findOneUserNam(String folder_content,String item_id);
}
