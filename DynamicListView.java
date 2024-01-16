package net.imedicaldoctor.imd.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class DynamicListView extends ListView {

    /* renamed from: W2 */
    private static final TypeEvaluator<Rect> f74338W2 = new TypeEvaluator<Rect>() { // from class: net.imedicaldoctor.imd.Fragments.DynamicListView.5
        @Override // android.animation.TypeEvaluator
        /* renamed from: a */
        public Rect evaluate(float f, Rect rect, Rect rect2) {
            return new Rect(m4641b(rect.left, rect2.left, f), m4641b(rect.top, rect2.top, f), m4641b(rect.right, rect2.right, f), m4641b(rect.bottom, rect2.bottom, f));
        }

        /* renamed from: b */
        public int m4641b(int i, int i2, float f) {
            return (int) (i + (f * (i2 - i)));
        }
    };

    /* renamed from: A2 */
    private final int f74339A2;

    /* renamed from: B2 */
    public ArrayList<String> f74340B2;

    /* renamed from: C2 */
    private int f74341C2;

    /* renamed from: D2 */
    private int f74342D2;

    /* renamed from: E2 */
    private int f74343E2;

    /* renamed from: F2 */
    private int f74344F2;

    /* renamed from: G2 */
    private boolean f74345G2;

    /* renamed from: H2 */
    private boolean f74346H2;

    /* renamed from: I2 */
    private int f74347I2;

    /* renamed from: J2 */
    private final int f74348J2;

    /* renamed from: K2 */
    private long f74349K2;

    /* renamed from: L2 */
    private long f74350L2;

    /* renamed from: M2 */
    private long f74351M2;

    /* renamed from: N2 */
    private BitmapDrawable f74352N2;

    /* renamed from: O2 */
    private Rect f74353O2;

    /* renamed from: P2 */
    private Rect f74354P2;

    /* renamed from: Q2 */
    private final int f74355Q2;

    /* renamed from: R2 */
    private int f74356R2;

    /* renamed from: S2 */
    private boolean f74357S2;

    /* renamed from: T2 */
    private int f74358T2;

    /* renamed from: U2 */
    private AdapterView.OnItemLongClickListener f74359U2;

    /* renamed from: V2 */
    private AbsListView.OnScrollListener f74360V2;

    /* renamed from: s */
    private final int f74361s;

    /* renamed from: z2 */
    private final int f74362z2;

    public DynamicListView(Context context) {
        super(context);
        this.f74361s = 15;
        this.f74362z2 = 150;
        this.f74339A2 = 15;
        this.f74341C2 = -1;
        this.f74342D2 = -1;
        this.f74343E2 = -1;
        this.f74344F2 = 0;
        this.f74345G2 = false;
        this.f74346H2 = false;
        this.f74347I2 = 0;
        this.f74348J2 = -1;
        this.f74349K2 = -1L;
        this.f74350L2 = -1L;
        this.f74351M2 = -1L;
        this.f74355Q2 = -1;
        this.f74356R2 = -1;
        this.f74357S2 = false;
        this.f74358T2 = 0;
        this.f74359U2 = new AdapterView.OnItemLongClickListener() { // from class: net.imedicaldoctor.imd.Fragments.DynamicListView.1
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                DynamicListView.this.f74344F2 = 0;
                DynamicListView dynamicListView = DynamicListView.this;
                int pointToPosition = dynamicListView.pointToPosition(dynamicListView.f74343E2, DynamicListView.this.f74342D2);
                View childAt = DynamicListView.this.getChildAt(pointToPosition - DynamicListView.this.getFirstVisiblePosition());
                DynamicListView dynamicListView2 = DynamicListView.this;
                dynamicListView2.f74350L2 = dynamicListView2.getAdapter().getItemId(pointToPosition);
                DynamicListView dynamicListView3 = DynamicListView.this;
                dynamicListView3.f74352N2 = dynamicListView3.m4649t(childAt);
                childAt.setVisibility(4);
                DynamicListView.this.f74345G2 = true;
                DynamicListView dynamicListView4 = DynamicListView.this;
                dynamicListView4.m4669F(dynamicListView4.f74350L2);
                return true;
            }
        };
        this.f74360V2 = new AbsListView.OnScrollListener() { // from class: net.imedicaldoctor.imd.Fragments.DynamicListView.6

            /* renamed from: a */
            private int f74372a = -1;

            /* renamed from: b */
            private int f74373b = -1;

            /* renamed from: c */
            private int f74374c;

            /* renamed from: d */
            private int f74375d;

            /* renamed from: e */
            private int f74376e;

            /* renamed from: c */
            private void m4638c() {
                if (this.f74375d <= 0 || this.f74376e != 0) {
                    return;
                }
                if (DynamicListView.this.f74345G2 && DynamicListView.this.f74346H2) {
                    DynamicListView.this.m4643z();
                } else if (DynamicListView.this.f74357S2) {
                    DynamicListView.this.m4670E();
                }
            }

            /* renamed from: a */
            public void m4640a() {
                if (this.f74374c == this.f74372a || !DynamicListView.this.f74345G2 || DynamicListView.this.f74350L2 == -1) {
                    return;
                }
                DynamicListView dynamicListView = DynamicListView.this;
                dynamicListView.m4669F(dynamicListView.f74350L2);
                DynamicListView.this.m4644y();
            }

            /* renamed from: b */
            public void m4639b() {
                if (this.f74374c + this.f74375d == this.f74372a + this.f74373b || !DynamicListView.this.f74345G2 || DynamicListView.this.f74350L2 == -1) {
                    return;
                }
                DynamicListView dynamicListView = DynamicListView.this;
                dynamicListView.m4669F(dynamicListView.f74350L2);
                DynamicListView.this.m4644y();
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                this.f74374c = i;
                this.f74375d = i2;
                int i4 = this.f74372a;
                if (i4 != -1) {
                    i = i4;
                }
                this.f74372a = i;
                int i5 = this.f74373b;
                if (i5 != -1) {
                    i2 = i5;
                }
                this.f74373b = i2;
                m4640a();
                m4639b();
                this.f74372a = this.f74374c;
                this.f74373b = this.f74375d;
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
                this.f74376e = i;
                DynamicListView.this.f74358T2 = i;
                m4638c();
            }
        };
        m4673B(context);
    }

    public DynamicListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f74361s = 15;
        this.f74362z2 = 150;
        this.f74339A2 = 15;
        this.f74341C2 = -1;
        this.f74342D2 = -1;
        this.f74343E2 = -1;
        this.f74344F2 = 0;
        this.f74345G2 = false;
        this.f74346H2 = false;
        this.f74347I2 = 0;
        this.f74348J2 = -1;
        this.f74349K2 = -1L;
        this.f74350L2 = -1L;
        this.f74351M2 = -1L;
        this.f74355Q2 = -1;
        this.f74356R2 = -1;
        this.f74357S2 = false;
        this.f74358T2 = 0;
        this.f74359U2 = new AdapterView.OnItemLongClickListener() { // from class: net.imedicaldoctor.imd.Fragments.DynamicListView.1
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                DynamicListView.this.f74344F2 = 0;
                DynamicListView dynamicListView = DynamicListView.this;
                int pointToPosition = dynamicListView.pointToPosition(dynamicListView.f74343E2, DynamicListView.this.f74342D2);
                View childAt = DynamicListView.this.getChildAt(pointToPosition - DynamicListView.this.getFirstVisiblePosition());
                DynamicListView dynamicListView2 = DynamicListView.this;
                dynamicListView2.f74350L2 = dynamicListView2.getAdapter().getItemId(pointToPosition);
                DynamicListView dynamicListView3 = DynamicListView.this;
                dynamicListView3.f74352N2 = dynamicListView3.m4649t(childAt);
                childAt.setVisibility(4);
                DynamicListView.this.f74345G2 = true;
                DynamicListView dynamicListView4 = DynamicListView.this;
                dynamicListView4.m4669F(dynamicListView4.f74350L2);
                return true;
            }
        };
        this.f74360V2 = new AbsListView.OnScrollListener() { // from class: net.imedicaldoctor.imd.Fragments.DynamicListView.6

            /* renamed from: a */
            private int f74372a = -1;

            /* renamed from: b */
            private int f74373b = -1;

            /* renamed from: c */
            private int f74374c;

            /* renamed from: d */
            private int f74375d;

            /* renamed from: e */
            private int f74376e;

            /* renamed from: c */
            private void m4638c() {
                if (this.f74375d <= 0 || this.f74376e != 0) {
                    return;
                }
                if (DynamicListView.this.f74345G2 && DynamicListView.this.f74346H2) {
                    DynamicListView.this.m4643z();
                } else if (DynamicListView.this.f74357S2) {
                    DynamicListView.this.m4670E();
                }
            }

            /* renamed from: a */
            public void m4640a() {
                if (this.f74374c == this.f74372a || !DynamicListView.this.f74345G2 || DynamicListView.this.f74350L2 == -1) {
                    return;
                }
                DynamicListView dynamicListView = DynamicListView.this;
                dynamicListView.m4669F(dynamicListView.f74350L2);
                DynamicListView.this.m4644y();
            }

            /* renamed from: b */
            public void m4639b() {
                if (this.f74374c + this.f74375d == this.f74372a + this.f74373b || !DynamicListView.this.f74345G2 || DynamicListView.this.f74350L2 == -1) {
                    return;
                }
                DynamicListView dynamicListView = DynamicListView.this;
                dynamicListView.m4669F(dynamicListView.f74350L2);
                DynamicListView.this.m4644y();
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                this.f74374c = i;
                this.f74375d = i2;
                int i4 = this.f74372a;
                if (i4 != -1) {
                    i = i4;
                }
                this.f74372a = i;
                int i5 = this.f74373b;
                if (i5 != -1) {
                    i2 = i5;
                }
                this.f74373b = i2;
                m4640a();
                m4639b();
                this.f74372a = this.f74374c;
                this.f74373b = this.f74375d;
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
                this.f74376e = i;
                DynamicListView.this.f74358T2 = i;
                m4638c();
            }
        };
        m4673B(context);
    }

    public DynamicListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f74361s = 15;
        this.f74362z2 = 150;
        this.f74339A2 = 15;
        this.f74341C2 = -1;
        this.f74342D2 = -1;
        this.f74343E2 = -1;
        this.f74344F2 = 0;
        this.f74345G2 = false;
        this.f74346H2 = false;
        this.f74347I2 = 0;
        this.f74348J2 = -1;
        this.f74349K2 = -1L;
        this.f74350L2 = -1L;
        this.f74351M2 = -1L;
        this.f74355Q2 = -1;
        this.f74356R2 = -1;
        this.f74357S2 = false;
        this.f74358T2 = 0;
        this.f74359U2 = new AdapterView.OnItemLongClickListener() { // from class: net.imedicaldoctor.imd.Fragments.DynamicListView.1
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i2, long j) {
                DynamicListView.this.f74344F2 = 0;
                DynamicListView dynamicListView = DynamicListView.this;
                int pointToPosition = dynamicListView.pointToPosition(dynamicListView.f74343E2, DynamicListView.this.f74342D2);
                View childAt = DynamicListView.this.getChildAt(pointToPosition - DynamicListView.this.getFirstVisiblePosition());
                DynamicListView dynamicListView2 = DynamicListView.this;
                dynamicListView2.f74350L2 = dynamicListView2.getAdapter().getItemId(pointToPosition);
                DynamicListView dynamicListView3 = DynamicListView.this;
                dynamicListView3.f74352N2 = dynamicListView3.m4649t(childAt);
                childAt.setVisibility(4);
                DynamicListView.this.f74345G2 = true;
                DynamicListView dynamicListView4 = DynamicListView.this;
                dynamicListView4.m4669F(dynamicListView4.f74350L2);
                return true;
            }
        };
        this.f74360V2 = new AbsListView.OnScrollListener() { // from class: net.imedicaldoctor.imd.Fragments.DynamicListView.6

            /* renamed from: a */
            private int f74372a = -1;

            /* renamed from: b */
            private int f74373b = -1;

            /* renamed from: c */
            private int f74374c;

            /* renamed from: d */
            private int f74375d;

            /* renamed from: e */
            private int f74376e;

            /* renamed from: c */
            private void m4638c() {
                if (this.f74375d <= 0 || this.f74376e != 0) {
                    return;
                }
                if (DynamicListView.this.f74345G2 && DynamicListView.this.f74346H2) {
                    DynamicListView.this.m4643z();
                } else if (DynamicListView.this.f74357S2) {
                    DynamicListView.this.m4670E();
                }
            }

            /* renamed from: a */
            public void m4640a() {
                if (this.f74374c == this.f74372a || !DynamicListView.this.f74345G2 || DynamicListView.this.f74350L2 == -1) {
                    return;
                }
                DynamicListView dynamicListView = DynamicListView.this;
                dynamicListView.m4669F(dynamicListView.f74350L2);
                DynamicListView.this.m4644y();
            }

            /* renamed from: b */
            public void m4639b() {
                if (this.f74374c + this.f74375d == this.f74372a + this.f74373b || !DynamicListView.this.f74345G2 || DynamicListView.this.f74350L2 == -1) {
                    return;
                }
                DynamicListView dynamicListView = DynamicListView.this;
                dynamicListView.m4669F(dynamicListView.f74350L2);
                DynamicListView.this.m4644y();
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i2, int i22, int i3) {
                this.f74374c = i2;
                this.f74375d = i22;
                int i4 = this.f74372a;
                if (i4 != -1) {
                    i2 = i4;
                }
                this.f74372a = i2;
                int i5 = this.f74373b;
                if (i5 != -1) {
                    i22 = i5;
                }
                this.f74373b = i22;
                m4640a();
                m4639b();
                this.f74372a = this.f74374c;
                this.f74373b = this.f74375d;
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i2) {
                this.f74376e = i2;
                DynamicListView.this.f74358T2 = i2;
                m4638c();
            }
        };
        m4673B(context);
    }

    /* renamed from: C */
    private void m4672C(ArrayList arrayList, int i, int i2) {
        Object obj = arrayList.get(i);
        arrayList.set(i, arrayList.get(i2));
        arrayList.set(i2, obj);
    }

    /* renamed from: D */
    private void m4671D() {
        View m4645x = m4645x(this.f74350L2);
        if (this.f74345G2) {
            this.f74349K2 = -1L;
            this.f74350L2 = -1L;
            this.f74351M2 = -1L;
            m4645x.setVisibility(0);
            this.f74352N2 = null;
            invalidate();
        }
        this.f74345G2 = false;
        this.f74346H2 = false;
        this.f74356R2 = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E */
    public void m4670E() {
        final View m4645x = m4645x(this.f74350L2);
        if (!this.f74345G2 && !this.f74357S2) {
            m4671D();
            return;
        }
        this.f74345G2 = false;
        this.f74357S2 = false;
        this.f74346H2 = false;
        this.f74356R2 = -1;
        if (this.f74358T2 != 0) {
            this.f74357S2 = true;
            return;
        }
        this.f74353O2.offsetTo(this.f74354P2.left, m4645x.getTop());
        ObjectAnimator ofObject = ObjectAnimator.ofObject(this.f74352N2, "bounds", f74338W2, this.f74353O2);
        ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: net.imedicaldoctor.imd.Fragments.DynamicListView.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                DynamicListView.this.invalidate();
            }
        });
        ofObject.addListener(new AnimatorListenerAdapter() { // from class: net.imedicaldoctor.imd.Fragments.DynamicListView.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                DynamicListView.this.f74349K2 = -1L;
                DynamicListView.this.f74350L2 = -1L;
                DynamicListView.this.f74351M2 = -1L;
                m4645x.setVisibility(0);
                DynamicListView.this.f74352N2 = null;
                DynamicListView.this.setEnabled(true);
                DynamicListView.this.invalidate();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                DynamicListView.this.setEnabled(false);
            }
        });
        ofObject.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F */
    public void m4669F(long j) {
        int m4646w = m4646w(j);
        StableArrayAdapter stableArrayAdapter = (StableArrayAdapter) getAdapter();
        this.f74349K2 = stableArrayAdapter.getItemId(m4646w - 1);
        this.f74351M2 = stableArrayAdapter.getItemId(m4646w + 1);
    }

    /* renamed from: b */
    static /* synthetic */ int m4667b(DynamicListView dynamicListView, int i) {
        int i2 = dynamicListView.f74344F2 + i;
        dynamicListView.f74344F2 = i2;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t */
    public BitmapDrawable m4649t(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        int top = view.getTop();
        int left = view.getLeft();
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), m4647v(view));
        this.f74354P2 = new Rect(left, top, width + left, height + top);
        Rect rect = new Rect(this.f74354P2);
        this.f74353O2 = rect;
        bitmapDrawable.setBounds(rect);
        return bitmapDrawable;
    }

    /* renamed from: u */
    private Bitmap m4648u(View view) {
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    /* renamed from: v */
    private Bitmap m4647v(View view) {
        Bitmap m4648u = m4648u(view);
        Canvas canvas = new Canvas(m4648u);
        Rect rect = new Rect(0, 0, m4648u.getWidth(), m4648u.getHeight());
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15.0f);
        paint.setColor(ViewCompat.f13139t);
        canvas.drawBitmap(m4648u, 0.0f, 0.0f, (Paint) null);
        canvas.drawRect(rect, paint);
        return m4648u;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y */
    public void m4644y() {
        final int i = this.f74341C2 - this.f74342D2;
        int i2 = this.f74354P2.top + this.f74344F2 + i;
        View m4645x = m4645x(this.f74351M2);
        View m4645x2 = m4645x(this.f74350L2);
        View m4645x3 = m4645x(this.f74349K2);
        boolean z = true;
        boolean z2 = m4645x != null && i2 > m4645x.getTop();
        z = (m4645x3 == null || i2 >= m4645x3.getTop()) ? false : false;
        if (z2 || z) {
            long j = z2 ? this.f74351M2 : this.f74349K2;
            if (!z2) {
                m4645x = m4645x3;
            }
            int positionForView = getPositionForView(m4645x2);
            if (m4645x == null) {
                m4669F(this.f74350L2);
                return;
            }
            m4672C(this.f74340B2, positionForView, getPositionForView(m4645x));
            ((BaseAdapter) getAdapter()).notifyDataSetChanged();
            this.f74342D2 = this.f74341C2;
            final int top = m4645x.getTop();
            m4645x2.setVisibility(0);
            m4645x.setVisibility(4);
            m4669F(this.f74350L2);
            final ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            final long j2 = j;
            viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: net.imedicaldoctor.imd.Fragments.DynamicListView.2
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    viewTreeObserver.removeOnPreDrawListener(this);
                    View m4645x4 = DynamicListView.this.m4645x(j2);
                    DynamicListView.m4667b(DynamicListView.this, i);
                    m4645x4.setTranslationY(top - m4645x4.getTop());
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(m4645x4, View.TRANSLATION_Y, 0.0f);
                    ofFloat.setDuration(150L);
                    ofFloat.start();
                    return true;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z */
    public void m4643z() {
        this.f74346H2 = m4674A(this.f74353O2);
    }

    /* renamed from: A */
    public boolean m4674A(Rect rect) {
        int i;
        int computeVerticalScrollOffset = computeVerticalScrollOffset();
        int height = getHeight();
        int computeVerticalScrollExtent = computeVerticalScrollExtent();
        int computeVerticalScrollRange = computeVerticalScrollRange();
        int i2 = rect.top;
        int height2 = rect.height();
        if (i2 <= 0 && computeVerticalScrollOffset > 0) {
            i = -this.f74347I2;
        } else if (i2 + height2 < height || computeVerticalScrollOffset + computeVerticalScrollExtent >= computeVerticalScrollRange) {
            return false;
        } else {
            i = this.f74347I2;
        }
        smoothScrollBy(i, 0);
        return true;
    }

    /* renamed from: B */
    public void m4673B(Context context) {
        setOnItemLongClickListener(this.f74359U2);
        setOnScrollListener(this.f74360V2);
        this.f74347I2 = (int) (15.0f / context.getResources().getDisplayMetrics().density);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        BitmapDrawable bitmapDrawable = this.f74352N2;
        if (bitmapDrawable != null) {
            bitmapDrawable.draw(canvas);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0026, code lost:
        if (r5.getPointerId((r5.getAction() & androidx.core.view.MotionEventCompat.f13041f) >> 8) != r4.f74356R2) goto L13;
     */
    @Override // android.widget.AbsListView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            int r0 = r5.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 0
            if (r0 == 0) goto L6b
            r2 = 1
            if (r0 == r2) goto L67
            r2 = 2
            if (r0 == r2) goto L2d
            r1 = 3
            if (r0 == r1) goto L29
            r1 = 6
            if (r0 == r1) goto L16
            goto L7f
        L16:
            int r0 = r5.getAction()
            r1 = 65280(0xff00, float:9.1477E-41)
            r0 = r0 & r1
            int r0 = r0 >> 8
            int r0 = r5.getPointerId(r0)
            int r1 = r4.f74356R2
            if (r0 != r1) goto L7f
            goto L67
        L29:
            r4.m4671D()
            goto L7f
        L2d:
            int r0 = r4.f74356R2
            r2 = -1
            if (r0 != r2) goto L33
            goto L7f
        L33:
            int r0 = r5.findPointerIndex(r0)
            float r0 = r5.getY(r0)
            int r0 = (int) r0
            r4.f74341C2 = r0
            int r2 = r4.f74342D2
            int r0 = r0 - r2
            boolean r2 = r4.f74345G2
            if (r2 == 0) goto L7f
            android.graphics.Rect r5 = r4.f74353O2
            android.graphics.Rect r2 = r4.f74354P2
            int r3 = r2.left
            int r2 = r2.top
            int r2 = r2 + r0
            int r0 = r4.f74344F2
            int r2 = r2 + r0
            r5.offsetTo(r3, r2)
            android.graphics.drawable.BitmapDrawable r5 = r4.f74352N2
            android.graphics.Rect r0 = r4.f74353O2
            r5.setBounds(r0)
            r4.invalidate()
            r4.m4644y()
            r4.f74346H2 = r1
            r4.m4643z()
            return r1
        L67:
            r4.m4670E()
            goto L7f
        L6b:
            float r0 = r5.getX()
            int r0 = (int) r0
            r4.f74343E2 = r0
            float r0 = r5.getY()
            int r0 = (int) r0
            r4.f74342D2 = r0
            int r0 = r5.getPointerId(r1)
            r4.f74356R2 = r0
        L7f:
            boolean r5 = super.onTouchEvent(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: net.imedicaldoctor.imd.Fragments.DynamicListView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setCheeseList(ArrayList<String> arrayList) {
        this.f74340B2 = arrayList;
    }

    /* renamed from: w */
    public int m4646w(long j) {
        View m4645x = m4645x(j);
        if (m4645x == null) {
            return -1;
        }
        return getPositionForView(m4645x);
    }

    /* renamed from: x */
    public View m4645x(long j) {
        int firstVisiblePosition = getFirstVisiblePosition();
        StableArrayAdapter stableArrayAdapter = (StableArrayAdapter) getAdapter();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (stableArrayAdapter.getItemId(firstVisiblePosition + i) == j) {
                return childAt;
            }
        }
        return null;
    }
}
