package net.imedicaldoctor.imd;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/* loaded from: classes2.dex */
public class ShakeService extends Service implements SensorEventListener {

    /* renamed from: X */
    private Sensor f83109X;

    /* renamed from: Y */
    private float f83110Y;

    /* renamed from: Z */
    private float f83111Z;

    /* renamed from: s */
    private SensorManager f83112s;

    /* renamed from: z2 */
    private float f83113z2;

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr = sensorEvent.values;
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        this.f83113z2 = this.f83111Z;
        float sqrt = (float) Math.sqrt((f * f) + (f2 * f2) + (f3 * f3));
        this.f83111Z = sqrt;
        float f4 = (this.f83110Y * 0.9f) + (sqrt - this.f83113z2);
        this.f83110Y = f4;
        if (f4 > 11.0f) {
            LocalBroadcastManager.m43863b(this).m43861d(new Intent("Shake"));
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        SensorManager sensorManager = (SensorManager) getSystemService("sensor");
        this.f83112s = sensorManager;
        Sensor defaultSensor = sensorManager.getDefaultSensor(1);
        this.f83109X = defaultSensor;
        this.f83112s.registerListener(this, defaultSensor, 2, new Handler());
        return 1;
    }
}
