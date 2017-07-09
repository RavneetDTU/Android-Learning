package com.example.ravneet.todomanager.db;

/**
 * Created by ravneet on 9/7/17.
 */

public class TodoTable {

    public static final String TABLE_NAME = "todos";

    interface Coloumns{
        String COL_ID = "id";
        String COL_TASK = "task";
    }

    public static final String CMD_CREATE_TABLE = +TABLE_NAME

}
