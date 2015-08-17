package net.dektyarev.kupandaviewer.rest;

import android.content.Context;

import net.dektyarev.kupandaviewer.R;

import java.util.HashMap;
import java.util.Map;

public class RequestUtils {

    public static final Map<String, String> constructParametersMap(Context context) {
        Map<String, String> parameters = new HashMap<String, String>();

        String[] requestKeys = context.getResources().getStringArray(R.array.request_parameter_keys);
        String[] requestValues = context.getResources().getStringArray(R.array.request_parameter_values);

        if (requestKeys.length != requestValues.length) {
            return null;
        }

        for (int i = 0; i < requestKeys.length; ++i) {
            parameters.put(requestKeys[i], requestValues[i]);
        }

        return parameters;
    }

}
