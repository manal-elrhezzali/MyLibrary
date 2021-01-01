package me.elrhezzalimanal.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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

                }

            }
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