package com.example.ORMLiteOneToMany.access;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.ORMLiteOneToMany.model.Client;
import com.example.ORMLiteOneToMany.model.Commercial;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sam on 03/02/2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "ClientsCommerciaux";
    public static final int VERSION = 2;

    private Dao< Client, Integer> daoClient;
    private Dao< Commercial, Integer> daoCommercial;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Client.class);
            TableUtils.createTable(connectionSource, Commercial.class);

            insertCommerciaux();
           /* getDaoCommercial().create(new Commercial("John"));
            Map<String, Object> fields = new HashMap<>();
            fields.put(Commercial.FIELD_NOM, "John");
            Commercial john = getDaoCommercial().queryForFieldValues(fields).get(0);
            getDaoClient().createIfNotExists(new Client("Sam",  john));*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertCommerciaux() throws SQLException {
        Dao<Commercial, Integer> daoCommercial = getDaoCommercial();
        daoCommercial.create(new Commercial("Chris TUCKER"));
        daoCommercial.create(new Commercial("Jackie CHAN"));
        daoCommercial.create(new Commercial("Sam MICROSOFT"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, Client.class, true);
            TableUtils.dropTable(connectionSource, Commercial.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Client, Integer> getDaoClient() throws SQLException {
        if(this.daoClient == null){
            daoClient = getDao(Client.class);
        }
        return this.daoClient;
    }

    public Dao<Commercial, Integer> getDaoCommercial() throws SQLException {
        if(this.daoCommercial == null){
            daoCommercial = getDao(Commercial.class);
        }
        return this.daoCommercial;
    }
}
