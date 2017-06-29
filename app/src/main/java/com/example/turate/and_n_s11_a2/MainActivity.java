package com.example.turate.and_n_s11_a2;

import android.app.Activity;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.List;

public class MainActivity extends Activity {

    /*
     * Change to type CustomAutoCompleteView instead of AutoCompleteTextView
     * since we are extending to customize the view and disable filter
     * The same with the XML view, type will be CustomAutoCompleteView
     */
    AutoComplete myAutoComplete;

    // adapter for auto-complete
    ArrayAdapter<String> myAdapter;

    // for database operations
    DatabaseHandler databaseH;

    // just to add some initial value
    String[] item = new String[] {"Please search..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            // instantiate database handler
            databaseH = new DatabaseHandler(MainActivity.this);

            // put sample data to database
            insertSampleData();

            // autocompletetextview is in activity_main.xml
            myAutoComplete = (AutoComplete) findViewById(R.id.myautocomplete);

            // add the listener so it will tries to suggest while the user types
            myAutoComplete.addTextChangedListener(new AutoCompleteTextChangedListener(this));

            // set our adapter
            myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
            myAutoComplete.setAdapter(myAdapter);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertSampleData(){

        // CREATE
        databaseH.create( new MyObject("TELEVISION") );
        databaseH.create( new MyObject("WASHING MACHINE") );
        databaseH.create( new MyObject("AIR CONDITIONER") );
        databaseH.create( new MyObject("SPOONS") );
        databaseH.create( new MyObject("MOBILE PHONE") );
        databaseH.create( new MyObject("HEADPHONES") );
        databaseH.create( new MyObject("TABLET") );
        databaseH.create( new MyObject("LAPTOP") );
        databaseH.create( new MyObject("DESKTOP") );
        databaseH.create( new MyObject("PENDRIVE") );
        databaseH.create( new MyObject("HARD DRIVE") );
        databaseH.create( new MyObject("USB CABLE") );
        databaseH.create( new MyObject("CHARGER") );
        databaseH.create( new MyObject("BATTERY") );
        databaseH.create( new MyObject("ROUTER") );
        databaseH.create( new MyObject("AUX CABLE") );
        databaseH.create( new MyObject("BLUETOOTH") );
        databaseH.create( new MyObject("REMOTE") );
        databaseH.create( new MyObject("TOYS") );
        databaseH.create( new MyObject("MICROWAVE") );

    }

    // this function is used in CustomAutoCompleteTextChangedListener.java
    public String[] getItemsFromDb(String searchTerm){

        // add items on the array dynamically
        List<MyObject> products = databaseH.read(searchTerm);
        int rowCount = products.size();

        String[] item = new String[rowCount];
        int x = 0;

        for (MyObject record : products) {

            item[x] = record.objectName;
            x++;
        }

        return item;
    }

}
