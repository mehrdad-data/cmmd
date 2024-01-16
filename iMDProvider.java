package net.imedicaldoctor.imd.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import io.requery.android.database.sqlite.SQLiteCursor;
import io.requery.android.database.sqlite.SQLiteCursorDriver;
import io.requery.android.database.sqlite.SQLiteDatabase;
import io.requery.android.database.sqlite.SQLiteQuery;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import net.imedicaldoctor.imd.iMDLogger;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class iMDProvider extends ContentProvider {

    /* renamed from: X */
    private static final String f73890X = "net.imedicaldoctor.imd";

    /* renamed from: Y */
    private static HashMap<String, SQLiteDatabase> f73891Y;

    /* renamed from: Z */
    private static HashMap<String, Integer> f73892Z;

    /* renamed from: s */
    private static final UriMatcher f73893s = m4833e();

    /* renamed from: z2 */
    public static Bundle f73894z2;

    /* loaded from: classes2.dex */
    public class LeaklessCursor extends SQLiteCursor {

        /* renamed from: Y */
        static final String f73895Y = "LeaklessCursor";

        /* renamed from: s */
        final SQLiteDatabase f73897s;

        public LeaklessCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
            super(sQLiteCursorDriver, str, sQLiteQuery);
            this.f73897s = sQLiteDatabase;
        }

        @Override // io.requery.android.database.sqlite.SQLiteCursor, io.requery.android.database.AbstractCursor, android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            iMDLogger.m3296d(f73895Y, "Closing LeaklessCursor: " + this.f73897s.getPath());
            super.close();
            SQLiteDatabase sQLiteDatabase = this.f73897s;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
        }
    }

    /* loaded from: classes2.dex */
    public class LeaklessCursorFactory implements SQLiteDatabase.CursorFactory {
        public LeaklessCursorFactory() {
        }

        @Override // io.requery.android.database.sqlite.SQLiteDatabase.CursorFactory
        public Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
            return new LeaklessCursor(sQLiteDatabase, sQLiteCursorDriver, str, sQLiteQuery);
        }
    }

    /* renamed from: a */
    private SQLiteDatabase m4837a(String str) {
        SQLiteDatabase openDatabase;
        if (f73891Y == null) {
            f73891Y = new HashMap<>();
        }
        if (f73892Z == null) {
            f73892Z = new HashMap<>();
        }
        if (f73891Y.containsKey(str)) {
            openDatabase = f73891Y.get(str);
        } else {
            openDatabase = SQLiteDatabase.openDatabase(str, (SQLiteDatabase.CursorFactory) null, str.contains("/DBs.db") ? 1 : 2);
            openDatabase.disableWriteAheadLogging();
            openDatabase.execSQL("PRAGMA temp_store = MEMORY");
            f73891Y.put(str, openDatabase);
        }
        m4836b(str);
        return openDatabase;
    }

    /* renamed from: b */
    private void m4836b(String str) {
        int i;
        if (f73892Z.containsKey(str)) {
            i = f73892Z.get(str).intValue();
            f73892Z.remove(str);
        } else {
            i = 0;
        }
        f73892Z.put(str, Integer.valueOf(i + 1));
    }

    /* renamed from: c */
    private void m4835c(String str) {
        if (f73891Y == null) {
            f73891Y = new HashMap<>();
        }
        if (f73892Z == null) {
            f73892Z = new HashMap<>();
        }
        if (f73891Y.containsKey(str)) {
            m4834d(str);
            if (f73892Z.get(str).intValue() <= 0) {
                SQLiteDatabase sQLiteDatabase = f73891Y.get(str);
                try {
                    if (sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                        iMDLogger.m3294f("iMDProvider", "Closed database");
                    }
                } catch (Exception unused) {
                }
                f73891Y.remove(str);
            }
        }
    }

    /* renamed from: d */
    private void m4834d(String str) {
        if (f73892Z.containsKey(str)) {
            f73892Z.remove(str);
            f73892Z.put(str, Integer.valueOf(f73892Z.get(str).intValue() - 1));
        }
    }

    /* renamed from: e */
    private static UriMatcher m4833e() {
        UriMatcher uriMatcher = new UriMatcher(-1);
        uriMatcher.addURI("net.imedicaldoctor.imd", "*", 101);
        return uriMatcher;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return false;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (str2 != null) {
            iMDLogger.m3294f("iMDProvider", "Query " + str + " - " + str2);
        }
        int match = f73893s.match(uri);
        if (str2 == null) {
            try {
                m4835c(str);
            } catch (Exception e) {
                FirebaseCrashlytics.m18030d().m18027g(e);
            }
            return null;
        } else if (match != 101) {
            return null;
        } else {
            try {
                return m4837a(str).rawQuery(str2, null);
            } catch (Exception e2) {
                String cls = iMDProvider.class.toString();
                iMDLogger.m3294f(cls, "Error in iMDProvider query , " + e2.toString() + " in " + e2.getStackTrace());
                return null;
            }
        }
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int i = 0;
        String str2 = strArr[0];
        SQLiteDatabase openDatabase = SQLiteDatabase.openDatabase(str, (SQLiteDatabase.CursorFactory) null, 2);
        openDatabase.disableWriteAheadLogging();
        openDatabase.execSQL("PRAGMA temp_store = MEMORY");
        if (strArr.length <= 1) {
            openDatabase.execSQL(str2);
        } else if (strArr[0].equals("SQLFile")) {
            String str3 = strArr[1];
            String[] split = StringUtils.split(str3, "/");
            String str4 = split[split.length - 1];
            int intValue = Integer.valueOf(strArr[2]).intValue();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(str3))), 131072);
                for (int i2 = 0; i2 < intValue; i2++) {
                    bufferedReader.readLine();
                }
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        i++;
                        try {
                            iMDLogger.m3296d("SQL Line - " + str4, i + "");
                            openDatabase.execSQL(readLine);
                        } catch (Exception e) {
                            FirebaseCrashlytics.m18030d().m18027g(e);
                            e.printStackTrace();
                        }
                    } catch (Exception e2) {
                        FirebaseCrashlytics.m18030d().m18027g(e2);
                        e2.printStackTrace();
                    }
                }
                openDatabase.close();
            } catch (Exception e3) {
                FirebaseCrashlytics.m18030d().m18027g(e3);
                iMDLogger.m3294f("ExecuteDB", "Error in reading and executing " + str3 + " With error : " + e3.getMessage());
                e3.printStackTrace();
            }
        } else {
            openDatabase.beginTransaction();
            int length = strArr.length;
            while (i < length) {
                openDatabase.execSQL(strArr[i]);
                i++;
            }
            openDatabase.setTransactionSuccessful();
            openDatabase.endTransaction();
        }
        try {
            if (openDatabase.isOpen()) {
                openDatabase.close();
            }
        } catch (Exception e4) {
            FirebaseCrashlytics.m18030d().m18027g(e4);
            iMDLogger.m3293g("iMD Provider", "Closing database", e4);
        }
        return 1;
    }
}
