package com.demoapp.service;

import android.content.Context;

import com.demoapp.models.ImageModel;
import com.demoapp.utils.DatabaseHelper;

import java.io.IOException;
import java.util.List;

/**
 * Created by mitesh on 12/10/17.
 */

public class DatabaseService {

    DatabaseHelper dbHelper;

    public DatabaseService(Context _context) {
        try {
            dbHelper = new DatabaseHelper(_context);
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ImageModel> getAllImages() {
        return dbHelper.getAllImageModelList();
    }

    public void addNewImage(ImageModel imageModel) {
        dbHelper.addNewImage(imageModel);
    }

    public void updateImage(int id, ImageModel imageModel) {
        dbHelper.updateImage(id, imageModel);
    }

    public void deleteImage(int id) {
        dbHelper.deleteImage(id);
    }
}
