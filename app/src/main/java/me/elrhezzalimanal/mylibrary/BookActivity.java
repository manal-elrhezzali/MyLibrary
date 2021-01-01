package me.elrhezzalimanal.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddToWantToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavorite;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();
//         made this a comment cuz now we get the data from the RecyclerView by using the intent
//        String longDescription = "I am the long descriptionI am the long descriptionI am the long descriptionI am the long descriptionI am the long descriptionI am the long descriptionI am the long description"+"\n"+"I am the long descriptionI am the long descriptionI am the long descriptionI am the long descriptionI am the long descriptionI am the long descriptionI am the long description"+"\n"+"I am the long descriptionI am the long descriptionI am the long descriptionI am the long descriptionI am the long descriptionI am the long descriptionI am the long description";
//        Book book = new Book(1,"1Q84","Haruki Murakami",1350,"https://images-na.ssl-images-amazon.com/images/I/41FdmYnaNuL._SX322_BO1,204,203,200_.jpg",
//                "A work of maddening brilliance", longDescription +longDescription + longDescription);
        Intent intent = getIntent();
        if (null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY,-1);
            if (bookId != -1){ // to make sure that we received data from the intent
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if (null != incomingBook){ //checks if we found a book in the allBooks list that matches the id
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);

                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);

                }

            }
        }
    }


    /**
     * Enables and Disables btnAddToCurrentlyReading
     * Adds a book to currentlyReadingBooks ArrayList
     * @param incomingBook
     */
    private void handleCurrentlyReadingBooks(final Book incomingBook){
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance().getCurrentlyReadingBooks();
        boolean existInCurrentlyReadingBooks = false;
        for (Book b: currentlyReadingBooks) {
            if (b.getId() == incomingBook.getId()){
                existInCurrentlyReadingBooks = true;
            }

        }

        if (existInCurrentlyReadingBooks){
            btnAddToCurrentlyReading.setEnabled(false); //disable the button if the book already exists in currentlyReadingBooks static list
        }else{
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToCurrentlyReading(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book Added Successfully", Toast.LENGTH_SHORT).show();
                        //navigates the user to a new activity that shows the elements of the static list currentlyReadingBooks
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Oops! something unexpected happened. Please Try again", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    /**
     * Enables and Disables btnAddToFavorite
     * Adds a book to favoriteBooks ArrayList
     * @param incomingBook
     */

    private void handleFavoriteBooks(final Book incomingBook){
        ArrayList<Book> favoriteBooks = Utils.getInstance().getFavoriteBooks();
        boolean existInFavoriteBooks = false;
        for (Book b: favoriteBooks) {
            if (b.getId() == incomingBook.getId()){
                existInFavoriteBooks = true;
            }

        }

        if (existInFavoriteBooks){
            btnAddToFavorite.setEnabled(false); //disable the button if the book already exists in currentlyReadingBooks static list
        }else{
            btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToFavorite(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book Added Successfully", Toast.LENGTH_SHORT).show();
                        //navigates the user to a new activity that shows the elements of the static list currentlyReadingBooks
                        Intent intent = new Intent(BookActivity.this, FavoriteBooksActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Oops! something unexpected happened. Please Try again", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
    /**
     * Enables and Disables btnAddToWantToRead
     * Adds a book to wantToReadBooks ArrayList
     * @param incomingBook
     */
    private void handleWantToReadBooks(final Book incomingBook){
        ArrayList<Book> wantToReadBooks = Utils.getInstance().getWantToReadBooks();
        boolean existInWantToReadBooks = false;
        for (Book b: wantToReadBooks) {
            if (b.getId() == incomingBook.getId()){
                existInWantToReadBooks = true;
            }

        }

        if (existInWantToReadBooks){
            btnAddToWantToRead.setEnabled(false); //disable the button if the book already exists in alreadyReadBooks static list
        }else{
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToWantToRead(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book Added Successfully", Toast.LENGTH_SHORT).show();
                        //navigates the user to a new activity that shows the elements of the static list alreadyReadyBooks
                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Oops! something unexpected happened. Please Try again", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    /**
     * Enables and Disables btnAddToAlreadyRead
     * Adds a book to AlreadyReadBooks ArrayList
     * @param incomingBook
     */
    private void handleAlreadyRead(final Book incomingBook) {
        //checking if the incomingBook already exists in the alreadyReadBooks static list
        ArrayList<Book> alreadyReadBooks = Utils.getInstance().getAlreadyReadBooks();
        boolean existInAlreadyReadBooks = false;
        for (Book b: alreadyReadBooks) {
            if (b.getId() == incomingBook.getId()){
                existInAlreadyReadBooks = true;
            }
        }

        if (existInAlreadyReadBooks){
            btnAddToAlreadyRead.setEnabled(false); //disable the button if the book already exists in alreadyReadBooks static list
        }else{
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToAlreadyRead(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book Added Successfully", Toast.LENGTH_SHORT).show();
                        //navigates the user to a new activity that shows the elements of the static list alreadyReadyBooks
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Oops! something unexpected happened. Please Try again", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);
    }

    private void initViews() {
        txtAuthor = findViewById(R.id.txtAuthorName);
        txtBookName = findViewById(R.id.txtBookName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);

        btnAddToWantToRead = findViewById(R.id.btnAddToWantToReadList);
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyReadList);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToFavorite = findViewById(R.id.btnAddToFavorite);
        bookImage = findViewById(R.id.imgBook);

    }


}