// IBookManager.aidl
package com.shinelw.bindertestdemo;

// Declare any non-default types here with import statements
import com.shinelw.bindertestdemo.Book;
import com.shinelw.bindertestdemo.IOnNewBookArrivedListener;
interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}
