package hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Assign5 {
	
	
	public static String [] getInput(String file){
		String [] res = new String[11344];
		
		String temp;
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			int i = 0;
			while((temp = in.readLine()) != null){
				res[i++] = temp;
			}
			in.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		int [] primes = {5481877, 5481893, 5481907, 5481913, 5481961, 548196, 5481989, 5482019, 
				   5482039, 5482049, 5482063, 5482079, 5482093, 5482097, 5482111, 5482121, 
				   5482129, 5482153, 5482163, 5482181, 5482193, 5482207, 5482223, 5482237, 
				   5482249, 5482261, 5482291, 5482297, 5482331, 5482333, 5482361, 5482369, 
				   5482391, 5482403, 5482423, 5482427, 5482429, 5482447, 5482453, 5482459, 
				   5482471, 5482493, 5482523, 5482531, 548254, 5482553, 5482571, 5482627, 
				   5482637, 5482657, 5482663, 5482669, 5482699, 5482703, 5482733, 5482747, 
				   5482781, 5482783, 5482817, 5482843, 5482847, 5482853, 5482859, 5482907, 
				   5482913, 5482921, 5482927, 5482969, 5482979, 5483017, 5483021, 5483041, 
				   5483069, 5483081, 5483087, 5483089, 5483147, 5483173, 5483183, 5483209, 
				   5483227, 5483243, 5483251, 5483273, 5483281, 5483293, 5483297, 5483321, 
				   5483339, 5483341, 5483371, 5483389, 5483393, 5483399, 5483417, 5483431, 
				   5483441, 5483449, 5483461, 5483477, 5483501, 5483509, 5483521, 5483531,
				   30577,  30593,  30631,  30637,  30643 , 30649 , 30661  ,30671 , 30677 , 30689,
				   30697 , 30703 , 30707 , 30713 , 30727 , 30757  ,30763 , 30773 , 30781  ,30803,
				   39979 , 39983,  39989,  40009,  40013,  40031,  40037,  40039,  40063,  40087,
				   44281,  44293,  44351,  44357,  44371,  44381,  44383,  44389,  44417,  44449,
				   44753, 44771  ,44773 , 44777, 44789,  44797,  44809 , 44819,  44839,  44843};
		double maxef = 0;
		int maxefseed = 0;
		int maxefprime = 0;
		String [] s = getInput("inputA5.txt");
		for (int c = 0; c < primes.length; c++){
			for (int j = 2; j < 10000; j++){
				System.out.println("\n\nStats for seed: " + j + " with prime: " + primes[c] + "\n");
				
				StringHashTable t = new StringHashTable(16193);
				t.setPrime(primes[c]);
				t.setSeed(j);
				for (int i = 0; i < 11344 ; i++){
					t.add(s[i]);
				}
				double ef = 16193 - t.getCollisions();
				ef = ef / 16193.0;
				ef = ef * 100;
				double load = (11344.0/16193.0) * 100;
				//System.out.println("Collisions: " + t.getCollisions());
				//System.out.println("Load factor: " + t.loadFactor()*100);
				//System.out.println("Initial efficiency: " + ef);
				double eff = t.getEfficiency();
				System.out.println("Hashing efficiency: " + eff);
				//System.out.println("Max reads: " + t.getMaxReads());
				if (eff > maxef) {
					maxef = eff;
					maxefseed = j;
					maxefprime = primes[c];
				}
				System.out.println("Current best: " + maxef);
			}
		}
		System.out.println("Max efficiency: " + maxef + " for seed: " + maxefseed + " with prime: " + maxefprime);
		
		// current best: 35.73146392527241 for seed: 3592 with prime: 5483147
	}

}
