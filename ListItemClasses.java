package net.imedicaldoctor.imd.Utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import net.imedicaldoctor.imd.C4804R;

/* loaded from: classes2.dex */
public class ListItemClasses {

    /* loaded from: classes2.dex */
    public class DatabaseViewHolder {

        /* renamed from: a */
        public final TextView f83121a;

        /* renamed from: b */
        public final ImageView f83122b;

        public DatabaseViewHolder(View view) {
            this.f83121a = (TextView) view.findViewById(C4804R.C4808id.f86857database_title);
            this.f83122b = (ImageView) view.findViewById(C4804R.C4808id.f86854database_image);
        }
    }

    /* loaded from: classes2.dex */
    public class HeaderViewHolder {

        /* renamed from: a */
        public final TextView f83124a;

        public HeaderViewHolder(View view) {
            this.f83124a = (TextView) view.findViewById(C4804R.C4808id.f86913header_text);
        }
    }

    /* loaded from: classes2.dex */
    public class InteractionViewHolder {

        /* renamed from: a */
        public final TextView f83126a;

        /* renamed from: b */
        public final ImageView f83127b;

        /* renamed from: c */
        public final TextView f83128c;

        public InteractionViewHolder(View view) {
            this.f83128c = (TextView) view.findViewById(C4804R.C4808id.f87036subtitle_text);
            this.f83126a = (TextView) view.findViewById(C4804R.C4808id.f87060title_text);
            this.f83127b = (ImageView) view.findViewById(C4804R.C4808id.image);
        }
    }
}
