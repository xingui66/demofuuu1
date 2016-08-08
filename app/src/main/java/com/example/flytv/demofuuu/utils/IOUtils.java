package com.example.flytv.demofuuu.utils;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

/**
 * Created by Flytv on 2016/8/2.
 */
public class IOUtils
{static void deleteContents(File dir) throws IOException {
    File[] files = dir.listFiles();
    if (files == null) {
        throw new IllegalArgumentException("not a directory: " + dir);
    }
    for (File file : files) {
        if (file.isDirectory()) {
            deleteContents(file);
        }
        if (!file.delete()) {
            throw new IOException("failed to delete file: " + file);
        }
    }
}

    static void closeQuietly(/*Auto*/Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception ignored) {
            }
        }
    }
}
