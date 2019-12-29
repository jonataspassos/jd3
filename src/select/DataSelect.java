package select;

import java.util.ArrayList;

public class DataSelect extends MultSelect {
	// --------------------Atributos----------------------------//
	private ArrayList<Object> dataList;
	private ArrayList<AbstractSelect> exitSelection = new ArrayList<AbstractSelect>();

	// --------------------Construtores-------------------------//
	public DataSelect(ArrayList<AbstractSelect> selection, Tag parent, ArrayList<Object> dataList) {
		super(selection, parent);
		this.dataList = dataList;

		int size = this.size();
		if (dataList.size() < this.size()) {
			size = dataList.size();
			for (int i = dataList.size(); i < this.size();) {
				exitSelection.add(this.getSelection().remove(i));
			}
		}

		for (int i = 0; i < size; i++) {
			this.getSelection().get(i).setDI(dataList.get(i), i);
		}

	}

	// --------------------GET & SET----------------------------//

	protected ArrayList<Object> getDataList() {
		return dataList;
	}

	protected ArrayList<AbstractSelect> getExitSelection() {
		return exitSelection;
	}

	// --------------------Metodos------------------------------//

	// Attr
	@Override
	public DataSelect attr(String key, F f) throws Exception {
		return (DataSelect) super.attr(key, f);
	}

	@Override
	public DataSelect attr(String key, String value) {
		return (DataSelect) super.attr(key, value);
	}

	@Override
	public DataSelect attr(String key, int value) {
		return (DataSelect) super.attr(key, value);
	}

	@Override
	public DataSelect attr(String key, float value) {
		return (DataSelect) super.attr(key, value);
	}

	@Override
	public DataSelect attr(String key, double value) {
		return (DataSelect) super.attr(key, value);
	}

	@Override
	public DataSelect data(Object[] d) {
		ArrayList<Object> temp = new ArrayList<Object>();
		for (Object i : d) {
			temp.add(i);
		}
		return this.data(temp);
	}

	@Override
	public DataSelect data(ArrayList<Object> d) {
		for (Object i : exitSelection) {
			this.getSelection().add((AbstractSelect) i);
		}
		return new DataSelect(getSelection(), getParent(), d);
	}

	public Object data(int i) {
		if (i < this.dataList.size() && i >= 0)
			return this.dataList.get(i);
		else
			return null;
	}

	public Object[] data() {
		return getDataList().toArray();
	}

	public MultSelect enter() {
		ArrayList<AbstractSelect> listEnter = new ArrayList<AbstractSelect>();
		for (int i = this.size(); i < dataList.size(); i++) {
			listEnter.add(new Select(null, dataList.get(i), i));
		}
		return new MultSelect(listEnter, getParent()) {
			@Override
			public MultSelect append(Tag element) {
				for (int i = 0; i < this.size(); i++) {
					((Select) this.getSelection().get(i)).setTag(this.getParent().append(element));
				}
				return new MultSelect(this.getSelection(), this.getParent());
			}

			@Override
			public MultSelect append(String element) {
				for (int i = 0; i < this.size(); i++) {
					((Select) this.getSelection().get(i)).setTag(this.getParent().append(element));
				}
				return new MultSelect(this.getSelection(), this.getParent());
			}

			@Override
			public MultSelect append(F f) throws Exception {
				for (int i = 0; i < this.size(); i++) {
					Select temp = ((Select) this.getSelection().get(i));
					temp.setTag(this.getParent().append(f.f(temp.getD(), temp.getI())));
				}
				return new MultSelect(this.getSelection(), this.getParent());
			}
		};
	}

	public MultSelect exit() {
		return new MultSelect(exitSelection, getParent());
	}
}
