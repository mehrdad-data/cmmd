package net.imedicaldoctor.imd;

import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.itextpdf.tool.xml.html.HTML;
import java.util.Map;
import net.imedicaldoctor.imd.Data.CompressHelper;

/* loaded from: classes2.dex */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override // com.google.firebase.messaging.FirebaseMessagingService
    /* renamed from: q */
    public void mo3489q(RemoteMessage remoteMessage) {
        super.mo3489q(remoteMessage);
        Log.d("PushNotification", "From: " + remoteMessage.m16549z());
        Map<String, String> m16550x = remoteMessage.m16550x();
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.putExtra("title", m16550x.get("title"));
        intent.putExtra("content", m16550x.get("content"));
        intent.putExtra(HTML.Tag.f65852C, m16550x.get(HTML.Tag.f65852C));
        intent.addFlags(268435456);
        startActivity(intent);
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    /* renamed from: s */
    public void mo3488s(@NonNull String str) {
        new CompressHelper(this).m4899l0();
    }
}
