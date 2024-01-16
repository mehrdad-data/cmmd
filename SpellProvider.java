package net.imedicaldoctor.imd.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import io.requery.android.database.sqlite.SQLiteDatabase;
import net.imedicaldoctor.imd.iMDLogger;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class SpellProvider extends ContentProvider {

    /* renamed from: Z */
    private static final UriMatcher f73885Z = m4838a();

    /* renamed from: z2 */
    private static final String f73886z2 = "net.imedicaldoctor.spell";

    /* renamed from: X */
    private String f73887X;

    /* renamed from: Y */
    private String f73888Y = null;

    /* renamed from: s */
    private SQLiteDatabase f73889s;

    /* renamed from: a */
    private static UriMatcher m4838a() {
        UriMatcher uriMatcher = new UriMatcher(-1);
        uriMatcher.addURI(f73886z2, "spell/*/*", 101);
        uriMatcher.addURI(f73886z2, "cspell/*/*", 102);
        return uriMatcher;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return this.f73888Y == null ? 0 : 1;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        if (f73885Z.match(uri) != 401) {
            return null;
        }
        return this.f73887X;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        try {
            this.f73889s = SQLiteDatabase.openDatabase(new CompressHelper(getContext()).m5018A(), (SQLiteDatabase.CursorFactory) null, 1);
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            Log.e("SpellProvider", "Can't open spell Database");
        }
        this.f73887X = "0";
        return false;
    }

    @Override // android.content.ContentProvider, android.content.ComponentCallbacks
    public void onLowMemory() {
        iMDLogger.m3294f("UTDProvider", "OnLowMemory");
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String[] split;
        SQLiteDatabase sQLiteDatabase;
        String str3;
        int match = f73885Z.match(uri);
        Cursor cursor = null;
        try {
            if (match == 101) {
                String str4 = uri.getLastPathSegment().trim().split(StringUtils.SPACE)[split.length - 1];
                String str5 = "";
                for (int i = 0; i < split.length - 1; i++) {
                    str5 = str5 + StringUtils.SPACE + split[i];
                }
                String trim = str5.trim();
                sQLiteDatabase = this.f73889s;
                str3 = "Select rowid as _id, word as suggest_text_1,\"" + trim + " \" || word from spell where word match '" + str4 + "*'";
            } else if (match != 102) {
                return null;
            } else {
                sQLiteDatabase = this.f73889s;
                str3 = "Select rowid as _id, word as suggest_text_1 from contentspell where word match '" + uri.getLastPathSegment() + "*'";
            }
            cursor = sQLiteDatabase.rawQuery(str3, null);
            return cursor;
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            iMDLogger.m3294f(SpellProvider.class.toString(), "Error in UTDProvider query , " + e.toString());
            return cursor;
        }
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        onCreate();
        return 1;
    }
}
