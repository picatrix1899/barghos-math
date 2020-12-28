/*
MIT License

Copyright (c) 2019 picatrix1899

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package org.barghos.math.utils.api;

import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fW;

import org.barghos.math.matrix.Mat4f;
import org.barghos.math.point.Point3f;
import org.barghos.math.vec3.Vec3f;

/**
 * @author picatrix1899
 *
 */
public interface Transform3f extends Transformable3f
{
	Point3f getPosition();
	<T extends Tup3fW> T getPosition(T res);
	
	EulerAngles3fR getOrientation();
	<T extends EulerAngles3fW> T getOrientation(T res);
	
	Vec3f getScale();
	<T extends Tup3fW> T getScale(T res);
	
	Transform3f set(Transform3f t);
	
	Transform3f set(Tup3fR position, EulerAngles3fR orientation, Tup3fR scale);
	Transform3f set(float posX, float posY, float posZ, EulerAngles3fR orientation, Tup3fR scale);
	Transform3f set(Tup3fR position, float pitch, float yaw, float roll, Tup3fR scale);
	Transform3f set(float posX, float posY, float posZ, float pitch, float yaw, float roll, Tup3fR scale);
	Transform3f set(Tup3fR position, EulerAngles3fR orientation, float scale);
	Transform3f set(float posX, float posY, float posZ, EulerAngles3fR orientation, float scale);
	Transform3f set(Tup3fR position, float pitch, float yaw, float roll, float scale);
	Transform3f set(float posX, float posY, float posZ, float pitch, float yaw, float roll, float scale);
	Transform3f set(Tup3fR position, EulerAngles3fR orientation, float scaleX, float scaleY, float scaleZ);
	Transform3f set(float posX, float posY, float posZ, EulerAngles3fR orientation, float scaleX, float scaleY, float scaleZ);
	Transform3f set(Tup3fR position, float pitch, float yaw, float roll,  float scaleX, float scaleY, float scaleZ);
	Transform3f set(float posX, float posY, float posZ, float pitch, float yaw, float roll,  float scaleX, float scaleY, float scaleZ);
	
	Transform3f setPosition(Tup3fR pos);
	Transform3f setPosition(float x, float y, float z);
	
	Transform3f setPosX(float x);
	Transform3f setPosY(float y);
	Transform3f setPosZ(float z);
	
	Transform3f setOrientation(Tup3fR orientation);
	Transform3f setOrientation(float pitch, float yaw, float roll);
	
	Transform3f setPitch(float pitch);
	Transform3f setYaw(float pitch);
	Transform3f setRoll(float pitch);
	
	Transform3f setScale(Tup3fR scale);
	Transform3f setScale(float factor);
	Transform3f setScale(float x, float y, float z);
	
	Transform3f setScaleX(float x);
	Transform3f setScaleY(float y);
	Transform3f setScaleZ(float z);
	
	Mat4f getTranslationMatrix4f();
	Mat4f getTranslationMatrix4f(Mat4f res);
	Mat4f getOrientationMatrix4f();
	Mat4f getOrientationMatrix4f(Mat4f res);
	Mat4f getScalingMatrix4f();
	Mat4f getScalingMatrix4f(Mat4f res);
	
	Mat4f getTransformationMatrix4f();
	Mat4f getTransformationMatrix4f(Mat4f res);
}
