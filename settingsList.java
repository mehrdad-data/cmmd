package net.imedicaldoctor.imd.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import net.imedicaldoctor.imd.C4804R;
import net.imedicaldoctor.imd.Data.CompressHelper;

/* loaded from: classes2.dex */
public class settingsList extends DialogFragment {

    /* renamed from: g4 */
    private ArrayList<Bundle> f77021g4;

    /* renamed from: h4 */
    private String f77022h4;

    /* renamed from: i4 */
    private String f77023i4;

    /* renamed from: j4 */
    private String f77024j4;

    @Override // androidx.fragment.app.DialogFragment
    /* renamed from: X2 */
    public Dialog mo3313X2(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(m44716w());
        View inflate = m44716w().getLayoutInflater().inflate(C4804R.C4810layout.f87155fragment_general_section_viewer, (ViewGroup) null);
        ListView listView = (ListView) inflate.findViewById(C4804R.C4808id.f86950list_view);
        this.f77021g4 = m44859B().getParcelableArrayList(FirebaseAnalytics.Param.f55203f0);
        this.f77022h4 = m44859B().getString("titleProperty");
        this.f77023i4 = m44859B().getString("type");
        this.f77024j4 = m44859B().getString("selected");
        new CompressHelper(m44716w());
        listView.setAdapter((ListAdapter) new ArrayAdapter<Bundle>(m44716w(), C4804R.C4810layout.f87288list_view_item_simple_text_check, C4804R.C4808id.text, this.f77021g4) { // from class: net.imedicaldoctor.imd.Fragments.settingsList.1
            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = LayoutInflater.from(settingsList.this.m44716w()).inflate(C4804R.C4810layout.f87288list_view_item_simple_text_check, viewGroup, false);
                    view.setTag(view.findViewById(C4804R.C4808id.text));
                }
                String string = ((Bundle) settingsList.this.f77021g4.get(i)).getString(settingsList.this.f77022h4);
                ((TextView) view.getTag()).setText(string);
                if (string.equals(settingsList.this.f77024j4)) {
                    view.findViewById(C4804R.C4808id.f86840check_icon).setVisibility(0);
                } else {
                    view.findViewById(C4804R.C4808id.f86840check_icon).setVisibility(8);
                }
                return view;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: net.imedicaldoctor.imd.Fragments.settingsList.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ((accountFragment) settingsList.this.m44753k0()).m3919p3((Bundle) settingsList.this.f77021g4.get(i), settingsList.this.f77023i4);
                settingsList.this.mo27003Q2();
            }
        });
        builder.setView(inflate);
        return builder.create();
    }
}
