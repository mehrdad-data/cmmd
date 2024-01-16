package net.imedicaldoctor.imd;

import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import io.requery.android.database.sqlite.SQLiteDatabase;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.imedicaldoctor.imd.Data.CompressHelper;

/* loaded from: classes2.dex */
public class iMDLogger {
    /* renamed from: a */
    private static boolean m3299a() {
        return PreferenceManager.getDefaultSharedPreferences(iMD.m3306d()).getBoolean("savelogs", false);
    }

    /* renamed from: b */
    private static void m3298b(String str, String str2, String str3, Exception exc) {
        try {
            if (str.equals("e")) {
                Log.e(str2, str3);
            } else if (str.equals("d")) {
                Log.d(str2, str3);
            } else if (str.equals("w")) {
                Log.w(str2, str3);
            } else if (str.equals("v")) {
                Log.v(str2, str3);
            } else if (str.equals("i")) {
                Log.i(str2, str3);
            }
            if (m3299a()) {
                m3286n(str, str2, str3, exc != null ? exc.getLocalizedMessage() : "");
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: c */
    public static String m3297c() {
        try {
            CompressHelper compressHelper = new CompressHelper(iMD.m3307c());
            String str = compressHelper.m4856z1() + "/zlogs.db";
            if (!new File(str).exists()) {
                SQLiteDatabase.openOrCreateDatabase(str, (SQLiteDatabase.CursorFactory) null).execSQL("create table logs (id integer primary key autoincrement,cat varchar(10), tag varchar(255), description text, error text, date varchar(50), appsession varchar(20),stacktrace text);");
            }
            return str;
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            FirebaseCrashlytics.m18030d().m18027g(e);
            return null;
        }
    }

    /* renamed from: d */
    public static void m3296d(String str, String str2) {
        m3298b("d", str, str2, null);
    }

    /* renamed from: e */
    public static void m3295e(String str, String str2, Exception exc) {
        m3298b("d", str, str2, exc);
    }

    /* renamed from: f */
    public static void m3294f(String str, String str2) {
        m3298b("e", str, str2, null);
    }

    /* renamed from: g */
    public static void m3293g(String str, String str2, Exception exc) {
        m3298b("e", str, str2, exc);
    }

    /* renamed from: h */
    public static void m3292h(String str, String str2) {
        m3298b("i", str, str2, null);
    }

    /* renamed from: i */
    public static void m3291i(String str, String str2, Exception exc) {
        m3298b("i", str, str2, exc);
    }

    /* renamed from: j */
    public static void m3290j(String str, String str2) {
        m3298b("v", str, str2, null);
    }

    /* renamed from: k */
    public static void m3289k(String str, String str2, Exception exc) {
        m3298b("v", str, str2, exc);
    }

    /* renamed from: l */
    public static void m3288l(String str, String str2) {
        m3298b("w", str, str2, null);
    }

    /* renamed from: m */
    public static void m3287m(String str, String str2, Exception exc) {
        m3298b("w", str, str2, exc);
    }

    /* renamed from: n */
    private static void m3286n(String str, String str2, String str3, String str4) {
        try {
            if (m3297c() == null) {
                return;
            }
            CompressHelper compressHelper = new CompressHelper(iMD.m3306d());
            String m3285o = m3285o(str);
            String m3285o2 = m3285o(str2);
            String m3285o3 = m3285o(str4);
            String m3285o4 = m3285o(str3);
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String valueOf = String.valueOf(Looper.getMainLooper().getThread().getId());
            String m3297c = m3297c();
            compressHelper.m4885q(m3297c, "Insert into logs (cat, tag, description,error, date, appsession, stacktrace) values ('" + m3285o + "', '" + m3285o2 + "', '" + m3285o4 + "', '" + m3285o3 + "', '" + format + "', '" + valueOf + "', '')");
        } catch (Exception unused) {
        }
    }

    /* renamed from: o */
    private static String m3285o(String str) {
        return str.replace("'", "''");
    }
}
