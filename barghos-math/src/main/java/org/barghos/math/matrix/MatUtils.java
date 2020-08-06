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

package org.barghos.math.matrix;

public class MatUtils
{
	public static double det2x2( double m0x, double m0y,
								double m1x, double m1y)
	{
		return	m0x * +m1y +
				m0y * -m1x;
	}
	
	public static double det3x3( double m0x, double m0y, double m0z,
								double m1x, double m1y, double m1z,
								double m2x, double m2y, double m2z)
	{
		return	m0x * +det2x2(m1y, m1z, m2y, m2z) +
				m0y * -det2x2(m1x, m1z, m2x, m2z) +
				m0z * +det2x2(m1x, m1y, m2x, m2y);
	}
	
	public static double det4x4(	double m0x, double m0y, double m0z, double m0w,
								double m1x, double m1y, double m1z, double m1w,
								double m2x, double m2y, double m2z, double m2w,
								double m3x, double m3y, double m3z, double m3w)
	{
		return	m0x * +det3x3(m1y, m1z, m1w, m2y, m2z, m2w, m3y, m3z, m3w) +
				m0y * -det3x3(m1x, m1z, m1w, m2x, m2z, m2w, m3x, m3z, m3w) +
				m0z * +det3x3(m1x, m1y, m1w, m2x, m2y, m2w, m3x, m3y, m3w) +
				m0w * -det3x3(m1x, m1y, m1z, m2x, m2y, m2z, m3x, m3y, m3z);
	}
	
	public static double det5x5( double m0x, double m0y, double m0z, double m0w, double m0v,
								double m1x, double m1y, double m1z, double m1w, double m1v,
								double m2x, double m2y, double m2z, double m2w, double m2v,
								double m3x, double m3y, double m3z, double m3w, double m3v,
								double m4x, double m4y, double m4z, double m4w, double m4v)
	{
		return  m0x * +det4x4(m1y, m1z, m1w, m1v, m2y, m2z, m2w, m2v, m3y, m3z, m3w, m3v, m4y, m4z, m4w, m4v) +
				m0y * -det4x4(m1x, m1z, m1w, m1v, m2x, m2z, m2w, m2v, m3x, m3z, m3w, m3v, m4x, m4z, m4w, m4v) +
				m0z * +det4x4(m1x, m1y, m1w, m1v, m2x, m2y, m2w, m2v, m3x, m3y, m3w, m3v, m4x, m4y, m4w, m4v) + 
				m0w * -det4x4(m1x, m1y, m1z, m1v, m2x, m2y, m2z, m2v, m3x, m3y, m3z, m3v, m4x, m4y, m4z, m4v) +
				m0v * +det4x4(m1x, m1y, m1z, m1w, m2x, m2y, m2z, m2w, m3x, m3y, m3z, m3w, m4x, m4y, m4z, m4w);
	}
}
