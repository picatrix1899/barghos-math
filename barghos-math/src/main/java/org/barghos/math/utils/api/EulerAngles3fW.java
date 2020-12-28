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

import org.barghos.math.matrix.api.Mat3fR;
import org.barghos.math.matrix.api.Mat4fR;

/**
 * @author picatrix1899
 *
 */
public interface EulerAngles3fW
{
	EulerAngles3fW set(EulerAngles3fR angles);
	EulerAngles3fW set(Tup3fR t);
	EulerAngles3fW set(Mat3fR m);
	EulerAngles3fW set(Mat4fR m);
	
	EulerAngles3fW setDeg(float pitch, float yaw, float roll);
	EulerAngles3fW setPitchDeg(float pitch);
	EulerAngles3fW setYawDeg(float yaw);
	EulerAngles3fW setRollDeg(float roll);
	
	EulerAngles3fW setRad(float pitch, float yaw, float roll);
	EulerAngles3fW setPitchRad(float pitch);
	EulerAngles3fW setYawRad(float yaw);
	EulerAngles3fW setRollRad(float roll);
	
	EulerAngles3fW invert();
	
	<T extends EulerAngles3fW> T invert(T res);

	EulerAngles3fW invertN();
	
	EulerAngles3fW advance(EulerAngles3fR angles);
	EulerAngles3fW advance(Tup3fR t);
	
	EulerAngles3fW advanceDeg(float pitch, float yaw, float roll);
	EulerAngles3fW advancePitchDeg(float pitch);
	EulerAngles3fW advanceYawDeg(float yaw);
	EulerAngles3fW advanceRollDeg(float roll);
	
	EulerAngles3fW advanceRad(float pitch, float yaw, float roll);
	EulerAngles3fW advancePitchRad(float pitch);
	EulerAngles3fW advanceYawRad(float yaw);
	EulerAngles3fW advanceRollRad(float roll);
}
