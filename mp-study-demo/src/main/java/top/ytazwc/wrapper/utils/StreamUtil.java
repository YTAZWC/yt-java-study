package top.ytazwc.wrapper.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title StreamUtil
 * @date 2024/5/18 9:16
 * @package top.ytazwc.wrapper.utils
 * @description TODO
 */
public class StreamUtil {

    public static byte[] readStream(InputStream stream, int length) throws IOException{
        byte[]streamData=null;
        List<Integer> lengths = new ArrayList<Integer>();
        List<byte[]> buffers = new ArrayList<byte[]>();
        int l = 0;
        int totalLength = 0;
        byte[] buffer = null;
        while (totalLength < length && l != -1) {
            buffer = new byte[length];
            l = stream.read(buffer);
            if (l != -1) {
                lengths.add(new Integer(l));
                buffers.add(buffer);
                totalLength+=l;
            }
        }
        if(totalLength==0) {
            return null;
        }
        l=0;
        streamData = new byte[totalLength];
        length =buffers.size();
        int blength=0;
        byte[] bbuffer=null;
        for (int i = 0; i < length; i++) {
            blength = ((Integer) lengths.get(i)).intValue();
            bbuffer = (byte[]) buffers.get(i);
            System.arraycopy(bbuffer, 0, streamData, l,blength);
            l=l+blength;
        }
        stream=null; lengths=null; buffers=null;	buffer=null;
        return streamData;
    }

    public static byte[] readBytes(BufferedReader bufferedReader, String charset) throws IOException {
        StringBuffer sb = new StringBuffer();
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            sb.append(s);
        }
        if(sb.length() == 0){
            return "".getBytes(charset);
        }
        return sb.toString().getBytes(charset);
    }
}
