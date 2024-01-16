package net.imedicaldoctor.imd;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/* loaded from: classes2.dex */
public class Premissions extends Activity {

    /* renamed from: X */
    private static final int f77091X = 1;

    /* renamed from: s */
    private Context f77092s;

    public Premissions(Context context) {
        this.f77092s = context;
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Context context;
        String str;
        if (i != 1) {
            return;
        }
        if (iArr.length <= 0 || iArr[0] != 0) {
            context = this.f77092s;
            str = "Permission Denied";
        } else {
            context = this.f77092s;
            str = "Permission Granted";
        }
        Toast.makeText(context, str, 0).show();
    }
}
