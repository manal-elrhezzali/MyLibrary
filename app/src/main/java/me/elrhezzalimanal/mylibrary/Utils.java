package me.elrhezzalimanal.mylibrary;

import java.util.ArrayList;

//Here we will use static lists instead of a database , the downside of this method is the data
//won't be persistent the data will be lost once the app is closed

//This is a Singleton class : we can only have one instance of this class in the entire application
//This class will interact with the static ArrayLists
public class Utils {

    private static Utils instance;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favoriteBooks;



    private Utils(){
        if (null == allBooks){
            allBooks = new ArrayList<>();
            initData();
        }

        if (null == alreadyReadBooks){
            alreadyReadBooks = new ArrayList<>();
        }

        if (null == wantToReadBooks){
            wantToReadBooks = new ArrayList<>();
        }

        if (null == currentlyReadingBooks){
            currentlyReadingBooks = new ArrayList<>();
        }

        if (null == favoriteBooks){
            favoriteBooks = new ArrayList<>();
        }

    }

    private void initData() {
        //TODO: add initial data
        allBooks.add(new Book(1,"1Q84","Haruki Murakami",1350,"https://images-na.ssl-images-amazon.com/images/I/41FdmYnaNuL._SX322_BO1,204,203,200_.jpg",
                       "A work of maddening brilliance", "I am 1Q84 Long Description"));
        allBooks.add(new Book(2,"You are a Badass","Jen Sincero",1350,"https://images.squarespace-cdn.com/content/5dd0ae59b4b4b35c07faf907/1580282463170-JX342II4L0N60XYA14R7/5b51ypp1C%252B97L.jpg?content-type=image%2Fjpeg",
                       "A great book", "I am you are a badass Long Description Long Description Long Description"));
    }

    public static Utils getInstance(){
        if (null != instance){
            return instance;
        }else {
            instance = new Utils();
            return instance;
        }
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public Book getBookById(int id){
        for(Book b : allBooks){
            if (b.getId() == id){
                return b;
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book){
        return alreadyReadBooks.add(book);
        //add returns true if the book was added successfully
    }

    public boolean addToWantToRead(Book book){
        return wantToReadBooks.add(book);
    }

    public boolean addToFavorite(Book book){
        return favoriteBooks.add(book);
    }

    public boolean addToCurrentlyReading(Book book){
        return currentlyReadingBooks.add(book);
    }
}
