package net.imedicaldoctor.imd.Views;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import net.imedicaldoctor.imd.C4804R;

/* loaded from: classes2.dex */
public class SnackBar extends Dialog {

    /* renamed from: A2 */
    View f83427A2;

    /* renamed from: B2 */
    ButtonFlat f83428B2;

    /* renamed from: C2 */
    int f83429C2;

    /* renamed from: D2 */
    int f83430D2;

    /* renamed from: E2 */
    OnHideListener f83431E2;

    /* renamed from: F2 */
    private boolean f83432F2;

    /* renamed from: G2 */
    private int f83433G2;

    /* renamed from: H2 */
    Thread f83434H2;

    /* renamed from: I2 */
    Handler f83435I2;

    /* renamed from: X */
    float f83436X;

    /* renamed from: Y */
    String f83437Y;

    /* renamed from: Z */
    View.OnClickListener f83438Z;

    /* renamed from: s */
    String f83439s;

    /* renamed from: z2 */
    Activity f83440z2;

    /* loaded from: classes2.dex */
    public interface OnHideListener {
        /* renamed from: a */
        void m3318a();
    }

    public SnackBar(Activity activity, String str) {
        super(activity, 16973839);
        this.f83436X = 14.0f;
        this.f83429C2 = Color.parseColor("#333333");
        this.f83430D2 = Color.parseColor("#1E88E5");
        this.f83432F2 = false;
        this.f83433G2 = 3000;
        this.f83434H2 = new Thread(new Runnable() { // from class: net.imedicaldoctor.imd.Views.SnackBar.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(SnackBar.this.f83433G2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SnackBar.this.f83435I2.sendMessage(new Message());
            }
        });
        this.f83435I2 = new Handler(new Handler.Callback() { // from class: net.imedicaldoctor.imd.Views.SnackBar.3
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                OnHideListener onHideListener = SnackBar.this.f83431E2;
                if (onHideListener != null) {
                    onHideListener.m3318a();
                }
                SnackBar.this.dismiss();
                return false;
            }
        });
        this.f83440z2 = activity;
        this.f83439s = str;
    }

    public SnackBar(Activity activity, String str, String str2, View.OnClickListener onClickListener) {
        super(activity, 16973839);
        this.f83436X = 14.0f;
        this.f83429C2 = Color.parseColor("#333333");
        this.f83430D2 = Color.parseColor("#1E88E5");
        this.f83432F2 = false;
        this.f83433G2 = 3000;
        this.f83434H2 = new Thread(new Runnable() { // from class: net.imedicaldoctor.imd.Views.SnackBar.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(SnackBar.this.f83433G2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SnackBar.this.f83435I2.sendMessage(new Message());
            }
        });
        this.f83435I2 = new Handler(new Handler.Callback() { // from class: net.imedicaldoctor.imd.Views.SnackBar.3
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                OnHideListener onHideListener = SnackBar.this.f83431E2;
                if (onHideListener != null) {
                    onHideListener.m3318a();
                }
                SnackBar.this.dismiss();
                return false;
            }
        });
        this.f83440z2 = activity;
        this.f83439s = str;
        this.f83437Y = str2;
        this.f83438Z = onClickListener;
    }

    /* renamed from: c */
    public int m3326c() {
        return this.f83433G2;
    }

    /* renamed from: d */
    public boolean m3325d() {
        return this.f83432F2;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.f83440z2, C4804R.anim.f85976snackbar_hide_animation);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: net.imedicaldoctor.imd.Views.SnackBar.4
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                SnackBar.super.dismiss();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        this.f83427A2.startAnimation(loadAnimation);
    }

    /* renamed from: e */
    public void m3324e(int i) {
        this.f83429C2 = i;
        View view = this.f83427A2;
        if (view != null) {
            view.setBackgroundColor(i);
        }
    }

    /* renamed from: f */
    public void m3323f(int i) {
        this.f83430D2 = i;
        ButtonFlat buttonFlat = this.f83428B2;
        if (buttonFlat != null) {
            buttonFlat.setBackgroundColor(i);
        }
    }

    /* renamed from: g */
    public void m3322g(int i) {
        this.f83433G2 = i;
    }

    /* renamed from: h */
    public void m3321h(boolean z) {
        this.f83432F2 = z;
    }

    /* renamed from: i */
    public void m3320i(float f) {
        this.f83436X = f;
    }

    /* renamed from: j */
    public void m3319j(OnHideListener onHideListener) {
        this.f83431E2 = onHideListener;
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(C4804R.C4810layout.f87312snackbar);
        setCanceledOnTouchOutside(false);
        ((TextView) findViewById(C4804R.C4808id.text)).setText(this.f83439s);
        ((TextView) findViewById(C4804R.C4808id.text)).setTextSize(this.f83436X);
        ButtonFlat buttonFlat = (ButtonFlat) findViewById(C4804R.C4808id.f86827buttonflat);
        this.f83428B2 = buttonFlat;
        if (this.f83439s == null || this.f83438Z == null) {
            buttonFlat.setVisibility(8);
        } else {
            buttonFlat.setText(this.f83437Y);
            this.f83428B2.setBackgroundColor(this.f83430D2);
            this.f83428B2.setOnClickListener(new View.OnClickListener() { // from class: net.imedicaldoctor.imd.Views.SnackBar.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SnackBar.this.dismiss();
                    SnackBar.this.f83438Z.onClick(view);
                }
            });
        }
        View findViewById = findViewById(C4804R.C4808id.f87020snackbar);
        this.f83427A2 = findViewById;
        findViewById.setBackgroundColor(this.f83429C2);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            dismiss();
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Dialog
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.f83440z2.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        this.f83427A2.setVisibility(0);
        this.f83427A2.startAnimation(AnimationUtils.loadAnimation(this.f83440z2, C4804R.anim.f85977snackbar_show_animation));
        if (this.f83432F2) {
            return;
        }
        this.f83434H2.start();
    }
}
