/**
 * Created by m91snik on 09.04.15.
 */
package org.sergei.business.service.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BytesUtil {

    public String bytesToString(byte[] bytes) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(bytes);
        out.close();
        return out.toString();
    }

}
