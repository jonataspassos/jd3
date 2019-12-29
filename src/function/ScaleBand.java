package function;

public class ScaleBand extends Scale {
	
	private Object[] domainValue = {};
	
	

	protected Object[] getDomainValue() {
		return domainValue;
	}



	protected void setDomainValue(Object[] domainValue) {
		this.domainValue = domainValue;
	}
	
	public ScaleBand domain (Object[] domainValue) {
		setDomainValue(domainValue);
		return this;
	}
	
	public ScaleBand range(float[] rangeValue) throws Exception {
		return (ScaleBand) super.range(rangeValue);
	}
	
	public ScaleBand range(float s, float e) throws Exception {
		return (ScaleBand) super.range(s,e);
	}
	
	private int search (Object value) {
		for(int i=0;i<domainValue.length;i++) {
			if(domainValue[i].equals(value)) {
				return i;
			}
		}
		return -1;
	}



	@Override
	public float apply(Object v){
		float i = (search(v)/(this.domainValue.length-1.0f));
		return getRangeValue()[0] + i*(getRangeValue()[1]-getRangeValue()[0]);
	}

}
