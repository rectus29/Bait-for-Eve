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

import java.lang.Character;
import java.util.*;

public class Dictionary {

	private final String[] words;

	public Dictionary(String[] words) {
		this.words = words;
	}

	public String findMostSimilar(String to) {
		String bestWord = null;
		int bestWeight = 99999;
		for (String word : words) {
			int weight = 0;
			char[] wordChars = word.toCharArray();
			List<String> chars = Arrays.asList(to.split(""));
			for(char wordChar:wordChars){
				if(!chars.contains(new String(wordChar+""))){
					weight++;
					chars.remove(chars.indexOf(new String(wordChar+"")));
				}
			}
			int sizeDiff = Math.abs(chars.size() - wordChars.length);
			weight += sizeDiff;
			if (weight < bestWeight) {
				bestWord = word;
				bestWeight = weight;
			}
			System.out.println(word + " " + weight);
		}
		System.out.println(to + " = " + bestWord);
		return bestWord;
	}
}