import java.util.Random;
import java.util.Scanner;

public class Main {
	static char[] text;
	static int[] cryptoText;
	static int[] decrypt;
	static char[] iu;
	static int n, e = 0, d = 0,  e1 = 0, d1 = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try (Scanner scanner = new Scanner(System.in)) {
			Random random = new Random();
			int p =479;
			int q = 97;
			
			
			if(simple(p) && simple(q)) {
				System.out.println("Число простое");
				System.out.println(p);
				System.out.println(q);

			}else {
				System.exit(0);
			}
			//Вычисляется произведение 
			 n = p * q;
			//Вычислить функцию Эйлера φ (n) = (p-1) (q-1)
			int pq = (p -1) * (q -1);

			//Определяем закрытую экспоненту и проверка его на взаимопростоту
			//с числом ((p_simple-1)*(q_simple-1)).
			while (d1 != 1)
			{
				d = random.nextInt(1000);
				d1 = CommonDivisor(d, pq);
			}

			//Выбираем открытую экспоненту
			while (e1 != 1)
			{
				e += 1;
				e1 = (e*d) % (pq);
			}

			System.out.println(e + " " + n + " " + "Open key");
			System.out.println(d + " " + n + " " + "Secret key");

			String textt;
			System.out.println("Ввод текста");
			textt = scanner.nextLine();
			
			text = textt.toCharArray();
			int l = text.length;
			cryptoText = new int[l];
			decrypt = new int[l];
			
		
			sifr();
			desifr();
			
			iu = new char[text.length];
			
			for(int i = 0; i < text.length; i++) {
				iu[i] = (char) decrypt[i];
			}
			
			print();
		}
	}
	
	public static boolean simple(int m) {
	
		boolean b = true;
		
	    for (int i=2; i<=m/2; i++) {
	          int temp = m % i;
	          if (temp == 0) {
	              b = false;
	              break;
	    }	
	 }
		return b;
	}
	public static int CommonDivisor(int number1, int number2) {
        while (number1 > 0 && number2 > 0) {
            if ((number2 % number1) == 0) {
                return number1;
            }
 
            number2 %= number1;
 
            if ((number1 % number2) == 0) {
                return number2;
            }
 
            number1 %= number2;
        }
 
        if (number1 == 0) {
            return number2;
        }
 
        return number1;
    }
	
	public static void sifr() {
		for (int i = 0; i < text.length; i++){
			
			int c = 1;
			int a = 0;
			int ASCII = (text[i]);

			while (a<e)
			{
				c = c * ASCII;
				c = c % n;
				a++;
			}
			cryptoText[i] = c;
		}
	}
	public static void desifr() {
		for (int i = 0; i < text.length; i++){
			
			int m = 1;
			int a = 0;
			while (a<d)
			{
				m = m * cryptoText[i];
				m = m % n;
				a++;
			}
			decrypt[i] = m;
		}
	}
	
	public static void print() {
		for (int i = 0; i < text.length; i++){
			
			System.out.println(text[i] + " " + Integer.valueOf(text[i]) + 
					" " + cryptoText[i] + " " + decrypt[i] + " " + iu[i]);
		}
	}
}
