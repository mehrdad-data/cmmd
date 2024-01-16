package net.imedicaldoctor.imd.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class StableArrayAdapter extends ArrayAdapter<Bundle> {

    /* renamed from: X */
    HashMap<String, Integer> f75348X;

    /* renamed from: s */
    final int f75349s;

    public StableArrayAdapter(Context context, int i, List<Bundle> list) {
        super(context, i, list);
        this.f75349s = -1;
        this.f75348X = new HashMap<>();
        int i2 = 0;
        for (Bundle bundle : list) {
            HashMap<String, Integer> hashMap = this.f75348X;
            hashMap.put("Section" + bundle.getString("title"), Integer.valueOf(i2));
            i2++;
            for (int i3 = 0; i3 < bundle.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).size(); i3++) {
                HashMap<String, Integer> hashMap2 = this.f75348X;
                hashMap2.put("Database" + ((Bundle) bundle.getParcelableArrayList(FirebaseAnalytics.Param.f55203f0).get(i3)).getString("Name"), Integer.valueOf(i2));
                i2++;
            }
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public long getItemId(int i) {
        StringBuilder sb;
        String str;
        if (i < 0 || i >= this.f75348X.size()) {
            return -1L;
        }
        Bundle bundle = (Bundle) getItem(i);
        if (bundle.containsKey("Item")) {
            bundle = bundle.getBundle("Item");
            sb = new StringBuilder();
            sb.append("Database");
            str = "Name";
        } else {
            sb = new StringBuilder();
            sb.append("Section");
            str = "Title";
        }
        sb.append(bundle.getString(str));
        return this.f75348X.get(sb.toString()).intValue();
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }
}
