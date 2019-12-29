package select;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Tag {
	// --------------------Atributos----------------------------//
	private String name;
	private ArrayList<Row> attrList = new ArrayList<Row>();
	private ArrayList<Tag> innerList = new ArrayList<Tag>();
	private String innerText = "";
	private Tag parent = null;
	private ArrayList<StyleAttr> styleList = new ArrayList<StyleAttr>();

	// --------------------Construtores-------------------------//
	public Tag(String name, Tag parent) {
		super();
		this.name = name;
		this.parent = parent;
	}

	// --------------------GET & SET----------------------------//
	
	
	
	public String getName() {
		return name;
	}

	protected String getInnerText() {
		return innerText;
	}

	protected void setInnerText(String innerText) {
		this.innerText = innerText;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected Tag getParent() {
		return parent;
	}

	// --------------------Metodos------------------------------//

	// Selects
	public Select select(String identifier) {
		return new Select(this.select(identifier, true));
	}

	protected Tag select(String identifier, boolean pro) {
		boolean name = identifier.charAt(0) != '.' && identifier.charAt(0) != '#';
		String attr = name ? "none"
				: (identifier.charAt(0) == '.' ? "class" : (identifier.charAt(0) == '#') ? "id" : "none");

		// Busca no nó
		if (name && this.getName().equals(identifier)) {
			return this;
		} else if (attr.equals("id") 
				&& this.attr("id")!=null &&
				this.attr("id")
					.equals(identifier.substring(1))) {
			return this;
		} else if (attr.equals("class")) {
			String c = this.attr("class");
			if (c != null) {
				String[] cList = c.split(" ");
				c = identifier.substring(1);

				for (int i = 0; i < cList.length; i++) {
					if (cList[i].equals(c))
						return this;
				}
			}
		}

		// Busca dentro do nó
		for (int i = 0; i < this.innerList.size(); i++) {
			Tag temp = this.innerList.get(i).select(identifier, true);
			if (temp != null)
				return temp;
		}

		return null;
	}

	public MultSelect selectAll(String identifier) {
		return new MultSelect(this.selectAll(identifier, true, null, 0), this);
	} 
	
	protected ArrayList<AbstractSelect> selectAll(String identifier, boolean pro, Object d, int i) {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();

		boolean name = identifier.charAt(0) != '.' && identifier.charAt(0) != '#';
		String attr = name ? "none"
				: (identifier.charAt(0) == '.' ? "class" : (identifier.charAt(0) == '#') ? "id" : "none");

		// Busca no nó
		if (name && this.name.equals(identifier)) {
			ret.add(new Select(this, d, i));
		} else if (attr.equals("id") && this.attr("id").equals(identifier.substring(1))) {
			ret.add(new Select(this, d, i));
		} else if (attr.equals("class")) {
			String c = this.attr("class");
			if (c != null) {
				String[] cList = c.split(" ");
				c = identifier.substring(1);

				for (int j = 0; j < cList.length; j++) {
					if (cList[j].equals(c))
						ret.add(new Select(this, d, i));
				}
			}
		}

		// Busca dentro do nó
		for (int j = 0; j < this.innerList.size(); j++) {
			ArrayList<AbstractSelect> temp = this.innerList.get(i).selectAll(identifier, true, d, i);
			if (!temp.isEmpty())
				for (int k = 0; j < temp.size(); k++) {
					ret.add((Select) temp.get(k));
				}
		}
		return ret;
	}

	// Attrs
	public Tag attr(String key, String value) {
		Row element = new Row(key, value);

		int index = attrList.indexOf(element);

		if (index >= 0) {
			attrList.set(index, element);
		} else {
			attrList.add(element);
		}
		return this;
	}

	public Tag attr(String key, int value) {
		return attr(key, "" + value);
	}

	public Tag attr(String key, float value) {
		return attr(key, "" + value);
	}

	public Tag attr(String key, double value) {
		return attr(key, "" + value);
	}

	public String attr(String key) {
		Row element = new Row(key, "");
		int index = attrList.indexOf(element);
		if (index >= 0) {
			return attrList.get(index).getValue();
		}
		return null;
	}

	// Appends
	public Tag append(Tag element) {
		this.innerList.add(element);
		return element;
	}

	public Tag append(String element) {
		if(element.equals("style"))
			return this.append(new StyleTag(element, this));
		return this.append(new Tag(element, this));
	}
	
	// Preppends
		public Tag preppend(Tag element) {
			this.innerList.add(0, element);
			return element;
		}

		public Tag preppend(String element) {
			if(element.equals("style"))
				return this.preppend(new StyleTag(element, this));
			return this.preppend(new Tag(element, this));
		}

	// Others
	@Override
	public String toString() {
		if (this.name == null)
			return "";

		String attr = "";
		String style = "";
		String inner = "";
		for (Object i : this.styleList.toArray()) {
			style += ((Row)i) + " ";
		}
		for (int i = 0; i < this.attrList.size(); i++) {
			attr += this.attrList.get(i) + " ";
		}
		if(!style.equals(""))
			attr += "\r\n style = \""+style+"\"";
		
		for (Object i : this.innerList.toArray()) {
			inner += ((Tag)i) + "\r\n";
		}
		
		return "<" + this.name + " " + attr + ">\r\n" + inner + "\r\n" + innerText + "\r\n" + "</" + this.name + ">";
	}

	public void remove() {
		this.attrList = null;
		for (Object i : this.innerList.toArray()) {
			((Tag) i).remove();
		}
		this.parent.innerList.remove(this);
	}
	
	public String innerText() {
		return getInnerText();
	}
	
	public Tag innerText(String inn) {
		setInnerText(inn);
		return this;
	}
	
	public Tag style(String key, String value) {
		try {
			int index = this.styleList.indexOf(new StyleAttr(key, value));
			this.styleList.get(index).setValue(value);
		} catch (Exception e) {
			this.styleList.add(new StyleAttr(key, value));
		}
		return this;
	}
	
	public Tag fileOut(String path) {
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

class Row {
	// --------------------Atributos----------------------------//
	private String key;
	private String value;

	// --------------------Construtores-------------------------//
	public Row(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	// --------------------GET & SET----------------------------//
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	// --------------------Metodos------------------------------//
	@Override
	public String toString() {

		return (value == null ? "" : key + (!value.isEmpty() ? "=\"" + value + "\"" : ""));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Row other = (Row) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
}
