package com.example.pizzawizza.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pizzawizza.data.CartItem;

import java.util.List;

// Data Access Object (DAO) interface for CartItem operations
@Dao
public interface ProductDao {

    // Query to get all CartItems from the database
    @Query("SELECT * FROM CartItem")
    List<CartItem> getAll();

    // LiveData query to get all CartItems, limited to 500 results
    @Query("SELECT * FROM CartItem limit 500")
    LiveData<List<CartItem>> liveGetAll();

    // LiveData query to get the count of CartItems, limited to 500 results
    @Query("SELECT count(*) FROM CartItem limit 500")
    LiveData<Integer> liveGetCount();

    // Query to get all CartItems with their corresponding Product details
    @Query("SELECT * FROM CartItem,Product where Product.id=CartItem.productId")
    List<CartProduct> getAllCartProducts();

    // Query to get a CartItem by product ID
    @Query("SELECT * FROM CartItem where productId=:pid")
    CartItem getByProductId(int pid);

    // Query to get the count of all CartItems
    @Query("SELECT count(*) FROM CartItem")
    int getCount();

    // Insert or replace a CartItem in the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertOrReplace(CartItem CartItem);

    // Insert or replace a list of CartItems in the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceAll(List<CartItem> CartItems);

    // Delete a specific CartItem from the database
    @Delete
    void delete(CartItem blogPost);

    // Update a specific CartItem in the database
    @Update
    void update(CartItem blogPost);

    // Delete all CartItems from the database
    @Query("DELETE FROM CartItem")
    void deleteAll();
}
