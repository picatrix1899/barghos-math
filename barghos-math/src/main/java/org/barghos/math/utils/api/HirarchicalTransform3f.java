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
public interface HirarchicalTransform3f extends Transform3f
{
	Point3f getRelativePosition();
	<T extends Tup3fW> T getRelativePosition(T res);
	
	EulerAngles3fR getRelativeOrientation();
	<T extends EulerAngles3fW> T getRelativeOrientation(T res);
	
	Vec3f getRelativeScale();
	<T extends Tup3fW> T getRelativeScale(T res);
	
	HirarchicalTransform3f set(Transform3f t);
	HirarchicalTransform3f set(HirarchicalTransform3f t);
	
	HirarchicalTransform3f set(Tup3fR position, EulerAngles3fR orientation, Tup3fR scale);
	HirarchicalTransform3f set(float posX, float posY, float posZ, EulerAngles3fR orientation, Tup3fR scale);
	HirarchicalTransform3f set(Tup3fR position, float pitch, float yaw, float roll, Tup3fR scale);
	HirarchicalTransform3f set(float posX, float posY, float posZ, float pitch, float yaw, float roll, Tup3fR scale);
	HirarchicalTransform3f set(Tup3fR position, EulerAngles3fR orientation, float scale);
	HirarchicalTransform3f set(float posX, float posY, float posZ, EulerAngles3fR orientation, float scale);
	HirarchicalTransform3f set(Tup3fR position, float pitch, float yaw, float roll, float scale);
	HirarchicalTransform3f set(float posX, float posY, float posZ, float pitch, float yaw, float roll, float scale);
	HirarchicalTransform3f set(Tup3fR position, EulerAngles3fR orientation, float scaleX, float scaleY, float scaleZ);
	HirarchicalTransform3f set(float posX, float posY, float posZ, EulerAngles3fR orientation, float scaleX, float scaleY, float scaleZ);
	HirarchicalTransform3f set(Tup3fR position, float pitch, float yaw, float roll,  float scaleX, float scaleY, float scaleZ);
	HirarchicalTransform3f set(float posX, float posY, float posZ, float pitch, float yaw, float roll,  float scaleX, float scaleY, float scaleZ);
	
	HirarchicalTransform3f setPosition(Tup3fR pos);
	HirarchicalTransform3f setPosition(float x, float y, float z);
	
	HirarchicalTransform3f setPosX(float x);
	HirarchicalTransform3f setPosY(float y);
	HirarchicalTransform3f setPosZ(float z);
	
	HirarchicalTransform3f setOrientation(Tup3fR orientation);
	HirarchicalTransform3f setOrientation(float pitch, float yaw, float roll);
	
	HirarchicalTransform3f setPitch(float pitch);
	HirarchicalTransform3f setYaw(float pitch);
	HirarchicalTransform3f setRoll(float pitch);
	
	HirarchicalTransform3f setScale(Tup3fR scale);
	HirarchicalTransform3f setScale(float factor);
	HirarchicalTransform3f setScale(float x, float y, float z);
	
	HirarchicalTransform3f setScaleX(float x);
	HirarchicalTransform3f setScaleY(float y);
	HirarchicalTransform3f setScaleZ(float z);
	
	Mat4f getRelativeTranslationMatrix4f();
	Mat4f getRelativeTranslationMatrix4f(Mat4f res);
	Mat4f getRelativeOrientationMatrix4f();
	Mat4f getRelativeOrientationMatrix4f(Mat4f res);
	Mat4f getRelativeScalingMatrix4f();
	Mat4f getRelativeScalingMatrix4f(Mat4f res);
	
	Mat4f getRelativeTransformationMatrix4f();
	Mat4f getRelativeTransformationMatrix4f(Mat4f res);
	
	HirarchicalTransform3f setParent(Transformable3f parent);
	Transformable3f getParent();
	boolean hasParent();
	
	HirarchicalTransform3f getTransform();
}
