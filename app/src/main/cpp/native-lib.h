//
// Created by Shuo.Wang on 2017/5/16.
//




#include <jni.h>


#ifndef FFMPEGANDROID_NATIVE_LIB_H
#define FFMPEGANDROID_NATIVE_LIB_H
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jint JNICALL Java_com_vary_salaryandcash_modules_CameraActivity_initial
        (JNIEnv *, jobject, jint , jint );

JNIEXPORT jint JNICALL Java_com_vary_salaryandcash_modules_CameraActivity_encode
        (JNIEnv *, jobject , jbyteArray );

JNIEXPORT jint JNICALL Java_com_vary_salaryandcash_modules_CameraActivity_flush
        (JNIEnv *, jobject );

JNIEXPORT jint JNICALL Java_com_vary_salaryandcash_modules_CameraActivity_close
        (JNIEnv *, jobject );

#ifdef __cplusplus
}
#endif
#endif