// IOnNewBookArrivedListener.aidl
package com.shinelw.bindertestdemo;
import com.shinelw.bindertestdemo.Book;
// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {
   void onNewBookArrived(in Book newBook);
}
