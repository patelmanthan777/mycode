package com.example.administrator.myapplication.archite;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.administrator.myapplication.AppExecutors;
import com.example.administrator.myapplication.archite.paging.db.ListItemBean;
import com.example.administrator.myapplication.archite.paging.db.ListItemDao;


/**
 * Created by xcy on 2017/12/8 0008.
 */
@Database(entities = {UserBean.class, ListItemBean.class}, version = 2, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract ListItemDao mListItemDao();

    private static MyDatabase sInstance;

    public static final String DATABASE_NAME = "basic-sample-db";

    public static MyDatabase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (MyDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static MyDatabase buildDatabase(final Context appContext,
                                            final AppExecutors executors) {
        return Room.databaseBuilder(appContext, MyDatabase.class, DATABASE_NAME)
//                .addCallback(new Callback() {
//                    @Override
//                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                        super.onCreate(db);
//                        executors.diskIO().execute(() -> {
//                            // Add a delay to simulate a long-running operation
//                            addDelay();
//                            // Generate the data for pre-population
//                            MyDatabase database = MyDatabase.getInstance(appContext, executors);
//                            List<ProductEntity> products = DataGenerator.generateProducts();
//                            List<CommentEntity> comments =
//                                    DataGenerator.generateCommentsForProducts(products);
//
//                            insertData(database, products, comments);
//                            // notify that the database was created and it's ready to be used
//                            database.setDatabaseCreated();
//                        });
//                    }
//                })
                .build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }
}
