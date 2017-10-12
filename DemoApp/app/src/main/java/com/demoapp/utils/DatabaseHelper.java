package com.demoapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.demoapp.models.ImageModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mitesh on 12/10/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH = "";
    private static String DB_NAME = Constants.DB_NAME;
    private SQLiteDatabase mDataBase;
    private Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.mContext = context;

    }

    public void createDataBase() throws IOException {
        //If the database does not exist, copy it from the assets.

        boolean mDataBaseExist = checkDataBase();
        if (!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                //Copy the database from assests
                copyDataBase();
                Log.e("DBHELPER", "createDatabase database created");
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    //Check that the database exists here: /data/data/your package/databases/Da Name
    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }

    //Copy the database from assets
    private void copyDataBase() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    //Open the database, so we can query it
    public boolean openDataBase() throws SQLException {
        String mPath = DB_PATH + DB_NAME;
        //Log.v("mPath", mPath);
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDataBase != null;
    }

    public List<ImageModel> getAllImageModelList(){
        openDataBase();
        List<ImageModel> list = new ArrayList<>();
        String query = "select * from "+ Constants.TABLE_P2D_PHOTO;
        Cursor cursor = mDataBase.rawQuery(query, null);
        Log.e("Count:", "=>"+cursor.getCount());
        if(cursor.getCount()>0){
            if(cursor.moveToFirst()){
                do{
                    ImageModel imageModel = new ImageModel();
                    imageModel.id =cursor.getInt(cursor.getColumnIndex(Constants.TABLE_KEY_ID));
                    imageModel.batch_id =cursor.getString(cursor.getColumnIndex(Constants.TABLE_KEY_BATCH_ID));
                    imageModel.batch_name =cursor.getString(cursor.getColumnIndex(Constants.TABLE_KEY_BATCH_NAME));
                    imageModel.image_name =cursor.getString(cursor.getColumnIndex(Constants.TABLE_KEY_IMAGE_NAME));
                    imageModel.timestamp =cursor.getString(cursor.getColumnIndex(Constants.TABLE_KEY_TIMESTAMP));
                    list.add(imageModel);
                }while(cursor.moveToNext());
            }
        }
        close();
        return list;
    }

    public void addNewImage(ImageModel imageModel){
        openDataBase();
        Log.e("addNewImage", "image_name : "+imageModel.image_name);
        ContentValues cv = new ContentValues();
        cv.put(Constants.TABLE_KEY_BATCH_ID, imageModel.batch_id);
        cv.put(Constants.TABLE_KEY_BATCH_NAME, imageModel.batch_name);
        cv.put(Constants.TABLE_KEY_IMAGE_NAME, imageModel.image_name);
        cv.put(Constants.TABLE_KEY_TIMESTAMP, imageModel.timestamp);

        mDataBase.insert(Constants.TABLE_P2D_PHOTO, null, cv);
        close();
    }

    public void updateImage(int id, ImageModel imageModel){
        openDataBase();
        Log.e("updateImage", "image_name : "+imageModel.image_name);
        ContentValues cv = new ContentValues();
        cv.put(Constants.TABLE_KEY_BATCH_ID, imageModel.batch_id);
        cv.put(Constants.TABLE_KEY_BATCH_NAME, imageModel.batch_name);
        cv.put(Constants.TABLE_KEY_IMAGE_NAME, imageModel.image_name);
        cv.put(Constants.TABLE_KEY_TIMESTAMP, imageModel.timestamp);

        mDataBase.update(Constants.TABLE_P2D_PHOTO, cv, "id="+id, null);
        close();
    }


    public void deleteImage(int id){
        openDataBase();
        mDataBase.delete(Constants.TABLE_P2D_PHOTO, "id="+id, null);
        close();
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
