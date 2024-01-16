package net.imedicaldoctor.imd.Views;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;
import net.imedicaldoctor.imd.C4804R;

/* loaded from: classes2.dex */
public class AnimUtils {

    /* renamed from: net.imedicaldoctor.imd.Views.AnimUtils$9 */
    /* loaded from: classes2.dex */
    static /* synthetic */ class C48399 {

        /* renamed from: a */
        static final /* synthetic */ int[] f83348a;

        static {
            int[] iArr = new int[Style.values().length];
            f83348a = iArr;
            try {
                iArr[Style.Fade.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f83348a[Style.Pop.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f83348a[Style.Fly.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f83348a[Style.BrightnessSaturationFade.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public enum Style {
        None,
        Fade,
        Pop,
        Fly,
        BrightnessSaturationFade,
        ProgressWidth
    }

    private AnimUtils() {
    }

    /* renamed from: a */
    public static ValueAnimator m3358a(View view, Style style, Animator.AnimatorListener animatorListener) {
        int i = C48399.f83348a[style.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return view instanceof ImageView ? m3356c((ImageView) view, animatorListener) : m3354e(view, animatorListener);
                    }
                    if (animatorListener != null) {
                        animatorListener.mo8718d(null);
                    }
                    return null;
                }
                return m3352g(view, animatorListener);
            }
            return m3348k(view, animatorListener);
        }
        return m3354e(view, animatorListener);
    }

    /* renamed from: b */
    public static ValueAnimator m3357b(View view, Style style, Animator.AnimatorListener animatorListener) {
        int i = C48399.f83348a[style.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return view instanceof ImageView ? m3355d((ImageView) view, animatorListener) : m3353f(view, animatorListener);
                    }
                    if (animatorListener != null) {
                        animatorListener.mo8718d(null);
                    }
                    return null;
                }
                return m3351h(view, animatorListener);
            }
            return m3347l(view, animatorListener);
        }
        return m3353f(view, animatorListener);
    }

    /* renamed from: c */
    public static ValueAnimator m3356c(final ImageView imageView, Animator.AnimatorListener animatorListener) {
        final ValueAnimator m8852w0 = ValueAnimator.m8852w0(0.0f, 1.0f);
        final AccelerateDecelerateInterpolator accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
        m8852w0.mo8858o(accelerateDecelerateInterpolator);
        m8852w0.mo8859n(800L);
        if (animatorListener != null) {
            m8852w0.m9046a(animatorListener);
        }
        m8852w0.m8887Q(new ValueAnimator.AnimatorUpdateListener() { // from class: net.imedicaldoctor.imd.Views.AnimUtils.7

            /* renamed from: a */
            ColorMatrix f83338a = new ColorMatrix();

            /* renamed from: b */
            ColorMatrix f83339b = new ColorMatrix();

            @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
            /* renamed from: e */
            public void mo3346e(ValueAnimator valueAnimator) {
                float m8876a0 = ValueAnimator.this.m8876a0();
                this.f83338a.setSaturation(((Float) ValueAnimator.this.m8874b0()).floatValue());
                float interpolation = 2.0f - accelerateDecelerateInterpolator.getInterpolation(Math.min((4.0f * m8876a0) / 3.0f, 1.0f));
                this.f83339b.setScale(interpolation, interpolation, interpolation, accelerateDecelerateInterpolator.getInterpolation(Math.min(m8876a0 * 2.0f, 1.0f)));
                this.f83338a.preConcat(this.f83339b);
                imageView.setColorFilter(new ColorMatrixColorFilter(this.f83338a));
                if (imageView.getParent() != null) {
                    ((View) imageView.getParent()).postInvalidate();
                }
            }
        });
        m8852w0.mo8850y();
        return m8852w0;
    }

    /* renamed from: d */
    public static ValueAnimator m3355d(final ImageView imageView, Animator.AnimatorListener animatorListener) {
        final ValueAnimator m8852w0 = ValueAnimator.m8852w0(1.0f, 0.0f);
        final AccelerateDecelerateInterpolator accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
        m8852w0.mo8858o(accelerateDecelerateInterpolator);
        m8852w0.mo8859n(800L);
        if (animatorListener != null) {
            m8852w0.m9046a(animatorListener);
        }
        m8852w0.m8887Q(new ValueAnimator.AnimatorUpdateListener() { // from class: net.imedicaldoctor.imd.Views.AnimUtils.8

            /* renamed from: a */
            ColorMatrix f83343a = new ColorMatrix();

            /* renamed from: b */
            ColorMatrix f83344b = new ColorMatrix();

            @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
            /* renamed from: e */
            public void mo3346e(ValueAnimator valueAnimator) {
                float m8876a0 = ValueAnimator.this.m8876a0();
                this.f83343a.setSaturation(((Float) ValueAnimator.this.m8874b0()).floatValue());
                float f = 1.0f - m8876a0;
                float interpolation = 2.0f - accelerateDecelerateInterpolator.getInterpolation(Math.min((4.0f * f) / 3.0f, 1.0f));
                this.f83344b.setScale(interpolation, interpolation, interpolation, accelerateDecelerateInterpolator.getInterpolation(Math.min(f * 2.0f, 1.0f)));
                this.f83343a.preConcat(this.f83344b);
                imageView.setColorFilter(new ColorMatrixColorFilter(this.f83343a));
                if (imageView.getParent() != null) {
                    ((View) imageView.getParent()).postInvalidate();
                }
            }
        });
        m8852w0.mo8850y();
        return m8852w0;
    }

    /* renamed from: e */
    public static ValueAnimator m3354e(final View view, Animator.AnimatorListener animatorListener) {
        if (view.getVisibility() != 0) {
            ViewHelper.m8815o(view, 0.0f);
        }
        float m8829a = ViewHelper.m8829a(view);
        ValueAnimator m8852w0 = ValueAnimator.m8852w0(m8829a, 1.0f);
        m8852w0.mo8859n((1.0f - m8829a) * 200.0f);
        m8852w0.mo8858o(new DecelerateInterpolator());
        if (animatorListener != null) {
            m8852w0.m9046a(animatorListener);
        }
        m8852w0.m8887Q(new ValueAnimator.AnimatorUpdateListener() { // from class: net.imedicaldoctor.imd.Views.AnimUtils.1
            @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
            /* renamed from: e */
            public void mo3346e(ValueAnimator valueAnimator) {
                ViewHelper.m8815o(view, ((Float) valueAnimator.m8874b0()).floatValue());
                if (view.getParent() != null) {
                    ((View) view.getParent()).postInvalidate();
                }
            }
        });
        m8852w0.mo8850y();
        return m8852w0;
    }

    /* renamed from: f */
    public static ValueAnimator m3353f(final View view, Animator.AnimatorListener animatorListener) {
        float m8829a = ViewHelper.m8829a(view);
        ValueAnimator m8852w0 = ValueAnimator.m8852w0(m8829a, 0.0f);
        m8852w0.mo8859n(m8829a * 200.0f);
        m8852w0.mo8858o(new DecelerateInterpolator());
        if (animatorListener != null) {
            m8852w0.m9046a(animatorListener);
        }
        m8852w0.m8887Q(new ValueAnimator.AnimatorUpdateListener() { // from class: net.imedicaldoctor.imd.Views.AnimUtils.2
            @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
            /* renamed from: e */
            public void mo3346e(ValueAnimator valueAnimator) {
                ViewHelper.m8815o(view, ((Float) valueAnimator.m8874b0()).floatValue());
                if (view.getParent() != null) {
                    ((View) view.getParent()).postInvalidate();
                }
            }
        });
        m8852w0.mo8850y();
        return m8852w0;
    }

    /* renamed from: g */
    public static ValueAnimator m3352g(final View view, Animator.AnimatorListener animatorListener) {
        if (view.getVisibility() != 0) {
            ViewHelper.m8815o(view, 0.0f);
        }
        float m8829a = ViewHelper.m8829a(view);
        ValueAnimator m8852w0 = ValueAnimator.m8852w0(m8829a, 1.0f);
        m8852w0.mo8859n((1.0f - m8829a) * 200.0f);
        m8852w0.mo8858o(new DecelerateInterpolator());
        if (animatorListener != null) {
            m8852w0.m9046a(animatorListener);
        }
        m8852w0.m8887Q(new ValueAnimator.AnimatorUpdateListener() { // from class: net.imedicaldoctor.imd.Views.AnimUtils.5
            @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
            /* renamed from: e */
            public void mo3346e(ValueAnimator valueAnimator) {
                ViewHelper.m8815o(view, ((Float) valueAnimator.m8874b0()).floatValue());
                View view2 = view;
                ViewHelper.m8804z(view2, Math.min(view2.getHeight() / 2, view.getResources().getDimension(C4804R.dimen.f86413carbon_1dip) * 50.0f) * (1.0f - ((Float) valueAnimator.m8874b0()).floatValue()));
                if (view.getParent() != null) {
                    ((View) view.getParent()).postInvalidate();
                }
            }
        });
        m8852w0.mo8850y();
        return m8852w0;
    }

    /* renamed from: h */
    public static ValueAnimator m3351h(final View view, Animator.AnimatorListener animatorListener) {
        float m8829a = ViewHelper.m8829a(view);
        ValueAnimator m8852w0 = ValueAnimator.m8852w0(m8829a, 0.0f);
        m8852w0.mo8859n(m8829a * 200.0f);
        m8852w0.mo8858o(new DecelerateInterpolator());
        if (animatorListener != null) {
            m8852w0.m9046a(animatorListener);
        }
        m8852w0.m8887Q(new ValueAnimator.AnimatorUpdateListener() { // from class: net.imedicaldoctor.imd.Views.AnimUtils.6
            @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
            /* renamed from: e */
            public void mo3346e(ValueAnimator valueAnimator) {
                ViewHelper.m8815o(view, ((Float) valueAnimator.m8874b0()).floatValue());
                View view2 = view;
                ViewHelper.m8804z(view2, Math.min(view2.getHeight() / 2, view.getResources().getDimension(C4804R.dimen.f86413carbon_1dip) * 50.0f) * (1.0f - ((Float) valueAnimator.m8874b0()).floatValue()));
                if (view.getParent() != null) {
                    ((View) view.getParent()).postInvalidate();
                }
            }
        });
        m8852w0.mo8850y();
        return m8852w0;
    }

    /* renamed from: i */
    public static float m3350i(float f, float f2, float f3) {
        return (f2 * (1.0f - f)) + (f3 * f);
    }

    /* renamed from: j */
    public static int m3349j(float f, int i, int i2) {
        return Color.argb((int) m3350i(f, i >> 24, i2 >> 24), (int) m3350i(f, (i >> 16) & 255, (i2 >> 16) & 255), (int) m3350i(f, (i >> 8) & 255, (i2 >> 8) & 255), (int) m3350i(f, i & 255, i2 & 255));
    }

    /* renamed from: k */
    public static ValueAnimator m3348k(final View view, Animator.AnimatorListener animatorListener) {
        if (view.getVisibility() != 0) {
            ViewHelper.m8815o(view, 0.0f);
        }
        float m8829a = ViewHelper.m8829a(view);
        ValueAnimator m8852w0 = ValueAnimator.m8852w0(m8829a, 1.0f);
        m8852w0.mo8859n((1.0f - m8829a) * 200.0f);
        m8852w0.mo8858o(new DecelerateInterpolator());
        if (animatorListener != null) {
            m8852w0.m9046a(animatorListener);
        }
        m8852w0.m8887Q(new ValueAnimator.AnimatorUpdateListener() { // from class: net.imedicaldoctor.imd.Views.AnimUtils.3
            @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
            /* renamed from: e */
            public void mo3346e(ValueAnimator valueAnimator) {
                ViewHelper.m8815o(view, ((Float) valueAnimator.m8874b0()).floatValue());
                ViewHelper.m8809u(view, ((Float) valueAnimator.m8874b0()).floatValue());
                ViewHelper.m8808v(view, ((Float) valueAnimator.m8874b0()).floatValue());
                if (view.getParent() != null) {
                    ((View) view.getParent()).postInvalidate();
                }
            }
        });
        m8852w0.mo8850y();
        return m8852w0;
    }

    /* renamed from: l */
    public static ValueAnimator m3347l(final View view, Animator.AnimatorListener animatorListener) {
        float m8829a = ViewHelper.m8829a(view);
        ValueAnimator m8852w0 = ValueAnimator.m8852w0(m8829a, 0.0f);
        m8852w0.mo8859n(m8829a * 200.0f);
        m8852w0.mo8858o(new DecelerateInterpolator());
        if (animatorListener != null) {
            m8852w0.m9046a(animatorListener);
        }
        m8852w0.m8887Q(new ValueAnimator.AnimatorUpdateListener() { // from class: net.imedicaldoctor.imd.Views.AnimUtils.4
            @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
            /* renamed from: e */
            public void mo3346e(ValueAnimator valueAnimator) {
                ViewHelper.m8815o(view, ((Float) valueAnimator.m8874b0()).floatValue());
                ViewHelper.m8809u(view, ((Float) valueAnimator.m8874b0()).floatValue());
                ViewHelper.m8808v(view, ((Float) valueAnimator.m8874b0()).floatValue());
                if (view.getParent() != null) {
                    ((View) view.getParent()).postInvalidate();
                }
            }
        });
        m8852w0.mo8850y();
        return m8852w0;
    }
}
