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

/**
 * This interface describes a rounding behavior.
 * This is often necessary when calculating with integers and long values.
 * 
 * @author picatrix1899
 * 
 * @since 1.0
 */
public interface RoundingBehavior
{
	/**
	 * Rounds a double value acording to the defined behavior and returns the result as an integer value.
	 * 
	 * @param value The value to round.
	 * 
	 * @return The rounded value as an integer value.
	 * 
	 * @since 1.0
	 */
	public int roundi(double value);
	
	/**
	 * Rounds a double value acording to the defined behavior and returns the result as a long value.
	 * 
	 * @param value The value to round.
	 * 
	 * @return The rounded value as a long value.
	 * 
	 * @since 1.0
	 */
	public long roundl(double value);
	
	/**
	 * Rounds a double value acording to the defined behavior and returns the result as a float value.
	 * 
	 * @param value The value to round.
	 * 
	 * @return The rounded value as a float value.
	 * 
	 * @since 1.0
	 */
	public float roundf(double value);
	
	/**
	 * Rounds a double value acording to the defined behavior and returns the result as a double value.
	 * 
	 * @param value The value to round.
	 * 
	 * @return The rounded value as a double value.
	 * 
	 * @since 1.0
	 */
	public double roundd(double value);
	
	/**
	 * Rounds a float value acording to the defined behavior and returns the result as an integer value.
	 * 
	 * @param value The value to round.
	 * 
	 * @return The rounded value as an integer value.
	 * 
	 * @since 1.0
	 */
	public int roundi(float value);
	
	/**
	 * Rounds a float value acording to the defined behavior and returns the result as a long value.
	 * 
	 * @param value The value to round.
	 * 
	 * @return The rounded value as a long value.
	 * 
	 * @since 1.0
	 */
	public long roundl(float value);
	
	/**
	 * Rounds a float value acording to the defined behavior and returns the result as a float value.
	 * 
	 * @param value The value to round.
	 * 
	 * @return The rounded value as a float value.
	 * 
	 * @since 1.0
	 */
	public float roundf(float value);
	
	/**
	 * Rounds a float value acording to the defined behavior and returns the result as a double value.
	 * 
	 * @param value The value to round.
	 * 
	 * @return The rounded value as a double value.
	 * 
	 * @since 1.0
	 */
	public double roundd(float value);
}
