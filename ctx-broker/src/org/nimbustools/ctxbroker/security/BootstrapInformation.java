/*
 * Copyright 1999-2008 University of Chicago
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.nimbustools.ctxbroker.security;

import org.globus.gsi.OpenSSLKey;
import org.globus.gsi.bc.BouncyCastleOpenSSLKey;
import org.apache.axis.encoding.Base64;

import java.security.cert.X509Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.KeyPair;
import java.io.StringWriter;
import java.io.IOException;

public class BootstrapInformation {


    // for PEM string
    public static final int LINE_LENGTH = 64;
    public static final String lineSep = "\n";
    public static final String certHeader = "-----BEGIN CERTIFICATE-----";
    public static final String certFooter = "-----END CERTIFICATE-----";
    public static final String keyHeader = "-----BEGIN RSA PRIVATE KEY-----";
    public static final String keyFooter = "-----END RSA PRIVATE KEY-----";

    private String publicX509String = null;
    private String privateString = null;

    // save the objects (for future functionality)
    private X509Certificate x509Cert = null;
    private KeyPair keypair = null;

    // convenience (globus DN style)
    private String bootstrapDN = null;

    public String getPublicX509String() {
        return publicX509String;
    }

    public String getPrivateString() {
        return privateString;
    }

    public String getBootstrapDN() {
        return bootstrapDN;
    }

    public void setBootstrapDN(String bootstrapDN) {
        this.bootstrapDN = bootstrapDN;
    }

    public X509Certificate getX509Cert() {
        return x509Cert;
    }

    public void setX509Cert(X509Certificate x509Cert)
            throws CertificateEncodingException {
        
        this.x509Cert = x509Cert;
        this.publicX509String =
                certToPEMString(Base64.encode(x509Cert.getEncoded()));
    }

    public KeyPair getKeypair() {
        return keypair;
    }

    public void setKeypair(KeyPair keypair) throws IOException {
        this.keypair = keypair;

        OpenSSLKey k = new BouncyCastleOpenSSLKey(keypair.getPrivate());
        StringWriter writer = new StringWriter();
        k.writeTo(writer);
        this.privateString = writer.toString();
    }


    /**
     * Creates PEM encoded cert string with line length, header and footer.
     *
     * @param base64Data already encoded into string
     * @return string
     */
    public static String certToPEMString(String base64Data) {
        return toStringImpl(base64Data, false);
    }

    private static String toStringImpl(String base64Data, boolean isKey) {

        int length = LINE_LENGTH;
        int offset = 0;

        StringBuffer buf = new StringBuffer();

        if (isKey) {
            buf.append(keyHeader);
        } else {
            buf.append(certHeader);
        }
        buf.append(lineSep);

        int size = base64Data.length();
        while (offset < size) {
            if (LINE_LENGTH > (size - offset)) {
                length = size - offset;
            }
            buf.append(base64Data.substring(offset, offset+length));
            buf.append(lineSep);
            offset = offset + LINE_LENGTH;
        }

        if (isKey) {
            buf.append(keyFooter);
        } else {
            buf.append(certFooter);
        }
        buf.append(lineSep);

        return buf.toString();
    }

}
