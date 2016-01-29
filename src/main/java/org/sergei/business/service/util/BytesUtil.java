package org.sergei.business.service.util;

import java.io.ByteArrayOutputStream;

public class BytesUtil {

    public String bytesToString(byte[] bytes) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(bytes);
        out.close();
        return out.toString();
    }

}
