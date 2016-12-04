package hackathon.london.smartshopper;

import android.support.annotation.Nullable;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class StringUtils {

    public static boolean startsWithIgnoringCase(String string, String prefix) {
        return string.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    @Nullable
    public static String getStringWithDefaultValueIfEmpty(@Nullable final String stringToCompare, @Nullable final String defaultValue) {
        return isEmpty(stringToCompare) ? defaultValue : stringToCompare;
    }
}
