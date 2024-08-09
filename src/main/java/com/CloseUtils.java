package com;

import java.io.Closeable;
import java.util.zip.ZipInputStream;

/**
 * 关闭文件流
 */
public class CloseUtils {

    /**
     * 关闭文件流
     *
     * @param closeables
     */
    public static void closeQuietly(Closeable... closeables) {
        if (closeables != null && closeables.length > 0) {

            for (Closeable closeable : closeables) {

                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (Exception e) {}
                }
            }
        }
    }

    public static void closeEntryQuietly(ZipInputStream... closeables) {
        if (closeables != null && closeables.length > 0) {
            for (ZipInputStream closeable : closeables) {
                if (closeable != null) {
                    try {
                        closeable.closeEntry();
                    } catch (Exception e) { }
                }
            }
        }
    }
}

