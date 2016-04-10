package jiaming.myschedule;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import static junit.framework.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import junit.framework.Assert;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

//more and more
public class MainActivity extends ActionBarActivity implements OnClickListener, OnItemClickListener {

    private ArrayAdapter<String> adapter;

    // Any List Interface Data Structure
    private ArrayList<String> listItems = new ArrayList<String>();;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View prevButton = findViewById(R.id.button);
        prevButton.setOnClickListener(this);

        readItems();

        ListView listView = (ListView)this.findViewById(R.id.listOfschedule);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , listItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        //////////////long click delete()*****
        /*LinearLayout layout = (LinearLayout) findViewById(R.id.listOfschedule);
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            /////////////delete() method********


        });*/

    }

    /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Dialog box");
    builder.setMessage("Max count reached. Start over?");
    builder.setCancelable(false);
    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            count = 0;
            // Note, you have to call update count here because.
            //   the call builder.show() below is non blocking.
            updateCount();
        }
    });
    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            // Do nothing
        }
    });
    builder.show();*/

    // This is for button clicks
    @Override
    public void onClick(View arg0) {
        Assert.assertNotNull(arg0);
        // Get string entered
        TextView tv = (TextView) findViewById(R.id.scheduletext);
        // Add string to underlying data structure
        listItems.add(tv.getText().toString());
        // Notify adapter that underlying data structure changed
        adapter.notifyDataSetChanged();
        writeItems();
    }

    // This is for selecting an item from the list
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, PickerActivity.class);
        //startActivity(i);
        startActivityForResult(i, 1);///////**************

        //i.putExtra(PickerActivity.S)


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        //check if the request code is same as what is passed here it is 2
        if(requestCode == 1)
        {


            if(resultCode ==PickerActivity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }


    }
/////////////////////*********************

    ////////////////save string data for Submittion
    private void readItems () {
        File filesDir = getFilesDir();
        File scheduleFile = new File (filesDir, "schedule.txt");
        try{
            listItems = new ArrayList<String>(FileUtils.readLines(scheduleFile));
        } catch (IOException e) {
            listItems = new ArrayList<String>();
        }
    }

    private void writeItems () {
        File fileDir = getFilesDir();
        File scheduleFile = new File (fileDir, "schedule.txt");
        try {
            FileUtils.writeLines(scheduleFile, listItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

