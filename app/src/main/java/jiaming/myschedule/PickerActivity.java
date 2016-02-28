package jiaming.myschedule;

import java.util.Calendar;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.content.Context;
import android.content.Intent;



public class PickerActivity extends Activity {

    Button button;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_picker);

        addListenerOnButton();

    }

    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.setdate);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PickDateActivity.class);
                startActivity(intent);
            }
        });

    }





    /*
    private TextView tvDisplayTime;
    private TimePicker timePicker1;
    private int hour;
    private int minute;


    private void setCurrentTimeOnView() {

        tvDisplayTime = (TextView) findViewById(R.id.tvTime);
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);

        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        tvDisplayTime.setText(new StringBuilder().append(pad(hour)).append(":").append(pad(minute)));

        timePicker1 = setCurrentHour(hour);
        timePicker1 = setCurrentMinute(minute);

    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
    ////////For Activity Part/////////////////////
        <TextView
        android:id="@+id/lblTime"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Current Time (H:M):"
        android:textAppearance="?android:attr/textAppearanceLarge"/>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/tvTime"
        android:text=""
        android:textAppearance="?android:attr/textAppearanceLarge"/>


    */
}
