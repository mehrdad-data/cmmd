package net.imedicaldoctor.imd.Fragments;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes2.dex */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: c */
    private static final int[] f74332c = {16843284};

    /* renamed from: d */
    public static final int f74333d = 0;

    /* renamed from: e */
    public static final int f74334e = 1;

    /* renamed from: a */
    private Drawable f74335a;

    /* renamed from: b */
    private int f74336b;

    public DividerItemDecoration(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(f74332c);
        this.f74335a = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        m4676n(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    /* renamed from: f */
    public void mo4680f(Rect rect, int i, RecyclerView recyclerView) {
        if (this.f74336b == 1) {
            rect.set(0, 0, 0, this.f74335a.getIntrinsicHeight());
        } else {
            rect.set(0, 0, this.f74335a.getIntrinsicWidth(), 0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    /* renamed from: h */
    public void mo4679h(Canvas canvas, RecyclerView recyclerView) {
        if (this.f74336b == 1) {
            m4677m(canvas, recyclerView);
        } else {
            m4678l(canvas, recyclerView);
        }
    }

    /* renamed from: l */
    public void m4678l(Canvas canvas, RecyclerView recyclerView) {
        int paddingTop = recyclerView.getPaddingTop();
        int height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            int right = childAt.getRight() + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) childAt.getLayoutParams())).rightMargin;
            this.f74335a.setBounds(right, paddingTop, this.f74335a.getIntrinsicHeight() + right, height);
            this.f74335a.draw(canvas);
        }
    }

    /* renamed from: m */
    public void m4677m(Canvas canvas, RecyclerView recyclerView) {
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            int bottom = childAt.getBottom() + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) childAt.getLayoutParams())).bottomMargin;
            this.f74335a.setBounds(paddingLeft, bottom, width, this.f74335a.getIntrinsicHeight() + bottom);
            this.f74335a.draw(canvas);
        }
    }

    /* renamed from: n */
    public void m4676n(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation");
        }
        this.f74336b = i;
    }
}
