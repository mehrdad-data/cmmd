package net.imedicaldoctor.imd;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import net.imedicaldoctor.imd.Fragments.accountFragment;
import net.imedicaldoctor.imd.Fragments.contentFragment;
import net.imedicaldoctor.imd.Fragments.databasesFragment;
import net.imedicaldoctor.imd.Fragments.downloadFragment;
import net.imedicaldoctor.imd.Fragments.favoritesFragment;
import net.imedicaldoctor.imd.Fragments.searchFragment;

/* loaded from: classes2.dex */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    /* renamed from: n */
    public FragmentManager f83114n;

    public TabsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.f83114n = fragmentManager;
    }

    /* renamed from: z */
    private static String m3482z(int i, int i2) {
        return "android:switcher:" + i + ":" + i2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    /* renamed from: e */
    public int mo3486e() {
        return 6;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    /* renamed from: g */
    public CharSequence mo3485g(int i) {
        return "";
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    /* renamed from: v */
    public Fragment mo3484v(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                return null;
                            }
                            return new accountFragment();
                        }
                        return new downloadFragment();
                    }
                    return new contentFragment();
                }
                return new favoritesFragment();
            }
            return new databasesFragment();
        }
        return new searchFragment();
    }

    /* renamed from: y */
    public Fragment m3483y(ViewPager viewPager, int i) {
        return this.f83114n.m44466q0(m3482z(viewPager.getId(), i));
    }
}
