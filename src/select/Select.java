package select;

public class Select extends AbstractSelect {
	// --------------------Atributos----------------------------//
	private Tag tag;
	private Object d;
	private int i;

	// --------------------Construtores-------------------------//
	public Select(Tag tag, Object d, int i) {
		super();
		this.tag = tag;
		this.d = d;
		this.i = i;
	}

	public Select(Tag tag) {
		super();
		this.tag = tag;
		this.d = null;
		this.i = 0;
	}

	// --------------------GET & SET----------------------------//
	protected Object getD() {
		return d;
	}

	protected void setD(Object d) {
		this.d = d;
	}

	protected int getI() {
		return i;
	}

	protected void setI(int i) {
		this.i = i;
	}

	protected Tag getTag() {
		return tag;
	}

	protected void setTag(Tag tag) {
		this.tag = tag;
	}

	// --------------------Metodos------------------------------//

	@Override
	protected void setDI(Object d, int i) {
		setD(d);
		setI(i);
	}

	// Selects
	@Override
	public Select select(String identifier) {
		return new Select(this.tag.select(identifier, true), d, i);
	}

	@Override
	public MultSelect selectAll(String identifier) {
		return new MultSelect(this.tag.selectAll(identifier, true, d, i), this.tag);
	}

	@Override
	public AbstractSelect select(F f) throws Exception {
		return this.select(f.f(d, i));
	}

	@Override
	public MultSelect selectAll(F f) throws Exception {
		return new MultSelect(this.tag.selectAll(f.f(d, i), true, d, i), this.tag);
	}

	// Attrs
	@Override
	public Select attr(String key, F f) throws Exception {
		if (tag != null)
			return new Select(this.tag.attr(key, f.f(d, i)), d, i);
		return this;
	}

	@Override
	public Select attr(String key, String value) {
		if (tag != null)
			return new Select(this.tag.attr(key, value), d, i);
		return this;
	}

	@Override
	public Select attr(String key, int value) {
		if (tag != null)
			return new Select(this.tag.attr(key, value), d, i);
		return this;
	}

	@Override
	public Select attr(String key, float value) {
		if (tag != null)
			return new Select(this.tag.attr(key, value), d, i);
		return this;
	}

	@Override
	public Select attr(String key, double value) {
		if (tag != null)
			return new Select(this.tag.attr(key, value), d, i);
		return this;
	}

	@Override
	public String attr(String key) {
		if (tag != null)
			return this.tag.attr(key);
		return null;
	}

	@Override
	public AbstractSelect innerText(String text) {
		this.tag.setInnerText(text);
		return this;
	}

	@Override
	public AbstractSelect innerText(F f) throws Exception {
		this.tag.setInnerText(f.f(d, i));
		return this;
	}

	@Override
	public String innerText() {
		return this.tag.getInnerText();
	}

	// Appends
	@Override
	public Select append(Tag element) {
		return new Select(this.tag.append(element), d, i);
	}

	@Override
	public Select append(String element) {
		return new Select(this.tag.append(element), d, i);
	}

	@Override
	public AbstractSelect append(F f) throws Exception {
		return new Select(this.tag.append(f.f(d, i)), d, i);
	}

	// Preppends
	@Override
	public Select preppend(Tag element) {
		return new Select(this.tag.preppend(element), d, i);
	}

	@Override
	public Select preppend(String element) {
		return new Select(this.tag.preppend(element), d, i);
	}

	@Override
	public AbstractSelect preppend(F f) throws Exception {
		return new Select(this.tag.preppend(f.f(d, i)), d, i);
	}

	// Others
	@Override
	public String toString() {
		return "" + this.tag;
	}

	@Override
	public void remove() {
		this.tag.remove();
		this.tag = null;
		this.d = null;
	}

	@Override
	public boolean isEmpty() {
		return tag == null;
	}

	@Override
	public Tag thisParent() {
		return this.tag.getParent();
	}

}
