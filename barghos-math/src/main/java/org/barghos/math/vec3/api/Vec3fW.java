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

package org.barghos.math.vec3.api;

import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.core.util.Nullable;
import org.barghos.math.vec3.Vec3f;

/**
 * @author picatrix1899
 *
 */
public interface Vec3fW extends Tup3fW
{

	<T extends Vec3fW> T add(Tup3fR t);
	<T extends Vec3fW> T add(float scalar);
	<T extends Vec3fW> T add(float x, float y, float z);
	
	<T extends Tup3fW> T add(Tup3fR t, T res);
	<T extends Tup3fW> T add(float scalar, T res);
	<T extends Tup3fW> T add(float x, float y, float z, T res);
	
	<T extends Vec3fW> T addN(Tup3fR t);
	<T extends Vec3fW> T addN(float scalar);
	<T extends Vec3fW> T addN(float x, float y, float z);
	
	<T extends Vec3fW> T sub(Tup3fR t);
	<T extends Vec3fW> T sub(float scalar);
	<T extends Vec3fW> T sub(float x, float y, float z);
	
	<T extends Tup3fW> T sub(Tup3fR t, T res);
	<T extends Tup3fW> T sub(float scalar, T res);
	<T extends Tup3fW> T sub(float x, float y, float z, T res);
	
	<T extends Vec3fW> T subN(Tup3fR t);
	<T extends Vec3fW> T subN(float scalar);
	<T extends Vec3fW> T subN(float x, float y, float z);
	
	<T extends Vec3fW> T mul(Tup3fR t);
	<T extends Vec3fW> T mul(float scalar);
	<T extends Vec3fW> T mul(float x, float y, float z);
	
	<T extends Tup3fW> T mul(Tup3fR t, T res);
	<T extends Tup3fW> T mul(float scalar, T res);
	<T extends Tup3fW> T mul(float x, float y, float z, T res);
	
	<T extends Vec3fW> T mulN(Tup3fR t);
	<T extends Vec3fW> T mulN(float scalar);
	<T extends Vec3fW> T mulN(float x, float y, float z);
	
	<T extends Vec3fW> T div(Tup3fR t);
	<T extends Vec3fW> T div(float scalar);
	<T extends Vec3fW> T div(float x, float y, float z);
	
	<T extends Tup3fW> T div(Tup3fR t, T res);
	<T extends Tup3fW> T div(float scalar, T res);
	<T extends Tup3fW> T div(float x, float y, float z, T res);
	
	<T extends Vec3fW> T divN(Tup3fR t);
	<T extends Vec3fW> T divN(float scalar);
	<T extends Vec3fW> T divN(float x, float y, float z);
	
	float length();
	
	float lengthSafe();
	float lengthSafe(float tolerance);
	
	float reciprocalLength();
	
	float reciprocalLengthSafe();
	float reciprocalLengthSafe(float tolerance);
	
	float squaredLength();
	
	Vec3f normal();
	
	Vec3f normal(@Nullable Vec3f res);
	
	Vec3f normalSafe();
	Vec3f normalSafe(float tolerance);
	
	Vec3f normalSafe(@Nullable Vec3f res);
	Vec3f normalSafe(float tolerance, @Nullable Vec3f res);
}
