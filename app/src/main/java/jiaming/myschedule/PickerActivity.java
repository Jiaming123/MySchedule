package jiaming.myschedule;

import java.util.Calendar;
import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.content.Context;
import android.content.Intent;
import android.app.DatePickerDialog;



public class PickerActivity extends Activity {

    private TextView tvDisplayTime;
    private TimePicker timePicker1;
    private Button btnChangeTime;
    Button button;

    private int hour;
    private int minute;

    static final int TIME_DIALOG_ID = 999;

    ///////////////////////
    private TextView tvDisplayDate;
    private DatePicker dpResult;
    private Button btnChangeDate;

    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 888;

    private Button Notification;

    private final static String PREFS_HOUR = "hour";
    private final static String PREFS_MIN = "minute";
    private final static String PREFS_DAY = "day";
    private final static String PREFS_MONTH = "month";
    private final static String PREFS_YEAR = "year";

    private final static String Savehour = "Savehour";
    private final static String Savemin = "saveminute";
    private final static String Saveday = "Saveday";
    private final static String Savemonth = "savemonth";
    private final static String Saveyear = "saveyear";

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_picker);

        setCurrentTimeOnView();

        /////////Added
        setCurrentDateOnView();
        addListenerOnButton();

        SharedPreferences sethour = getSharedPreferences(PREFS_HOUR,0);
        SharedPreferences setmin = getSharedPreferences(PREFS_MIN,1);
        SharedPreferences setday = getSharedPreferences(PREFS_DAY,2);
        SharedPreferences setmonth = getSharedPreferences(PREFS_MONTH,3);
        SharedPreferences setyear = getSharedPreferences(PREFS_YEAR,4);

        hour = sethour.getInt(Savehour, 0);
        minute = setmin.getInt(Savemin,1);
        day = setday.getInt(Saveday, 2);
        month = setmonth.getInt(Saveday, 3);
        year = setyear.getInt(Saveyear, 4);

        //View temperatureButton = findViewById(R.id.creat_notification_button);
        //temperatureButton.setOnClickListener(this);

    }

    public void Setall(View v) {

        String message=tvDisplayTime.getText().toString();
        Intent intent = new Intent();

        //intent.putExtra("",message);
        setResult(1, intent);
        finish();

    }

    // display current time
    public void setCurrentTimeOnView() {

        tvDisplayTime = (TextView) findViewById(R.id.tvTime);
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);

        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        // set current time into textview
        tvDisplayTime.setText(
                new StringBuilder().append(pad(hour))
                        .append(":").append(pad(minute)));

        // set current time into timepicker
        timePicker1.setCurrentHour(hour);
        timePicker1.setCurrentMinute(minute);

    }

    /////////////////////////Added
    // display current date
    public void setCurrentDateOnView() {

        tvDisplayDate = (TextView) findViewById(R.id.tvDate);
        dpResult = (DatePicker) findViewById(R.id.dpResult);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        // set current date into datepicker
        dpResult.init(year, month, day, null);

    }


    public void addListenerOnButton() {

        btnChangeTime = (Button) findViewById(R.id.btnChangeTime);

        btnChangeTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(TIME_DIALOG_ID);
            }

        });

        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);

        btnChangeDate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);
            }

        });

        Notification = (Button) findViewById(R.id.creat_notification_button);

        Notification.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(this)
                        .setContentTile("Important Notification")
                        .setContentText("You have been notified.");

                Intent resultIntent = new Intent (PickerActivity.this, MainActivity.class);

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(MainActivity.class);

                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // 1 allows you to update the notification later on.
                mNotificationManager.notify(1, mBuilder.build());

                // Don't forget to remove...
                try {
                    boolean someVar = false;
                    if (someVar) {
                        Exception e = new Exception("Capital Crime!");
                        throw e;
                    }
                }
                catch (Exception e) {
                    System.out.print("Never ever do this");
                }

            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                // set time picker as current time
                return new TimePickerDialog(this, timePickerListener, hour, minute,false);


            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener, year, month,day);

            default:
                System.out.println("System Broke!");
                break;
        }

        /*updatehour();
        updatemin();
        updateday();
        updatemonth();
        updateyear();*/
        //////////////////////////////****************************
        return null;

    }


    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;

                    // set current time into textview
                    tvDisplayTime.setText(new StringBuilder().append(pad(hour))
                            .append(":").append(pad(minute)));

                    // set current time into timepicker
                    timePicker1.setCurrentHour(hour);
                    timePicker1.setCurrentMinute(minute);

                    SharedPreferences sethour = getSharedPreferences(PREFS_HOUR, 0);
                    SharedPreferences.Editor editorhour = sethour.edit();
                    editorhour.putInt(Savehour, hour);
                    editorhour.commit();
                    //testing can Save save two values
                    SharedPreferences setmint = getSharedPreferences(PREFS_MIN,1);
                    SharedPreferences.Editor editormin = setmint.edit();
                    editormin.putInt(Savemin, minute);
                    editormin.commit();


                    updatehour();
                    updatemin();

                }
            };

    //////////////////////////////Added
    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            tvDisplayDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

            // set selected date into datepicker also
            dpResult.init(year, month, day, null);

            SharedPreferences setday = getSharedPreferences(PREFS_DAY, 2);
            SharedPreferences.Editor editorday = setday.edit();
            editorday.putInt(Saveday, day);
            editorday.commit();

            SharedPreferences setmonth = getSharedPreferences(PREFS_MONTH,3);
            SharedPreferences.Editor editormonth = setmonth.edit();
            editormonth.putInt(Savemonth, month);
            editormonth.commit();

            SharedPreferences setyear = getSharedPreferences(PREFS_YEAR,4);
            SharedPreferences.Editor editoryear = setyear.edit();
            editoryear.putInt(Saveyear, year);
            editoryear.commit();

            updateday();
            updatemonth();
            updateyear();

        }
    };


    private void updatehour() {
        TextView h = (TextView)findViewById(R.id.tvTime);
        h.setText(Integer.toString(hour));
    }

    private void updatemin() {
        TextView m = (TextView) findViewById(R.id.tvTime);
        m.setText(Integer.toString(minute));
    }

    private void updateday() {
        TextView m = (TextView) findViewById(R.id.tvDate);
        m.setText(Integer.toString(day));
    }

    private void updatemonth() {
        TextView m = (TextView) findViewById(R.id.tvDate);
        m.setText(Integer.toString(month));
    }

    private void updateyear() {
        TextView m = (TextView) findViewById(R.id.tvDate);
        m.setText(Integer.toString(year));
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }


}

