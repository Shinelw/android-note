package com.shinelw.bindertestdemo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by shinelw on 10/26/15.
 */
public class ProviderActivity extends Activity {
    private static final String TAG = "ProviderActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri bookUri = Uri.parse("content://com.shinelw.bindertestdemo.provider/book");
        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "程序设计的艺术");
        getContentResolver().insert(bookUri, values);
        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);
        while (bookCursor.moveToNext()) {
            Book book = new Book();
            book.bookId = bookCursor.getInt(0);
            book.bookName = bookCursor.getString(1);
            Log.d(TAG, "query book" + book.toString());
        }
        bookCursor.close();

        Uri userUri = Uri.parse("content://com.shinelw.bindertestdemo.provider/user");
        ContentResolver contentResolver = getContentResolver();
        Cursor userCursor = contentResolver.query(userUri, new String[] {"_id", "name", "sex"}, null, null, null);
        while(userCursor.moveToNext()) {
            Log.d(TAG, "query user:" + userCursor.getInt(0) + userCursor.getString(1) + userCursor.getInt(2));
        }
        userCursor.close();

    }
}
