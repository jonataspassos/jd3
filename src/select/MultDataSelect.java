package select;

import java.util.ArrayList;

public class MultDataSelect extends AbstractSelect{

	private ArrayList<DataSelect> multiDataList;
	private Tag parent;

	public MultDataSelect(ArrayList<DataSelect> multiDataList,Tag parent) {
		super();
		this.multiDataList = multiDataList;
	}

	@Override
	protected void setD(Object d) {
		for(Object i : multiDataList.toArray()) {
			((DataSelect)i).setD(d);
		}
		
	}

	@Override
	protected void setI(int i) {
		for(Object j : multiDataList.toArray()) {
			((DataSelect)j).setI(i);
		}
		
	}

	@Override
	protected void setDI(Object d, int i) {
		for(Object j : multiDataList.toArray()) {
			((DataSelect)j).setDI(d,i);
		}		
	}

	@Override
	public MultSelect select(String identifier) {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		for(Object j : multiDataList.toArray()) {
			ret.add(((DataSelect)j).select(identifier));
		}
		return new MultSelect(ret,this.thisParent());
	}

	@Override
	public MultSelect selectAll(String identifier) {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		for(Object j : multiDataList.toArray()) {
			ret.add(((DataSelect)j).selectAll(identifier));
		}
		return new MultSelect(ret,this.thisParent());
	}

	@Override
	public AbstractSelect select(F f) throws Exception {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		for(Object j : multiDataList.toArray()) {
			ret.add(((DataSelect)j).select(f));
		}
		return new MultSelect(ret,this.thisParent());
	}

	@Override
	public MultSelect selectAll(F f) throws Exception {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		for(Object j : multiDataList.toArray()) {
			ret.add(((DataSelect)j).selectAll(f));
		}
		return new MultSelect(ret,this.thisParent());
	}

	@Override
	public MultDataSelect attr(String key, F f) throws Exception {
		for(Object j : multiDataList.toArray()) {
			((DataSelect)j).attr(key, f);
		}
		return this;
	}

	@Override
	public AbstractSelect attr(String key, String value) {
		for(Object j : multiDataList.toArray()) {
			((DataSelect)j).attr(key, value);
		}
		return this;
	}

	@Override
	public AbstractSelect attr(String key, int value) {
		for(Object j : multiDataList.toArray()) {
			((DataSelect)j).attr(key, value);
		}
		return this;
	}

	@Override
	public AbstractSelect attr(String key, float value) {
		for(Object j : multiDataList.toArray()) {
			((DataSelect)j).attr(key, value);
		}
		return this;
	}

	@Override
	public AbstractSelect attr(String key, double value) {
		for(Object j : multiDataList.toArray()) {
			((DataSelect)j).attr(key, value);
		}
		return this;
	}

	@Override
	public String attr(String key) {
		return "";
	}
	
	@Override
	public AbstractSelect innerText(String text) {
		for(Object j : multiDataList.toArray()) {
			((DataSelect)j).innerText(text);
		}
		return this;
	}

	@Override
	public AbstractSelect innerText(F f) throws Exception {
		for(Object j : multiDataList.toArray()) {
			((DataSelect)j).innerText(f);
		}
		return this;
	}

	@Override
	public String innerText() {
		return "";
	}

	@Override
	protected MultSelect append(Tag element) {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		
		for(Object j : multiDataList.toArray()) {
			ret.add(((DataSelect)j).append(element));
		}
		return new MultSelect(ret, this.thisParent());
	}

	@Override
	public AbstractSelect append(String element) {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		
		for(Object j : multiDataList.toArray()) {
			ret.add(((DataSelect)j).append(element));
		}
		return new MultSelect(ret, this.thisParent());
	}

	@Override
	public AbstractSelect append(F f) throws Exception {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		
		for(Object j : multiDataList.toArray()) {
			ret.add(((DataSelect)j).append(f));
		}
		return new MultSelect(ret, this.thisParent());
	}
	
	@Override
	protected MultSelect preppend(Tag element) {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		
		for(Object j : multiDataList.toArray()) {
			ret.add(((DataSelect)j).preppend(element));
		}
		return new MultSelect(ret, this.thisParent());
	}

	@Override
	public AbstractSelect preppend(String element) {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		
		for(Object j : multiDataList.toArray()) {
			ret.add(((DataSelect)j).preppend(element));
		}
		return new MultSelect(ret, this.thisParent());
	}

	@Override
	public AbstractSelect preppend(F f) throws Exception {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		
		for(Object j : multiDataList.toArray()) {
			ret.add(((DataSelect)j).preppend(f));
		}
		return new MultSelect(ret, this.thisParent());
	}

	@Override
	public void remove() {
		for(Object j : multiDataList.toArray()) {
			((DataSelect)j).remove();
		}
		this.multiDataList = null;
	}

	@Override
	public boolean isEmpty() {
		if(this.multiDataList.isEmpty()) {
			return true;
		}else {
			for(Object j : multiDataList.toArray()) {
				if(!((DataSelect)j).isEmpty())
					return false;
			}
			return true;
		}
		
	}

	@Override
	protected Tag thisParent() {
		return this.parent;
	}
	
	public MultSelect enter() {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		
		for(Object j : multiDataList.toArray()) {
			ret.add(((DataSelect)j).enter());
		}
		return new MultSelect(ret, this.thisParent()) {
			@Override
			public MultSelect append(Tag element) {
				ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
				for(Object i : this.getSelection()) {
					ret.add(((AbstractSelect)i).append(element));
				}
				return new MultSelect(ret, this.getParent());
			}
			@Override
			public MultSelect append(String element) {
				ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
				for(Object i : this.getSelection()) {
					ret.add(((AbstractSelect)i).append(element));
				}
				return new MultSelect(ret, this.getParent());
			}
			@Override
			public MultSelect append(F f) throws Exception {
				ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
				for(Object i : this.getSelection()) {
					ret.add(((AbstractSelect)i).append(f));
				}
				return new MultSelect(ret, this.getParent());
			}
		};
	}
	
	public MultSelect exit() {
		ArrayList<AbstractSelect> ret = new ArrayList<AbstractSelect>();
		for(Object j : multiDataList.toArray()) {
			ret.add(((DataSelect)j).exit());
		}
		return new MultSelect(ret, this.thisParent());
	}

	
}
