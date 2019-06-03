package com.itliusir.test.compress;


import com.twitter.hpack.Decoder;
import com.twitter.hpack.Encoder;
import com.twitter.hpack.HeaderListener;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * TODO
 *
 * @author liugang
 * @since 2019/4/10
 */

@Slf4j
public class TestHpack {

    public static void main(String[] args) {
        try {
            int maxHeaderSize = 40960;
            int maxHeaderTableSize = 40960;
            byte[] name = "name".getBytes();
            byte[] value = "value".getBytes();
            boolean sensitive = false;

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            String cookie = "SN_SESSION_ID=1dad570f-e051-4dcb-8c47-6c535e0a7c4a; ___rl__test__cookies=1554893403219; _snvd=15540813453130C4o1jJ51wL; hm_guid=02825e05-262a-4637-9751-31d14cdabf92; OUTFOX_SEARCH_USER_ID_NCOO=1083495928.220588; cityId=9017; districtId=10106; _df_ud=f5585da8-fdd3-4696-8850-9ba2aed16b41; _device_session_id=p_3dea8e49-0bbe-4401-8286-19db5cb8b9a2; smhst=686906768|0000000000a688450414|0000000000a10532544229|0000000000; ; _snmc=1; _snsr=yd-ifeng%7Cxxl5%7C%7C%7C; _snzwt=THjNNb16a06d5bc4curiqd67d; city=1000000; province=10; district=10000001; provinceCode=10; cityCode=010; districtCode=01; streetCode=0100100; SN_CITY=10_010_1000000_9017_01_10106_1_1; authId=si8C042797CAC5A3A21747B4FB8401C915; secureToken=12CDB71499D476260266F52CE39C763E; _snma=1%7C155408134628420221%7C1554081346284%7C1554893394682%7C1554893483111%7C33%7C5; _snmp=155489348192889317; _snmb=155489280046228275%7C1554893483130%7C1554893483115%7C7; _snck=155489348317866412";
            out.write(cookie.getBytes());
            // encode header list into header block
            Encoder encoder = new Encoder(maxHeaderTableSize);
            encoder.encodeHeader(out, name, value, sensitive);

            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());



            HeaderListener listener = new HeaderListener() {
                @Override
                public void addHeader(byte[] name, byte[] value, boolean sensitive) {
                    // handle header field
                }
            };

            // decode header list from header block
            Decoder decoder = new Decoder(maxHeaderSize, maxHeaderTableSize);
            decoder.decode(in, listener);
            decoder.endHeaderBlock();
        } catch (IOException e) {
            // handle exception
        }
    }
}
