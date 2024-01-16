package net.imedicaldoctor.imd.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes2.dex */
public class AutoSpanRecyclerView extends RecyclerView {

    /* renamed from: H4 */
    private int f83115H4;

    /* renamed from: I4 */
    private int f83116I4;

    /* renamed from: J4 */
    private LayoutRequester f83117J4;

    /* loaded from: classes2.dex */
    private class LayoutRequester implements Runnable {
        private LayoutRequester() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AutoSpanRecyclerView.this.requestLayout();
        }
    }

    public AutoSpanRecyclerView(Context context) {
        super(context);
        this.f83117J4 = new LayoutRequester();
    }

    /* renamed from: V1 */
    public void m3479V1(int i, int i2, int i3) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, i, false);
        this.f83116I4 = i2;
        this.f83115H4 = i3;
        setLayoutManager(gridLayoutManager);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            RecyclerView.LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                View inflate = LayoutInflater.from(getContext()).inflate(this.f83116I4, (ViewGroup) this, false);
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                inflate.measure(makeMeasureSpec, makeMeasureSpec);
                int measuredWidth = inflate.getMeasuredWidth();
                ((GridLayoutManager) layoutManager).m43288M3(Math.max(this.f83115H4, getMeasuredWidth() / measuredWidth));
                post(this.f83117J4);
            }
        }
    }
}
