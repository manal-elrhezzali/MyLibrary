package me.elrhezzalimanal.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

//Here we will use static lists instead of a database , the downside of this method is the data
//won't be persistent the data will be lost once the app is closed

//This is a Singleton class : we can only have one instance of this class in the entire application
//This class will interact with the static ArrayLists
public class Utils {

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS = "already_read_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private static final String FAVORITE_BOOKS = "favorite_books";


    private static Utils instance;

    private SharedPreferences sharedPreferences;

//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> wantToReadBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> favoriteBooks;


// added parameter to constructor of type Context to use it to get an instance of sharedPreferences
    private Utils(Context context){
        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);


        if (null == getAllBooks()){
            initData();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getAlreadyReadBooks()){
            editor.putString(ALREADY_READ_BOOKS,gson.toJson( new ArrayList<Book>() ) );//if the books stored in the sharedPreferences is null it initializes it to an empty ArrayList of books
            editor.commit();
        }

        if (null == getWantToReadBooks()){
            editor.putString(WANT_TO_READ_BOOKS,gson.toJson( new ArrayList<Book>() ) );
            editor.commit();
        }

        if (null == getCurrentlyReadingBooks()){
            editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getFavoriteBooks()){
            editor.putString(FAVORITE_BOOKS,gson.toJson( new ArrayList<Book>() ) );
            editor.commit();
        }

    }

    private void initData() {

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1,"1Q84","Haruki Murakami",1350,"https://images-na.ssl-images-amazon.com/images/I/41FdmYnaNuL._SX322_BO1,204,203,200_.jpg",
                       "A work of maddening brilliance", "I am 1Q84 Long Description"));
        books.add(new Book(2,"You are a Badass","Jen Sincero",1350,"https://images.squarespace-cdn.com/content/5dd0ae59b4b4b35c07faf907/1580282463170-JX342II4L0N60XYA14R7/5b51ypp1C%252B97L.jpg?content-type=image%2Fjpeg",
                       "A great book", "I am you are a badass Long Description Long Description Long Description"));
//saving the books list in the sharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
//to serialize our ArrayList: String text = gson.toJson(books);
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
//validates the changes we made in the sharedPreferences
        editor.commit();

        //let's add the books ArrayList into our sharedPreferences
//      //adds initial data
//        allBooks.add(new Book(1,"1Q84","Haruki Murakami",1350,"https://images-na.ssl-images-amazon.com/images/I/41FdmYnaNuL._SX322_BO1,204,203,200_.jpg",
//                       "A work of maddening brilliance", "I am 1Q84 Long Description"));
//        allBooks.add(new Book(2,"You are a Badass","Jen Sincero",1350,"https://images.squarespace-cdn.com/content/5dd0ae59b4b4b35c07faf907/1580282463170-JX342II4L0N60XYA14R7/5b51ypp1C%252B97L.jpg?content-type=image%2Fjpeg",
//                       "A great book", "I am you are a badass Long Description Long Description Long Description"));
    }

    public static Utils getInstance(Context context){
        if (null != instance){
            return instance;
        }else {
            instance = new Utils(context);
            return instance;
        }
    }


//    public static ArrayList<Book> getAllBooks() {
//        return allBooks;
//    }


    public ArrayList<Book> getAllBooks() {

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();//returns a null value if it fails to get the type
//      null is the default value so if we don't get anything from sharedPreferences a null we will returned as the string value
//      type is the Type we want to convert our strings data to
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY,null),type);
        return books;
    }

//    public static ArrayList<Book> getAlreadyReadBooks() {
//        return alreadyReadBooks;
//    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        //if (type == null) { System.out.println("type is null");}
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS,null),type);
        return books;
    }

//    public static ArrayList<Book> getWantToReadBooks() {
//        return wantToReadBooks;
//    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
       // if (type == null) { System.out.println("type is null");}

        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS,null),type);
//        if (books == null) { System.out.println("books list is null");}

        return books;
    }

//    public static ArrayList<Book> getCurrentlyReadingBooks() {
//        return currentlyReadingBooks;
//    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS,null),type);
        return books;
    }

//    public static ArrayList<Book> getFavoriteBooks() {
//        return favoriteBooks;
//    }

    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS,null),type);
        return books;
    }


//    public Book getBookById(int id){
//        for(Book b : allBooks){
//            if (b.getId() == id){
//                return b;
//            }
//        }
//        return null;
//    }

    public Book getBookById(int id){
        ArrayList<Book> books = getAllBooks();
        if (null != books){
            for(Book b : books){
                if (b.getId() == id){
                    return b;
                }
            }

        }

        return null;
    }

