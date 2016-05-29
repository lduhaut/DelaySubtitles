package fr.ldu.vid.sub.delay;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe écrite pour pouvoir rediffuser une vidéo, pour laquelle les sous-titres étaient (beaucoup) décalés.
 * + besoin de découper la vidéo en plusieurs extraits à diffuser séparément.
 * 
 * @author Loïc
 *
 */
public class DelaySubs {

	public static void main(String[] args) {
		// 14:50 --> 2:28
		// int delayInSec = 60+60+28 - (14*60 + 50);

		// 01 14:50 --> 0:13
//		int delayInSec = 13 - (14 * 60 + 50);
		
		// 02 28:41 --> 0:08
//		int delayInSec = 8 - (28 * 60 + 41);
		
		// 03 42:21 --> 0:11
//		int delayInSec = 11 - (42 * 60 + 21);
		
		// 04 01:04:26 --> 0:08
//		int delayInSec = 8 - (3600 + 4 * 60 + 26);
		
		// 05 1:21:00 --> 0:07
//		int delayInSec = 7 - (3600 + 21 * 60 + 0);
		
		// 06 01:31:37 --> 0:02 
//		int delayInSec = 2 - (3600 + 31 * 60 + 37);
		
		// 07 01:38:42 --> 0:08 
//		int delayInSec = 8 - (3600 + 38 * 60 + 42);
		
		// 08 01:46:41 --> 0:02
//		int delayInSec = 2 - (3600 + 46 * 60 + 41);
		
		// 09 01:52:50 --> 0:08
//		int delayInSec = 8 - (3600 + 52 * 60 + 50);
		
		// 10 01:59:40 --> 0:02
		int delayInSec = 0 - (3600 + 59 * 60 + 40);
		
		
		File fileSub = new File("C:\\Users\\Me\\Desktop\\sous-titre-original.srt");

		if (fileSub.exists()) {

			int subId = 0;
			int idWritten = 1;

			boolean started = false;

			try (Scanner sc = new Scanner(fileSub, "ISO-8859-1")) {

				while (sc.hasNextLine()) {
					String line = sc.nextLine();

					if (line.trim().equals(Integer.toString(subId + 1))) {
						String temps = sc.nextLine();
						String[] tempsArr = temps.replace(" --> ", ":").split(":");
						++subId;

						String debut = delay(tempsArr[0], tempsArr[1], tempsArr[2], delayInSec);
						// Suppression des sous-titres qui commencent avant le début de la partie à extraire
						if (null != debut) {
							started = true;

							// Renumérotation de chaque sous-titre si décalage.
							System.out.println(idWritten++);
							System.out.println(
									debut + " --> " + delay(tempsArr[3], tempsArr[4], tempsArr[5], delayInSec));
						}

					} else {
						if (started)
							System.out.println(line);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private static String delay(String hS, String minS, String secAndMsS, int delayInSec) {
		String[] sEtMs = secAndMsS.split(",");
		int sec = Integer.parseInt(sEtMs[0]);
		int min = Integer.parseInt(minS);
		int h = Integer.parseInt(hS);
		int nbSec = 3600 * h + 60 * min + sec + delayInSec;

		if (nbSec < 0) {
			return null;
		}

		h = nbSec / 3600;
		min = (nbSec - (3600 * h)) / 60;
		sec = (nbSec - (3600 * h) - (60 * min));

		return (h > 9 ? h : "0" + h) + ":" + (min > 9 ? min : "0" + min) + ":" + (sec > 9 ? sec : "0" + sec)
				+ (sEtMs.length == 1 ? "" : ("," + sEtMs[1]));
	}

}
