/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_placelab_spotter_WiFiSpotter */

#ifndef _Included_org_placelab_spotter_WiFiSpotter
#define _Included_org_placelab_spotter_WiFiSpotter
#ifdef __cplusplus
extern "C" {
#endif
/* Inaccessible static: spotterAvailable */
/* Inaccessible static: spotterInitialized */
/* Inaccessible static: numInitialized */
/*
 * Class:     org_placelab_spotter_WiFiSpotter
 * Method:    spotter_init
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_placelab_spotter_WiFiSpotter_spotter_1init
  (JNIEnv *, jobject);

/*
 * Class:     org_placelab_spotter_WiFiSpotter
 * Method:    spotter_shutdown
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_placelab_spotter_WiFiSpotter_spotter_1shutdown
  (JNIEnv *, jobject);

/*
 * Class:     org_placelab_spotter_WiFiSpotter
 * Method:    spotter_poll
 * Signature: ()[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_org_placelab_spotter_WiFiSpotter_spotter_1poll
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif