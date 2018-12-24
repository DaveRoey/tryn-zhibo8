package com.tryndamere.zhibo8.trynpersistent.custom.utils;

import com.tryndamere.zhibo8.trynpersistent.custom.algorithm.PartitionByMurmurHash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author Dave
 * @Date 2018/12/23
 * @Description
 */
@Component
public class MurmurHashUtils {

    private static int nodeSize;
    private static volatile PartitionByMurmurHash murmurHashInstance = null;

    @Value("${murmur.node}")
    public void setNodeSize(int size) {
        MurmurHashUtils.nodeSize =size;
    }


    public static PartitionByMurmurHash getMurmurHash() {
        if (murmurHashInstance == null) {
            synchronized (MurmurHashUtils.class) {
                if (murmurHashInstance == null) {
                    murmurHashInstance = new PartitionByMurmurHash();
                    murmurHashInstance.setCount(nodeSize);
                    murmurHashInstance.init();
                }
            }
        }
        return murmurHashInstance;
    }
}
