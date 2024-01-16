package net.imedicaldoctor.imd.Utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import net.imedicaldoctor.imd.C4804R;

@SuppressLint({"ClickableViewAccessibility"})
/* loaded from: classes2.dex */
public class MaterialRippleLayout extends FrameLayout {

    /* renamed from: b3 */
    private static final int f83130b3 = 550;

    /* renamed from: c3 */
    private static final int f83131c3 = 75;

    /* renamed from: d3 */
    private static final float f83132d3 = 35.0f;

    /* renamed from: e3 */
    private static final float f83133e3 = 0.2f;

    /* renamed from: f3 */
    private static final int f83134f3 = -16777216;

    /* renamed from: g3 */
    private static final int f83135g3 = 0;

    /* renamed from: h3 */
    private static final boolean f83136h3 = true;

    /* renamed from: i3 */
    private static final boolean f83137i3 = true;

    /* renamed from: j3 */
    private static final boolean f83138j3 = false;

    /* renamed from: k3 */
    private static final boolean f83139k3 = false;

    /* renamed from: l3 */
    private static final boolean f83140l3 = false;

    /* renamed from: m3 */
    private static final int f83141m3 = 50;

    /* renamed from: n3 */
    private static final long f83142n3 = 2500;

    /* renamed from: A2 */
    private int f83143A2;

    /* renamed from: B2 */
    private boolean f83144B2;

    /* renamed from: C2 */
    private boolean f83145C2;

    /* renamed from: D2 */
    private int f83146D2;

    /* renamed from: E2 */
    private int f83147E2;

    /* renamed from: F2 */
    private int f83148F2;

    /* renamed from: G2 */
    private boolean f83149G2;

    /* renamed from: H2 */
    private int f83150H2;

    /* renamed from: I2 */
    private boolean f83151I2;

    /* renamed from: J2 */
    private Drawable f83152J2;

    /* renamed from: K2 */
    private boolean f83153K2;

    /* renamed from: L2 */
    private float f83154L2;

    /* renamed from: M2 */
    private AdapterView<?> f83155M2;

    /* renamed from: N2 */
    private View f83156N2;

    /* renamed from: O2 */
    private AnimatorSet f83157O2;

    /* renamed from: P2 */
    private ObjectAnimator f83158P2;

    /* renamed from: Q2 */
    private Point f83159Q2;

    /* renamed from: R2 */
    private Point f83160R2;

    /* renamed from: S2 */
    private boolean f83161S2;

    /* renamed from: T2 */
    private boolean f83162T2;

    /* renamed from: U2 */
    private int f83163U2;

    /* renamed from: V2 */
    private GestureDetector f83164V2;

    /* renamed from: W2 */
    private PerformClickEvent f83165W2;

    /* renamed from: X2 */
    private PressedEvent f83166X2;

    /* renamed from: Y2 */
    private GestureDetector.SimpleOnGestureListener f83167Y2;

    /* renamed from: Z2 */
    private Property<MaterialRippleLayout, Float> f83168Z2;

    /* renamed from: a3 */
    private Property<MaterialRippleLayout, Integer> f83169a3;

    /* renamed from: s */
    private final Paint f83170s;

    /* renamed from: z2 */
    private final Rect f83171z2;

    /* loaded from: classes2.dex */
    private class PerformClickEvent implements Runnable {
        private PerformClickEvent() {
        }

