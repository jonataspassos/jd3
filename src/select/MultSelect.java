package select;

import java.util.ArrayList;

public class MultSelect extends AbstractSelect {
	// --------------------Atributos----------------------------//
	private ArrayList<AbstractSelect> selection;
	private Tag parent;

	// --------------------Construtores-------------------------//
	public MultSelect(ArrayList<AbstractSelect> selection, Tag parent) {
		super();
		this.selection = selection;
		this.parent = parent;
	}

	// --------------------GET & SET----------------------------//
	
	@Override
	protected void setD(Object d) {
		for(Object i : selection) {
			((AbstractSelect)i).setD(d);
		}
		
	}

	@Override
	protected void setI(int i) {
		for(Object o : selection) {
			((AbstractSelect)o).setI(i);
		}
		
	}

	@Override
	protected void setDI(Object d, int i) {
		for(Object o : selection) {
			((AbstractSelect)o).setD(d);
			((AbstractSelect)o).setI(i);
		}
		
	}
	
	protected ArrayList<AbstractSelect> getSelection() {
		return selection;
	}

	protected Tag getParent() {
		return parent;
	}
	
	// --------------------Metodos------------------------------//

	// Selects
	@Override
	public MultSelect select(String identifier) {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		for (Object i : this.selection.toArray()) {
			if (!((AbstractSelect) i).isEmpty())
				ret.add(((AbstractSelect) i).select(identifier));
			else
				ret.add(((AbstractSelect) i));
		}
		return new MultSelect(ret, parent);
	}

	@Override
	public MultSelect selectAll(String identifier) {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		for (Object i : this.selection.toArray()) {
			if (!((AbstractSelect) i).isEmpty())
				ret.add(((AbstractSelect) i).selectAll(identifier));
			else
				ret.add(((AbstractSelect) i));
		}
		return new MultSelect(ret, parent);
	}

	@Override
	public MultSelect select(F f) throws Exception {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		for (Object i : this.selection.toArray()) {
			if (!((AbstractSelect) i).isEmpty())
				ret.add(((AbstractSelect) i).select(f));
			else
				ret.add(((AbstractSelect) i));
		}
		return new MultSelect(ret, parent);
	}

	@Override
	public MultSelect selectAll(F f) throws Exception {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		for (Object i : this.selection.toArray()) {
			if (!((AbstractSelect) i).isEmpty())
				ret.add(((AbstractSelect) i).selectAll(f));
			else
				ret.add(((AbstractSelect) i));
		}
		return new MultSelect(ret, parent);
	}

	@Override
	public MultSelect attr(String key, F f) throws Exception {
		for (Object i : this.selection.toArray()) {
			if (!((AbstractSelect) i).isEmpty())
				((AbstractSelect) i).attr(key, f);

		}
		return this;
	}

	@Override
	public MultSelect attr(String key, String value) {
		for (Object i : this.selection.toArray()) {
			if (!((AbstractSelect) i).isEmpty())
				((AbstractSelect) i).attr(key, value);

		}
		return this;
	}

	@Override
	public MultSelect attr(String key, int value) {
		for (Object i : this.selection.toArray()) {
			if (!((AbstractSelect) i).isEmpty())
				((AbstractSelect) i).attr(key, value);

		}
		return this;
	}

	@Override
	public MultSelect attr(String key, float value) {
		for (Object i : this.selection.toArray()) {
			if (!((AbstractSelect) i).isEmpty())
				((AbstractSelect) i).attr(key, value);

		}
		return this;
	}

	@Override
	public MultSelect attr(String key, double value) {
		for (Object i : this.selection.toArray()) {
			if (!((AbstractSelect) i).isEmpty())
				((AbstractSelect) i).attr(key, value);

		}
		return this;
	}

	@Override
	public String attr(String key) {
		if (!isEmpty())
			return this.selection.get(0).attr(key);
		else
			return "";
	}
	
	@Override
	public AbstractSelect innerText(String text) {
		for (Object i : this.selection.toArray()) {
			if (!((AbstractSelect) i).isEmpty())
				((AbstractSelect) i).innerText(text);

		}
		return this;
	}
	
