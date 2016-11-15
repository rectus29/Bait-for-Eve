package com.rectuscorp.evetool.dictionnary;/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 10/11/2016 15:50              */
/*                 All right reserved                  */
/*                  Class Dictionary                      */
/*-----------------------------------------------------*/

		import java.util.Arrays;
		import java.util.List;
		import org.junit.Test;
		import static org.junit.Assert.assertEquals;

public class DictionaryTest {

		@Test
		public void testBerries() {
			Dictionary dictionary = new Dictionary(new String[]{"cherry", "pineapple", "melon", "strawberry", "raspberry"});
			assertEquals("strawberry", dictionary.findMostSimilar("strawbery"));
			assertEquals("cherry", dictionary.findMostSimilar("berry"));
		}

		@Test
		public void testLanguages() {
			Dictionary dictionary = new Dictionary(new String[]{"javascript", "java", "ruby", "php", "python", "coffeescript"});
			assertEquals("java", dictionary.findMostSimilar("heaven"));
			assertEquals("javascript", dictionary.findMostSimilar("javascript"));
		}

		@Test
		public void test() {
			Dictionary dictionary = new Dictionary(new String[]{
					"psaysnhfrrqgxwik",
					"pdyjrkaylryr",
					"lnjhrzfrosinb",
					"afirbipbmkamjzw",
					"loogviwcojxgvi",
					"iqkyztorburjgiudi",
					"cwhyyzaorpvtnlfr",
					"iroezmixmberfr",
					"jhjyasikwyufr",
					"tklquxrnhfiggb",
					"cpnqknjyviusknmte",
					"hwzsemiqxjwfk",
					"ntwmwwmicnjvhtt",
					"emvquxrvvlvwvsi",
					"sefsknopiffajor",
					"znystgvifufptxr",
					"xuwahveztwoor",
					"hrwuhmtxxvmygb",
					"karpscdigdvucfr",
					"xrgdgqfrldwk",
					"nnsoamjkrzgldi",
					"ljxzjjorwgb",
					"cfvruditwcxr",
					"eglanhfredaykxr",
					"fxjskybblljqr",
					"qifwqgdsijibor",
					"xikoctmrhpvi",
					"ajacizfrgxfumzpvi",
					"mhmkakybpczjbb",
					"vkholxrvjwisrk",
					"npyrgrpbdfqhhncdi",
					"pxyousorusjxxbt",
					"jcocndjkyb",
					"fxpvfhfrujjaifr",
					"hkldhadcxrjbmkmcdi",
					"hirldidcuzbyb",
					"ggcvrtxrtnafw",
					"tdvibqccxr",
					"osbednerciaai",
					"qojfrlhufr",
					"kqijoorfkejdcxr",
					"zqdrhpviqslik",
					"clxmqmiycvidiyr",
					"xffrkbdyjveb",
					"dyhxgviphoptak",
					"dihhiczkdwiofpr",
					"riyhpvimgaliuxr",
					"fgtrjakzlnaebxr",
					"ppctybxgtleipb",
					"ucxmdeudiycokfnb"
			});
			assertEquals("zqdrhpviqslik", dictionary.findMostSimilar("rkacypviuburk"));
		}

	}