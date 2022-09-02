#include <jni.h>
#include <string>
#include <stdio.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_myapplication_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    //std::string hello = "Hello from C++";
    //return env->NewStringUTF(hello.c_str());
    // Construct a String
    jstring jstr = env->NewStringUTF("This string comes from JNI");
    // First get the class that contains the method you need to call
    jclass clazz = env->FindClass("com/example/myapplication/MainActivity");
    // Get the method that you want to call
    jmethodID messageMe = env->GetMethodID(clazz, "messageMe", "(Ljava/lang/String;)V");
    // Call the method on the object
    env->CallVoidMethod(clazz, messageMe, jstr);
    // Get a C-style string
    //const char* str = env->GetStringUTFChars((jstring) result, NULL);
    //printf("%s\n", str);
    // Clean up
   // env->ReleaseStringUTFChars(jstr, str);

//    // Shutdown the VM.
//    vm->DestroyJavaVM();

    return env->NewStringUTF("Hello from JNI!");
}