package com.example.plantysick;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABSE_NAME = "plantysick.db";
    private static final String TABLE_NAME = "users_details";
    private static final String ID = "ID";
    private static final String EMAIL = "Email";
    private static final String USERNAME = "User_Name";
    private static final String PASSWORD = "Password";
    private static final String CONTACT = "Contact";
    private static final String DOB = "DOB";
    private static final String NAME = "Name";
    private static final String QUESTION_TITLE = "questionTitle";
    private static final String QUESTION_DESCRIPTION = "questionDescription";
    private static final String DATE = "date";
    private static final String FEEDBACK = "feedback";
    private static final String RATING = "rating";
    private static final String STATE = "state";
    private static final String DISTRICT = "district";
    private static final String REACT_TABLE = "reacts";
    private static final String REACT_POSTID = "post_id";
    private static final String REACT_USERNAME = "username";
    private static final String REACT = "react";

    private static final int VERSION_NUMBER = 27;
    private static final String TABLE_NAME_2 = "community_post";
    private static final String TABLE_NAME_3 = "feedback_post";


    private Context context;

    //creating user_details table
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USERNAME + " VARCHAR(255) NOT NULL, "
            + EMAIL + " TEXT NOT NULL,"
            + CONTACT + " TEXT NOT NULL, "
            + DOB + " TEXT, "
            + NAME + " TEXT, "
            + PASSWORD + " TEXT NOT NULL, "
            + STATE + " TEXT, "
            + DISTRICT + " TEXT  " +
            ");";
    //dropping user details table
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    //creating community_post table
    private static final String CREATE_TABLE_2 = "CREATE TABLE " + TABLE_NAME_2 + "(" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USERNAME + " VARCHAR(255) NOT NULL, "
            + QUESTION_TITLE + " TEXT NOT NULL," + QUESTION_DESCRIPTION
            + " TEXT NOT NULL, "
            + DATE + " TEXT NOT NULL );";

    //dropping community_post table
    private static final String DROP_TABLE_2 = "DROP TABLE IF EXISTS " + TABLE_NAME_2;

    //creating feedback table
    private static final String CREATE_TABLE_3 = "CREATE TABLE " + TABLE_NAME_3 + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USERNAME + " VARCHAR(255) NOT NULL, "
            + FEEDBACK + " VARCHAR(255) NOT NULL, "
            + RATING + " FLOAT NOT NULL, "
            + DATE + " TEXT NOT NULL );";

    //like and dislike store table
    private static final String create_react_table = "CREATE TABLE " +
            REACT_TABLE + "("
            + REACT_POSTID + " INTEGER, "
            + REACT_USERNAME + " VARCHAR(100), "
            + REACT + " INTEGER DEFAULT 0, "
            + "PRIMARY KEY ( " + REACT_POSTID + ", " + REACT_USERNAME + "))";

    //COMMENT TABLE
    private static final String COMMENT_TABLE = "comments";
    private static final String POSTID = "post_id";
    private static final String CMNT_USER = "username";
    private static final String COMMENT = "comment";
    private static final String CMNT_DATE = "date";
    private static final String CMNT_ID = "id";
    private static final String create_comment_table = "CREATE TABLE " +
            COMMENT_TABLE + "("
            + CMNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + POSTID + " INTEGER, "
            + CMNT_USER + " VARCHAR(100), "
            + CMNT_DATE + " VARCHAR(20) default CURRENT_DATE, "
            + COMMENT + " TEXT DEFAULT NULL)";
    //dropping feedback table
    private static final String DROP_TABLE_3 = "DROP TABLE IF EXISTS " + TABLE_NAME_3;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABSE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            //sqLiteDatabase.execSQL(CREATE_TABLE);
            //sqLiteDatabase.execSQL(CREATE_TABLE_2);
            sqLiteDatabase.execSQL(CREATE_TABLE_3);

            //sqLiteDatabase.execSQL(create_comment_table);
            //sqLiteDatabase.execSQL(create_react_table);

            Toast.makeText(context, "table is created", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(context, "Exception: " + e, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            //sqLiteDatabase.execSQL(DROP_TABLE);
            Toast.makeText(context, "Table Dropped", Toast.LENGTH_SHORT).show();
            //sqLiteDatabase.execSQL(DROP_TABLE_2);
            sqLiteDatabase.execSQL(DROP_TABLE_3);
            onCreate(sqLiteDatabase);
            //Toast.makeText(context," table dropped",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(context, "Exception: " + e, Toast.LENGTH_SHORT).show();

        }
    }

    //it will insert user information after register
    public long insertData(UserDetails userDetails) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, userDetails.getUserName());
        contentValues.put(EMAIL, userDetails.getUserEmail());
        contentValues.put(CONTACT, userDetails.getUserContact());
        contentValues.put(PASSWORD, userDetails.getUserConfirmPassword());
        long rowId = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return rowId;
    }


    //it will insert post if anyone press ask community button
    public long insertPost(String user_name, String question_title, String question_description, String date) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, user_name);
        contentValues.put(QUESTION_TITLE, question_title);
        contentValues.put(QUESTION_DESCRIPTION, question_description);
        contentValues.put(DATE, date);
        long rowId = sqLiteDatabase.insert(TABLE_NAME_2, null, contentValues);
        return rowId;
    }

    //it will insert feedback if anyone add feedback
    public long insertFeedback(String user_name, String feedback, String rating, String date) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, user_name);
        contentValues.put(FEEDBACK, feedback);
        contentValues.put(RATING, rating);
        contentValues.put(DATE, date);
        long rowId = sqLiteDatabase.insert(TABLE_NAME_3, null, contentValues);
        return rowId;
    }


    //IT WILL BE CALL FROM LOG IN PAGE TO CHECK USER AND PASSWORD IS AVAILABLE OR NOT
    public boolean findpassword(String uname, String pass) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Boolean result = false;
        if (cursor.getCount() == 0) {
            //NO NEED TO DO ANYTHING
        } else {
            while (cursor.moveToNext()) {
                String userName = cursor.getString(1);
                String password = cursor.getString(6);
                if (userName.equals(uname) && password.equals(pass)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public Boolean findUserName(String uname) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Boolean result = false;
        if (cursor.getCount() == 0) {
            //Toast.makeText(context,"user name is uniq",Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String userName = cursor.getString(1);
                if (userName.equals(uname)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public boolean updateUserInfo(String uname, String fullname, String contact, String dob,
                                  String email, String state, String district) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET "
                + NAME + " = \"" + fullname + "\", "
                + CONTACT + " = \"" + contact + "\", "
                + DOB + " = \"" + dob + "\", "
                + STATE + " = \"" + state + "\", "
                + DISTRICT + " = \"" + district + "\" "
                + "WHERE "
                + USERNAME + " = \"" + uname + "\";";
        ;
        try {
            db.execSQL(query);
            return true;
        } catch (Exception e) {
            Log.i("farjana", "updateUserInfo: " + e);
            return false;
        }
    }
    public int getReactSum(int post_id){
        String query = "select sum(react) from reacts where post_id = " + post_id;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor result = db.rawQuery(query, null);

        if(result.moveToFirst()){
            return result.getInt(0);
        }else{
            return 0;
        }


    }
    public Cursor getdata(int pageNo) {
        int skip = (pageNo - 1) * 10;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME_2 + " LIMIT 10 " +
                "OFFSET " + skip, null);
        return cursor;
    }

    public Cursor getFeedbackdata() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME_3, null);
        return cursor;
    }

    public UserDetails finduser(String uname) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Boolean result = false;
        UserDetails userDetails = new UserDetails(context, "", "", "", "", "", "");
        while (cursor.moveToNext()) {
            String userName = cursor.getString(1);
            if (userName.equals(uname)) {
                userDetails = new UserDetails(context, cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(6), cursor.getString(4), cursor.getString(5));
                break;
            }
        }
        return userDetails;
    }

    //add like and dislike
    public boolean addReact(int postID, String username, int react) {
        String query = "INSERT OR REPLACE INTO " + REACT_TABLE + "( " + REACT_POSTID + ", " + REACT_USERNAME + ", " + REACT + ") " +
                "VALUES (" +
                postID + ", \"" + username + "\", " + react + ");";
        Log.i("babel", "addReact: " + username + query);
        final SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL(query);
            return true;
        } catch (Exception e) {
            Log.i("babel", "addReact: " + e);
            return false;
        }
    }
    public double avgRating(){
        String str = "select avg(rating) from " + TABLE_NAME_3;
        SQLiteDatabase db = getReadableDatabase();
        try{
            Cursor c = db.rawQuery(str, null);
            c.moveToFirst();
            return c.getDouble(0);
        }catch (Exception e){
            Log.i("knock", "avgRating: " + e);
            return -1;
        }
    }
    public Cursor getComments(int postId) {
        String query = "select * from " + COMMENT_TABLE + " where " + POSTID + " = " + postId;
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(query, null);
    }
    public void addComment(String username, String comment, int postid){
        ContentValues cv = new ContentValues();
        cv.put(CMNT_USER, username);
        cv.put(COMMENT, comment);
        cv.put(POSTID, postid);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(COMMENT_TABLE, null, cv);
    }
    public int getReact(int postId, String username) {
        String query = "select " + REACT + " from " + REACT_TABLE +
                " where " + REACT_USERNAME + "  = \"" + username + "\" and " + REACT_POSTID + "= " + postId + ";";


        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor result = db.rawQuery(query, null);
            if (result.moveToFirst())
                return result.getInt(0);
            return 0;
        } catch (Exception e) {
            Log.i("babel", "getReact: " + e);
            return 0;
        }
    }
}


