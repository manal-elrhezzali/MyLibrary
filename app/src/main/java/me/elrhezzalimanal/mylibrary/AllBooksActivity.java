package me.elrhezzalimanal.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {
    private RecyclerView booksRecView;
    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

// animation:       overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        adapter = new BookRecViewAdapter(this,"allBooks");

        booksRecView = findViewById(R.id.booksRecView);
        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));

        /* commented this because now this list will be initialized in the Utils Singleton class
         * ArrayList<Book> books = new ArrayList<>();
         * books.add(new Book(1,"1Q84","Haruki Murakami",1350,"https://images-na.ssl-images-amazon.com/images/I/41FdmYnaNuL._SX322_BO1,204,203,200_.jpg",
         *       "A work of maddening brilliance", "Long Description"));
         * books.add(new Book(2,"You are a Badass","Jen Sincero",1350,"https://images.squarespace-cdn.com/content/5dd0ae59b4b4b35c07faf907/1580282463170-JX342II4L0N60XYA14R7/5b51ypp1C%252B97L.jpg?content-type=image%2Fjpeg",
         *       "A great book", "Long Description Long Description Long Description"));
         */

        adapter.setBooks(Utils.getInstance(this).getAllBooks());


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}