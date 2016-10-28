package com.rectuscorp.evetool.bagel;
/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 25/10/2016 14:37              */
/*                 All right reserved                  */
/*                  Class BagelTest                      */
/*-----------------------------------------------------*/

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;

public class BagelTest {

	@Test
	public void testBagel() {
		Bagel bagel = BagelSolver.getBagel();
		assertEquals(
				bagel.getValue() == 4,
				Boolean.TRUE
		);
	}

	@Test
	public void tete(){

		assertEquals(nbYear(1500000 , 0.0, 10000, 2000000), 50);
	}

	public static int nbYear(int p0, double percent, int aug, int p) {
		System.out.println(p);
		int nbYear = 0;
		while( p0 < p){
			p0 += p0 * (percent /100);
			p0 += aug;
			nbYear++;
		}
		return nbYear;
	}

}
