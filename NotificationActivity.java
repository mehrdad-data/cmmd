package net.imedicaldoctor.imd;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.itextpdf.tool.xml.html.HTML;
import net.imedicaldoctor.imd.Data.CompressHelper;

/* loaded from: classes2.dex */
public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    /* renamed from: Q2 */
    Dialog f77080Q2;

    /* renamed from: R2 */
    Button f77081R2;

    /* renamed from: S2 */
    Button f77082S2;

    /* renamed from: T2 */
    TextView f77083T2;

    /* renamed from: U2 */
    TextView f77084U2;

    /* renamed from: V2 */
    String f77085V2 = "StartCompaignDialogActivity";

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == C4804R.C4808id.f86965moreinfo) {
            finish();
            this.f77080Q2.dismiss();
            new Handler().postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.NotificationActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    new CompressHelper(NotificationActivity.this).m4973P(NotificationActivity.this.getIntent().getStringExtra(HTML.Tag.f65852C));
                }
            }, 1000L);
        } else if (view.getId() == C4804R.C4808id.f86833cancel) {
            this.f77080Q2.dismiss();
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C4804R.C4810layout.f87307nothing);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflate = getLayoutInflater().inflate(C4804R.C4810layout.f87106activity_notification, (ViewGroup) null);
        builder.setView(inflate);
        AlertDialog create = builder.create();
        this.f77080Q2 = create;
        create.show();
        setFinishOnTouchOutside(false);
        this.f77080Q2.setCanceledOnTouchOutside(false);
        this.f77081R2 = (Button) inflate.findViewById(C4804R.C4808id.f86965moreinfo);
        this.f77082S2 = (Button) inflate.findViewById(C4804R.C4808id.f86833cancel);
        this.f77083T2 = (TextView) inflate.findViewById(C4804R.C4808id.f86974notification_title);
        this.f77084U2 = (TextView) inflate.findViewById(C4804R.C4808id.f86973notification_content);
        this.f77083T2.setText(getIntent().getStringExtra("title"));
        this.f77084U2.setText(getIntent().getStringExtra("content"));
        this.f77081R2.setOnClickListener(this);
        this.f77082S2.setOnClickListener(this);
        if (getIntent().getStringExtra(HTML.Tag.f65852C).length() < 4) {
            this.f77081R2.setVisibility(8);
        }
        this.f77080Q2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: net.imedicaldoctor.imd.NotificationActivity.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                NotificationActivity.this.finish();
            }
        });
    }
}
