package jp.co.mz.cn;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class ContactListActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactlist);
        
        String[] proj = new String[]{Contacts.DISPLAY_NAME,Contacts.HAS_PHONE_NUMBER};
        Cursor cursor = managedQuery(Contacts.CONTENT_URI, proj, null, null, null);
        cursor.moveToFirst();
        Log.d("count", Integer.toString(cursor.getCount()));
        for (int i = 0; i > cursor.getCount(); i++) {
        	
        	Log.d("DATA NAME",cursor.getString(1));
        	Log.d("DATA NUMBER",cursor.getString(2));
        	cursor.moveToNext();
        }
        if (!cursor.isClosed()) cursor.close();
        
        
        // Register handler for UI elements
        Button mAddAccountButton = (Button) findViewById(R.id.addContactButton);
        mAddAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchContactAdder();
            }
        });
    }
    
    /**
     * Launches the ContactAdder activity to add a new contact to the selected accont.
     */
    protected void launchContactAdder() {
        Intent i = new Intent(this, ContactAdder.class);
        startActivity(i);
    }
}