	@Override
	public AbstractSelect innerText(F f) throws Exception {
		for (Object i : this.selection.toArray()) {
			if (!((AbstractSelect) i).isEmpty())
				((AbstractSelect) i).innerText(f);

		}
		return this;
	}

	@Override
	public String innerText() {
		return "";
	}

	@Override
	public MultSelect append(Tag element) {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		for (Object i : this.selection.toArray()) {
			if (!((AbstractSelect) i).isEmpty())
				ret.add(((AbstractSelect) i).append(element));
			else
				ret.add(((AbstractSelect) i));
		}
		return new MultSelect(ret, parent);
	}

	@Override
	public MultSelect append(String element) {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		for (Object i : this.selection.toArray()) {
			if (!((AbstractSelect) i).isEmpty())
				ret.add(((AbstractSelect) i).append(element));
			else
				ret.add(((AbstractSelect) i));
		}
		return new MultSelect(ret, parent);
	}

	@Override
	public MultSelect append(F f) throws Exception {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		for (Object i : this.selection.toArray()) {
			if (!((AbstractSelect) i).isEmpty())
				ret.add(((AbstractSelect) i).append(f));
			else
				ret.add(((AbstractSelect) i));
		}
		return new MultSelect(ret, parent);
	}
	
	@Override
	public MultSelect preppend(Tag element) {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		for (Object i : this.selection.toArray()) {
			if (!((AbstractSelect) i).isEmpty())
				ret.add(((AbstractSelect) i).preppend(element));
			else
				ret.add(((AbstractSelect) i));
		}
		return new MultSelect(ret, parent);
	}

	@Override
	public MultSelect preppend(String element) {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		for (Object i : this.selection.toArray()) {
			if (!((AbstractSelect) i).isEmpty())
				ret.add(((AbstractSelect) i).preppend(element));
			else
				ret.add(((AbstractSelect) i));
		}
		return new MultSelect(ret, parent);
	}

	@Override
	public MultSelect preppend(F f) throws Exception {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		for (Object i : this.selection.toArray()) {
			if (!((AbstractSelect) i).isEmpty())
				ret.add(((AbstractSelect) i).preppend(f));
			else
				ret.add(((AbstractSelect) i));
		}
		return new MultSelect(ret, parent);
	}

	@Override
	public void remove() {
		for (Object i : this.selection.toArray()) {
			((AbstractSelect) i).remove();
		}
		this.selection = null;
		this.parent = null; 
	}

	@Override
	public boolean isEmpty() {
		if(this.selection.isEmpty())
			return true;
		for(Object i : this.selection.toArray()) {
			if(!((AbstractSelect)i).isEmpty())
				return false;
		}
		return true;
	}

	@Override
	public Tag thisParent() {
		return this.parent;
	}
	
	protected int size() {
		return this.selection.size();
	}
	
	public MultSelect resume() {
		if(this.selection.isEmpty()) {
			return this;
		}
		Object[] temp = this.selection.toArray();
		for(Object i : temp) {
			if(!((AbstractSelect)i).isEmpty())
				this.selection.remove(i);
		}
		return this;
	}
	
	public DataSelect data(Object [] d) {
		ArrayList<Object>temp = new ArrayList<Object>();
		for(Object i : d) {
			temp.add(i);
		}
		return new DataSelect(this.selection,parent,temp);
	}
	
	public DataSelect data(ArrayList<Object> d) {
		return new DataSelect(this.selection,parent,d);
	}
	public MultDataSelect data(F f) throws Exception {
		ArrayList<DataSelect> list = new ArrayList<DataSelect>();
		for(int i=0;i<this.size();i++) {
			Object [] dataList = f.d(i);
				ArrayList<Object> temp = new ArrayList<Object>();
				for(Object j: dataList) {
					temp.add(j);
				}
				list.add(new DataSelect(new ArrayList<AbstractSelect>(), this.selection.get(i).thisParent(), temp));
		}
		return new MultDataSelect(list,this.getParent());
	}
	@Override
	public String toString() {
		String selection = "";
		for(Object i : this.selection) {
			selection += i + "\n";
		}
		return  selection;
	}

	



	
	
}
