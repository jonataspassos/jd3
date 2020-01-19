package function;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

public class JD3String {

	public static void main(String args[]) {
		String s = "abcdefg";
		System.out.println(width(s));
		System.out.println(filter(s, 50));
	}

	private static final CharSize[] chars = { new CharSize('A', 12.81f), new CharSize('B', 10.69f),
			new CharSize('C', 10.69f), new CharSize('D', 11.56f), new CharSize('E', 10.41f), new CharSize('F', 08.91f),
			new CharSize('G', 12.02f), new CharSize('H', 12.02f), new CharSize('I', 05.61f), new CharSize('J', 06.42f),
			new CharSize('K', 12.02f), new CharSize('L', 10.41f), new CharSize('M', 14.42f), new CharSize('N', 12.81f),
			new CharSize('O', 11.56f), new CharSize('P', 08.91f), new CharSize('Q', 11.56f), new CharSize('R', 11.22f),
			new CharSize('S', 08.91f), new CharSize('T', 09.78f), new CharSize('U', 12.81f), new CharSize('V', 12.81f),
			new CharSize('W', 15.22f), new CharSize('X', 12.81f), new CharSize('Y', 12.81f), new CharSize('Z', 10.58f),
			new CharSize('a', 08.02f), new CharSize('b', 08.81f), new CharSize('c', 07.22f), new CharSize('d', 08.81f),
			new CharSize('e', 07.22f), new CharSize('f', 07.22f), new CharSize('g', 08.00f), new CharSize('h', 09.63f),
			new CharSize('i', 04.81f), new CharSize('j', 06.06f), new CharSize('k', 09.63f), new CharSize('l', 04.81f),
			new CharSize('m', 13.61f), new CharSize('n', 09.63f), new CharSize('o', 08.00f), new CharSize('p', 08.81f),
			new CharSize('q', 08.81f), new CharSize('r', 07.22f), new CharSize('s', 06.42f), new CharSize('t', 05.61f),
			new CharSize('u', 09.63f), new CharSize('v', 09.63f), new CharSize('w', 12.81f), new CharSize('x', 08.81f),
			new CharSize('y', 09.63f), new CharSize('z', 07.22f), new CharSize('_', 09.63f), new CharSize('0', 08.00f),
			new CharSize('1', 08.00f), new CharSize('2', 08.00f), new CharSize('3', 08.00f), new CharSize('4', 08.00f),
			new CharSize('5', 08.00f), new CharSize('6', 08.00f), new CharSize('7', 08.00f), new CharSize('8', 08.00f),
			new CharSize('9', 08.00f) };
	private static final int lUinit = 0, lDinit = 26, und = 27, ninit = 28;
	private static final float point3 = 12.0f, between = 0.5f;

	public static float width(String s) {
		float ret = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
				ret += chars[s.charAt(i) - 'A' + lUinit].size;
			if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
				ret += chars[s.charAt(i) - 'a' + lDinit].size;
			if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
				ret += chars[s.charAt(i) - '0' + ninit].size;
			if (s.charAt(i) == '_')
				ret += chars[und].size;
			ret += between;
		}
		return ret;
	}

	public static String filter(String s, float w) {
		if (width(s) <= w)
			return s;

		while (width(s) + between + point3 > w) {
			s = s.substring(0, s.length() - 1);
		}
		return s + "...";
	}

	public static Comparator<String> comparator() {
		return new Comparator<String>() {
			@Override
			public int compare(String arg0, String arg1) {
				float w0 = JD3String.width(arg0);
				float w1 = JD3String.width(arg1);
				return w0 > w1 ? 1 : (w0 < w1 ? -1 : 0);
			}
		};
	}

	public static int greatWidth(ArrayList<String> l) {
		if (l.size() == 0)
			return -1;
		int max = 0;
		Comparator<String> c = comparator();
		for (int i = 1; i < l.size(); i++) {
			int d = c.compare(l.get(max), l.get(i));
			max = d == 1 ? max : i;
		}
		return max;
	}

	public static void sortWidth(ArrayList<String> l) {
		l.sort(comparator());
	}

	public static void fileOut(String path, String content) {
		try {
			File file = new File(path);

			try {
				if (file.createNewFile()) {
					// System.out.println("File create");
				} else {
					// System.out.println("File already exists!");
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

			// Java 7
			Files.write(Paths.get(path), content.getBytes());

			// encoding
			// Files.write(Paths.get(path), content.getBytes(StandardCharsets.UTF_8));

			// extra options
			// Files.write(Paths.get(path), content.getBytes(),
			// StandardOpenOption.CREATE, StandardOpenOption.APPEND);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String fileIn(String path) {
		String content = "";
	    try
	    {
	        content = new String ( Files.readAllBytes( Paths.get(path) ) );
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	        return "";
	    }
	    return content;
	}
}

class CharSize {
	public final char c;
	public final float size;

	public CharSize(char c, float size) {
		super();
		this.c = c;
		this.size = size;
	}

}