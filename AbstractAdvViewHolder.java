package net.imedicaldoctor.imd.Fragments;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder;

/* loaded from: classes2.dex */
public abstract class AbstractAdvViewHolder extends RecyclerView.ViewHolder implements SwipeableItemViewHolder, DraggableItemViewHolder {

    /* renamed from: I */
    private int f73906I;

    /* renamed from: J */
    private int f73907J;

    /* renamed from: K */
    private int f73908K;

    /* renamed from: L */
    private float f73909L;

    /* renamed from: M */
    private float f73910M;

    /* renamed from: N */
    private float f73911N;

    /* renamed from: O */
    private int f73912O;

    public AbstractAdvViewHolder(View view) {
        super(view);
        this.f73907J = 0;
        this.f73908K = 0;
        this.f73910M = -3.4028235E38f;
        this.f73911N = Float.MAX_VALUE;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    /* renamed from: a */
    public int mo4819a() {
        return this.f73907J;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    /* renamed from: b */
    public float mo4818b() {
        return this.f73911N;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    /* renamed from: c */
    public void mo4817c(int i) {
        this.f73906I = i;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder
    /* renamed from: d */
    public int mo4816d() {
        return this.f73912O;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    /* renamed from: f */
    public int mo4815f() {
        return this.f73908K;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    /* renamed from: g */
    public void mo4814g(float f) {
        this.f73909L = f;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    /* renamed from: h */
    public void mo4813h(int i) {
        this.f73907J = i;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder
    /* renamed from: i */
    public void mo4812i(int i) {
        this.f73912O = i;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    /* renamed from: j */
    public void mo4811j(float f) {
        this.f73910M = f;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    /* renamed from: k */
    public float mo4810k() {
        return this.f73909L;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    /* renamed from: l */
    public void mo4809l(float f) {
        this.f73911N = f;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    /* renamed from: n */
    public float mo4808n() {
        return this.f73910M;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    /* renamed from: o */
    public void mo4807o(int i) {
        this.f73908K = i;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    /* renamed from: p */
    public int mo4806p() {
        return this.f73906I;
    }
}
