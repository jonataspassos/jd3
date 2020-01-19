package select;

import java.util.ArrayList;

import function.JD3String;

public class StyleTag extends Tag {
	// --------------------Atributos----------------------------//
	private ArrayList<StyleAttr> attrList = new ArrayList<StyleAttr>();
	private ArrayList<StyleTag> innerList = new ArrayList<StyleTag>();

	// --------------------Construtores-------------------------//
	public StyleTag(String name, Tag tag) {
		super(name, tag);
	}

	// --------------------GET & SET----------------------------//

	// --------------------Metodos------------------------------//

	// Selects
	public Select select(String identifier) {
		return new Select(this.select(identifier, true));
	}

	protected StyleTag select(String identifier, boolean pro) {
		if (this.getParent() instanceof StyleTag)
			return null;
		return (StyleTag) super.select(identifier, pro);
	}

	protected ArrayList<AbstractSelect> selectAll(String identifier, boolean pro, Object d, int i) {
		if (this.getParent() instanceof StyleTag)
			return new ArrayList<AbstractSelect>();
		return super.selectAll(identifier, pro, d, i);
	}

	// Attrs
	public StyleTag attr(String key, String value) {
		if (key.equals("id") || key.equals("class") || !(this.getParent() instanceof StyleTag))
			return this;
		StyleAttr element = new StyleAttr(key, value);

		int index = attrList.indexOf(element);

		if (index >= 0) {
			attrList.set(index, element);
		} else {
			attrList.add(element);
		}
		return this;
	}

	public StyleTag attr(String key, int value) {
		return attr(key, "" + value);
	}

	public StyleTag attr(String key, float value) {
		return attr(key, "" + value);
	}

	public StyleTag attr(String key, double value) {
		return attr(key, "" + value);
	}

	public String attr(String key) {
		StyleAttr element = new StyleAttr(key, "");
		int index = attrList.indexOf(element);
		if (index >= 0) {
			return attrList.get(index).getValue();
		}
		return null;
	}

	// Appends
	public StyleTag append(StyleTag element) {
		if (this.getParent() instanceof StyleTag)
			return null;
		this.innerList.add(element);
		return element;
	}

	public StyleTag append(String element) {
		return this.append(new StyleTag(element, this));
	}

	// Others
	@Override
	public String toString() {
		if (this.getName() == null || this.getParent() instanceof StyleTag)
			return "";

		String style = "";

		for (Object i : this.innerList.toArray()) {
			style += ((StyleTag) i).getName() + " {";
			for (Object j : ((StyleTag) i).attrList.toArray()) {
				style += "\n\t" + ((StyleAttr) j).getKey() + ":" + ((StyleAttr) j).getValue() + ";";
			}
			style += style.charAt(style.length() - 1) != '{' ? "\n}" : "}";
		}

		return "<" + this.getName() + ">\r\n" + style + "\r\n" + "</" + this.getName() + ">";
	}

	public StyleTag fileOut(String path) {
		String content = "" + this;
		if (content.equals("")) {
			System.err.println("CSS vazio");

		} else {
			content = content.replaceFirst("<" + this.getName() + ">", "");
			content = content.replaceFirst("</" + this.getName() + ">", "");
			JD3String.fileOut(path, content);
		}
		return this;
	}
}

class StyleAttr extends Row {

	public StyleAttr(String key, String value) {
		super(key, value);
	}

	@Override
	public String toString() {
		return "" + this.getKey() + ":" + this.getValue() + ";";
	}

}
