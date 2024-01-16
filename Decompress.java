package net.imedicaldoctor.imd;

import android.content.Context;
import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.material.timepicker.TimeModel;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.SequenceInputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import net.imedicaldoctor.imd.Data.CompressHelper;
import net.imedicaldoctor.imd.Data.UnzipCompleted;
import net.lingala.zip4j.progress.ProgressMonitor;
import org.apache.commons.p024io.IOUtils;

/* loaded from: classes2.dex */
public class Decompress {

    /* renamed from: a */
    private String f73899a;

    /* renamed from: b */
    private String f73900b;

    /* renamed from: c */
    Context f73901c;

    /* renamed from: d */
    CompressHelper f73902d;

    public Decompress(String str, String str2, Context context) {
        this.f73899a = str;
        this.f73900b = str2;
        this.f73901c = context;
        this.f73902d = new CompressHelper(context);
        m4832a("");
    }

    /* renamed from: a */
    private void m4832a(String str) {
        File file = new File(this.f73900b + str);
        if (file.isDirectory()) {
            return;
        }
        file.mkdirs();
    }

    /* renamed from: b */
    public static byte[] m4831b(String str, String str2) {
        ZipEntry nextEntry;
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(fileInputStream));
            do {
                nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    zipInputStream.close();
                    fileInputStream.close();
                    return null;
                }
            } while (!nextEntry.getName().equals(str2));
            byte[] byteArray = IOUtils.toByteArray(zipInputStream);
            zipInputStream.close();
            return byteArray;
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            iMDLogger.m3294f("Error in unzip", e.getLocalizedMessage());
            return null;
        }
    }

    /* renamed from: c */
    public static byte[] m4830c(String str, String str2, Bundle bundle) {
        Date date = new Date();
        try {
            Vector vector = new Vector(10);
            for (int i = 1; i < 11; i++) {
                vector.add(new FileInputStream(str + "." + i));
            }
            SequenceInputStream sequenceInputStream = new SequenceInputStream(vector.elements());
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(sequenceInputStream));
            String str3 = "";
            while (true) {
                try {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry == null) {
                        break;
                    } else if (!nextEntry.isDirectory()) {
                        nextEntry.getName().toLowerCase().endsWith(str2);
                    } else if (str3.length() == 0) {
                        str3 = nextEntry.getName();
                    }
                } catch (Exception e) {
                    FirebaseCrashlytics.m18030d().m18027g(e);
                    return null;
                }
            }
            zipInputStream.close();
            sequenceInputStream.close();
            Iterator it2 = vector.iterator();
            while (it2.hasNext()) {
                try {
                    ((FileInputStream) it2.next()).close();
                } catch (Exception unused) {
                }
            }
            long seconds = TimeUnit.MILLISECONDS.toSeconds(new Date().getTime() - date.getTime());
            iMDLogger.m3294f("Found file", "In " + seconds + " Seconds");
        } catch (Exception e2) {
            FirebaseCrashlytics.m18030d().m18027g(e2);
            iMDLogger.m3294f("Error in unzip", e2.getLocalizedMessage() + " in ");
            e2.printStackTrace();
        }
        return null;
    }

    /* renamed from: d */
    public static Observable<byte[]> m4829d(final String str, final String str2) {
        return Observable.m7156x1(new ObservableOnSubscribe<byte[]>() { // from class: net.imedicaldoctor.imd.Decompress.2
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            /* renamed from: a */
            public void mo3518a(@NonNull ObservableEmitter<byte[]> observableEmitter) throws Throwable {
                try {
                    InputStream inputStream = new ZipFile(str).getInputStream(new ZipEntry(str2));
                    byte[] byteArray = IOUtils.toByteArray(inputStream);
                    inputStream.close();
                    observableEmitter.onNext(byteArray);
                } catch (Exception e) {
                    e.printStackTrace();
                    iMDLogger.m3294f("Error in unzip", e.getLocalizedMessage());
                }
            }
        });
    }

    /* renamed from: e */
    public static void m4828e(String str, String str2, UnzipCompleted unzipCompleted) {
        try {
            InputStream inputStream = new ZipFile(str).getInputStream(new ZipEntry(str2));
            byte[] byteArray = IOUtils.toByteArray(inputStream);
            inputStream.close();
            unzipCompleted.mo4056b(byteArray);
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            iMDLogger.m3294f("Error in unzip", e.getLocalizedMessage());
            unzipCompleted.mo4057a(e.getLocalizedMessage());
        }
    }

    /* renamed from: f */
    public String m4827f(ObservableEmitter<Bundle> observableEmitter, String str) {
        try {
            net.lingala.zip4j.ZipFile zipFile = new net.lingala.zip4j.ZipFile(this.f73899a);
            if (zipFile.m3271D()) {
                zipFile.m3257R("imedicaldoctor".toCharArray());
            }
            Bundle bundle = new Bundle();
            bundle.putString("progress", "");
            bundle.putString("labelText", str);
            zipFile.m3256S(true);
            ProgressMonitor m3274A = zipFile.m3274A();
            zipFile.m3239o(this.f73900b);
            Object obj = "";
            while (m3274A.m2767i() == ProgressMonitor.State.BUSY) {
                PrintStream printStream = System.out;
                printStream.println("Percent Done: " + m3274A.m2769g());
                PrintStream printStream2 = System.out;
                printStream2.println("File: " + m3274A.m2770f());
                String format = String.format(TimeModel.f50793D2, Integer.valueOf(m3274A.m2769g()));
                if (!format.equals(obj)) {
                    iMDLogger.m3294f("Decompress", "Percent : " + format);
                    bundle.remove("progress");
                    bundle.putString("progress", format);
                    observableEmitter.onNext(bundle);
                    obj = format;
                }
            }
            if (m3274A.m2768h() == ProgressMonitor.Result.SUCCESS) {
                return "0";
            }
            m3274A.m2771e().printStackTrace();
            return "0";
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            iMDLogger.m3294f("Error in unzip", e.getLocalizedMessage() + " in ");
            e.printStackTrace();
            return e.getLocalizedMessage() + " in ";
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(17:41|42|(3:44|45|(4:47|48|49|50)(2:51|52))(2:143|144)|53|54|55|56|57|58|59|60|61|62|63|(4:64|65|66|(7:68|69|(5:114|115|116|(2:119|120)|118)(1:71)|72|73|(12:75|76|77|78|79|80|(3:82|83|84)(1:102)|85|86|87|88|90)(2:109|110)|91)(1:124))|125|95) */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x03d5, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x03d6, code lost:
        r16 = r2;
        r2 = r3;
        r30 = r4;
        r6 = r22;
        r5 = r28;
        r4 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x03e2, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x03e3, code lost:
        r16 = r2;
        r2 = r3;
        r30 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x03e9, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x03ea, code lost:
        r16 = r2;
        r2 = r3;
        r30 = r4;
        r18 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x03f1, code lost:
        r4 = r6;
        r6 = r22;
        r5 = r28;
        r16 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x03f6, code lost:
        r3 = r37;
        r14 = r39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0152, code lost:
        if (r5 == false) goto L172;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0154, code lost:
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0159, code lost:
        if (r3 >= r6.size()) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0167, code lost:
        r36.f73902d.m4906j(((android.os.Bundle) r6.get(r3)).getString(r14));
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x016f, code lost:
        r1 = r36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0172, code lost:
        r1 = r36;
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0179, code lost:
        if (r3 >= r26.size()) goto L176;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x017b, code lost:
        r2 = r26;
        r1.f73902d.m4906j(((android.os.Bundle) r2.get(r3)).getString(r14));
        r3 = r3 + 1;
        r26 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0191, code lost:
        r12.close();
        net.imedicaldoctor.imd.iMDLogger.m3294f("Zip Completed", "In " + java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(new java.util.Date().getTime() - r23.getTime()) + " Seconds");
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x01c5, code lost:
        return "0";
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v56, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r0v80, types: [android.os.Bundle, android.os.BaseBundle] */
    /* JADX WARN: Type inference failed for: r2v21, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v39 */
    /* JADX WARN: Type inference failed for: r3v40 */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r4v37 */
    /* JADX WARN: Type inference failed for: r5v21, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r6v31, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v32 */
    /* JADX WARN: Type inference failed for: r6v34 */
    /* renamed from: g */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m4826g(io.reactivex.rxjava3.core.ObservableEmitter<android.os.Bundle> r37, java.lang.String r38, android.app.Activity r39) {
        /*
            Method dump skipped, instructions count: 1221
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.imedicaldoctor.imd.Decompress.m4826g(io.reactivex.rxjava3.core.ObservableEmitter, java.lang.String, android.app.Activity):java.lang.String");
    }

    /* renamed from: h */
    public boolean m4825h() {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(this.f73899a));
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    zipInputStream.close();
                    return true;
                }
                iMDLogger.m3290j("Decompress", "Unzipping " + nextEntry.getName());
                if (nextEntry.isDirectory()) {
                    m4832a(nextEntry.getName());
                } else {
                    FileOutputStream fileOutputStream = new FileOutputStream(this.f73900b + nextEntry.getName());
                    IOUtils.copyLarge(zipInputStream, fileOutputStream);
                    zipInputStream.closeEntry();
                    fileOutputStream.close();
                }
            }
        } catch (Exception e) {
            iMDLogger.m3294f("Decompress", "unzip failed" + e);
            return false;
        }
    }

    /* renamed from: i */
    public boolean m4824i() {
        try {
            FileInputStream fileInputStream = new FileInputStream(this.f73899a);
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(fileInputStream));
            long length = new File(this.f73899a).length();
            long j = 0;
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    zipInputStream.close();
                    fileInputStream.close();
                    return true;
                }
                j += nextEntry.getCompressedSize();
                long j2 = j / length;
                if (nextEntry.isDirectory()) {
                    m4832a(nextEntry.getName());
                } else {
                    String str = this.f73900b + "/" + nextEntry.getName();
                    if (new File(str).exists()) {
                        this.f73902d.m4906j(str);
                    }
                    byte[] bArr = new byte[262144];
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(this.f73900b + "/" + nextEntry.getName()), 262144);
                    while (true) {
                        int read = zipInputStream.read(bArr, 0, 262144);
                        if (read == -1) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, read);
                    }
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                }
            }
        } catch (Exception e) {
            FirebaseCrashlytics.m18030d().m18027g(e);
            iMDLogger.m3294f("Error in unzip", e.getLocalizedMessage());
            return false;
        }
    }

    /* renamed from: j */
    public Observable<String> m4823j() {
        return Observable.m7156x1(new ObservableOnSubscribe<String>() { // from class: net.imedicaldoctor.imd.Decompress.1
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            /* renamed from: a */
            public void mo3518a(@NonNull ObservableEmitter<String> observableEmitter) throws Throwable {
                if (Boolean.valueOf(Decompress.this.m4824i()).booleanValue()) {
                    observableEmitter.onComplete();
                } else {
                    observableEmitter.onError(null);
                }
            }
        });
    }

    /* renamed from: k */
    public String m4822k(ObservableEmitter<Bundle> observableEmitter, String str) {
        FileInputStream fileInputStream;
        ZipInputStream zipInputStream;
        long length;
        Bundle bundle;
        File file;
        long j;
        String str2;
        Date date;
        String str3;
        ZipEntry nextEntry;
        long j2;
        Date date2;
        String str4;
        String str5 = "progress";
        String str6 = "";
        try {
            fileInputStream = new FileInputStream(this.f73899a);
            zipInputStream = new ZipInputStream(new BufferedInputStream(fileInputStream));
            length = new File(this.f73899a).length();
            Date date3 = new Date();
            bundle = new Bundle();
            bundle.putString("progress", "");
            bundle.putString("labelText", str);
            file = new File(this.f73900b);
            j = 0;
            str2 = "";
            date = date3;
        } catch (Exception e) {
            e = e;
        }
        while (true) {
            try {
                nextEntry = zipInputStream.getNextEntry();
            } catch (Exception e2) {
                String str7 = str5;
                Date date4 = date;
                str3 = str2;
                FirebaseCrashlytics.m18030d().m18027g(e2);
                iMDLogger.m3294f("Error", "Error in getNextEntry : " + e2.getLocalizedMessage());
                e2.printStackTrace();
                if (!e2.getLocalizedMessage().equals("CRC mismatch")) {
                    return e2.getLocalizedMessage();
                }
                date = date4;
                str2 = str3;
                str5 = str7;
            }
            if (nextEntry == null) {
                zipInputStream.close();
                fileInputStream.close();
                return "0";
            }
            try {
                j += nextEntry.getCompressedSize();
                if (nextEntry.isDirectory()) {
                    try {
                        m4832a(nextEntry.getName());
                    } catch (Exception e3) {
                        e = e3;
                        str6 = str2;
                    }
                } else {
                    Date date5 = date;
                    if (TimeUnit.MILLISECONDS.toSeconds(new Date().getTime() - date.getTime()) > 1) {
                        str3 = str2;
                        j2 = j;
                        try {
                            String format = String.format("%.2f", Double.valueOf((j / length) * 100.0d));
                            iMDLogger.m3294f("Decompress", "Percent : " + format);
                            bundle.remove(str5);
                            bundle.putString(str5, format);
                            observableEmitter.onNext(bundle);
                            date2 = new Date();
                        } catch (Exception e4) {
                            e = e4;
                            str6 = str3;
                            FirebaseCrashlytics.m18030d().m18027g(e);
                            iMDLogger.m3294f("Error in unzip", e.getLocalizedMessage() + " in " + str6);
                            e.printStackTrace();
                            return e.getLocalizedMessage() + " in " + str6;
                        }
                    } else {
                        j2 = j;
                        date2 = date5;
                    }
                    String str8 = this.f73900b + "/" + nextEntry.getName();
                    try {
                        if (new File(str8).exists()) {
                            iMDLogger.m3294f("Decompress", nextEntry.getName() + " Exists.");
                            long length2 = new File(str8).length();
                            long size = nextEntry.getSize();
                            if (length2 == size) {
                                str4 = str5;
                                str3 = size;
                                date = date2;
                                str2 = str8;
                                str5 = str4;
                                j = j2;
                            } else {
                                iMDLogger.m3294f("Decompress", nextEntry.getName() + " Different Size.");
                                this.f73902d.m4906j(str8);
                                iMDLogger.m3294f("Decompress", nextEntry.getName() + " Deleted.");
                            }
                        }
                        long usableSpace = file.getUsableSpace();
                        long size2 = nextEntry.getSize();
                        if (size2 > usableSpace) {
                            iMDLogger.m3294f("Decompress", "Not Enough space");
                            return ExifInterface.f14403S4;
                        }
                        byte[] bArr = new byte[262144];
                        StringBuilder sb = new StringBuilder();
                        str4 = str5;
                        sb.append(this.f73900b);
                        sb.append("/");
                        sb.append(nextEntry.getName());
                        int i = 262144;
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(sb.toString()), 262144);
                        while (true) {
                            int read = zipInputStream.read(bArr, 0, i);
                            if (read == -1) {
                                break;
                            }
                            bufferedOutputStream.write(bArr, 0, read);
                            i = 262144;
                        }
                        bufferedOutputStream.flush();
                        bufferedOutputStream.close();
                        str3 = size2;
                        date = date2;
                        str2 = str8;
                        str5 = str4;
                        j = j2;
                    } catch (Exception e5) {
                        e = e5;
                        str6 = str8;
                    }
                }
            } catch (Exception e6) {
                e = e6;
                str3 = str2;
                str6 = str3;
                FirebaseCrashlytics.m18030d().m18027g(e);
                iMDLogger.m3294f("Error in unzip", e.getLocalizedMessage() + " in " + str6);
                e.printStackTrace();
                return e.getLocalizedMessage() + " in " + str6;
            }
            e = e3;
            str6 = str2;
            FirebaseCrashlytics.m18030d().m18027g(e);
            iMDLogger.m3294f("Error in unzip", e.getLocalizedMessage() + " in " + str6);
            e.printStackTrace();
            return e.getLocalizedMessage() + " in " + str6;
        }
    }

    /* renamed from: l */
    public String m4821l(ObservableEmitter<Bundle> observableEmitter, String str) {
        ZipEntry nextEntry;
        ZipInputStream zipInputStream;
        Date date;
        ZipInputStream zipInputStream2;
        Decompress decompress = this;
        String str2 = "progress";
        String str3 = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(decompress.f73899a);
            ZipInputStream zipInputStream3 = new ZipInputStream(new BufferedInputStream(fileInputStream));
            long length = new File(decompress.f73899a).length();
            Date date2 = new Date();
            Bundle bundle = new Bundle();
            bundle.putString("progress", "");
            bundle.putString("labelText", str);
            File file = new File(decompress.f73900b);
            long j = 0;
            String str4 = "";
            Date date3 = date2;
            while (true) {
                try {
                    nextEntry = zipInputStream3.getNextEntry();
                } catch (Exception e) {
                    String str5 = str2;
                    Date date4 = date3;
                    FileInputStream fileInputStream2 = fileInputStream;
                    ZipInputStream zipInputStream4 = zipInputStream3;
                    FirebaseCrashlytics.m18030d().m18027g(e);
                    iMDLogger.m3294f("Error", "Error in getNextEntry : " + e.getLocalizedMessage());
                    e.printStackTrace();
                    if (!e.getLocalizedMessage().equals("CRC mismatch")) {
                        return e.getLocalizedMessage();
                    }
                    decompress = this;
                    zipInputStream3 = zipInputStream4;
                    fileInputStream = fileInputStream2;
                    str2 = str5;
                    date3 = date4;
                }
                if (nextEntry == null) {
                    zipInputStream3.close();
                    fileInputStream.close();
                    return "0";
                }
                try {
                    j += nextEntry.getCompressedSize();
                    if (nextEntry.isDirectory()) {
                        decompress.m4832a(nextEntry.getName());
                    } else {
                        Date date5 = date3;
                        FileInputStream fileInputStream3 = fileInputStream;
                        if (TimeUnit.MILLISECONDS.toSeconds(new Date().getTime() - date3.getTime()) > 1) {
                            zipInputStream = zipInputStream3;
                            String format = String.format("%.2f", Double.valueOf((j / length) * 100.0d));
                            iMDLogger.m3294f("Decompress", "Percent : " + format);
                            bundle.remove(str2);
                            bundle.putString(str2, format);
                            observableEmitter.onNext(bundle);
                            date = new Date();
                        } else {
                            zipInputStream = zipInputStream3;
                            date = date5;
                        }
                        str4 = decompress.f73900b + "/" + nextEntry.getName();
                        if (new File(str4).exists()) {
                            decompress.f73902d.m4906j(str4);
                        }
                        if (nextEntry.getSize() > file.getUsableSpace()) {
                            iMDLogger.m3294f("Decompress", "Not Enough space");
                            return ExifInterface.f14403S4;
                        }
                        byte[] bArr = new byte[262144];
                        StringBuilder sb = new StringBuilder();
                        String str6 = str2;
                        sb.append(decompress.f73900b);
                        sb.append("/");
                        sb.append(nextEntry.getName());
                        int i = 262144;
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(sb.toString()), 262144);
                        while (true) {
                            zipInputStream2 = zipInputStream;
                            int read = zipInputStream2.read(bArr, 0, i);
                            if (read == -1) {
                                break;
                            }
                            bufferedOutputStream.write(bArr, 0, read);
                            zipInputStream = zipInputStream2;
                            i = 262144;
                        }
                        bufferedOutputStream.flush();
                        bufferedOutputStream.close();
                        decompress = this;
                        fileInputStream = fileInputStream3;
                        str2 = str6;
                        Date date6 = date;
                        zipInputStream3 = zipInputStream2;
                        date3 = date6;
                    }
                } catch (Exception e2) {
                    e = e2;
                    str3 = str4;
                    FirebaseCrashlytics.m18030d().m18027g(e);
                    iMDLogger.m3294f("Error in unzip", e.getLocalizedMessage() + " in " + str3);
                    e.printStackTrace();
                    return e.getLocalizedMessage() + " in " + str3;
                }
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:39|40|(2:42|(4:44|45|46|47)(2:48|49))(1:96)|50|51|52|53|54|55|(4:56|57|58|(3:60|(10:62|63|64|(1:66)|67|68|69|70|71|73)(1:84)|74)(1:85))|86|77) */
    /* JADX WARN: Can't wrap try/catch for region: R(16:31|32|(3:34|(1:36)|37)(1:100)|38|39|40|(2:42|(4:44|45|46|47)(2:48|49))(1:96)|50|51|52|53|54|55|(4:56|57|58|(3:60|(10:62|63|64|(1:66)|67|68|69|70|71|73)(1:84)|74)(1:85))|86|77) */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x02ef, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x02f1, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x02f2, code lost:
        r17 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x02f4, code lost:
        r21 = r7;
     */
    /* renamed from: m */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m4820m(io.reactivex.rxjava3.core.ObservableEmitter<android.os.Bundle> r31, java.lang.String r32, android.app.Activity r33) {
        /*
            Method dump skipped, instructions count: 948
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.imedicaldoctor.imd.Decompress.m4820m(io.reactivex.rxjava3.core.ObservableEmitter, java.lang.String, android.app.Activity):java.lang.String");
    }
}
