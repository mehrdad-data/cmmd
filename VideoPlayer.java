package net.imedicaldoctor.imd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/* loaded from: classes2.dex */
public class VideoPlayer extends iMDActivity {

    /* loaded from: classes2.dex */
    public static class VideoPlayerFragment extends Fragment {
        @Override // androidx.fragment.app.Fragment
        /* renamed from: U0 */
        public View mo3277U0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(C4804R.C4810layout.f87205fragment_video_player, viewGroup, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.imedicaldoctor.imd.iMDActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C4804R.C4810layout.f87122activity_video_player);
        if (bundle == null) {
            m44690E().m44464r().m44301b(C4804R.C4808id.container, new VideoPlayerFragment()).mo44289n();
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        menuItem.getItemId();
        return super.onOptionsItemSelected(menuItem);
    }
}
