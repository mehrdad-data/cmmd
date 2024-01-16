package net.imedicaldoctor.imd;

import android.content.Context;
import android.graphics.PointF;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes2.dex */
public class LinearLayoutManagerWithSmoothScroller extends LinearLayoutManager {

    /* loaded from: classes2.dex */
    private class TopSnappedSmoothScroller extends LinearSmoothScroller {
        public TopSnappedSmoothScroller(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        /* renamed from: C */
        protected int mo3491C() {
            return -1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
        /* renamed from: a */
        public PointF mo3490a(int i) {
            return LinearLayoutManagerWithSmoothScroller.this.mo42386a(i);
        }
    }

    public LinearLayoutManagerWithSmoothScroller(Context context, int i, boolean z) {
        super(context, i, z);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    /* renamed from: f2 */
    public void mo3492f2(RecyclerView recyclerView, RecyclerView.State state, int i) {
        TopSnappedSmoothScroller topSnappedSmoothScroller = new TopSnappedSmoothScroller(recyclerView.getContext());
        topSnappedSmoothScroller.m42599q(i);
        m42722g2(topSnappedSmoothScroller);
    }
}
