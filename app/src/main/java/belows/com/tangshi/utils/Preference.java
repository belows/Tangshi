package belows.com.tangshi.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yy.androidlib.util.apache.StringUtils;
import com.yy.androidlib.util.logging.Logger;

/**
 * Created by belows on 15/6/11.
 */
public class Preference {
    private static ObjectMapper mObjectMappter = new ObjectMapper();

    public enum CommonKey {
        TANGSHI_INITED
    }

    public enum UserKey {

    }

    public static <T> T get(Context pContext, long pUid, UserKey pKey, TypeReference pTypeReference) {
        return get(getUserPreferences(pContext, pUid), pKey.name(), pTypeReference);
    }

    public static <T> T get(Context pContext, long pUid, UserKey pKey, Class<T> pClass) {
        return get(getUserPreferences(pContext, pUid), pKey.name(), pClass);
    }

    public static <T> T get(Context pContext, CommonKey pKey, TypeReference pTypeReference) {
        return get(getCommonPreferences(pContext), pKey.name(), pTypeReference);
    }

    public static <T> T get(Context pContext, CommonKey pKey, Class<T> pClass) {
        return get(getCommonPreferences(pContext), pKey.name(), pClass);
    }

    public static Boolean get(Context pContext, long pUid, UserKey pKey, boolean pDefaultValue) {
        return getUserPreferences(pContext, pUid).getBoolean(pKey.name(), pDefaultValue);
    }

    public static Boolean get(Context pContext, CommonKey pKey, boolean pDefaultValue) {
        return getCommonPreferences(pContext).getBoolean(pKey.name(), pDefaultValue);
    }

    public static void save(Context pContext, long pUid, UserKey pKey, Object pValue) {
        save(getUserPreferences(pContext, pUid), pKey.name(), pValue);
    }

    public static void save(Context pContext, CommonKey pKey, Object pValue) {
        save(getCommonPreferences(pContext), pKey.name(), pValue);
    }

    public static void save(Context pContext, long pUid, UserKey pKey, boolean pValue) {
        SharedPreferences.Editor _editor = getUserPreferences(pContext, pUid).edit();
        _editor.putBoolean(pKey.name(), pValue);
        _editor.commit();
    }

    public static void save(Context pContext, CommonKey pKey, boolean pValue) {
        SharedPreferences.Editor _editor = getCommonPreferences(pContext).edit();
        _editor.putBoolean(pKey.name(), pValue);
        _editor.commit();
    }

    public static <T> T get(SharedPreferences pSharedPreferences, String pKey, TypeReference pTypeReference) {
        String _value = pSharedPreferences.getString(pKey, "");
        if (StringUtils.isEmpty(_value)) {
            return null;
        } else {
            try {
                return mObjectMappter.readValue(_value, pTypeReference);
            } catch (Exception e) {
                Logger.error(Preference.class, "get preferences error,value:%s", _value, e);
                return null;
            }
        }
    }

    public static <T> T get(SharedPreferences pSharedPreferences, String pKey, Class<T> pClass) {
        String _value = pSharedPreferences.getString(pKey, "");
        if (StringUtils.isEmpty(_value)) {
            return null;
        } else {
            try {
                return mObjectMappter.readValue(_value, pClass);
            } catch (Exception e) {
                Logger.error(Preference.class, "get preferences error,value:%s", _value, e);
                return null;
            }
        }
    }

    public static void save(SharedPreferences pSharedPreferences, String pKey, Object pValue) {
        SharedPreferences.Editor _editor = pSharedPreferences.edit();
        try {
            String _value = mObjectMappter.writeValueAsString(pValue);
            boolean _success = _editor.putString(pKey, _value).commit();
            if (_success) {
                Logger.verbose(Preference.class, "save preferences value:%s", pValue);
            } else {
                Logger.error(Preference.class, "save preferences failed,value:%s", pValue);
            }
        } catch (Exception e) {
            Logger.error(Preference.class, "save preferences error:" + e);
        }
    }

    private static SharedPreferences getCommonPreferences(Context pContext) {
        return getSharedPreferences(pContext, getCommonPreferencesName());
    }

    private static SharedPreferences getUserPreferences(Context pContext, long pUid) {
        return getSharedPreferences(pContext, getUserPreferencesName(pUid));
    }

    private static String getUserPreferencesName(long pUid) {
        return "uid_" + pUid;
    }

    private static String getCommonPreferencesName() {
        return "common";
    }

    private static SharedPreferences getSharedPreferences(Context pContext, String pName) {
        return pContext.getSharedPreferences(pName, Context.MODE_PRIVATE);
    }
}
