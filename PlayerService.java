package net.imedicaldoctor.imd;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.p011ui.PlayerNotificationManager;
import net.imedicaldoctor.imd.Fragments.CMEInfo.Player;

/* loaded from: classes2.dex */
public class PlayerService extends Service {

    /* renamed from: X */
    public static final String f77088X = "imd_channel";

    /* renamed from: Y */
    public static final int f77089Y = 2;

    /* renamed from: s */
    private PlayerNotificationManager f77090s;

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        PlayerNotificationManager playerNotificationManager = this.f77090s;
        if (playerNotificationManager != null) {
            playerNotificationManager.m32031B(null);
            this.f77090s = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        PlayerNotificationManager playerNotificationManager = this.f77090s;
        if (playerNotificationManager == null) {
            playerNotificationManager.m32031B(Player.f74083a3);
            return 1;
        }
        return 1;
    }
}