        /* JADX WARN: Type inference failed for: r1v3, types: [android.widget.Adapter] */
        /* renamed from: a */
        private void m3450a(AdapterView<?> adapterView) {
            int positionForView = adapterView.getPositionForView(MaterialRippleLayout.this);
            long itemId = adapterView.getAdapter() != null ? adapterView.getAdapter().getItemId(positionForView) : 0L;
            if (positionForView != -1) {
                adapterView.performItemClick(MaterialRippleLayout.this, positionForView, itemId);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            AdapterView<?> m3460p;
            if (MaterialRippleLayout.this.getParent() instanceof AdapterView) {
                m3460p = (AdapterView) MaterialRippleLayout.this.getParent();
            } else if (!MaterialRippleLayout.this.f83153K2) {
                MaterialRippleLayout.this.f83156N2.performClick();
                return;
            } else {
                m3460p = MaterialRippleLayout.this.m3460p();
            }
            m3450a(m3460p);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class PressedEvent implements Runnable {

        /* renamed from: s */
        private final MotionEvent f83180s;

        public PressedEvent(MotionEvent motionEvent) {
            this.f83180s = motionEvent;
        }

        @Override // java.lang.Runnable
        public void run() {
            MaterialRippleLayout.this.f83162T2 = false;
            MaterialRippleLayout.this.f83156N2.onTouchEvent(this.f83180s);
            MaterialRippleLayout.this.f83156N2.setPressed(true);
            if (MaterialRippleLayout.this.f83145C2) {
                MaterialRippleLayout.this.m3456t();
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class RippleBuilder {

        /* renamed from: a */
        private final Context f83181a;

        /* renamed from: b */
        private final View f83182b;

        /* renamed from: c */
        private int f83183c = -16777216;

        /* renamed from: d */
        private boolean f83184d = false;

        /* renamed from: e */
        private boolean f83185e = true;

        /* renamed from: f */
        private float f83186f = MaterialRippleLayout.f83132d3;

        /* renamed from: g */
        private int f83187g = MaterialRippleLayout.f83130b3;

        /* renamed from: h */
        private float f83188h = 0.2f;

        /* renamed from: i */
        private boolean f83189i = true;

        /* renamed from: j */
        private int f83190j = 75;

        /* renamed from: k */
        private boolean f83191k = false;

        /* renamed from: l */
        private int f83192l = 0;

        /* renamed from: m */
        private boolean f83193m = false;

        public RippleBuilder(View view) {
            this.f83182b = view;
            this.f83181a = view.getContext();
        }

        /* renamed from: a */
        public MaterialRippleLayout m3449a() {
            int i;
            MaterialRippleLayout materialRippleLayout = new MaterialRippleLayout(this.f83181a);
            materialRippleLayout.setRippleColor(this.f83183c);
            materialRippleLayout.setDefaultRippleAlpha((int) this.f83188h);
            materialRippleLayout.setRippleDelayClick(this.f83189i);
            materialRippleLayout.setRippleDiameter((int) MaterialRippleLayout.m3462n(this.f83181a.getResources(), this.f83186f));
            materialRippleLayout.setRippleDuration(this.f83187g);
            materialRippleLayout.setRippleFadeDuration(this.f83190j);
            materialRippleLayout.setRippleHover(this.f83185e);
            materialRippleLayout.setRipplePersistent(this.f83191k);
            materialRippleLayout.setRippleOverlay(this.f83184d);
            materialRippleLayout.setRippleBackground(this.f83192l);
            materialRippleLayout.setRippleInAdapter(this.f83193m);
            ViewGroup.LayoutParams layoutParams = this.f83182b.getLayoutParams();
            ViewGroup viewGroup = (ViewGroup) this.f83182b.getParent();
            if (viewGroup == null || !(viewGroup instanceof MaterialRippleLayout)) {
                if (viewGroup != null) {
                    i = viewGroup.indexOfChild(this.f83182b);
                    viewGroup.removeView(this.f83182b);
                } else {
                    i = 0;
                }
                materialRippleLayout.addView(this.f83182b, new ViewGroup.LayoutParams(-1, -1));
                if (viewGroup != null) {
                    viewGroup.addView(materialRippleLayout, i, layoutParams);
                }
                return materialRippleLayout;
            }
            throw new IllegalStateException("MaterialRippleLayout could not be created: parent of the view already is a MaterialRippleLayout");
        }

        /* renamed from: b */
        public RippleBuilder m3448b(float f) {
            this.f83188h = f * 255.0f;
            return this;
        }

        /* renamed from: c */
        public RippleBuilder m3447c(int i) {
            this.f83192l = i;
            return this;
        }

        /* renamed from: d */
        public RippleBuilder m3446d(int i) {
            this.f83183c = i;
            return this;
        }

        /* renamed from: e */
        public RippleBuilder m3445e(boolean z) {
            this.f83189i = z;
            return this;
        }

        /* renamed from: f */
        public RippleBuilder m3444f(int i) {
            this.f83186f = i;
            return this;
        }

        /* renamed from: g */
        public RippleBuilder m3443g(int i) {
            this.f83187g = i;
            return this;
        }

        /* renamed from: h */
        public RippleBuilder m3442h(int i) {
            this.f83190j = i;
            return this;
        }

        /* renamed from: i */
        public RippleBuilder m3441i(boolean z) {
            this.f83185e = z;
            return this;
        }

        /* renamed from: j */
        public RippleBuilder m3440j(boolean z) {
            m3440j(z);
            return this;
        }

        /* renamed from: k */
        public RippleBuilder m3439k(boolean z) {
            this.f83184d = z;
            return this;
        }

        /* renamed from: l */
        public RippleBuilder m3438l(boolean z) {
            this.f83191k = z;
            return this;
        }
    }

    public MaterialRippleLayout(Context context) {
        this(context, null, 0);
    }

    public MaterialRippleLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialRippleLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Paint paint = new Paint(1);
        this.f83170s = paint;
        this.f83171z2 = new Rect();
        this.f83159Q2 = new Point();
        this.f83160R2 = new Point();
        this.f83167Y2 = new GestureDetector.SimpleOnGestureListener() { // from class: net.imedicaldoctor.imd.Utils.MaterialRippleLayout.2
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                MaterialRippleLayout.this.f83156N2.performLongClick();
            }
        };
        this.f83168Z2 = new Property<MaterialRippleLayout, Float>(Float.class, "radius") { // from class: net.imedicaldoctor.imd.Utils.MaterialRippleLayout.4
            @Override // android.util.Property
            /* renamed from: a */
            public Float get(MaterialRippleLayout materialRippleLayout) {
                return Float.valueOf(materialRippleLayout.getRadius());
            }

            @Override // android.util.Property
            /* renamed from: b */
            public void set(MaterialRippleLayout materialRippleLayout, Float f) {
                materialRippleLayout.setRadius(f.floatValue());
            }
        };
        this.f83169a3 = new Property<MaterialRippleLayout, Integer>(Integer.class, "rippleAlpha") { // from class: net.imedicaldoctor.imd.Utils.MaterialRippleLayout.5
            @Override // android.util.Property
            /* renamed from: a */
            public Integer get(MaterialRippleLayout materialRippleLayout) {
                return Integer.valueOf(materialRippleLayout.getRippleAlpha());
            }

            @Override // android.util.Property
            /* renamed from: b */
            public void set(MaterialRippleLayout materialRippleLayout, Integer num) {
                materialRippleLayout.setRippleAlpha(num);
            }
        };
        setWillNotDraw(false);
        this.f83164V2 = new GestureDetector(context, this.f83167Y2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C4804R.styleable.f81159Fq);
        this.f83143A2 = obtainStyledAttributes.getColor(2, -16777216);
        this.f83146D2 = obtainStyledAttributes.getDimensionPixelSize(4, (int) m3462n(getResources(), f83132d3));
        this.f83144B2 = obtainStyledAttributes.getBoolean(9, false);
        this.f83145C2 = obtainStyledAttributes.getBoolean(7, true);
        this.f83147E2 = obtainStyledAttributes.getInt(5, f83130b3);
        this.f83148F2 = (int) (obtainStyledAttributes.getFloat(0, 0.2f) * 255.0f);
        this.f83149G2 = obtainStyledAttributes.getBoolean(3, true);
        this.f83150H2 = obtainStyledAttributes.getInteger(6, 75);
        this.f83152J2 = new ColorDrawable(obtainStyledAttributes.getColor(1, 0));
        this.f83151I2 = obtainStyledAttributes.getBoolean(10, false);
        this.f83153K2 = obtainStyledAttributes.getBoolean(8, false);
        obtainStyledAttributes.recycle();
        paint.setColor(this.f83143A2);
        paint.setAlpha(this.f83148F2);
    }

    private float getEndRadius() {
        int width = getWidth();
        int height = getHeight();
        int i = width / 2;
        int i2 = height / 2;
        Point point = this.f83159Q2;
        int i3 = point.x;
        float f = i > i3 ? width - i3 : i3;
        int i4 = point.y;
        return ((float) Math.sqrt(Math.pow(f, 2.0d) + Math.pow(i2 > i4 ? height - i4 : i4, 2.0d))) * 1.2f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getRadius() {
        return this.f83154L2;
    }

    /* renamed from: k */
    private boolean m3465k() {
        if (this.f83153K2) {
            int positionForView = m3460p().getPositionForView(this);
            boolean z = positionForView != this.f83163U2;
            this.f83163U2 = positionForView;
            if (z) {
                m3463m();
                m3464l();
                this.f83156N2.setPressed(false);
                setRadius(0.0f);
            }
            return z;
        }
        return false;
    }

    /* renamed from: l */
    private void m3464l() {
        AnimatorSet animatorSet = this.f83157O2;
        if (animatorSet != null) {
            animatorSet.cancel();
            this.f83157O2.removeAllListeners();
        }
        ObjectAnimator objectAnimator = this.f83158P2;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    /* renamed from: m */
    private void m3463m() {
        PressedEvent pressedEvent = this.f83166X2;
        if (pressedEvent != null) {
            removeCallbacks(pressedEvent);
            this.f83162T2 = false;
        }
    }

    /* renamed from: n */
    static float m3462n(Resources resources, float f) {
        return TypedValue.applyDimension(1, f, resources.getDisplayMetrics());
    }

    /* renamed from: o */
    private boolean m3461o(View view, int i, int i2) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                View childAt = viewGroup.getChildAt(i3);
                Rect rect = new Rect();
                childAt.getHitRect(rect);
                if (rect.contains(i, i2)) {
                    return m3461o(childAt, i - rect.left, i2 - rect.top);
                }
            }
        } else if (view != this.f83156N2) {
            if (view.isEnabled()) {
                return view.isClickable() || view.isLongClickable() || view.isFocusableInTouchMode();
            }
            return false;
        }
        return view.isFocusableInTouchMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p */
    public AdapterView<?> m3460p() {
        AdapterView<?> adapterView = this.f83155M2;
        if (adapterView != null) {
            return adapterView;
        }
        ViewParent parent = getParent();
        while (!(parent instanceof AdapterView)) {
            try {
                parent = parent.getParent();
            } catch (NullPointerException unused) {
                throw new RuntimeException("Could not find a parent AdapterView");
            }
        }
        AdapterView<?> adapterView2 = (AdapterView) parent;
        this.f83155M2 = adapterView2;
        return adapterView2;
    }

    /* renamed from: q */
    private boolean m3459q() {
        for (ViewParent parent = getParent(); parent != null && (parent instanceof ViewGroup); parent = parent.getParent()) {
            if (((ViewGroup) parent).shouldDelayChildPressedState()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: r */
    public static RippleBuilder m3458r(View view) {
        return new RippleBuilder(view);
    }

    /* renamed from: s */
    private void m3457s() {
        if (this.f83153K2) {
            this.f83163U2 = m3460p().getPositionForView(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t */
    public void m3456t() {
        if (this.f83161S2) {
            return;
        }
        ObjectAnimator objectAnimator = this.f83158P2;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        ObjectAnimator duration = ObjectAnimator.ofFloat(this, this.f83168Z2, this.f83146D2, (float) (Math.sqrt(Math.pow(getWidth(), 2.0d) + Math.pow(getHeight(), 2.0d)) * 1.2000000476837158d)).setDuration(f83142n3);
        this.f83158P2 = duration;
        duration.setInterpolator(new LinearInterpolator());
        this.f83158P2.start();
    }

    /* renamed from: u */
    private void m3455u(final Runnable runnable) {
        if (this.f83161S2) {
            return;
        }
        float endRadius = getEndRadius();
        m3464l();
        AnimatorSet animatorSet = new AnimatorSet();
        this.f83157O2 = animatorSet;
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: net.imedicaldoctor.imd.Utils.MaterialRippleLayout.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (!MaterialRippleLayout.this.f83151I2) {
                    MaterialRippleLayout.this.setRadius(0.0f);
                    MaterialRippleLayout materialRippleLayout = MaterialRippleLayout.this;
                    materialRippleLayout.setRippleAlpha(Integer.valueOf(materialRippleLayout.f83148F2));
                }
                if (runnable != null && MaterialRippleLayout.this.f83149G2) {
                    runnable.run();
                }
                MaterialRippleLayout.this.f83156N2.setPressed(false);
            }
        });
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, this.f83168Z2, this.f83154L2, endRadius);
        ofFloat.setDuration(this.f83147E2);
        ofFloat.setInterpolator(new DecelerateInterpolator());
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this, this.f83169a3, this.f83148F2, 0);
        ofInt.setDuration(this.f83150H2);
        ofInt.setInterpolator(new AccelerateInterpolator());
        ofInt.setStartDelay((this.f83147E2 - this.f83150H2) - 50);
        if (this.f83151I2) {
            this.f83157O2.play(ofFloat);
        } else if (getRadius() > endRadius) {
            ofInt.setStartDelay(0L);
            this.f83157O2.play(ofInt);
        } else {
            this.f83157O2.playTogether(ofFloat, ofInt);
        }
        this.f83157O2.start();
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("MaterialRippleLayout can host only one child");
        }
        this.f83156N2 = view;
        super.addView(view, i, layoutParams);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean m3465k = m3465k();
        if (!this.f83144B2) {
            if (!m3465k) {
                this.f83152J2.draw(canvas);
                Point point = this.f83159Q2;
                canvas.drawCircle(point.x, point.y, this.f83154L2, this.f83170s);
            }
            super.draw(canvas);
            return;
        }
        if (!m3465k) {
            this.f83152J2.draw(canvas);
        }
        super.draw(canvas);
        if (m3465k) {
            return;
        }
        Point point2 = this.f83159Q2;
        canvas.drawCircle(point2.x, point2.y, this.f83154L2, this.f83170s);
    }

    public <T extends View> T getChildView() {
        return (T) this.f83156N2;
    }

    public int getRippleAlpha() {
        return this.f83170s.getAlpha();
    }

    @Override // android.view.View
    public boolean isInEditMode() {
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return !m3461o(this.f83156N2, (int) motionEvent.getX(), (int) motionEvent.getY());
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f83171z2.set(0, 0, i, i2);
        this.f83152J2.setBounds(this.f83171z2);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (isEnabled() && this.f83156N2.isEnabled()) {
            boolean contains = this.f83171z2.contains((int) motionEvent.getX(), (int) motionEvent.getY());
            if (contains) {
                Point point = this.f83160R2;
                Point point2 = this.f83159Q2;
                point.set(point2.x, point2.y);
                this.f83159Q2.set((int) motionEvent.getX(), (int) motionEvent.getY());
            }
            if (this.f83164V2.onTouchEvent(motionEvent)) {
                return true;
            }
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 0) {
                if (actionMasked == 1) {
                    this.f83165W2 = new PerformClickEvent();
                    if (this.f83162T2) {
                        this.f83156N2.setPressed(true);
                        postDelayed(new Runnable() { // from class: net.imedicaldoctor.imd.Utils.MaterialRippleLayout.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MaterialRippleLayout.this.f83156N2.setPressed(false);
                            }
                        }, ViewConfiguration.getPressedStateDuration());
                    }
                    if (contains) {
                        m3455u(this.f83165W2);
                    } else if (!this.f83145C2) {
                        setRadius(0.0f);
                    }
                    if (!this.f83149G2 && contains) {
                        this.f83165W2.run();
                    }
                } else if (actionMasked == 2) {
                    if (this.f83145C2) {
                        if (contains && !this.f83161S2) {
                            invalidate();
                        } else if (!contains) {
                            m3455u(null);
                        }
                    }
                    if (!contains) {
                        m3463m();
                        ObjectAnimator objectAnimator = this.f83158P2;
                        if (objectAnimator != null) {
                            objectAnimator.cancel();
                        }
                        this.f83156N2.onTouchEvent(motionEvent);
                        this.f83161S2 = true;
                    }
                } else if (actionMasked == 3) {
                    if (this.f83153K2) {
                        Point point3 = this.f83159Q2;
                        Point point4 = this.f83160R2;
                        point3.set(point4.x, point4.y);
                        this.f83160R2 = new Point();
                    }
                    this.f83156N2.onTouchEvent(motionEvent);
                    if (!this.f83145C2) {
                        this.f83156N2.setPressed(false);
                    } else if (!this.f83162T2) {
                        m3455u(null);
                    }
                }
                m3463m();
            } else {
                m3457s();
                this.f83161S2 = false;
                if (m3459q()) {
                    m3463m();
                    this.f83162T2 = true;
                    PressedEvent pressedEvent = new PressedEvent(motionEvent);
                    this.f83166X2 = pressedEvent;
                    postDelayed(pressedEvent, ViewConfiguration.getTapTimeout());
                } else {
                    this.f83156N2.onTouchEvent(motionEvent);
                    this.f83156N2.setPressed(true);
                    if (this.f83145C2) {
                        m3456t();
                    }
                }
            }
            return true;
        }
        return onTouchEvent;
    }

    public void setDefaultRippleAlpha(int i) {
        this.f83148F2 = i;
        this.f83170s.setAlpha(i);
        invalidate();
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        View view = this.f83156N2;
        if (view == null) {
            throw new IllegalStateException("MaterialRippleLayout must have a child view to handle clicks");
        }
        view.setOnClickListener(onClickListener);
    }

    public void setRadius(float f) {
        this.f83154L2 = f;
        invalidate();
    }

    public void setRippleAlpha(Integer num) {
        this.f83170s.setAlpha(num.intValue());
        invalidate();
    }

    public void setRippleBackground(int i) {
        ColorDrawable colorDrawable = new ColorDrawable(i);
        this.f83152J2 = colorDrawable;
        colorDrawable.setBounds(this.f83171z2);
        invalidate();
    }

    public void setRippleColor(int i) {
        this.f83143A2 = i;
        this.f83170s.setColor(i);
        this.f83170s.setAlpha(this.f83148F2);
        invalidate();
    }

    public void setRippleDelayClick(boolean z) {
        this.f83149G2 = z;
    }

    public void setRippleDiameter(int i) {
        this.f83146D2 = i;
    }

    public void setRippleDuration(int i) {
        this.f83147E2 = i;
    }

    public void setRippleFadeDuration(int i) {
        this.f83150H2 = i;
    }

    public void setRippleHover(boolean z) {
        this.f83145C2 = z;
    }

    public void setRippleInAdapter(boolean z) {
        this.f83153K2 = z;
    }

    public void setRippleOverlay(boolean z) {
        this.f83144B2 = z;
    }

    public void setRipplePersistent(boolean z) {
        this.f83151I2 = z;
    }
}
