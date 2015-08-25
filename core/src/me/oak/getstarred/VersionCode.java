
package spaceisnear;

public final class VersionCode {

    public static final int BUILD_NUMBER = 105;
    public static final String VERSION = "0.0.1";

    public static String getCode() {
	return VERSION + "-b" + BUILD_NUMBER;
    }
}
