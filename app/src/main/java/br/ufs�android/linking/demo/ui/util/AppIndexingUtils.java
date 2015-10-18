package br.ufs.android.linking.demo.ui.util;

import android.net.Uri;
import android.util.Log;

import com.google.android.gms.appindexing.Action;

import java.net.MalformedURLException;
import java.net.URL;

import br.ufs.android.linking.demo.dto.DribbbleShot;

/**
 * Created by ubiratansoares on 10/17/15.
 */

public class AppIndexingUtils {

    private static final String PACKAGE = "br.ufs.android.linking.demo";
    private static final String SCHEMA = "https";
    private static final String HOST = "dribbble.com";

    private static final Uri BASE_INDEX_URI = Uri.parse(
            "android-app:" + "//" + PACKAGE + "/" + SCHEMA + "/" + HOST
    );

    public static Action getActionForShot(DribbbleShot shot) {
        final String title = shot.getTitle();

        try {
            URL url = new URL(shot.getWebURL());
            String path = url.getPath().substring(1);
            Uri uri = BASE_INDEX_URI.buildUpon().appendEncodedPath(path).build();
            Log.v("URL", "URL -> " + uri.toString());
            return Action.newAction(Action.TYPE_VIEW, title, uri);

        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Malformed URL for Shot");
        }
    }

    public static String parseShotId(String shotURL) {
        String[] parts = shotURL.replace("https://", "").split("/");
        String path = parts[2]; // dribble.com/shots/{id}-title
        String id = path.split("-")[0]; // {id}
        return id;
    }

}
