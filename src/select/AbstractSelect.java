package select;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class AbstractSelect {
	protected abstract void setD(Object d);
	protected abstract void setI(int i);
	protected abstract void setDI(Object d, int i);
	public abstract AbstractSelect select(String identifier);
	public abstract MultSelect selectAll(String identifier);
	public abstract AbstractSelect select(F f) throws Exception;
	public abstract MultSelect selectAll(F f) throws Exception;
	public abstract AbstractSelect attr(String key, F f) throws Exception;
	public abstract AbstractSelect attr(String key, String value);
	public abstract AbstractSelect attr(String key, int value);
	public abstract AbstractSelect attr(String key, float value);
	public abstract AbstractSelect attr(String key, double value);
	public abstract AbstractSelect innerText(String text);
	public abstract AbstractSelect innerText(F f) throws Exception;
	public abstract String innerText();
	public abstract String attr(String key);
	protected abstract AbstractSelect append(Tag element);
	public abstract AbstractSelect append(String element);
	public abstract AbstractSelect append(F f) throws Exception;
	protected abstract AbstractSelect preppend(Tag element);
	public abstract AbstractSelect preppend(String element);
	public abstract AbstractSelect preppend(F f) throws Exception;
	
	public abstract void remove();
	public abstract boolean isEmpty();
	protected abstract Tag thisParent();
	
	public AbstractSelect fileOut(String path) {
		String content = "" + this;
		

		try {
			File file = new File(path);

			try {
				if (file.createNewFile()) {
					//System.out.println("File create");
				} else {
					//System.out.println("File already exists!");
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
		return this;
	}
	
}