//    public boolean addToAlreadyRead(Book book){
//        return alreadyReadBooks.add(book);
//        //add returns true if the book was added successfully
//    }

    public boolean addToAlreadyRead(Book book){
        ArrayList<Book> books = getAlreadyReadBooks();
        if(books != null){
            // if add returns true => the book is added successfully
            if (books.add(book)){
                // now we need to convert the books ArrayList into a Json file and
                // then add it to the SharedPreferences instance
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //now let's clear our alreadyReadBooks list from the sharedPreferences and
                // then store the updated alreadyReadBooks list
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;

            }
        }
        return false;
    }

//    public boolean addToWantToRead(Book book){
//        return wantToReadBooks.add(book);
//    }

    public boolean addToWantToRead(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if(books != null){
            // if add returns true => the book is added successfully
            if (books.add(book)){
                // now we need to convert the books ArrayList into a Json file and
                // then add it to the SharedPreferences instance
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //now let's clear our wantToReadBooks list from the sharedPreferences and
                // then store the updated wantToReadBooks list
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;

            }
        }
        return false;
    }

//    public boolean addToFavorite(Book book){
//        return favoriteBooks.add(book);
//    }

    public boolean addToFavorite(Book book){
        ArrayList<Book> books = getFavoriteBooks();
        if(books != null){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS);
                editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                editor.commit();
                return true;

            }
        }
        return false;
    }

//    public boolean addToCurrentlyReading(Book book){
//        return currentlyReadingBooks.add(book);
//    }

    public boolean addToCurrentlyReading(Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if(books != null){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return true;

            }
        }
        return false;
    }

//    public boolean removeFromAlreadyRead(Book book){
//        return alreadyReadBooks.remove(book);
//        // remove() Returns true if this list contained the specified
//        // element (or equivalently, if this list changed as a result of the call
//    }

    public boolean removeFromAlreadyRead(Book book){
        ArrayList<Book> books = getAlreadyReadBooks();
        if(books != null){
            //we can't use if(books.remove(book)){} because even though the values of the book's description ,author.. are the same the references of the book passed to the method and the book in the ArrayList are different
            for (Book b: books) {
                if(b.getId() == book.getId()){
                    //b book that is in the list not the one passed to the method
                    if(books.remove(b)){// if b is removed => remove returns true then we need to update our sharedPreferences
                       Gson gson= new Gson();
                       SharedPreferences.Editor editor = sharedPreferences.edit();
                       editor.remove(ALREADY_READ_BOOKS);
                       editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                       editor.commit();
                       return true;
                    }
                }

            }
        }
        return false;
    }

//    public boolean removeFromWantToRead(Book book){
//        return wantToReadBooks.remove(book);
//    }

    public boolean removeFromWantToRead(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if(books != null){
            //we can't use if(books.remove(book)){} because even though the values of the book's description ,author.. are the same the references of the book passed to the method and the book in the ArrayList are different
            for (Book b: books) {
                if(b.getId() == book.getId()){
                    //b book that is in the list not the one passed to the method
                    if(books.remove(b)){// if b is removed => remove returns true then we need to update our sharedPreferences
                        Gson gson= new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }

            }
        }
        return false;
    }

//    public boolean removeFromCurrentlyReading(Book book){
//        return currentlyReadingBooks.remove(book);
//    }

    public boolean removeFromCurrentlyReading(Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if(books != null){
            //we can't use if(books.remove(book)){} because even though the values of the book's description ,author.. are the same the references of the book passed to the method and the book in the ArrayList are different
            for (Book b: books) {
                if(b.getId() == book.getId()){
                    //b book that is in the list not the one passed to the method
                    if(books.remove(b)){// if b is removed => remove returns true then we need to update our sharedPreferences
                        Gson gson= new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }

            }
        }
        return false;
    }

//    public boolean removeFromFavorites(Book book){
//        return favoriteBooks.remove(book);
//    }

    public boolean removeFromFavorites(Book book){
        ArrayList<Book> books = getFavoriteBooks();
        if(books != null){
            //we can't use if(books.remove(book)){} because even though the values of the book's description ,author.. are the same the references of the book passed to the method and the book in the ArrayList are different
            for (Book b: books) {
                if(b.getId() == book.getId()){
                    //b book that is in the list not the one passed to the method
                    if(books.remove(b)){// if b is removed => remove returns true then we need to update our sharedPreferences
                        Gson gson= new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS);
                        editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }

            }
        }
        return false;
    }
}
