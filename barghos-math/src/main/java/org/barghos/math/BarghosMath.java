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

package org.barghos.math;

import org.barghos.math.utils.Maths;
import org.barghos.math.utils.api.EulerRotationOrder3;

/**
 * @author picatrix1899
 *
 */
public class BarghosMath
{
	public static final boolean BUILD_FLAG__PARAMETER_CHECKS = false;

	public static final EulerRotationOrder3 DEFAULT_EULER_ROTATION_ORDER = EulerRotationOrder3.ROLL_YAW_PITCH;
	public static float DEFAULT_ZERO_THRESHOLD_F = Maths.SMALL_NUMBER_E6f;
	public static double DEFAULT_ZERO_THRESHOLD = Maths.SMALL_NUMBER_E6;
}
